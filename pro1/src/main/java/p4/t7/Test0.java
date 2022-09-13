package p4.t7;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test0 {
    static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        synchronized (lock) {
            lock.wait();
        }
    }
}
