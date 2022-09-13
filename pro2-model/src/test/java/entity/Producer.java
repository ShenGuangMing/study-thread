package entity;

import model.entity.Message;

public class Producer implements Runnable{
    private MessageQueue messageQueue;
    private Message message;

    public Producer(MessageQueue messageQueue, Message message) {
        this.messageQueue = messageQueue;
        this.message = message;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            messageQueue.production(message);
        }
    }
}
