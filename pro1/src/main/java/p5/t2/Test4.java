package p5.t2;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

@Slf4j
public class Test4 {
    static int x = 20;
    static boolean run = false;
    static boolean start ;
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (!run || start) {
                start = true;
            }

        }, "t1");
        t1.start();
        Sleeper.sleepBySeconds(1);
        if (start) {
            System.out.println(123);
        }
    }
}
