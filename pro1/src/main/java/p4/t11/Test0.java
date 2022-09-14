package p4.t11;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test0 {
    public static void main(String[] args) {
        BigRoom bigRoom = new BigRoom();
        new Thread(bigRoom::sleep, "小女").start();

        new Thread(bigRoom::study, "小南").start();
    }

}
@Slf4j
class BigRoom {
    private final Object studyRoom = new Object();
    private final Object badRoom = new Object();

    public void study() {
        synchronized(studyRoom) {
            log.debug("{} 进入房间开始学习", Thread.currentThread().getName());
            Sleeper.sleepBySeconds(1);
            log.debug("{} 学习完", Thread.currentThread().getName());
        }
    }

    public void sleep() {
        synchronized(badRoom) {
            log.debug("{} 进入房间开始睡觉", Thread.currentThread().getName());
            Sleeper.sleepBySeconds(2);
            log.debug("{} 睡醒", Thread.currentThread().getName());
        }
    }
}
