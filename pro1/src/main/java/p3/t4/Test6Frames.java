package p3.t4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test6Frames {
    public static void main(String[] args) {
        method1(10);
    }

    public static void method1(int x) {
        int y = x +1;
        Object o = method2();
        System.out.println(o);
    }

    public static Object method2() {
        Object o = new Object();
        return o;
    }
}
