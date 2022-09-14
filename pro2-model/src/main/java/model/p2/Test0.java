package model.p2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test0 {
    private static final Object lock = new Object();
    private static boolean t2Run = false;
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("t1获取到锁");
                while (!t2Run) {
                    try {
                        log.debug("t1等待");
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.debug("1");
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized(lock) {
                log.debug("t2获取到锁");
                log.debug("2");
                t2Run = true;
                //叫醒其他线程
                lock.notifyAll();
            }
        }, "t1").start();
    }
}
