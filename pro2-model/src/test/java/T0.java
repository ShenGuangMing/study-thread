import entity.MailBox;
import entity.Person;
import entity.Postman;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class T0 {
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (i = 0; i < 50; i++) {
            Person person = new Person(2000);
            new Thread(person, "t"+i).start();
        }
        Thread.sleep(1999);
        for (Integer id : MailBox.getIds()) {
            Postman postman = new Postman(id, "value" + id);
            new Thread(postman, "t"+(i++)).start();
        }

    }
}
