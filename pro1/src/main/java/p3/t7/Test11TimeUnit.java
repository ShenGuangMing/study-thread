package p3.t7;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Test11TimeUnit {
    public static void main(String[] args) throws InterruptedException {
        log.debug("enter sleep");
        TimeUnit.SECONDS.sleep(1);
        log.debug("wake up");
    }
}
