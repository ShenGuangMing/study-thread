package p3.t8;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test15Join {
    static int r1 = 0;
    public static void main(String[] args) throws InterruptedException {
        test2();
    }
    public static void test2() throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    r1 = 10;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        t1.start();
        long start = System.currentTimeMillis();
        log.debug("join begin");
        t1.join(1500);//主线程只会等待t1线程1.5s，如果1.5s后t1线程没有结束，主线程将不会等待
        long end = System.currentTimeMillis();
        log.debug("r1: {}, cost: {}", r1,  (end-start));
    }
}
