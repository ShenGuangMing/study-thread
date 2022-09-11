package p3.t1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test1 {
    public static void main(String[] args) {
        //1.创建任务对像(匿名内部类创建Runnable实现)
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.debug("hello");
            }
        };
        //2.创建线程对象
        Thread thread = new Thread(runnable);
        //3.运行
        thread.start();
        log.debug("hello");
    }
}
