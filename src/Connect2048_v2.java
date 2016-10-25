import java.util.ArrayList;

/**
 * Created by noahfranks on 10/15/16.
 */

public class Connect2048_v2 {
    final char emptySymbol = ' ';
    private int gravity = 2;
    private GameWindow controller;
    char[] players = {'a', 'A'};
    int player = 0;
    private char[][] board = new char[6][7];
    boolean hasWon;

    public Connect2048_v2(GameWindow controller){
        this.controller = controller;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                board[i][j] = emptySymbol;
            }
        }
    }

    public void setGravity(int gravity){
        changeGravity(gravity);
        controller.getView().update(board);
    }

    public char[][] getBoard(){return board.clone();}

    public int getGravity(){return gravity;}

    public char getPlayer(){
        return players[player];
    }

    public void changeGravity(int gravity) {
        this.gravity = gravity;

        for (int crunchCount = 0; crunchCount < 5; crunchCount++) {
            switch (gravity) {
                case 0:
                    for (int row = 0; row < board.length; row++) {
                        for (int col = 0; col < board[row].length; col++) {
                            char playerSymbol = board[row][col];
                            board[row][col] = emptySymbol;
                            putPiece(col, row, playerSymbol);
                        }
                    }
                    break;
                case 1:
                    for (int row = board.length - 1; row >= 0; row--) {
                        for (int col = 0; col < board[row].length; col++) {
                            char playerSymbol = board[row][col];
                            board[row][col] = emptySymbol;
                            putPiece(col, row, playerSymbol);
                        }
                    }
                    break;
                case 2:
                    for (int row = board.length - 1; row >= 0; row--) {
                        for (int col = 0; col < board[row].length; col++) {
                            char playerSymbol = board[row][col];
                            board[row][col] = emptySymbol;
                            putPiece(col, row, playerSymbol);
                        }
                    }
                    break;
                case 3:
                    for (int row = 0; row < board.length; row++) {
                        for (int col = board[row].length - 1; col >= 0; col--) {
                            char playerSymbol = board[row][col];
                            board[row][col] = emptySymbol;
                            putPiece(col, row, playerSymbol);
                        }
                    }
                    break;
            }
            combine();
        }
    }


    public int putPiece(int column, int row, char color) {

        switch (gravity) {
            case 0:
                for (; row >= 0; row--) {
                    if (board[row][column] != emptySymbol) {
                        break;
                    } else if (row == 0) {
                        board[row][column] = color;
                        return row;
                    } else if (board[row - 1][column] != emptySymbol) {
                        board[row][column] = color;
                        return row;
                    }
                }
                break;
            case 1:
                for (; column >= 0; column--) {
                    if (board[row][column] != emptySymbol) {
                        break;
                    } else if (column == 0) {
                        board[row][column] = color;
                        return column;
                    } else if (board[row][column - 1] != emptySymbol) {
                        board[row][column] = color;
                        return column;
                    }
                }
                break;
            case 2:
                for (; row < board.length; row++) {
                    if (board[row][column] != emptySymbol) {
                        break;
                    } else if (row + 1 == board.length) {
                        board[row][column] = color;
                        return row;
                    } else if (board[row + 1][column] != emptySymbol) {
                        board[row][column] = color;
                        return row;
                    }
                }
                break;
            case 3:
                for (; column < board[row].length; column++) {
                    if (board[row][column] != emptySymbol) {
                        break;
                    } else if (column + 1 == board[row].length) {
                        board[row][column] = color;
                        return column;
                    } else if (board[row][column + 1] != emptySymbol) {
                        board[row][column] = color;
                        return column;
                    }
                }
                break;
        }
        return -1;
    }

    private char incrementCharacter(char letter) {
        switch (letter) {
            case 'a':
                return 'b';
            case 'b':
                return 'c';
            case 'c':
                return 'd';
            case 'A':
                return 'B';
            case 'B':
                return 'C';
            case 'C':
                return 'D';
        }
        return 'X';
    }


    public void combine() {
        switch (gravity) {
            case 0:
                for (int row = 0; row < board.length; row++) {
                    for (int col = 0; col < board[row].length; col++) {
                        if (row + 1 < board.length &&
                                board[row][col] != emptySymbol &&
                                board[row][col] != 'd' &&
                                board[row][col] != 'D' &&
                                board[row][col] == board[row + 1][col]) {
                            board[row][col] = incrementCharacter(board[row][col]);
                            board[row + 1][col] = emptySymbol;
                        }
                    }
                }
                break;
            case 1:
                for (int row = board.length - 1; row >= 0; row--) {
                    for (int col = 0; col < board[row].length; col++) {
                        if (col + 1 < board[row].length &&
                                board[row][col] != emptySymbol &&
                                board[row][col] != 'd' &&
                                board[row][col] != 'D' &&
                                board[row][col] == board[row][col + 1]) {
                            board[row][col] = incrementCharacter(board[row][col]);
                            board[row][col + 1] = emptySymbol;
                        }
                    }
                }
                break;
            case 2:
                for (int row = board.length - 1; row >= 0; row--) {
                    for (int col = 0; col < board[row].length; col++) {
                        if (row - 1 >= 0 &&
                                board[row][col] != emptySymbol &&
                                board[row][col] != 'd' &&
                                board[row][col] != 'D' &&
                                board[row][col] == board[row - 1][col]) {
                            board[row][col] = incrementCharacter(board[row][col]);
                            board[row - 1][col] = emptySymbol;
                        }
                    }
                }
                break;
            case 3:
                for (int row = 0; row < board.length; row++) {
                    for (int col = board[row].length - 1; col >= 0; col--) {
                        if (col - 1 >= 0 &&
                                board[row][col] != emptySymbol &&
                                board[row][col] != 'd' &&
                                board[row][col] != 'D' &&
                                board[row][col] == board[row][col - 1]) {
                            board[row][col] = incrementCharacter(board[row][col]);
                            board[row][col - 1] = emptySymbol;
                        }
                    }
                }
                break;


        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RED_BOLD = "\u001B[31;1m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLUE_BOLD = "\u001B[34;1m";

    /*public void printScreen() {
        String[][] boardCopy = new String[board.length][board[0].length];

        System.out.print("  ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print("   " + i + "   ");
        }
        System.out.println("");
        System.out.print("  ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print("-------");
        }

        System.out.println("");

        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < board[i].length; j++) {
                switch (board[i][j]) {
                    case 'a':
                        boardCopy[i][j] = ANSI_RED + "  1  " + ANSI_RESET + "|";
                        break;
                    case 'b':
                        boardCopy[i][j] = ANSI_RED + "  2  " + ANSI_RESET + "|";
                        break;
                    case 'c':
                        boardCopy[i][j] = ANSI_RED + "  3  " + ANSI_RESET + "|";
                        break;
                    case 'd':
                        boardCopy[i][j] = ANSI_RED_BOLD + "  4  " + ANSI_RESET + "|";
                        break;
                    case 'A':
                        boardCopy[i][j] = ANSI_BLUE + "  1  " + ANSI_RESET + "|";
                        break;
                    case 'B':
                        boardCopy[i][j] = ANSI_BLUE + "  2  " + ANSI_RESET + "|";
                        break;
                    case 'C':
                        boardCopy[i][j] = ANSI_BLUE + "  3  " + ANSI_RESET + "|";
                        break;
                    case 'D':
                        boardCopy[i][j] = ANSI_BLUE_BOLD + "  4  " + ANSI_RESET + "|";
                        break;
                    case ' ':
                        boardCopy[i][j] = "     |";
                        break;
                }
                System.out.print("" + boardCopy[i][j] + " ");
            }
            System.out.println("");
            System.out.print("  ");
            for (int x = 0; x < board[0].length; x++) {
                System.out.print("-------");
            }

            System.out.println("");
        }
        System.out.println();
        System.out.println();
    } */

   /*public void printScreen() {
       System.out.println("--------------------");
       for (int row = 0; row < board.length; row++) {

           for (int col = 0; col < board[0].length; col++) {
               System.out.print(" " + board[row][col] + " ");
           }
           System.out.println();
       }
       System.out.println("--------------------\n");
   }*/

    public char checkAlignment(int row, int col) {
        for (int k = 0; k < board.length-3; k++) { //vertical win
            if (
                    board[k][col] != ' ' &&
                            board[k][col] == board[k + 1][col] &&
                            board[k][col] == board[k + 2][col] &&
                            board[k][col] == board[k + 3][col]
                            && (board[k][col] == 'd' || board[k][col] == 'D')
                    ) {
                return board[k][col];
            }
        }

        for (int k = 0; k < board[0].length-3; k++) { //horizontal win
            if (
                    board[row][k] != ' ' &&
                            board[row][k] == board[row][k+1] &&
                            board[row][k] == board[row][k+2] &&
                            board[row][k] == board[row][k+3]
                            && (board[row][k] == 'd' || board[row][k] == 'D')) {
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
                            diagonal1.get(k) == diagonal1.get(k+3)
                            && (diagonal1.get(k) == 'd' || diagonal1.get(k) == 'D')) {
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
                            diagonal2.get(k) == diagonal2.get(k+3)
                            && (diagonal2.get(k) == 'd' || diagonal2.get(k) == 'D')) {
                return diagonal2.get(k);
            }
        }
        return ' ';
    }

    public void play(int row, int col){
        putPiece(col, row, getPlayer());
        changeGravity(gravity);
        hasWon = (checkAlignment(row, col) == 'd' || checkAlignment(row, col) == 'D');
        controller.getView().update(board);
        this.player = this.player == 0 ? 1 : 0;
    }

}
