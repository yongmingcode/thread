import java.util.concurrent.*;

/**
 * @description: FutureTask类是Future接口的一个实现类。
 * FutureTask是实现的RunnableFuture接口的，而RunnableFuture接口同时继承了Runnable接口和Future接口.
 *
 * 那FutureTask类有什么用？为什么要有一个FutureTask类？
 * Future只是一个接口，而它里面的cancel，get，isDone等方法要自己实现起来都是非常复杂的。
 * 所以JDK提供了一个FutureTask类来供我们使用。
 *
 * @author: buqi
 * @create: 2020-04-02 11:47
 */
public class FutureTaskDemo implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<>(new FutureTaskDemo());
        executor.submit(futureTask);
        System.out.println(futureTask.get());
    }
}
