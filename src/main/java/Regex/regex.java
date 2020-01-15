package Regex;

public class regex {

        public static void main(String[] args) {
            String s = "The     quick\t\t brown   fox  jumps   over the  lazy dog.";
            String r = s.replaceAll("\\s+", " ");
            System.out.println(r); // "The quick brown fox jumps over the lazy dog."       //把所有很多个空格  都替换成一个空格
        }

}
