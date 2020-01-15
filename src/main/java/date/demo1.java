package date;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class demo1 {

    public static void main(String[] args) {
        int i = 50001;
        System.out.println(i);
        System.out.println(Integer.toHexString(i));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(i));
        System.out.println(System.currentTimeMillis());


        Date date = new Date();
        System.out.println(date.getYear() + 1900); // 必须加上1900
        System.out.println(date.getMonth() + 1); // 0~11，必须加上1
        System.out.println(date.getDate()); // 1~31，不能加1
        // 转换为String:
        System.out.println(date.toString());
        // 转换为GMT时区:
        System.out.println(date.toGMTString());
        // 转换为本地时区:
        System.out.println(date.toLocaleString());
    }
    }

