package reflection;

public class demo1 {

    public static void main(String[] args) {
        String aa = "66";

        Class c1 = aa.getClass();
        Class c2 = String.class;
        System.out.println(c1==c2);
    }

}
