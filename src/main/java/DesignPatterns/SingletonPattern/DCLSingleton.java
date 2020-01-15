package DesignPatterns.SingletonPattern;

public class DCLSingleton {
    private static volatile DCLSingleton dclSingleton;
    private DCLSingleton(){};
    public static DCLSingleton getInstance(){
        if (dclSingleton == null){
            synchronized (DCLSingleton.class){
                dclSingleton = new DCLSingleton();
            }
        }
        return dclSingleton;
    }
}
