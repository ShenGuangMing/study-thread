package p4.t4;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

public class Test0 {
    private static final int THREAD_NUMBER = 2;
    private static final int LOOP_NUMBER = 200;

    public static void main(String[] args) {
//        ThreadUnsafe test = new ThreadUnsafe();
//        ThreadSafe test = new ThreadSafe();
        ThreadSafeSubClass test = new ThreadSafeSubClass();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                test.method1(LOOP_NUMBER);
            }, "Thread"+(i+1)).start();
        }
    }

}
class ThreadUnsafe {
    ArrayList<String> list = new ArrayList<String>();
    public void method1(int loopNumber) {
        for (int i = 0; i < loopNumber; i++) {
            method2();
            method3();
        }
    }
    private void method2() {
        list.add("1");
    }
    private void method3() {
        list.remove(0);
    }
}

class ThreadSafe {
    public void method1(int loopNumber) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < loopNumber; i++) {
            method2(list);
            method3(list);
        }
    }
    public void method3(ArrayList<String> list) {
        list.remove(0);
    }
    public void method2(ArrayList<String> list) {
        list.add("1");
    }
}
@Slf4j
class ThreadSafeSubClass extends ThreadSafe{
    @Override
    public void method3(ArrayList<String> list) {
        new Thread(() -> {
            list.remove(0);
        }).start();
    }
}