package p6.t4;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class Test1 {
    static AtomicReference<String> ref = new AtomicReference<String>("A");
    public static void main(String[] args) {
        log.debug("main start");
        //获取值A
        //这个共享变量被修改过，他能察觉嘛
        String prev = ref.get();
        other();
        Sleeper.sleepBySeconds(1);
        log.debug("A -> C {}", ref.compareAndSet(prev, "C"));
    }
    public static void other() {
        new Thread(() -> {
            log.debug("A -> B {}", ref.compareAndSet(ref.get(), "B"));
        }).start();
        new Thread(() -> {
            log.debug("B -> A {}", ref.compareAndSet(ref.get(), "A"));
        }).start();
    }
}
