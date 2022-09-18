package p5.t2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test3 {
}
//问题1：为什么要加final
//问题2：如果实现了序列化接口，还要怎么做才能防止反序列化破坏单例
final class  Singleton {
    //问题3：为什么设为私有？是否能防止反射创建新的实例
    private Singleton() {
    }
    //问题4：这样初始化是否能保证单例对象创建的线程安全
    private static final Singleton INSTANCE = new Singleton();
    //问题5：为什么提供静态方法而不是直接将INSTANCE设置为public，说出理由
    public static Singleton getInstance() {
        return INSTANCE;
    }

    //问题2解决方法
    public Object readResolve() {
        return INSTANCE;
    }
}
