package p3.t9;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Test18Interrupt {
    public static void main(String[] args) throws InterruptedException {
        TowPhaseTermination tpt = new TowPhaseTermination();
        tpt.start();
        TimeUnit.SECONDS.sleep(4);
        tpt.stop();
    }
}
@Slf4j
class TowPhaseTermination {
    private Thread monitor;

    //启动监控线程
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                //获取当前线程
                Thread current = Thread.currentThread();
                //判断是否被打断
                if (current.isInterrupted()) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);//情况1
                    log.debug("执行监控记录");//情况2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //重新设置打断标记
                    current.interrupt();
                }
            }
        });
        monitor.start();
    }
    //停止监控线程
    public void stop() {
        monitor.interrupt();
    }
}
