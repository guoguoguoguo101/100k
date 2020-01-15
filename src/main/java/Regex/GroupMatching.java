package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupMatching {

    public static void main(String[] args) {
        String bb = "0351-6227147";
        Pattern pattern = Pattern.compile("(\\d{3,4})\\-(\\d{7})");
        Pattern pattern1 = Pattern.compile("\\d{3,4}\\-\\d{7}");   //一定要加括号 不然切不出来
        Matcher m = pattern.matcher(bb);
        if (m.matches()){
            String g1 = m.group(0);
            String g2 = m.group(1);
            String g3 = m.group(2);


            System.out.println(g1);
            System.out.println(g2);
            System.out.println(g3);
        }
        System.out.println(m.matches());
    }
}
