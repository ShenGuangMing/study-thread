package util;

import java.util.concurrent.TimeUnit;

public class Sleeper {
    public static void sleepBySeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
