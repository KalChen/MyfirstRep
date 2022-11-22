package anywhere.util;

import java.util.Base64;

/**
 * A simple encrypt util.
 * @author Kal 2022/11/18
 */
public class CharAddEncryptUtil {

    public static void main(String[] args) {
        String key = "hello world";
        String text = "Hello World";
        String encrypted = encrypt(text, key);
        System.out.println("encrypt result: " + encrypted);
        String decrypted = decrypt(encrypted, key);
        System.out.println("decrypted result: " + decrypted);
        System.out.println("same as original text: " + text.equals(decrypted));
    }

    public static String encrypt(String text, String key) {
        StringBuilder encrypt = new StringBuilder();
        int index = 0;
        int len = key.length();
        char[] arr = key.toCharArray();
        for (char c : text.toCharArray()) {
            encrypt.append((char) ((int) c + (int) arr[index++ % len]));
        }
        String base64 = Base64.getEncoder().encodeToString(encrypt.toString().getBytes());
        return base64.substring(base64.length() - len) + base64.substring(0, base64.length() - len);
    }

    public static String decrypt(String encryptedText, String key) {
        StringBuilder decrypt = new StringBuilder();
        int index = 0;
        int len = key.length();
        encryptedText = encryptedText.substring(len) + encryptedText.substring(0, len);
        char[] arr = key.toCharArray();
        for (char c : new String(Base64.getDecoder().decode(encryptedText)).toCharArray()) {
            decrypt.append((char) ((int) c - (int) arr[index++ % len]));
        }
        return decrypt.toString();
    }
}
