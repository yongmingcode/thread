package threadGroup;

import java.util.stream.IntStream;

/**
 * @description: Java程序中对线程所设置的优先级只是给操作系统一个建议，
 * 操作系统不一定会采纳。而真正的调用顺序，是由操作系统的线程调度算法决定的。
 *
 * Java提供一个线程调度器来监视和控制处于RUNNABLE状态的线程。线程的调度策略采用抢占式，
 * 优先级高的线程比优先级低的线程会有更大的几率优先执行。在优先级相同的情况下，按照“先到先得”的原则。
 * 每个Java程序都有一个默认的主线程，就是通过JVM启动的第一个线程main线程。
 *
 * @author: buqi
 * @create: 2020-04-02 14:08
 */
public class ThreadPriorityTest {
    public static class T1 extends Thread{
        @Override
        public void run(){
            super.run();
            System.out.println(String.format("当前执行的线程是：%s，优先级：%d",
                    Thread.currentThread().getName(),
                    Thread.currentThread().getPriority()));
        }
    }

    /**
     * 真正的调用顺序，不是由线程优先级决定，而是由操作系统的线程调度算法决定的。
     * @param args
     */
    public static void main(String[] args){
        // IntStream 基本类型的Stream，内部使用的都是与基本类型相关的接口函数来进行操作， 进而避免装箱成相应的引用类型
        IntStream.range(1, 10).forEach(i -> {
            Thread thread = new Thread(new T1());
            thread.setPriority(i);
            thread.start();
        });
    }
}
