package p4.t13;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test3 {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c5, c1).start();
    }
}
class Chopstick extends ReentrantLock{
    private String name;
    public Chopstick(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Chopstick{" +
                "name='" + name + '\'' +
                '}';
    }
}
//哲学家类
@Slf4j
class Philosopher extends Thread {
    private final Chopstick left;
    private final Chopstick right;
    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            //尝试获取左筷子
            if (left.tryLock()) {
                try {
                    log.debug("{} 拿到左筷子", Thread.currentThread().getName());
                    //尝试获取右筷子
                    if (right.tryLock()) {
                        try {
                            log.debug("{} 拿到右筷子", Thread.currentThread().getName());
                            eat();
                        }finally {
                            right.unlock();
                            log.debug("{} 释放右筷子", Thread.currentThread().getName());
                        }
                    }
                }finally {
                    left.unlock();
                    log.debug("{} 释放左筷子", Thread.currentThread().getName());
                }
            }
            Sleeper.sleepByMillisecond(1000);
        }
    }

    private void eat() {
        log.debug("eating...");
        Sleeper.sleepBySeconds(1);
    }
}
