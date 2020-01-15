package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GreedyMatching {
    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("(\\d+)(0*)");
        Pattern pattern2 = Pattern.compile("(\\d+？)(0*)");   //使用非贪婪算法

        Matcher m = pattern.matcher("5060000");

        if (m.matches()){
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));        //默认使用贪婪算法  全算到前面去了  +？使用非贪婪算法
        }
    }
}
