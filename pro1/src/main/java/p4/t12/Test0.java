package p4.t12;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test0 {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c1, c5).start();
    }
}
class Chopstick {
    private String name;
    public Chopstick(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Chopstick{" +
                "name='" + name + '\'' +
                '}';
    }
}
//哲学家类
@Slf4j
class Philosopher extends Thread {
    private Chopstick left;
    private Chopstick right;
    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }
    @Override
    public void run() {
        while (true) {
            //尝试获取左边的筷子
            synchronized (left) {
                //尝试获取右边的筷子
                synchronized (right) {
                    eat();
                }
            }
        }
    }

    private void eat() {
        log.debug("eating...");
        Sleeper.sleepBySeconds(1);
    }
}
