package p3.t1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Slf4j
public class Test4 {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> task0 = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("running...");
                Thread.sleep(1000);
                return 100;
            }
        });
        Thread thread = new Thread(task0, "task0");
        thread.start();
        //主线程获取子线程的返回值，也就是阻塞
        log.debug("result {}", task0.get());
    }
}
