package p1.threadMethods;

public class test0 {
    public static void main(String[] args) {
        System.out.println("main method current thread is : " + Thread.currentThread().getName());
        MyThread0 myThread0 = new MyThread0();
        myThread0.setName("myThread0");
        myThread0.start();

        //因为MyThread0继承了Thread,Thread有实现了Runnable接口,所以可以使用带Runnable接口参数的构造器

        Thread thread = new Thread(myThread0);
        thread.setName("thread");
        //thread调用start()方法会调用myThread0的run方法所以this是myThread0
        thread.start();
    }
}
class MyThread0 extends Thread {
    public MyThread0() {
        System.out.println("执行构造方法的线程名: " + Thread.currentThread().getName() );
        //this
        System.out.println("构造方法this.getName() 的线程名是: " + this.getName() );
    }

    @Override
    public void run() {
        System.out.println("执行run方法的线程名: " + Thread.currentThread().getName());
        System.out.println("run方法this.getName() 的线程名是: " + this.getName() );
    }
}
