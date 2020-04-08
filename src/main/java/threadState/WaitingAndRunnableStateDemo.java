package threadState;

/**
 * @description: WAITING状态与RUNNABLE状态的转换
 *
 * 有3个方法可以使线程从Runnable状态转化为Waiting状态，这里主要介绍Object.wait()和Thread.join()。
 * Object.wait()
 *      调用wait()方法之前，线程必须持有对象的锁；
 *      线程调用wait()方法后，会释放当前的锁，直到有其它线程调用notify()/notifyAll()方法唤醒等待锁的线程;
 *      需要注意的是，其他线程调用notify()方法只会唤醒单个等待的线程，如果有多个线程都在等待这个锁的话不一定会唤醒到之前调用wait()方法的线程；
 *      同样，调用notifyAll()方法唤醒所有等待锁的线程之后，也不一定马上把时间片分给刚才放弃锁的那个线程，具体要看系统的调度。
 * Thread.join()
 *      调用join()方法不会释放锁，会一直等待当前线程（即发起调用的线程）执行完毕（转换为Terminated状态）。
 *
 * @author: buqi
 * @create: 2020-04-08 11:20
 */
public class WaitingAndRunnableStateDemo {

    private synchronized static void testMethod(){
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitingTest() throws InterruptedException {

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
        a.join();
        // 上面调用join()方法，使得main线程会等待a线程执行完毕，才继续执行b.start()
        b.start();

        // 打印a:TERMINATED
        System.out.println(a.getName() + ":" + a.getState());
        // 打印b:RUNNABLE或者b:TIMED_WAITING
        System.out.println(b.getName() + ":" + b.getState());
    }

    public static void main(String[] args) throws InterruptedException {
        waitingTest();
    }

}
