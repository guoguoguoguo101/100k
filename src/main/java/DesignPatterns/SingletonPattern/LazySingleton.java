package DesignPatterns.SingletonPattern;


    //线程不安全
public class LazySingleton {
    private LazySingleton(){};
    public static LazySingleton lazySingleton;

    public LazySingleton getinstance(){
        if (lazySingleton==null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
    //第一次使用才初始化 避免内存浪费
    //线程安全 但是需要加锁 影响效率
    public synchronized LazySingleton getinstancesafe(){
        if (lazySingleton==null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
