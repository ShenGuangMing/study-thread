package p4.t3;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test1 {
    public static void main(String[] args) {
        Number number = new Number();
        new Thread(() -> {
            Sleeper.sleepBySeconds(1);
            log.debug("begin");
            number.a();
        }).start();
        new Thread(() -> {
            log.debug("begin");
            number.b();
        }).start();
        new Thread(() -> {
            log.debug("begin");
            number.c();
        }).start();
    }
}
