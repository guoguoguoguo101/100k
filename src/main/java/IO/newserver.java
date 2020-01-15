package IO;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class newserver {
    public static void main(String[] args) throws IOException {


        ExecutorService threadPool = Executors.newCachedThreadPool();
        int i = 1;
        ServerSocket socket = new ServerSocket(7777);
        while (true){


            int finalI = i;
           Socket s0 =  socket.accept();
            System.out.println("第"+finalI+"个客户端 已经连接");

            threadPool.execute(new Runnable() {
               @Override
               public void run () {

                   try {
                       InputStream inputStream = s0.getInputStream();

                       while (true){

                           byte[] buffer = new byte[1024];
                           int read =  inputStream.read(buffer);
                           String b = new String(buffer,"GBK");
                           System.out.println(b+"--来自第"+finalI+"个客户端的数字尾巴"+b);
                           if (read == -1){
                               break;
                           }

                       }
                   }catch (Exception e){

                   }

               }
           }) ;
            i++;
        }
    }


}
