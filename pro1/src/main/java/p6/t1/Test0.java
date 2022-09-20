package p6.t1;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Test0 {
    public static void main(String[] args) {
        Account accountSafe = new AccountCas(10000);
        Account.demo(accountSafe);
        Account accountUnsafe = new AccountUnsafe(10000);
        Account.demo(accountUnsafe);
    }
}
class AccountCas implements Account {
    private AtomicInteger balance;
    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }
    @Override
    public Integer getBalance() {
        return this.balance.get();
    }
    @Override
    public void withdraw(Integer amount) {
//        while (true) {
//            //获取余额
//            int prev = balance.get();
//            //修改后的值
//            int next = prev - amount;
//            //真正修改如果成功
//            if (balance.compareAndSet(prev, next)) {
//                break;
//            }
//        }
        balance.getAndAdd(-1 * amount);

    }
}
class AccountUnsafe implements Account {
    private Integer balance;
    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }
    @Override
    public Integer getBalance() {
        synchronized (this) {
            return this.balance;
        }
    }
    @Override
    public void withdraw(Integer amount) {
        synchronized (this) {
            this.balance -= amount;
        }
    }
}
interface Account {
    //获取余额
    Integer getBalance();
    //取款
    void withdraw(Integer amount);
    /*
    方法内会启动1000个线程，每个线程做-10元的操作
    如果初始余额为10000那么正确的结果应该是0元
     */
    static void demo(Account account) {
        List<Thread> ts = new ArrayList<Thread>();
        for (int i = 0; i < 100; i++) {
            ts.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }
        ts.forEach(Thread::start);
        long start = System.nanoTime();
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance() + "cost: " + (end - start) + " ns");
    }
}
