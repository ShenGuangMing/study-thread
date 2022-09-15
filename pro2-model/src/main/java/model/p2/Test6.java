package model.p2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Test6 {
    public static void main(String[] args) throws InterruptedException {
        ParkUnPark p = new ParkUnPark(10);
        Th a = new Th("a", p);
        Th b = new Th("b", p);
        Th c = new Th("c\n", p);
        a.setNeedUnPark(b);
        b.setNeedUnPark(c);
        c.setNeedUnPark(a);
        a.start();
        b.start();
        c.start();
        Thread.sleep(1000);
        LockSupport.unpark(a);
    }
}
class ParkUnPark{
    private final int loopNumber;

    public ParkUnPark(int loopNumber) {
        this.loopNumber = loopNumber;
    }
    public void print(String str, Thread needUnPark) {
        for (int i = 0; i < loopNumber; i++) {
            //锁住
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(needUnPark);
        }
    }
}
class Th extends Thread{
    private final ParkUnPark p;
    private final String str;
    private Thread needUnPark;
    public Th(String str, ParkUnPark p) {
        this.p = p;
        this.str = str;
    }

    public void setNeedUnPark(Thread needUnPark) {
        this.needUnPark = needUnPark;
    }

    @Override
    public void run() {
        p.print(str, needUnPark);
    }
}