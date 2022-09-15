package p5.t2;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test1 {
    public static void main(String[] args) {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();
        tpt.start();
        tpt.start();
        tpt.start();
//        Sleeper.sleepBySeconds(3);
//        TwoPhaseTermination.stop();
    }
}
@Slf4j
class TwoPhaseTermination {
    volatile static boolean stop = false;
    private static Thread monitor;
    private static boolean starting = false;
    public void start() {
        synchronized (this) {
            if (starting) {
                log.info("已经运行了");
                return;
            }
            starting = true;
        }
        monitor = new Thread(() -> {
            while (true) {
                if (stop) {
                    log.debug("料理后事");
                    break;
                }
                //没有停止
                try {
                    log.debug("监控");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.debug("被打断");
                    e.printStackTrace();
                }
            }
        });
        monitor.start();
    }

    public static void stop() {
        stop = true;
        monitor.interrupt();
    }
}
