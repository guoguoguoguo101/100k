package IO;

import javax.tools.JavaCompiler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ReaderChar {

    public void readfile() throws Exception {
        File file = new File("C:\\Users\\guo.hongzhi1\\Desktop\\io.txt");
        Reader reader = new FileReader(file);
        char[] buffer = new char[100];

       for (;;){
           int n = reader.read(); // 反复调用read()方法，直到返回-1
           System.out.println(n+"**"+(char)n);
           if (n == -1) {
               break;
           }

       }

    }

    public void readbufferfile() throws Exception {
        File file = new File("C:\\Users\\guo.hongzhi1\\Desktop\\io.txt");
        Reader reader = new FileReader(file);
        char[] buffer = new char[30];
        int n;
        while ((n=(reader.read(buffer)))!=-1){
            System.out.println(buffer);
            System.out.println("read " + n + " chars.");
        }


    }


    public static void main(String[] args) throws Exception {
        new ReaderChar().readfile();
        System.out.println("*******");
        new ReaderChar().readbufferfile();
    }
}
