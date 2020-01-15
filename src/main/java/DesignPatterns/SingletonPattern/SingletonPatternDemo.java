package DesignPatterns.SingletonPattern;

public class SingletonPatternDemo {

    public static void main(String[] args) throws InterruptedException {
        SingleObject singleObject = SingleObject.getinstance();
        singleObject.old = 100;
        System.out.println(singleObject.hashCode());
        System.out.println(singleObject.old);

       Thread.sleep(200000);
        System.out.println("end");
    }
}
