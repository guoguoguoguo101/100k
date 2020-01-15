package ThreadBase;

import java.util.concurrent.*;


//completableFuture 就看不懂了
public class ThreadFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<String> future1 = executor.submit(new Task("第1个"));

        Future<String> future2 = executor.submit(new Task("第2个"));
        Future<String> future3 = executor.submit(new Task("第3个"));
        Future<String> future4 = executor.submit(new Task("第4个"));
        Future<String> future5 = executor.submit(new Task("第5个"));
        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
        System.out.println(future4.get());
        System.out.println(future5.get());



        System.out.println("我好了");

//            String result = future.get();
//            System.out.println(result);








    }

}
class Task implements Callable{

    private String name;
    Task(String name){
        this.name = name;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return name+"我好了";
    }
}
