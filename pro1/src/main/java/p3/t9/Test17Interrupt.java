package p3.t9;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test17Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    log.debug("被打断了");
                    break;
                }
            }
        }, "t1");
        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
    }
}
