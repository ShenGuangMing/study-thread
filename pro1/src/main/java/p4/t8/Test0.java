package p4.t8;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test0 {
    static final Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("获得锁");
//                Sleeper.sleepBySeconds(2);
                try {
                    lock.wait(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        Sleeper.sleepBySeconds(1);
        synchronized (lock) {
            log.debug("获得锁");
        }
    }
}
