package model.p1;

import lombok.extern.slf4j.Slf4j;
import model.entity.Message;
import model.entity.MessageQueue;

@Slf4j
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        //生产者
        MessageQueue messageQueue = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                messageQueue.put(new Message(id, "value "+ id));
            }, "生产者"+i).start();
        }
        //消费者
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Message take = messageQueue.take();

            }
        }, "消费者").start();
    }
}
