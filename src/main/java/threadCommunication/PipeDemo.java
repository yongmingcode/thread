package threadCommunication;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @description: 管道是基于“管道流”的通信方式。
 * 使用管道多半与I/O流相关。当我们一个线程需要先另一个线程发送一个信息（比如字符串）或者文件等等时，就需要使用管道通信了。
 *
 * 简单分析一下这个示例代码的执行流程：
 *    1.线程ReaderThread开始执行，
 *    2.线程ReaderThread使用管道reader.read()进入”阻塞“，
 *    3.线程WriterThread开始执行，
 *    4.线程WriterThread用writer.write("test")往管道写入字符串，
 *    5.线程WriterThread使用writer.close()结束管道写入，并执行完毕，
 *    6.线程ReaderThread接受到管道输出的字符串并打印，
 *    7.线程ReaderThread执行完毕。
 *
 *
 * @author: buqi
 * @create: 2020-04-09 14:52
 */
public class PipeDemo {
    static class ReaderThread implements Runnable{
        private PipedReader reader;

        public ReaderThread(PipedReader reader){
            this.reader = reader;
        }

        @Override
        public void run() {
            System.out.println("this is reader");
            int receive = 0;
            try {
                // 从管道中读
                while((receive = reader.read()) != -1){
                    System.out.print((char)receive);
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class WriterThread implements Runnable{
        private PipedWriter writer;

        public WriterThread(PipedWriter writer){
            this.writer = writer;
        }

        @Override
        public void run() {
            System.out.println("this is writer");
            try {
                // 向管道中写
                writer.write("test");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        // 这里注意一定要连接，才能通信
        writer.connect(reader);

        Thread a = new Thread(new ReaderThread(reader));
        a.start();
        System.out.println(a.getName() + " a :" + a.getState());
        Thread.sleep(1000);
        System.out.println(a.getName() + " a :" + a.getState());
        new Thread(new WriterThread(writer)).start();
        Thread.sleep(1000);
        System.out.println(a.getName() + " a :" + a.getState());
    }

}
