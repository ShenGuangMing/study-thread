package p4.t9;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Test0 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("start");
            Sleeper.sleepBySeconds(2);
            log.debug("park");
            LockSupport.park();
            log.debug("resume...");
        }, "t1");
        t1.start();
        Sleeper.sleepBySeconds(1);
        log.debug("unPark...");
        LockSupport.unpark(t1);
    }
}
