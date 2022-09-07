package createThread;

/**
 * 测试定义Thread类创建线程
 */
public class Test0 {
    public static void main(String[] args) {
        System.out.println("main start");
        //3.创建对象
        MyThread1 thread1 = new MyThread1();
        /*4.调用start方法,不要调用run方法,这样只是简单的调用,而不是创建一个线程并执行run方法,
        这个线程的执行有线程调度器来决定
        注意:
         - start()调用结束并不意味着子线程开始运行
         - 新开启的线程会执行run()方法
         - 如果我们开启了多个线程,start()的调用顺并不一定是线程启动的顺序
         - 多线程的运行结果与代码的执行顺序无关

         */
        thread1.start();
        System.out.println("main end");
    }
}
//1.定义一个类去继承Thread
class MyThread1 extends Thread{
    //2.重写Thread的run方法
    @Override
    public void run() {
        System.out.println("MyThread1 started");
    }
}
