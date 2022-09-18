package p5.t2;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test2 {
    static int x = 0;
    static Object m = new Object();
    static boolean run;
    public static void main(String[] args) {
//        new Thread(() -> {
//            synchronized (m) {
//                    x = 10;
//            }
//        }, "t1").start();
//        new Thread(() -> {
//            synchronized (m) {
//                System.out.println(x);
//            }
//        }, "t2").start();
        run = true;
        new Thread(() -> {
            while (run) {

            }
            log.debug("停下来了");
        });
        Sleeper.sleepBySeconds(1);
        run = false;
    }
}
