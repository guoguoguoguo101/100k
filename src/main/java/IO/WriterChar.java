package IO;

import java.io.*;

public class WriterChar {

    public void  writerchar() throws IOException {
        File file = new File("C:\\Users\\guo.hongzhi1\\Desktop\\io.txt");
        Writer writer = new FileWriter(file);
        writer.write('H'); // 写入单个字符
        writer.write("Hello".toCharArray()); // 写入char[]
        writer.write("Hellosss"); // 写入String
        System.out.println("写了没");
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        new WriterChar().writerchar();
    }
}
