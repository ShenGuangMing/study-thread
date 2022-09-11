package p4.t3;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test3 {
    public static void main(String[] args) {
        Num num = new Num();
        new Thread(() -> num.a()).start();
        new Thread(() -> num.b()).start();
    }
}
@Slf4j
class Num {
    public static synchronized void a() {
        Sleeper.sleepBySeconds(1);
        log.debug("1");
    }
    public static synchronized void b() {
        log.debug("2");
    }
}
