package p4.exercise.p4;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class Transfer {

    public static void main(String[] args) throws InterruptedException {
        Account a1 = new Account(1000);
        Account a2 = new Account(1000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a1.transfer(a2, randomAmount());
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a2.transfer(a1, randomAmount());
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("a1 count: " + a1.getCount());
        log.debug("a2 count: " + a2.getCount());
        log.debug("all count: " + (a2.getCount()+a1.getCount()));
    }
    static Random random = new Random();
    public static int randomAmount() {
        return random.nextInt(100)+1;
    }
}
class Account {
    private int count = 0;

    public Account(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void transfer(Account target, int num) {
        //这里有两个对象所以不能直接使用this，那样只能锁一个
        synchronized (Account.class) {
            if (this.count >= num) {
                this.setCount(this.count-num);
                target.setCount(target.getCount()+num);
            }
        }
    }
}
