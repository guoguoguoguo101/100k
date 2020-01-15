package DesignPatterns.AbstractFactoyPattern;

public class ShapeFactory extends AbstractFactory {
    @Override
    public color getcolor(String color) {
        return null;
    }

    @Override
    public shape getshape(String shape) {
        if (shape == null){
            return null;
        }else if (shape.equals("circle")){
            return new Circle();
        }else if (shape.equals("rectangle")){
            return new Rectangle();
        }else if (shape.equals("squre")){
            return new Squre();
        }
        return null;
    }
}
