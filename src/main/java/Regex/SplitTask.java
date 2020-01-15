package Regex;

public class SplitTask {
    public static void main(String[] args) {
        String aa = "a b c ";
        String bb = "a, b ,c";
        String[] cc =  aa.split("\\s");
        String[] dd = bb.split("[\\,\\;\\s]+");
        for (String a:dd){
            System.out.println(a);
        }

    }
}
