package DesignPatterns.AbstractFactoyPattern;

public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        AbstractFactory shapefactory = FactoryProducer.getFactory("shape");
        shape shape1 = shapefactory.getshape("circle");
        shape1.draw();
    }
}
