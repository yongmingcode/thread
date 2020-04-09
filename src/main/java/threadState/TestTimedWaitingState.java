package threadState;

/**
 * @description: 测试某个线程a的运行状态TIMED_WAITING
 * 代码中不加Thread.sleep(1000L)：
 *      可能会觉得线程a会先调用同步方法，而同步方法内调用了Thread.sleep()方法，那么控制台必然会输出TIMED_WAITING。
 *      其实不然，我们需要注意的是：一是在testTimedWaiting()方法内还有一个main线程，二是启动线程后执行run方法还是
 *      需要一定的时间，所以控制台应该是输出RUNNABLE（注意这里我用的是应该，这里只考虑应该的情况，因为如果run方法
 *      执行的特别快，那么在执行打印语句之前，a线程就执行了sleep,那么控制台将会打印逾期的TIMED_WAITING,不过就代码
 *      结构来看这里几乎不可能打印TIMED_WAITING）。
 * 代码中加Thread.sleep(1000L)：
 *      加上Thread.sleep(1000L)，就有意识的阻止了main线程执行的脚步。这里设置的是1000L毫秒，而线程a的sleep(2000L)
 *      方法使睡眠2000L毫秒，这样就极大的保证了控制台打印a线程状态时，a线程处于TIMED_WAITING状态。
 *
 * @author: buqi
 * @create: 2020-04-09 10:20
 */
public class TestTimedWaitingState {

    public static void testTimedWaiting() throws InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                test();
            }
        }, "a");

        a.start();
//        Thread.sleep(1000L);
        System.out.println(a.getName() + ":" + a.getState());
    }

    public static void test(){
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        testTimedWaiting();
    }

}
