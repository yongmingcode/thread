package threadState;

/**
 * @description:
 * @author: buqi
 * @create: 2020-04-07 14:19
 */
public class TestNewState {

    private static void testStateNew(){
        Thread thread = new Thread(() -> {});
        System.out.println(thread.getState());
    }

    public static void main(String[] args){
        testStateNew();
    }
}
