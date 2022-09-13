package entity;

import model.entity.Message;

public class Consumer implements Runnable{
    private MessageQueue messageQueue;
    private Message message;

    public Consumer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Message consume = messageQueue.consume();
        }

    }
}
