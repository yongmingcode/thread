package threadState;

/**
 * @description: TIMED_WAITING与RUNNABLE状态转换
 * TIMED_WAITING与WAITING状态类似，只是TIMED_WAITING等待的时间是指定的。
 *
 * Thread.sleep(long)
 *      使当前线程睡眠指定时间。需要注意这里的“睡眠”只是暂时使线程停止执行，并不会释放锁。时间到后，线程会重新进入RUNNABLE状态。
 * Object.wait(long)
 *      waite(long)方法使线程进入TIMED_WAITING状态。这里的wait(long)方法与无参方法wait()相同的地方是，都可
 *      以通过其他线程调用notify()/notifyAll()方法来唤醒。
 *      不同的地方是，有参方法wait(long)就算其他线程不来唤醒它，经过指定的时间long之后它会自动唤醒，拥有去争夺锁的资格。
 * Thread.join(long)
 *      join(long)使当前线程执行指定时间，并且使线程进入TIMED_WAITING状态。
 *
 * @author: buqi
 * @create: 2020-04-08 14:14
 */
public class TimedWaitingAndRunnableStateDemo {

    public static synchronized void testWaiting(){
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testTimedWaiting() throws InterruptedException {

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testWaiting();
            }
        }, "a");

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testWaiting();
            }
        }, "b");

        a.start();
        // 这里调用a.join(1000L)，因为是指定了具体a线程的执行时间，
        // 并且执行时间小于a线程sleep的时间，所以a线程状态输出TIMED_WAITING，
        // b线程状态仍然不固定（RUNNABLE或BLOCKED）
        a.join(1000L);
        b.start();

        System.out.println(a.getName() + ":" + a.getState());
        System.out.println(b.getName() + ":" + b.getState());
    }

    public static void main(String[] args) throws InterruptedException {
        testTimedWaiting();
    }

}
