package createThread;

/**
 * 定义Runnable接口实现类创建线程
 */
public class Test2 {
    public  void main(String[] args) {
        System.out.println("main start");
        //3.创建定义的对象
        MyRunnable1 runnable1 = new MyRunnable1();
        //4.创建一个Thread对象(将定义的对象作为参数)
        Thread thread = new Thread(runnable1);
        //5.调用start()方法
        thread.start();
        System.out.println("main end");
    }
}
//1.定义类实现接口Runnable
class MyRunnable1 implements Runnable {
    //2.实现run()方法
    @Override
    public void run() {
        System.out.println("MyRunnable1 run");
    }
}

