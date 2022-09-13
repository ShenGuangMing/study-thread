package entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Person implements Runnable{
    private int timeout;

    public Person(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public void run() {
        GuardedObject go = MailBox.generateGuardedObject();
        Object o = go.get(timeout);
        log.debug("value: {}", o);
    }
}
