package DesignPatterns.AbstractFactoyPattern;

public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if (choice.equals("shape")){
            return new ShapeFactory();
        }else if (choice.equals("color")){
            return new ColorFactory();
        }
        return null;
    }
}
