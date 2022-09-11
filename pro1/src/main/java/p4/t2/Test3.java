package p4.t2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.increment();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.decrement();
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("num = {}", room.getNum());
    }
}

class Room {
    private int num;

    public void increment() {
        synchronized (this) {
            num++;
        }
    }
    public void decrement() {
        synchronized (this) {
            num--;
        }
    }
    public int getNum() {
        synchronized (this) {
            return num;
        }
    }
}
