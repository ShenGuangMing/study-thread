package model.p2;

public class Test4 {
    public static void main(String[] args) {
        WaitNotify wn = new WaitNotify(1, 10);
        new Thread(() -> {
            wn.print("a", 1, 2);
        }).start();
        new Thread(() -> {
            wn.print("b", 2, 3);

        }).start();
        new Thread(() -> {
            wn.print("c\n", 3, 1);

        }).start();
    }
}

class WaitNotify {
    private  int flag;
    private  int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }
    public void print(String str, int flag, int nextFlag){
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while (this.flag != flag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(str);
                this.flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}
