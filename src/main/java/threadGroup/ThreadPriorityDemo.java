package threadGroup;

/**
 * @description: 线程的优先级
 * java中线程优先级可以指定，范围是1~10。但是并不是所有的操作系统都支持10级优先级的划分
 * （比如有些操作系统只支持3级划分：低、中、高），java只是给操作系统一个线程优先级的参考值，
 * 线程最终在操作系统的优先级是多少，还是由操作系统的线程调度算法决定。
 *
 * java默认的优先级为5，线程的执行顺序由调度程序来决定，线程的优先级会在线程被调用之前设定。
 *
 * 优先级越高，线程被执行的几率更高。使用Thread类的setPriority()实例方法来设定线程的优先级。
 *
 * @author: buqi
 * @create: 2020-04-02 13:51
 */
public class ThreadPriorityDemo {

    /**
     * @param args
     * 执行结果：
     * 我是默认线程优先级：5
     * 我是设置过的线程优先级：10
     */
    public static void main(String[] args){
        Thread a = new Thread();
        System.out.println("我是默认线程优先级：" + a.getPriority());
        Thread b = new Thread();
        b.setPriority(10);
        System.out.println("我是设置过的线程优先级：" + b.getPriority());
    }
}
