import java.util.*;
public class jeeva {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 7;
        int[] key = new int[n];
        System.out.println("Enter 7-digit key for column permutation (values 1 to 7 in any order):");
        for (int i = 0; i < n; i++) key[i] = sc.nextInt();
        System.out.print("\nEnter PLAIN TEXT: ");
        String plain = sc.next().toUpperCase();
        while (plain.length() % n != 0) plain += "X";
        int rows = plain.length() / n;
        char[][] mat = new char[rows][n];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = plain.charAt(k++);
            }
        }
        System.out.println("\nColumn-wise filled Matrix:");
        for (char[] r : mat) {
            for (char c : r) System.out.print(c + " ");
            System.out.println();
        }
        StringBuilder cipher = new StringBuilder();
        for (int col : key) {
            for (int i = 0; i < rows; i++) cipher.append(mat[i][col - 1]);
        }
        System.out.println("\nCIPHER TEXT: " + cipher);
        char[][] decMat = new char[rows][n];
        k = 0;
        for (int col : key) {
            for (int i = 0; i < rows; i++) {
                decMat[i][col - 1] = cipher.charAt(k++);
            }
        }
        System.out.println("\nRow-wise filled Decryption Matrix:");
        for (char[] r : decMat) {
            for (char c : r) System.out.print(c + " ");
            System.out.println();
        }
        StringBuilder dec = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < n; j++) dec.append(decMat[i][j]);
        }
        System.out.println("\nDECRYPTED TEXT: " + dec);
    }
}
