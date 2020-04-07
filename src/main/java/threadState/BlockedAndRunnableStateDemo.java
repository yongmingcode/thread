package threadState;

/**
 * @description: BLOCKED与RUNNABLE状态的转换
 *
 * 运行main方法，控制台打印的结果是不确定的，注释掉代码中的Thread.sleep(1000L)，需要考虑：
 *      1.在该类中还有一个main线程
 *      2.启动线程后执行run方法还是需要消耗一定时间的（
 *          测试方法的main线程只保证了a，b两个线程调用start()方
 *          法（转化为RUNNABLE状态），还没等两个线程真正开始争夺锁，就已经打印此时两个线程的状态（RUNNABLE）了。
 *      ）
 *      就是说在这种情况下，在执行到a.start()时，如果run()方法被调用的快慢，都直接影响a、b两线程的状态。比如a.start()中
 *      的run()方法执行过快，a线程在main线程没有执行到b.start()时，就已经调用testMethod()，使得线程a休眠了2000ms（注
 *      意这里是没有释放锁的），此时main线程执行到b.start()，接着b线程执行的时候是争夺不到锁的。所以可能输出:
 *      a:TIMED_WAITING
 *      b:BLOCKED
 *
 *  不注释Thread.sleep(1000L)，这样基本保证了a.start()中的run()方法执行快于main线程调用b.start()，也就基本保证了a线程
 *  处于TIMED_WAITING状态，b的状态也是分情况的，就不细分析了。
 *
 *
 * @author: buqi
 * @create: 2020-04-07 18:18
 */
public class BlockedAndRunnableStateDemo {

    public static void blockedTest() throws InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");

        a.start();
//        Thread.sleep(1000L);
        b.start();

        System.out.println(a.getName() + ":" + a.getState());
        System.out.println(b.getName() + ":" + b.getState());

    }

    private synchronized static void testMethod(){
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        blockedTest();
    }
}
