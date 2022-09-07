package p3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test3 {
    public static void main(String[] args) {
        //创建任务对象(Lambda方式创建)
        Runnable runnable = ()-> log.debug("hello");
        //2.创建线程对象
        Thread thread = new Thread(runnable);
        //运行
        thread.start();
        log.debug("hello");
    }
}
