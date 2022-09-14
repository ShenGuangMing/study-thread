package p4.t13;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test4 {
    private static final ReentrantLock room = new ReentrantLock();
    private static final Condition waitCigaretteSet = room.newCondition();
    private static final Condition waitTakeoutSet = room.newCondition();
    private static boolean hasCigarette =false;
    private static boolean hasTakeout =false;


    public static void main(String[] args) {
        new Thread(() -> {
            //获取锁
            room.lock();
            try {
                while (!hasCigarette) {
                    log.debug("没有外卖，休息一会");
                    //取等烟休息室等待
                    waitCigaretteSet.await();
                }
                log.debug("开始干活");
            } catch (InterruptedException e) {
                log.debug("被打断");
                e.printStackTrace();
            } finally {
                room.unlock();
            }
        }, "小南").start();
        new Thread(() -> {
            room.lock();
            try {
                while (!hasTakeout) {
                    log.debug("没有外卖，休息一会");
                    //去等外卖休息室等待
                    waitTakeoutSet.await();
                }
                log.debug("开始干活");
            } catch (InterruptedException e) {
                log.debug("被打断");
                e.printStackTrace();
            } finally {
                room.unlock();
            }
        }, "小女").start();


        Sleeper.sleepBySeconds(1);
        new Thread(() -> {
            room.lock();
            try {
                log.debug("烟送到");
                hasCigarette = true;
                waitCigaretteSet.signalAll();
            }finally {
                room.unlock();
            }
        }, "送烟的").start();
        Sleeper.sleepBySeconds(1);
        new Thread(() -> {
            room.lock();
            try {
                log.debug("外卖送到");
                hasTakeout = true;
                waitTakeoutSet.signalAll();
            }finally {
                room.unlock();
            }
        }, "送外卖的").start();

    }
}
