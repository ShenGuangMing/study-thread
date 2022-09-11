package p4.t2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test1 {
    private static int num = 0;
    private static  Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    num++;
                    log.debug("t1 自增完");
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    num--;
                    log.debug("t2 自减完");
                    try {
                        log.debug("等待t1");
                        t1.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("num = {}", num);
    }
}
