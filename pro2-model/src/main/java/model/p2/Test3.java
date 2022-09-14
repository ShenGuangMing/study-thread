package model.p2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test3 {
    private static final Object lock = new Object();
    private static boolean runA = true;
    private static boolean runB = false;
    private static boolean runC = false;
    private static int printACount = 0;
    private static int printBCount = 0;
    private static int printCCount = 0;
    private static final int length = 100;
    public static void main(String[] args) {
        new Thread(()-> {
            synchronized(lock) {
                while (printACount < length) {
                    while (printACount - printBCount >= 1 || printACount - printCCount >=1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print(printACount+"\ta");
                    printACount++;

                    lock.notifyAll();
                }
            }
        }, "t1").start();
        new Thread(()-> {
            synchronized(lock) {
                while (printBCount < length) {
                    while (printACount - printBCount != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("b");
                    printBCount++;
                    lock.notifyAll();
                }
            }
        }, "t2").start();
        new Thread(()-> {
            while (printCCount < length) {
                synchronized(lock) {
                    while (printBCount - printCCount != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("c");
                    printCCount++;
                    lock.notifyAll();
                }
            }
        }, "t3").start();
    }
}
