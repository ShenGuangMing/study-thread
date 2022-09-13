package entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Postman implements Runnable{
    private final int id;
    private  String email;

    public Postman(int id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public void run() {
        //拿到GuardedObject对象
        GuardedObject go = MailBox.getById(id);
        go.complete(email);
    }
}
