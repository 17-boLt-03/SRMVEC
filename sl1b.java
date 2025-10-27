import java.util.*;
import java.io.*;

public class sl1b {

    private char pfmatrix[][] = new char[5][5];
    public String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String plain, cipher;
    int jflg = 0, xpad = 0;
    int row, col;

    public void matrixgen(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        boolean[] used = new boolean[26];
        int i = 0, j = 0;

        for (int k = 0; k < key.length(); k++) {
            char ch = key.charAt(k);
            if (!used[ch - 'A']) {
                pfmatrix[i][j] = ch;
                used[ch - 'A'] = true;
                j++;
                if (j == 5) {
                    j = 0;
                    i++;
                }
            }
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch == 'J') continue;
            if (!used[ch - 'A']) {
                pfmatrix[i][j] = ch;
                used[ch - 'A'] = true;
                j++;
                if (j == 5) {
                    j = 0;
                    i++;
                }
            }
        }
    }

    public void matrixdisplay() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                System.out.print(pfmatrix[i][j] + " ");
            System.out.println();
        }
    }

    public String pfencryption(String txt) {
        int ch1row, ch2row, ch1col, ch2col;
        char ch1, ch2, tmp1, tmp2;
        String nutext = "", text = "";
        int i = 0;

        txt = txt.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");

        while (i < txt.length()) {
            text += txt.charAt(i);
            if (i + 1 < txt.length()) {
                if (txt.charAt(i) == txt.charAt(i + 1)) {
                    text += 'X';
                    xpad++;
                } else {
                    text += txt.charAt(i + 1);
                    i++;
                }
            } else {
                text += 'X';
                xpad++;
            }
            i++;
        }

        System.out.println("TEXT : " + text);

        for (int k = 0; k < text.length(); k += 2) {
            ch1 = text.charAt(k);
            ch2 = text.charAt(k + 1);
            System.out.println("CHARACTER PAIR : " + ch1 + " " + ch2);

            matsearch(ch1);
            ch1row = row;
            ch1col = col;

            matsearch(ch2);
            ch2row = row;
            ch2col = col;

            if (ch1row == ch2row) {
                tmp1 = pfmatrix[ch1row][(ch1col + 1) % 5];
                tmp2 = pfmatrix[ch2row][(ch2col + 1) % 5];
            } else if (ch1col == ch2col) {
                tmp1 = pfmatrix[(ch1row + 1) % 5][ch1col];
                tmp2 = pfmatrix[(ch2row + 1) % 5][ch2col];
            } else {
                tmp1 = pfmatrix[ch1row][ch2col];
                tmp2 = pfmatrix[ch2row][ch1col];
            }

            nutext += tmp1;
            nutext += tmp2;

            System.out.println("TRANSLATED TEXT : " + tmp1 + " " + tmp2);
        }

        return nutext;
    }

    public String pfdecryption(String text) {
        int ch1row, ch2row, ch1col, ch2col;
        char ch1, ch2, tmp1, tmp2;
        String nutext = "", txt = "";

        for (int k = 0; k < text.length(); k += 2) {
            ch1 = text.charAt(k);
            ch2 = text.charAt(k + 1);
            System.out.println("CHARACTER PAIR : " + ch1 + " " + ch2);

            matsearch(ch1);
            ch1row = row;
            ch1col = col;

            matsearch(ch2);
            ch2row = row;
            ch2col = col;

            if (ch1row == ch2row) {
                int c1 = ch1col - 1;
                if (c1 < 0) c1 += 5;
                int c2 = ch2col - 1;
                if (c2 < 0) c2 += 5;
                tmp1 = pfmatrix[ch1row][c1];
                tmp2 = pfmatrix[ch2row][c2];
            } else if (ch1col == ch2col) {
                int r1 = ch1row - 1;
                int r2 = ch2row - 1;
                if (r1 < 0) r1 += 5;
                if (r2 < 0) r2 += 5;
                tmp1 = pfmatrix[r1][ch1col];
                tmp2 = pfmatrix[r2][ch2col];
            } else {
                tmp1 = pfmatrix[ch1row][ch2col];
                tmp2 = pfmatrix[ch2row][ch1col];
            }

            nutext += tmp1;
            nutext += tmp2;
            System.out.println("TRANSLATED TEXT : " + tmp1 + " " + tmp2);
        }

        if (xpad != 0) {
            int i = 0;
            while (i < nutext.length()) {
                if (nutext.charAt(i) == 'X') {
                    i++;
                    continue;
                }
                txt += nutext.charAt(i);
                i++;
            }
            System.out.println("TEXT : " + txt);
            return txt;
        } else {
            System.out.println("TEXT : " + nutext);
            return nutext;
        }
    }

    public void matsearch(char ch) {
        if (ch == 'J') ch = 'I';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (pfmatrix[i][j] == ch) {
                    row = i;
                    col = j;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Playfairs pf = new Playfairs();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the PLAYFAIR KEY: ");
        String pfkey = sc.nextLine();

        System.out.println("PLAYFAIR MATRIX");
        pf.matrixgen(pfkey);
        pf.matrixdisplay();

        System.out.println("Enter PLAIN TEXT");
        String ptext = sc.nextLine();

        String ctext = pf.pfencryption(ptext);
        System.out.println("\nCIPHER TEXT : " + ctext);

        String plaintext = pf.pfdecryption(ctext);
        System.out.println("\nPLAIN TEXT : " + plaintext);

        sc.close();
    }
}
