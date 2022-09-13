package entity;

import lombok.extern.slf4j.Slf4j;
import model.entity.Message;

import java.util.LinkedList;

@Slf4j
public class MessageQueue {
    //最大容量
    private final int capacity;
    private final LinkedList<Message> list = new LinkedList<Message>();
    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }
    //生产
    public Message consume() {
        synchronized (list) {
            //只要消息队列空就wait
            while (list.isEmpty()) {
                log.debug("{} 等待", Thread.currentThread().getName());
                waitObject(list);
            }
            //从头消费消息
            Message message = list.removeFirst();
            log.debug("{} 消费消息: {}", Thread.currentThread().getName(), message);
            //通知生产线程
            list.notifyAll();
            return message;
        }
    }
    public void waitObject(Object object) {
        try {
            object.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public  void production(Message message){
        synchronized (list) {
            //消息队列满了，wait
            while (list.size() == capacity) {
                log.debug("消息队列满了 {} 等待", Thread.currentThread().getName());
                waitObject(list);
            }
            //从尾部生产
            list.addLast(message);
            log.debug("{} 生产消息: {}", Thread.currentThread().getName(), message);
            //通知可以消费消息
            list.notifyAll();
        }
    }
}
