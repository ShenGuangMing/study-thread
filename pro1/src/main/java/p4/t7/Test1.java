package p4.t7;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test1 {
    final static Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("t1执行");
                try {
                    lock.wait();//让t1线程等待
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("t1的其他代码");
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (lock) {
                log.debug("t2执行");
                try {
                    lock.wait();//让t2线程等待
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("t2的其他代码");
            }
        }, "t2").start();
        //主线程
        Sleeper.sleepBySeconds(1);
        log.debug("唤醒lock其他线程");
        synchronized (lock) {
//            lock.notify();//唤醒一个
            lock.notifyAll();//唤醒所有
        }
    }
}
