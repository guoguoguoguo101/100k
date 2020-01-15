package helloworld;

import com.sun.crypto.provider.HmacSHA1;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class helllo {
//    public static String hmacSha1Encrypt(String encryptText, String encryptKey) throws Exception {
//        byte[] text = encryptText.getBytes(CHARSET_UTF8);
//        byte[] keyData = encryptKey.getBytes(CHARSET_UTF8);
//
//        SecretKeySpec secretKey = new SecretKeySpec(keyData, KEY_MAC_SHA1);
//        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
//        mac.init(secretKey);
//        return byte2hex(mac.doFinal(text));
//    }

    public static void main(String[] args) throws Exception {

        Random random = new Random();
        while (true){
           int x = (int) (100*Math.random());
            System.out.println(x);
            Thread.sleep(500);
        }

    }
}
