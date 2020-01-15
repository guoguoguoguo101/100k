package DesignPatterns.FactoryPatterns;

public class ShapeFactory {

    public shape getshape(String shapeType){
        if (shapeType == null){
            return null;
        }
        if (shapeType.equals("circle")){
            return new Circle();
        }else if (shapeType.equals("rectangle")){
            return new Rectangle();
        }else if (shapeType.equals("squre")){
            return new Squre();
        }
        return null;
    }
}
