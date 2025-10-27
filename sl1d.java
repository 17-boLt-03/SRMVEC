import java.util.*;
import java.io.*;

public class Vigenerecipher {
    public static String key = new String();
    public String extndkey;
    public String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String keyextnsn(String ptxt, String keytxt) {
        int j = 0;
        String nukey = "";
        for (int i = 0; i < ptxt.length(); i++) {
            nukey += keytxt.charAt(j);
            j++;
            if (j == keytxt.length()) j = 0;
        }
        return nukey;
    }

    public int valueofchar(char x) {
        for (int i = 0; i < 26; i++) {
            if (x == ALPHABET.charAt(i)) return i;
        }
        return 0;
    }

    public char charofvalue(int y) {
        return ALPHABET.charAt(y);
    }

    public String vcencryption(String txt) {
        int p, k, tmp1;
        char tmp;
        String ctxt = "";
        extndkey = keyextnsn(txt, key);
        System.out.println("VIGENERE ENCRYPTION");
        System.out.println("PLAIN TEXT : " + txt);
        System.out.println("VIGENERE KEY : " + extndkey);
        for (int i = 0; i < txt.length(); i++) {
            p = valueofchar(txt.charAt(i));
            k = valueofchar(extndkey.charAt(i));
            tmp1 = (p + k) % 26;
            tmp = charofvalue(tmp1);
            ctxt += tmp;
        }
        return ctxt;
    }

    public String vcdecryption(String txt) {
        int c, k, tmp1;
        char ch;
        String ptxt = "";
        System.out.println("VIGENERE DECRYPTION");
        System.out.println("CIPHER TEXT : " + txt);
        System.out.println("VIGENERE KEY : " + extndkey);
        for (int i = 0; i < txt.length(); i++) {
            c = valueofchar(txt.charAt(i));
            k = valueofchar(extndkey.charAt(i));
            tmp1 = (c - k + 26) % 26;
            ch = charofvalue(tmp1);
            ptxt += ch;
        }
        return ptxt;
    }

    public static void main(String[] args) {
        Vigenerecipher vc = new Vigenerecipher();
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER KEY");
        key = sc.next().toUpperCase();
        System.out.println("Enter PLAIN TEXT");
        String text = sc.next().toUpperCase();
        String ciphertext = vc.vcencryption(text);
        System.out.println("\nCIPHER TEXT :" + ciphertext);
        String plaintext = vc.vcdecryption(ciphertext);
        System.out.println("\nPLAIN TEXT :" + plaintext);
        sc.close();
    }
}
