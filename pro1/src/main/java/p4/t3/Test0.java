package p4.t3;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test0 {
    public static void main(String[] args) {
        Number number = new Number();
        new Thread(() -> {
            log.debug("begin");
            number.a();
        }).start();
        new Thread(() -> {
            log.debug("begin");
            number.b();
        }).start();
    }
}
@Slf4j
class Number {
    public synchronized void a() {
        Sleeper.sleepBySeconds(1);
        log.debug("1");
    }
    public synchronized void b() {
        log.debug("2");
    }
    public void c() {
        log.debug("3");
    }
}
