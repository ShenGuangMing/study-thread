package p3.t7;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test9Sleep {
    public static void main(String[] args) {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        thread.start();
        log.debug("t1的状态是: {}", thread.getState());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.debug("t1的状态是: {}", thread.getState());
    }
}
