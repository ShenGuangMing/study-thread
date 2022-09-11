package p3.t9;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Test19Interrupt {
    public static void main(String[] args) throws InterruptedException {
        test();
    }

    private static void test() throws InterruptedException {
        Thread t1 = new Thread(()->{
            log.debug("park...");
            LockSupport.park();
            log.debug("unPark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());//使用Thread.currentThread().interrupted()结果不同
            LockSupport.park();
            log.debug("unPark...");
        }, "t1");
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();

    }
}
