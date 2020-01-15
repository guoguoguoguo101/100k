package DesignPatterns.ObserverPattern;

public class Test {
    public static void main(String[] args) {

        Idol idol = new ConcreteIdol();
        concreteFan c1 = new concreteFan("nana");
        concreteFan c2 = new concreteFan("lulu");
        concreteFan c3 = new concreteFan("lala");

        idol.addFan(c1);
        idol.addFan(c2);
        idol.addFan(c3);

        idol.notify("唱跳rap篮球");
    }
}
