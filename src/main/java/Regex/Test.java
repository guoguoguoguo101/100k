package Regex;

public class Test {
    public static void main(String[] args) {

        /*  \\d 匹配数字
             . 匹配任意字符
             \w 匹配常用字符
             \s 匹配空格
             \d 匹配数字
             \WSD 匹配 非wsd的类型

             A\d* 可以匹配 A0 A33 A \d* 后面是数字（0位或很多位）
             A\d+         A0 A33 不能匹配A 必须有一位数
             A\d?         A0 A ?可以匹配一个或0个类型的字符
             A\d{3}       A666 精确匹配3个
             A\d{3,5}     A666  A6667 A66677 匹配范围内
             A\d{n,}      至少匹配n个

             ^         开头
             $         结尾
             [ABC]      任意字符【】里
             [A-F0-9xy]    A-F 0-9 x y
             [^A-F]        非 A-F


         */
        String regex = "20\\d\\d";
        System.out.println("2020".matches(regex));
        System.out.println("1019".matches(regex));
    }
}
