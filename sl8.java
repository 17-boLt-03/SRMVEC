import java.util.*;
import java.math.BigInteger;
class sl8 {
    final static BigInteger one = BigInteger.ONE;
    final static BigInteger zero = BigInteger.ZERO;
    public static BigInteger getNextPrime(String ans) {
        BigInteger test = new BigInteger(ans);
        while (!test.isProbablePrime(99)) {
            test = test.add(one);
        }
        return test;
    }
    public static BigInteger findQ(BigInteger n) {
        BigInteger start = new BigInteger("2");
        while (!n.isProbablePrime(99)) {
            while (!(n.mod(start).equals(zero))) {
                start = start.add(one);
            }
            n = n.divide(start);
        }
        return n;
    }
    public static BigInteger getGen(BigInteger p, BigInteger q, Random r) {
        BigInteger h;
        do {
            h = new BigInteger(p.bitLength(), r);
            h = h.mod(p.subtract(one)).add(one);
        } while (h.equals(one));
        return h.modPow((p.subtract(one)).divide(q), p);
    }
    public static void main(String[] args) throws Exception {
        Random randObj = new Random();
        BigInteger p = getNextPrime("10600");
        BigInteger q = findQ(p.subtract(one));
        BigInteger g = getGen(p, q, randObj);
        System.out.println("Digital Signature Algorithm");
        System.out.println("Global public key components are:");
        System.out.println("p is: " + p);
        System.out.println("q is: " + q);
        System.out.println("g is: " + g);
        BigInteger x = new BigInteger(q.bitLength(), randObj).mod(q);
        BigInteger y = g.modPow(x, p);
        BigInteger k;
        do {
            k = new BigInteger(q.bitLength(), randObj).mod(q);
        } while (k.equals(BigInteger.ZERO) || !k.gcd(q).equals(BigInteger.ONE));
        BigInteger hashVal = new BigInteger(p.bitLength(), randObj);
        BigInteger r = (g.modPow(k, p)).mod(q);
        BigInteger kInv = k.modInverse(q);
        BigInteger s = kInv.multiply(hashVal.add(x.multiply(r))).mod(q);
        System.out.println("Secret information:");
        System.out.println("x (private) is: " + x);
        System.out.println("k (secret) is: " + k);
        System.out.println("y (public) is: " + y);
        System.out.println("h (random hash) is: " + hashVal);
        System.out.println("Generating digital signature:");
        System.out.println("r is: " + r);
        System.out.println("s is: " + s);
        BigInteger w = s.modInverse(q);
        BigInteger u1 = (hashVal.multiply(w)).mod(q);
        BigInteger u2 = (r.multiply(w)).mod(q);
        BigInteger v = (g.modPow(u1, p).multiply(y.modPow(u2, p))).mod(p).mod(q);
        System.out.println("Verifying digital signature (checkpoints):");
        System.out.println("w is: " + w);
        System.out.println("u1 is: " + u1);
        System.out.println("u2 is: " + u2);
        System.out.println("v is: " + v);
        if (v.equals(r)) {
            System.out.println("Success: digital signature is verified! " + r);
        } else {
            System.out.println("Error: incorrect digital signature");
        }
    }
}
