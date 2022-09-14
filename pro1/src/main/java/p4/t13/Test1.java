package p4.t13;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test1 {
    private static final ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                log.debug("等待获取锁");
                lock.lockInterruptibly();
            }catch (Exception e) {
                //没有获取到锁结束等待
                log.debug("没有获取到锁结束等待");
                return;
            }
            try {
                log.debug("获取到锁");
            }finally {
                lock.unlock();
            }
        }, "t1");
        lock.lock();
        t1.start();
        Sleeper.sleepBySeconds(1);
        log.debug("打断t1");
        t1.interrupt();
    }
}
