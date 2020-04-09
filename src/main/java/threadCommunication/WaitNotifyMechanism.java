package threadCommunication;

/**
 * @description: 等待/通知机制
 * 在下面的代码里，线程A和线程B首先打印出自己需要的东西，然后使用noticfy()方法唤醒
 * 另外一个线程，然后自己再调用wait()方法陷入等待并释放lock锁。
 *
 * 需要注意的是，等待/通知机制使用的是同一个对象锁，如果你两个线程使用的是不同的对
 * 象锁，那它们之间是不能用等待/通知机制通信的。
 *
 * @author: buqi
 * @create: 2020-04-09 11:44
 */
public class WaitNotifyMechanism {
    public static Object lock = new Object();

    static class threadA implements Runnable{

        @Override
        public void run() {
            synchronized(lock) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("threadA : " + i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class threadB implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                for(int i = 0;i < 5; i++){
                    System.out.println("threadB : " + i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new threadA()).start();
        Thread.sleep(1000);
        new Thread(new threadB()).start();
    }


}
