package model.entity;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j
public class MessageQueue {
    //消息队列
    private final LinkedList<Message> list = new LinkedList<Message>();
    //消息容量
    private   int maxCount;

    public MessageQueue(int maxCount) {
        this.maxCount = maxCount;
    }
    //生产消息
    public void put(Message message) {
        synchronized (list) {
            while (list.size() == maxCount) {
                System.out.println("消息队列满了，等待消费");
               objWait(list);
            }
            //生产消息
            log.debug("add message {}", message);
            list.addLast(message);
            //唤醒消费
            list.notifyAll();
        }
    }

    //消费消息
    public Message take() {
       synchronized(list) {
           //队列是否为空
           while (list.isEmpty()) {
               log.debug("消息队列空，等待生产");
               objWait(list);
           }
           //每次消费从头取
           Message message = list.removeFirst();
           log.debug("消费消息 {}", message);
           //通知生产者
           list.notifyAll();
           return message;
       }
    }

    private void objWait(Object obj) {
        try {
            obj.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
