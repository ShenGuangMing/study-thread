package p5.t2;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test0 {
    //volatile 易变的
    static boolean run = true;
//    static final Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                System.out.println(run);
                    if (!run) {
                        break;
                }
                Sleeper.sleepByMillisecond(50);
            }
            log.debug("停下来了");
        }, "t1").start();
        Sleeper.sleepBySeconds(1);
        log.debug("给我停下来");
        run = false;
    }
}
