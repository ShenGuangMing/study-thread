package p3.t7;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test10Interrupt {
    public static void main(String[] args) {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                try {
                    log.debug("enter sleep");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.error("wake up");
                    throw new RuntimeException(e);
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.debug("interrupt");
        thread.interrupt();
    }
}
