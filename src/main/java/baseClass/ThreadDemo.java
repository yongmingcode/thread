package baseClass;

/**
 * @description: baseClass.ThreadDemo
 * @author: buqi
 * @create: 2020-04-01 17:30
 */
public class ThreadDemo {

    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread");
        }
    }
}
