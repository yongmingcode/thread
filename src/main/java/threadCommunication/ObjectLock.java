package threadCommunication;

/**
 * @description: 通过使用对象锁，实现线程间的同步。
 *  这种基于“锁”的方式，线程需要不断的尝试获得锁，如果失败了，再继续尝试。这可能会消耗服务器的资源。
 *
 * 下面程序的目的：等线程A先执行完之后，再由线程B去执行。
 *
 * 在下面的程序中，ThreadA和ThreadB内的同步代码块里，都是用synchronized关键字加上了同一个对象锁lock。
 *
 * 根据线程和锁的关系，同一时间只有一个线程持有一个锁，那么线程B就会等到线程A执行完成后释放lock,线程B才能获得lock。
 *
 *
 * @author: buqi
 * @create: 2020-04-08 18:34
 */
public class ObjectLock {

    private static Object lock = new Object();

    static class ThreadA implements Runnable{
        @Override
        public void run() {
            synchronized(lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("ThreadA:" + i);
                }
            }
        }
    }

    static class ThreadB implements Runnable{
        @Override
        public void run() {
            synchronized(lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("ThreadB:" + i);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        //这里在主线程里使用sleep方法使主线程睡眠10毫秒，是为了防止线程B先得到锁。因为如果同时start（几乎同时）,
        // 线程A和线程B都是出于就绪状态，操作系统可能会先让B运行。这样就会先输出B的内容，然后线程B执行完成之后自
        // 动释放锁，线程A再执行
        Thread.sleep(10);
        new Thread(new ThreadB()).start();
    }

}
