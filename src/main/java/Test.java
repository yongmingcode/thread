/**
 * @description:
 * @author: buqi
 * @create: 2020-04-07 14:59
 */
public class Test {
    public Test(){
        System.out.println("构造函数");
    }

    {
        System.out.println("构造代码块");
    }

    static {
        System.out.println("静态代码块");
    }

    public static void main(String[] args){
        new Test();
    }

}
