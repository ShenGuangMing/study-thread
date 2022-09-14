package p4.t12;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test1 {
    static volatile int count = 10;
    static final Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            //期望减到0就结束
            while (count > 0) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count--;
                log.debug("count: " + count);
            }
        }, "t1").start();
        new Thread(() -> {
            //期望加到20就结束
            while (count < 20) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count++;
                log.debug("count: " + count);
            }
        }, "t2").start();
    }
}
