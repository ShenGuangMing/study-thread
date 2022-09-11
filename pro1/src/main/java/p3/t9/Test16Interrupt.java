package p3.t9;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class Test16Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread( () -> {
            log.debug("sleep...");
            try {
                //sleep、wait、join被打断后会将 打断标记 清空
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t1");
        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt...");
        t1.interrupt();
        log.debug("打断状态: {}", t1.isInterrupted());
    }
}
