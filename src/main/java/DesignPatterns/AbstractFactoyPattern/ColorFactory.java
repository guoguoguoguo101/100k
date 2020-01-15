package DesignPatterns.AbstractFactoyPattern;

public class ColorFactory extends AbstractFactory {
    @Override
    public color getcolor(String color) {
        if (color ==null){
            return null;
        }else if (color.equals("blue")){
            return new Blue();
        }else if (color.equals("red")){
            return new Red();
        }else if (color.equals("yellow")){
            return new Yellow();
        }
        return null;
    }

    @Override
    public shape getshape(String shape) {
        return null;
    }
}
