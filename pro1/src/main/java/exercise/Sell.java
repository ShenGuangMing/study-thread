package exercise;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

@Slf4j
public class Sell {
    private static int all = 4000;
    public static void main(String[] args) throws InterruptedException {
        //模拟多人买票
        TicketWindow window = new TicketWindow(all);
        //统计卖出的票
        List<Integer> amountList = new Vector<>();
        //统计线程集合
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> {
                //随机生成票数
                int random = randomAmount();
                amountList.add(window.sell(random));
                //模拟等待
                try {
                    Thread.sleep(randomAmount()*2L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        //实际剩余
        log.debug("剩余: {}", (window.getCount()));
        //理论剩余
        log.debug("卖出: {}",  amountList.stream().mapToInt(i->i).sum());
        log.debug("卖出: {}",  (all-amountList.stream().mapToInt(i->i).sum()));
    }

    static Random random = new Random();
    public static int randomAmount() {
        return random.nextInt(5)+1;
    }
}

class TicketWindow {
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public synchronized int sell(int target) {
        if (this.count >= target) {
            this.count -= target;
            return target;
        }else {
            return 0;
        }
    }
    public  int getCount() {
        return this.count;
    }
}