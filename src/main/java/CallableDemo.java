import javafx.concurrent.Task;

import java.util.concurrent.*;

/**
 * @description: Callable接口
 * @author: buqi
 * @create: 2020-04-02 11:31
 */
public class CallableDemo implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        CallableDemo callableDemo = new CallableDemo();
        Future<Integer> future = executor.submit(callableDemo);
        // 调用get方法会阻塞当前线程，直到得到结果
        // 所以实际编码中建议使用可以设置超时时间的重载get方法
        System.out.println(future.get());

    }
}
