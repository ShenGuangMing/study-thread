package entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuardedObject {
    private int id;
    private Object value;

    public GuardedObject() {
    }

    public GuardedObject(int id) {
        this.id = id;
    }

    public synchronized Object get(long timeout) {
        //开始等待时间
        long start = System.currentTimeMillis();
        long waitTime = timeout;
        while(value == null && waitTime > 0) {
            try {
                this.wait(waitTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            waitTime = waitTime - (System.currentTimeMillis() - start);
        }
        if (value == null) {
            log.debug("超时");
        }
        return value;
    }

    public synchronized void complete(Object value) {
        //存储信息
        this.value = value;
        //通知线程取信息
        this.notifyAll();
    }

    public int getId() {
        return id;
    }
}
