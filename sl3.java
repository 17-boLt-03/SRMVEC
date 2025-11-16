import javax.swing.*;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Random;
public class sl3 {
    byte[] skey = new byte[1000];
    String skeyString;
    static byte[] raw;
    String inputMessage, encryptedData, decryptedMessage;
    public DES() {
        try {
            generateSymmetricKey();
            inputMessage = JOptionPane.showInputDialog(null, "Enter message to encrypt");
            if (inputMessage == null || inputMessage.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No input provided.");
                return;
            }
            byte[] inputBytes = inputMessage.getBytes();
            byte[] encryptedBytes = encrypt(raw, inputBytes);
            encryptedData = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("Encrypted message: " + encryptedData);
            JOptionPane.showMessageDialog(null, "Encrypted Data:\n" + encryptedData);
            byte[] decryptedBytes = decrypt(raw, Base64.getDecoder().decode(encryptedData));
            decryptedMessage = new String(decryptedBytes);
            System.out.println("Decrypted message: " + decryptedMessage);
            JOptionPane.showMessageDialog(null, "Decrypted Data:\n" + decryptedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void generateSymmetricKey() {
        try {
            Random r = new Random();
            int num = r.nextInt(10000);
            String knum = String.valueOf(num);
            byte[] knumb = knum.getBytes();
            skey = getRawKey(knumb);
            skeyString = Base64.getEncoder().encodeToString(skey);
            System.out.println("DES Symmetric Key (Base64) = " + skeyString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("DES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(56, sr);
        SecretKey skey = kgen.generateKey();
        raw = skey.getEncoded();
        return raw;
    }
    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return cipher.doFinal(clear);
    }
    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher.doFinal(encrypted);
    }
    public static void main(String[] args) {
        new DES();
    }
}
