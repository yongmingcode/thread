package Volatile;

/**
 * @description: volatile
 * volatile关键字在java中有特殊的内存语义：
 *      1.保证变量的内存可见性
 *      2.禁止volatile变量与普通变量重排序
 *
 * @author: buqi
 * @create: 2020-04-10 18:19
 */
public class VolatileExample {
    int a = 0;
    volatile boolean flag = false;

    public void writer(){
        // step 1
        a = 1;
        // step 2
        flag = true;
    }

    public void reader(){
        // step 3
        if(flag){
            System.out.println(a);
        }
    }

}
