import java.io.*;
import java.math.BigInteger;
class sl6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a prime number (p): ");
        BigInteger p = new BigInteger(br.readLine());
        System.out.print("Enter a primitive root modulo p (g): ");
        BigInteger g = new BigInteger(br.readLine());
        System.out.print("Enter private key for sender (x), 0 < x < p: ");
        BigInteger x = new BigInteger(br.readLine());
        BigInteger R1 = g.modPow(x, p);
        System.out.println("Sender's public key (R1) = " + R1);
        System.out.print("Enter private key for receiver (y), 0 < y < p: ");
        BigInteger y = new BigInteger(br.readLine());
        BigInteger R2 = g.modPow(y, p);
        System.out.println("Receiver's public key (R2) = " + R2);
        BigInteger k1 = R2.modPow(x, p);
        System.out.println("Shared secret key computed by sender: " + k1);
        BigInteger k2 = R1.modPow(y, p);
        System.out.println("Shared secret key computed by receiver: " + k2);
        if (k1.equals(k2)) {
            System.out.println("\nSuccess! Both shared keys match.");
        } else {
            System.out.println("\nError! Shared keys do not match.");
        }
    }
}
