/**
 * @description: 实现Runnable接口
 * @author: buqi
 * @create: 2020-04-01 17:40
 */
public class RunnableDemo {
    public static class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println("MyThread");
        }
    }

    public static void main(String[] args) {
//        new Thread(new MyThread()).start();
//        new MyThread().start();

        // Java 8 函数式编程，可以省略MyThread类
        new Thread(() -> {
            System.out.println("Java 8 匿名内部类");
        }).start();
    }
}
