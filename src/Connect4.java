import java.util.Scanner;
import java.util.ArrayList;

/**
 * Created by andy1445 on 10/5/2016.
 */
public class Connect4 {
    private char[][] board  = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
    };
    Scanner s = new Scanner(System.in);

    public Connect4() {
    }

    public char[][] getBoard() {
        char[][] currentBoard = new char[6][7];

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                currentBoard[r][c] = board[r][c];
            }
        }
        return currentBoard;
    }

    public int putPiece(int col, char color) { //returns row
        for (int k = board.length-1; k >= 0; k--) {
            if (board[k][col] == ' ') {
                board[k][col] = color;
                return k;
            }
        }
        return 0;
    }

    public char checkAlignment(int row, int col) {
        for (int k = 0; k < board.length-3; k++) { //vertical win
            if (
                    board[k][col] != ' ' &&
                    board[k][col] == board[k + 1][col] &&
                    board[k][col] == board[k + 2][col] &&
                    board[k][col] == board[k + 3][col]
                    ) {
                return board[k][col];
            }
        }

        for (int k = 0; k < board[0].length-3; k++) { //horizontal win
            if (
                    board[row][k] != ' ' &&
                    board[row][k] == board[row][k+1] &&
                    board[row][k] == board[row][k+2] &&
                    board[row][k] == board[row][k+3]) {
                return board[row][k];
            }
        }
        int r = row;
        int c = col;

        ArrayList<Character> diagonal1 = new ArrayList<>();
        while (row > 0 && col > 0) { //left up down right win
            row--; col--;
        }
        while (row < board.length && col < board[0].length) {
            diagonal1.add(board[row++][col++]);
        }
        for (int k = 0; k < diagonal1.size()-3; k++) {
            if (
                    diagonal1.get(k) != ' ' &&
                    diagonal1.get(k) == diagonal1.get(k+1) &&
                    diagonal1.get(k) == diagonal1.get(k+2) &&
                    diagonal1.get(k) == diagonal1.get(k+3)) {
                return diagonal1.get(k);
            }
        }

        ArrayList<Character> diagonal2 = new ArrayList<>();
        while (r < board.length-1 && c > 0) { //left down up right win
            r++; c--;
        }
        while ( r > 0 && c < board[0].length) {
            diagonal2.add(board[r--][c++]);
        }
        for (int k = 0; k < diagonal2.size()-3; k++) {
            if (
                    diagonal2.get(k) != ' ' &&
                    diagonal2.get(k) == diagonal2.get(k+1) &&
                    diagonal2.get(k) == diagonal2.get(k+2) &&
                    diagonal2.get(k) == diagonal2.get(k+3)) {
                return diagonal2.get(k);
            }
        }
        return ' ';
    }

    public void printScreen() {
        int rowCount = 0;
        for (int k = 0; k < board.length; k++) {
            for (int colCount = 0; colCount <= board.length && k == 0; colCount++) {
                if (colCount == 0)
                    System.out.print("  ");
                System.out.print(" " + colCount);
                if (colCount == board.length)
                    System.out.println();
            }

            System.out.print(rowCount + " ");
            System.out.print("|");

            for (int j = 0; j < board[0].length; j++) {
                System.out.printf(board[k][j] + "|");
            }
            System.out.println();
            System.out.println("  ---------------");
            rowCount++;
        }
    }

    public void play() {
        int n = 1;
        int col;
        char c;
        int putPieceRow;
        int numPlays = 0;

        do {
            if (n == 1)
                c = 'X';
            else
                c = 'O';
            n*=-1;
            printScreen();

            System.out.println("Enter a column");
            col = s.nextInt();
            putPieceRow = putPiece(col,c);
            numPlays++;
            if (numPlays == 42) {
                System.out.println("Tie!");
                break;
            }

        } while (checkAlignment(putPieceRow,col) != 'X' && checkAlignment(putPieceRow,col) != 'O');

        System.out.println(checkAlignment(putPieceRow,col) + " wins!");
    }
}