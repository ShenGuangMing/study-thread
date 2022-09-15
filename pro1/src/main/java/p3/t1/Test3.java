package p3.t1;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

import java.util.concurrent.FutureTask;

@Slf4j
public class Test3 {
    public static void main(String[] args) {
        //1.创建任务对象，使用Lambda
        FutureTask<Integer> task0 = new FutureTask<>(() -> {
            log.debug("hello");
            Sleeper.sleepBySeconds(1);
            return 100;
        });
        //2.创建线程对象，参数1：任务对象；参数2：线程名称，推荐
        Thread thread = new Thread(task0, "task0");
        //3.启动线程
        thread.start();
        //4.获取任务对象的返回值
        Integer result = null;
        try {
            result = task0.get();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.debug("结果是：{}", result);
    }
}
