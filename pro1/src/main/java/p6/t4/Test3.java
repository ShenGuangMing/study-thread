package p6.t4;

import lombok.extern.slf4j.Slf4j;
import util.Sleeper;

import java.util.concurrent.atomic.AtomicMarkableReference;

@Slf4j
public class Test3 {
    public static void main(String[] args) {
        GarbageBag bag = new GarbageBag("装满了垃圾");
        //参数2mark可以看作是一个标记，表示垃圾袋满了
        AtomicMarkableReference<GarbageBag> ref = new AtomicMarkableReference<GarbageBag>(bag, true);
        log.debug("start");
        GarbageBag prev = ref.getReference();
        log.debug(prev.toString());

        new Thread(() -> {
            log.debug("start");
            bag.setDesc("空垃圾袋");
            ref.compareAndSet(bag, bag, true, false);
            log.debug(bag.toString());
        }, "保洁阿姨").start();

        Sleeper.sleepBySeconds(1);
        log.debug("想换一个垃圾袋");
        boolean success = ref.compareAndSet(prev, new GarbageBag("空垃圾袋"), true, false);
        log.debug("换好了吗 {}", success);
        log.debug(ref.getReference().toString());
    }
}
class GarbageBag {
    String desc;
    public GarbageBag(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }
    @Override
    public String toString() {
        return super.toString() + "{desc=" + desc + "}";
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
