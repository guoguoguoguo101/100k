package DesignPatterns.SingletonPattern;

public class SingleObject {
    private static SingleObject singleObject = new SingleObject();
    private SingleObject(){};
    public static SingleObject getinstance(){
        return singleObject;
    }
    public int old = 20;


}
