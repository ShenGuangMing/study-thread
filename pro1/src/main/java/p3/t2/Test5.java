package p3.t2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test5 {
    public static void main(String[] args) {
        //线程0
        new Thread(() -> {
            while (true) {
                log.debug("running");
            }
        }, "t0").start();

        //线程1
        new Thread(() -> {
            while (true) {
                log.debug("running");
            }
        }, "t1").start();
    }
}
