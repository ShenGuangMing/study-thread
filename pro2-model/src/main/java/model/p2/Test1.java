package model.p2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test1 {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition con = lock.newCondition();
    private static boolean t2Run = false;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            lock.lock();
            try {
                log.debug("t1获取到锁");
                while (!t2Run) {
                    con.await();
                }
            } catch (InterruptedException e) {
                log.debug("被打断");
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            log.debug("1");
        }, "t1").start();
        Thread.sleep(1000);
        new Thread(() -> {
            lock.lock();
            try {
                log.debug("t2获取到锁");
                log.debug("2");
                t2Run = true;
                con.signalAll();
            }finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
