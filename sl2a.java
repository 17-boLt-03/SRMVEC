import java.util.*;
public class sl2a {
    public static String encrypt(String text, int key) {
        if (key == 1) return text;
        StringBuilder[] rail = new StringBuilder[key];
        for (int i = 0; i < key; i++)
            rail[i] = new StringBuilder();
        boolean down = false;
        int row = 0;
        for (char c : text.toCharArray()) {
            rail[row].append(c);
            if (row == 0 || row == key - 1)
                down = !down;

            row += down ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rail)
            result.append(sb);
        return result.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine();
        System.out.print("Enter Key (number of rails): ");
        int key = sc.nextInt();
        String cipher = encrypt(text, key);
        System.out.println("Encrypted Text: " + cipher);
    }
}
