package helloworld;

public class test {
    public static void main(String[] args) throws Exception {


        Object lock = new Object();
        Thread t1 = new Thread(new noise1(lock));
        Thread t2 = new Thread(new noise2(lock));
        t1.start();
        t2.start();

    }
}
