package p3.t6;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test8Run {
    public static void main(String[] args) {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                log.debug("当前线程为: {}", Thread.currentThread().getName());
            }
        };
        thread.run();
        log.debug("主线程结束");
    }
}
