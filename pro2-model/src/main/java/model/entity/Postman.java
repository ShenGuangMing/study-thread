package model.entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Postman extends Thread{
    private int id;
    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        //获取邮箱小格子
        GuardedObject go = Mailbox.getById(id);
        log.debug("{}邮递员，送信内容: {}", id, mail);
        go.complete(mail);
    }
}
