package DesignPatterns.FactoryPatterns;

public class FactoryPatternsDemo {

    public static void main(String[] args) {
      ShapeFactory factory = new ShapeFactory();


        shape s1 =factory.getshape("circle");
        shape s2 =factory.getshape("squre");
        shape s3 =factory.getshape("rectangle");
         s1.draw();
         s2.draw();
         s3.draw();

    }

}
