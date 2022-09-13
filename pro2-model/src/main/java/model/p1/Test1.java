package model.p1;

import lombok.extern.slf4j.Slf4j;
import model.entity.Mailbox;
import model.entity.People;
import model.entity.Postman;

@Slf4j
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }
        Thread.sleep(1000);
        System.out.println(Mailbox.getIds().size());
        for (Integer id : Mailbox.getIds()) {
            new Postman(id, "mail "+ id).start();
        }
    }
}
