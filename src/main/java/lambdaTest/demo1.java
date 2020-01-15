package lambdaTest;

import java.util.Arrays;
import java.util.Comparator;

public class demo1 {
    public static void main(String[] args) {

        String[] strings = {"ffff","bbb","cccc","dddd"};

      Arrays.sort(strings, new Comparator<String>() {
          @Override
          public int compare(String o1, String o2) {
              return o1.compareTo(o2);
          }
      });
      tostri(strings);


//
//

    }

    public static String tostri(String[] strings){
        StringBuffer buffer = new StringBuffer("");
        for (int i=0;i<strings.length;i++){

            buffer.append(strings[i]);
        }

        System.out.println(buffer.toString());
        return buffer.toString();
    }
}
