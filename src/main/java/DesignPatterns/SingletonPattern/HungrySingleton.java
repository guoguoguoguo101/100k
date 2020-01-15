package DesignPatterns.SingletonPattern;


//类加载时 就已经初始化 浪费内存
public class HungrySingleton {
    private static HungrySingleton singleton = new HungrySingleton();
    private HungrySingleton(){};
    public HungrySingleton getinstance(){
        return singleton;
    }

}
