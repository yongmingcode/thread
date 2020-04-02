package threadGroup;

/**
 * @description:如果某个线程优先级大于线程所在线程组的最大优先级，
 * 那么该线程的优先级将会失效，取而代之的是线程组的最大优先级。
 *
 * @author: buqi
 * @create: 2020-04-02 15:34
 */
public class ThreadGroupPriorityDemo {

    /**
     * @param args
     *
     * 输出结果：
     * 我是线程组的优先级6
     * 我是线程的优先级6
     *
     */
    public static void main(String[] args){
        ThreadGroup threadGroup = new ThreadGroup("t1");
        threadGroup.setMaxPriority(6);
        Thread thread = new Thread(threadGroup,"thread");
        thread.setPriority(9);
        System.out.println("我是线程组的优先级" + threadGroup.getMaxPriority());
        System.out.println("我是线程的优先级" + thread.getPriority());
    }
}
