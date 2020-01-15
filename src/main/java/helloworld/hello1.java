package helloworld;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class hello1 {
    public static void main(String[] args) throws UnknownHostException {

        byte[] buf = new byte[1024];
        System.out.println(buf.length);


         for (int i=-120;i<127;i++){
             int t;
             t = (int) ((Math.random()*9+1)*100000);
             System.out.println(String.valueOf(t));
         }

    }
}
