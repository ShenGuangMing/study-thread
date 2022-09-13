package model.entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class People extends Thread{
    @Override
    public void run() {
        GuardedObject go = Mailbox.generateGuardedObject();
        log.debug("{}用户等待收信", go.getId());
        Object o = go.get(3000);
        if (o == null) {
            log.debug("{}用户没有收到", go.getId());
        }
        log.debug("{}用户收到信: {}", go.getId(), o);
    }
}
