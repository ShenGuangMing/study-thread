package p4.t11;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test1 {
    public static void main(String[] args) {
        Object A = new Object();
        Object B = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                log.debug("locked A");
                Sleeper.sleepBySeconds(2);
                synchronized (B) {
                    log.debug("locked B");
                    log.debug("其他操作");
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            synchronized (B) {
                log.debug("locked B");
                Sleeper.sleepBySeconds(1);
                synchronized (A) {
                    log.debug("locked A");
                    log.debug("其他操作");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
