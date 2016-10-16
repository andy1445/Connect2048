import java.sql.SQLSyntaxErrorException;
import java.util.WeakHashMap;

/**
 * Created by andy1445 on 10/10/2016.
 */
public class ArrayBuilder {
    char[][] letterArray;
    char baseLetter;

    public static void main(String[] args) {
        ArrayBuilder a = new ArrayBuilder('A',27,27);
        a.printLetterArray();
    }

    public ArrayBuilder(char baseLetter, int m, int n) {
        letterArray = new char[m][n];
        this.baseLetter = baseLetter;
        build();
    }

    public void build() {
        char cRowStart = baseLetter;
        for (int row = 0; row < letterArray.length; row++) {
            char cCol = cRowStart;
            for(int col = 0; col < letterArray[0].length; col++) {
                letterArray[row][col] = cCol;
                cCol = getNext(cCol);
            }
            cRowStart = getNext(cRowStart);
        }
    }

    public static char getNext(char c) {
        if ((int) c >= 65 && (int) c <= 90) {
            if ((int) c == 90)
                return (char) 65;
            else
                return (char) ((int) c + 1);
        } else if ((int) c >= 97 && (int) c <= 122) {
            if ((int) c == 122)
                return (char) 97;
            else
                return (char) ((int) c + 1);
        }
        return ' ';
    }

    public void printLetterArray() {
        for (char[] k: letterArray) {
            for (char c: k)
                System.out.print(c);
            System.out.println();
        }
    }

    public char[][] getLetterArray() {
        char[][] temp = new char[letterArray.length][letterArray[0].length];
        for (int r = 0; r < letterArray.length; r++) {
            for (int c = 0; c < letterArray[0].length; c++) {
                temp[r][c] = letterArray[r][c];
            }
        }
        return temp;
    }
}
