package p4.t13;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test0 {
    private static final ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();
        try {
            m1();
        }finally {
            lock.unlock();
        }
    }
    public static void m1() {
        lock.lock();
        try {
            log.debug("{} m1", Thread.currentThread().getName());
            m2();
        }finally {
            lock.unlock();
        }
    }
    public static void m2() {
        lock.lock();
        try {
            log.debug("{} m2", Thread.currentThread().getName());
        }finally {
            lock.unlock();
        }
    }
}
