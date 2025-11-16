import java.math.BigInteger;
import java.util.Random;
public class sl5 {
    private BigInteger p, q, n, phi, e, d;
    private int bitLen = 1024;
    private Random rand;
    private static String bytesToString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(b).append(" ");
        }
        return result.toString();
    }
    public RSA() {
        rand = new Random();
        p = BigInteger.probablePrime(bitLen, rand);
        q = BigInteger.probablePrime(bitLen, rand);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitLen / 2, rand);

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }
    public byte[] encrypt(byte[] message) {
        return new BigInteger(message).modPow(e, n).toByteArray();
    }
    public byte[] decrypt(byte[] encrypted) {
        return new BigInteger(encrypted).modPow(d, n).toByteArray();
    }
    public static void main(String[] args) {
        try {
            RSA rsa = new RSA();
            String message = "SRM Valliammai";
            System.out.println("=== RSA Algorithm Simulation ===");
            System.out.println("Original message (string): " + message);
            System.out.println("Original message (bytes):  " + bytesToString(message.getBytes()));
            byte[] encrypted = rsa.encrypt(message.getBytes());
            System.out.println("Encrypted message (bytes): " + bytesToString(encrypted));
            byte[] decrypted = rsa.decrypt(encrypted);
            System.out.println("Decrypted message (bytes): " + bytesToString(decrypted));
            System.out.println("Decrypted message (string): " + new String(decrypted));
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
