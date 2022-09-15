package model.p2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        AwaitSignal as = new AwaitSignal(10);
        Condition a = as.newCondition();
        Condition b = as.newCondition();
        Condition c = as.newCondition();
        new Thread(() -> {
            as.print("a", a, b);
        }, "t1").start();
        new Thread(() -> {
            as.print("b", b, c);
        }, "t2").start();
        new Thread(() -> {
            as.print("c\n", c, a);
        }, "t3").start();
        Thread.sleep(1000);
        //获取锁
        as.lock();
        try {
            a.signal();
        }finally {
            as.unlock();
        }
    }
}
@Slf4j
class AwaitSignal extends ReentrantLock {
    private final int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }
    public void print(String str, Condition current, Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                current.await();
                System.out.print(str);
                next.signal();
            } catch (InterruptedException e) {
                log.debug("被打断");
            } finally {
                unlock();
            }
        }
    }

}