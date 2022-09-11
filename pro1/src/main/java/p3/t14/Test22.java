package p3.t14;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test22 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("t1-洗水壶开始");
            Sleeper.sleepBySeconds(1);
            log.debug("t1-洗完开始烧水");
            Sleeper.sleepBySeconds(7);
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.debug("t2-洗茶杯，洗茶壶，拿茶叶开始");
            Sleeper.sleepBySeconds(4);
            log.debug("t2-洗完开始等水开");
            Sleeper.sleepBySeconds(5);
            try {
                t1.join();
                log.debug("t2-水烧完了，泡茶");
                Sleeper.sleepBySeconds(1);
                log.debug("t2-泡完茶结束");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
