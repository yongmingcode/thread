package threadCommunication;

/**
 * @description: 基于volatile关键字的信号量通信
 * volatile关键字能够保证内存的可见性。即如果用volatile关键字声明一个变量，在一个线程里修改了这个变量的值，
 * 那其它线程立马可见更改后的值。
 *
 * 下面的程序，如果加了volatile关键字，控制台就能打印0、1、2、3、4，如果不加volatile关键之，口直太只能打印出0、1。
 * 原因就是使用了volatile关键字，只要在其中一个线程里修改single的值，另外一个线程就能监控到，并接着做修改。
 *
 * @author: buqi
 * @create: 2020-04-09 13:27
 */
public class VolatileSemaphoreDemo {
    private static volatile int single = 0;

    static class threadA implements Runnable{
        @Override
        public void run() {
            while(single < 5){
                if(single % 2 == 0){
                    System.out.println("threadA :" + single);
                    single ++;
                }
            }
        }
    }

    static class threadB implements Runnable{
        @Override
        public void run() {
            while(single < 5){
                if(single % 2 == 1){
                    System.out.println("threadB :" + single);
                    single = single + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new threadA()).start();
        Thread.sleep(1000L);
        new Thread(new threadB()).start();
    }

}
