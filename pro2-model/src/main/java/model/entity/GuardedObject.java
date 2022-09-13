package model.entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuardedObject {
    private int id;

    public GuardedObject() {
    }

    public GuardedObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    //结果
    private Object response;

    //获取结果
    public synchronized Object get() {
        //没有结果就一直等待
        while (response == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //有结果就返回
        return response;
    }

    /**
     * @param timeout 最大等待时间
     * @return 结果
     */
    public synchronized Object get(long timeout) {
        //开始时间
        long begin= System.currentTimeMillis();
        //经历的时间
        long passedTime = 0;
        while (response == null) {
            //应该等待时间
            long waitTime = timeout - passedTime;
            if (waitTime <= 0) {
                log.debug("超时");
                break;
            }
            try {
                /*参数不能是 timeout
                    如果是虚假唤醒，又会执行wait(timeout)，可能会超过timeout的时间
                 */
                this.wait(timeout-passedTime);
                passedTime = System.currentTimeMillis()-begin;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //有结果就返回
        return response;
    }

    //产生结果
    public synchronized void complete(Object response) {
        //赋值
        this.response = response;
        //唤醒其他线程
        this.notifyAll();
    }
}