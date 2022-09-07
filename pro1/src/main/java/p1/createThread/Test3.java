package p1.createThread;

public class Test3 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable run");
            }
        });
        thread.start();
    }
}
