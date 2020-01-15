package DesignPatterns.SingletonPattern;

public class test {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("test");
        SingleObject singleObject = SingleObject.getinstance();

        while (true){
            Thread.sleep(1000);
            System.out.println(singleObject.old);
        }

    }
}
