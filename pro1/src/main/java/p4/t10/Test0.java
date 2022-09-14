package p4.t10;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test0 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(123);
        }, "t1");
        thread.start();
    }
}
