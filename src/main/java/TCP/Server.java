package TCP;

import com.google.errorprone.annotations.Var;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        int i = 0;
        while (true){
            System.out.println(i);
            i++;
            Socket socket = serverSocket.accept();
            System.out.println("connected from "+socket.getRemoteSocketAddress());
            Thread t = new Handler(socket);
            t.start();


        }
    }
}
class Handler extends Thread{
    private  Socket socket;
    Handler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream inputStream = this.socket.getInputStream();
            OutputStream outputStream = this.socket.getOutputStream();
            handle(inputStream,outputStream);


        }catch (Exception e){

        }
    }
    private void handle(InputStream inputStream,OutputStream outputStream) throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        writer.write("hello\n");
        writer.flush();
        int i = 0;
        for (;;) {

            System.out.println("等待数据读取"+i);
            String s = reader.readLine();
            System.out.println("读取数据>>"+i+s);
            if (s.equals("bye")) {
                writer.write("bye\n");
                writer.flush();
                break;
            }
            writer.write("ok: " + s + "\n"+i);
            writer.flush();
            i++;
        }


    }
}
