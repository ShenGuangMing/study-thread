import entity.Consumer;
import entity.MessageQueue;
import entity.Producer;
import lombok.extern.slf4j.Slf4j;
import model.entity.Message;

@Slf4j
public class T1 {
    public static void main(String[] args) throws InterruptedException {
        MessageQueue mq = new MessageQueue(5);
        for (int i = 0; i < 2; i++) {
            Producer producer = new Producer(mq, new Message(i, "value" + i));
            new Thread(producer,"生产者"+i).start();
        }
        Consumer consumer = new Consumer(mq);
        new Thread(consumer, " 消费者").start();


    }
}
