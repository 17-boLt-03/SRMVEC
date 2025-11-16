import java.util.Scanner;

public class RowColumnCipherUserInput {
    public static String encrypt(String plainText, int rows, int cols) {
        char[][] matrix = new char[rows][cols];
        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (index < plainText.length()) {
                    matrix[r][c] = plainText.charAt(index++);
                } else {
                    matrix[r][c] = 'X';
                }
            }
        }
        StringBuilder cipher = new StringBuilder();
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                cipher.append(matrix[r][c]);
            }
        }
        return cipher.toString();
    }
    public static String decrypt(String cipherText, int rows, int cols) {
        char[][] matrix = new char[rows][cols];
        int index = 0;
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                matrix[r][c] = cipherText.charAt(index++);
            }
        }
        StringBuilder plain = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                plain.append(matrix[r][c]);
            }
        }
        return plain.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the plaintext: ");
        String plainText = sc.nextLine().replace(" ", "").toUpperCase();
        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();
        String encrypted = encrypt(plainText, rows, cols);
        System.out.println("\nEncrypted Text: " + encrypted);
        String decrypted = decrypt(encrypted, rows, cols);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
