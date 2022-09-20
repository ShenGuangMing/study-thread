package p6.t3;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class Test0 {
    public static void main(String[] args) {
        //---------------加减法--------------
        AtomicInteger i = new AtomicInteger(0);
        //获取自增(i = 0,结果i = 1,返回 0) 类似i++
        System.out.println(i.getAndIncrement());
        //获取自增(i = 1,结果i = 2,返回 2) 类似++i
        System.out.println(i.incrementAndGet());
        //获取自减(i = 2,结果i = 1,返回 1) 类似--i
        System.out.println(i.decrementAndGet());
        //获取自减(i = 1,结果i = 0,返回 1) 类似i--
        System.out.println(i.getAndDecrement());
        //获取并加值(i = 0,结果i = 5,返回 0)
        System.out.println(i.getAndAdd(5));
        //---------------乘法--------------
        //i = 5, 结果i =10, 返回 10
        System.out.println(i.updateAndGet(value -> value * 2));
        //i = 10, 结果i =20, 返回 10
        System.out.println(i.getAndUpdate(value -> value * 2));
        //模拟乘法运算
        while (true) {
            int prev = i.get();
            int next = prev * 2;
            if (i.compareAndSet(prev, next)) {
                break;
            }
        }
        System.out.println(i.get());
        //再改进一下
        upAndGet(i, x -> x/2);
        System.out.println(i.get());
    }
    public static void upAndGet(AtomicInteger i, IntUnaryOperator operator) {
        while (true) {
            int prev = i.get();
            int next = operator.applyAsInt(prev);
            if (i.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}
