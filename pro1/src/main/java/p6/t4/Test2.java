package p6.t4;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
public class Test2 {
    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);
    public static void main(String[] args) {
        //获取值
        String prev = ref.getReference();
        //获取版本
        int stamp = ref.getStamp();
        log.debug("stamp {}", stamp);
        //其他操作
        other();
        Sleeper.sleepBySeconds(1);
        log.debug("A -> C {}", ref.compareAndSet(prev, "C", stamp, stamp+1));
    }
    public static void other() {
        new Thread(() -> {
            int stamp = ref.getStamp();
            log.debug("stamp {}", stamp);
            log.debug("A -> B {}", ref.compareAndSet(ref.getReference(), "B", stamp, stamp+1));
        }).start();
        new Thread(() -> {
            int stamp = ref.getStamp();
            log.debug("stamp {}", stamp);
            log.debug("B -> A {}", ref.compareAndSet(ref.getReference(), "A", stamp, stamp+1));
        }).start();
    }
}
