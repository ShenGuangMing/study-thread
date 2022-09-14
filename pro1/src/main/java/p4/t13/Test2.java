package p4.t13;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test2 {
    private static final ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            //尝试获取锁
            try {
                log.debug("尝试获取锁，最多等待2s");
                //因为tryLock可以被打断所以需要再try catch块中
                if (!lock.tryLock(2, TimeUnit.SECONDS)) {
                    log.debug("超时没有获取到锁，返回");
                    return;
                }
            } catch (InterruptedException e) {
                log.debug("被打断，获取不到锁，返回");
                return;
            }
            try {
                log.debug("获取到锁");
            }finally {
                lock.unlock();
            }
        }, "t1");
        lock.lock();
        log.debug("主线程获取到锁");
        t1.start();
    }
}
