package createThread;

/**
 * 测试线程的执行是随机的
 */
public class Test1 {
    public static void main(String[] args) {
        MyThread2 thread2 = new MyThread2();
        thread2.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main thread " + i );
            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("\tsub thread " + i );
            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
