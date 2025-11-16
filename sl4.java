package com.includehelp.stringsample;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class sl4 {
    private static final String encryptionKey = "ABCDEFGHIJKLMNOP";
    private static final String characterEncoding = "UTF-8";
    private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithm = "AES";
    public static String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] keyBytes = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, aesEncryptionAlgorithm);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(characterEncoding));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null;
        }
    }
    public static String decrypt(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] keyBytes = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, aesEncryptionAlgorithm);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, characterEncoding);
        } catch (Exception e) {
            System.err.println("Decryption error: " + e.getMessage());
            return null;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter String to Encrypt: ");
        String plainText = scanner.nextLine();
        String encrypted = encrypt(plainText);
        if (encrypted != null) {
            System.out.println("Encrypted String: " + encrypted);
            String decrypted = decrypt(encrypted);
            System.out.println("Decrypted String: " + decrypted);
        } else {
            System.out.println("Encryption failed.");
        }
        scanner.close();
    }
}
