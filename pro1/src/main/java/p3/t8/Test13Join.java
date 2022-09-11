package p3.t8;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test13Join {
    static int r = 0;
    public static void main(String[] args) throws InterruptedException {
        test1();
    }
    public static void test1() throws InterruptedException {
        log.debug("开始");
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("开始");
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("结束");
                r = 10;
            }
        };
        t1.start();
        t1.join();
        log.debug("结果为: {}", r);
        log.debug("结束");
    }
}
