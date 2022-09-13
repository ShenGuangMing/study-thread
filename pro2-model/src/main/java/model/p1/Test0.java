package model.p1;

import lombok.extern.slf4j.Slf4j;
import model.entity.GuardedObject;
import util.Downloader;

import java.util.List;

/**
 * 线程1 等待 线程2 的下载结果
 */
@Slf4j
public class Test0 {
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            log.debug("开始等待");
            List<String> list = (List<String>) guardedObject.get(500);
            if (list != null) {
                log.debug("结果大小是: {}", list.size());
            }
        }, "t1").start();
        new Thread(() -> {
            log.debug("执行下载");
            try {
                //返回结果
                guardedObject.complete(Downloader.download());
//                guardedObject.complete(null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, "t2").start();

    }
}
