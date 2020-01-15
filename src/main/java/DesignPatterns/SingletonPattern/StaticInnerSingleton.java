package DesignPatterns.SingletonPattern;

public class StaticInnerSingleton {
    private StaticInnerSingleton(){}

    private  final static StaticInnerSingleton getinstance(){
        return single.Instance;
    }

   private static class single{
        private static final StaticInnerSingleton Instance = new StaticInnerSingleton();
    }
}
