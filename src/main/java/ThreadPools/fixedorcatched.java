package ThreadPools;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class fixedorcatched {
    public static void main(String[] args) {

        //newFixedThreadPool（4）  固定数量的 线程池

        //newCachedThreadPool() 动态变化的线程池
       ExecutorService es = Executors.newFixedThreadPool(4);
        for (int i=0;i<6;i++){
            es.submit(new task(""+i));
        }
        es.shutdown();




    }
}



class task implements Runnable{
    public final  String name;

    task(String name) {
        this.name =name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!name.equals(""+3)){
            try {
                System.out.println("正在运行 这个慢一点的线程");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程池play"+"****"+name);
    }
}
