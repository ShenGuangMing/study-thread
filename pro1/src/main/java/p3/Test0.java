package p3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test0 {
    public static void main(String[] args) {
        //创建线程并重写run方法
        Thread thread = new Thread() {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        //给线程命名
        thread.setName("t1");
        //运行线程
        thread.start();
        log.debug("running");
    }
}
