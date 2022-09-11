package p4.t3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test2 {
    public static void main(String[] args) {
        Number number1 = new Number();
        Number number2 = new Number();
        new Thread(() -> {number1.a();}).start();
        new Thread(() -> {number2.b();}).start();
    }
}
