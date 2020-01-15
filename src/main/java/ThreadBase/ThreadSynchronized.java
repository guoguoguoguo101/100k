package ThreadBase;

public class ThreadSynchronized {
    public static final Object lock = new Object();

    public static int n=0;
    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(new add());
        Thread t2 = new Thread(new dec());
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(ThreadSynchronized.n);
    }
}
class add extends Thread{
    public void run(){
        for (int i=0;i<10000;i++){
            synchronized (ThreadSynchronized.lock){
                ThreadSynchronized.n++;
            }


        }
    }
}
class dec extends Thread{
    public void run(){
        for (int i=0;i<10000;i++){
            synchronized (ThreadSynchronized.lock){
                ThreadSynchronized.n--;
            }

        }
    }
}