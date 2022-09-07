# 1.线程的概述
## 1.1线程的相关概念
- 进程(process)
> 1. 进程是计算机中的程序关于某个数据集合的一次运行活动,是操作系统进行资源分配与调度的基本单位.
> 2. 可以把进程简单的理解为正在操作系统上运行的一个程序
- 线程(thread)
> 1. 线程是进程的一个执行单元
> 2. 一个线程就是进程中一个单一顺序的控制流,进程的一个执行分支
> 3. 进程就是线程的容器,一个进程至少有一个线程,一个进程也可以有多个线程
> 4. 操作系统中,是按照进程为基本单位分配资源,每一个线程都有各自的线程栈,寄存器环境,线程的本地存储
- 主线程和子线程
> 1. JVM启动时会创建一个主线程,该线程负责执行main方法
> 2. java中的线程不是孤立存在的,线程之间存在一些联系.如果A线程创建的B线程,就称B线程是A线程的子线程,相应的A是B的父线程
- 串行并行和并发
> 有三个任务:
> - 任务A准备5分钟,等待10分钟
> - 任务B准备2分钟,等待8分钟
> - 任务C准备10分钟
>
> 时间:
> - 串行(Sequential): 所有任务逐个执行; 耗时: 15+10+10=35分钟
> - 并发(Concurrent): 等待时间可以开始下一个任务; 耗时:  5+2+10=17(C任务10分钟大于B任务8分钟,且B等待2分钟+C任务10分钟大于A任务等待10分钟)
> - 并行(Parallel): 3个任务同时开始,耗时取决于任务耗时最长的任务; 耗时: 15分钟
>
> 并发可以提高事务的处理效率,即一段时间可以处理完成更多的事务
> 并行是一种理想的并发,从硬件角度,单核CPU,操作系统使用时间片轮转技术,让用户感觉是并行执行的
>
## 1.2线程的创建和启动
> 在Java中,创建一个线程就是创建一个Thread(子类)的对象(实例)
### 1.2.1创建
> Thread类有两个常用的构造方法: Thread() 与 Thread(Runnable)
- 定义Thread类的子类
```java
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
```
- 定义一个Runnable接口的实现类
```java
/**
 * 定义Runnable接口实现类创建线程
 */
public class Test2 {
    public static void main(String[] args) {
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
```
```java
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
```
### 1.2.2验证
- 验证随机性
```java

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
```

# 1.3Thread的常用方法
## 1.3.1currentThread()方法
> Thread.currentThread()方法可以获取当前线程
> Java中的任何一段代码都是执行在某个线程当中的,执行当前代码的线程就是当前线程
>
> 同一段代码可能被不同的线程执行,因此当前线程是相对的



