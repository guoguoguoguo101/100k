package DesignPatterns.ObserverPattern;

public class concreteFan implements Fan {
    public String name;
    public concreteFan(String name){
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println(name+"知道了"+message);
    }
}
