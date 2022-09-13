package p4.t8;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test1 {
    static final Object lock = new Object();
    static boolean hasCigarette = false;//有没有烟
    static boolean hasTakeout = false;
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("获得锁");
                log.debug("有烟没?[{}]", hasCigarette);
                while (!hasCigarette) {
                    log.debug("没烟先休息一会");
//                    Sleeper.sleepBySeconds(2);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.debug("有烟没?[{}]", hasCigarette);
                if (hasCigarette) {
                    log.debug("可以开始干活");
                }else {
                    log.debug("不干活");
                }
            }
        }, "小南").start();
        new Thread(() -> {
            synchronized (lock) {
                log.debug("获得锁");
                log.debug("有披萨没?[{}]", hasCigarette);
                while (!hasTakeout) {
                    log.debug("没披萨先休息一会");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.debug("有披萨没?[{}]", hasCigarette);
                if (hasTakeout) {
                    log.debug("可以开始干活");
                }else {
                    log.debug("不干活");
                }
            }
        }, "小女").start();
        Sleeper.sleepBySeconds(1);
        new Thread(() -> {
            synchronized(lock) {
                hasTakeout = true;
                log.debug("披萨送到");
//                lock.notify();//能准确的叫醒小女吗
                lock.notifyAll();
            }
        }, "送披萨的").start();
    }
}
