/**
 * @author Chufan Gao, gao353@purdue.edu, lab b13
 * @version 10/6/2016
 */
public class Matrix {
    public static void main(String[] args) {
        int[][] matrix = {{1,0,2},
                          {0,1,0},
                          {2,0,1}};
        int[][] matrix2 = {{1,2,0},
                           {2,1,3},
                           {0,3,1}};
        int[][] matrix3 = {{1,0,0},
                           {0,1,0},
                           {0,0,1}};
        int[][] matrix4 = {{1,2,3},
                           {0,3,1},
                           {0,0,1}};

        System.out.println(isSymmetric(matrix));
        System.out.println((isDiagonal(matrix)));
        System.out.println((isIdentity(matrix)));
        System.out.println((isUpperTriangular(matrix)));
        System.out.println((isTridiagonal(matrix)));
        System.out.println();

        System.out.println(isSymmetric(matrix2));
        System.out.println((isDiagonal(matrix2)));
        System.out.println((isIdentity(matrix2)));
        System.out.println((isUpperTriangular(matrix2)));
        System.out.println((isTridiagonal(matrix2)));
        System.out.println();

        System.out.println(isSymmetric(matrix3));
        System.out.println((isDiagonal(matrix3)));
        System.out.println((isIdentity(matrix3)));
        System.out.println((isUpperTriangular(matrix3)));
        System.out.println((isTridiagonal(matrix3)));
        System.out.println();

        System.out.println(isSymmetric(matrix4));
        System.out.println((isDiagonal(matrix4)));
        System.out.println((isIdentity(matrix4)));
        System.out.println((isUpperTriangular(matrix4)));
        System.out.println((isTridiagonal(matrix4)));
        System.out.println();
    }

    public static boolean isSymmetric(int[][] matrix) { //checks if symmetric
        if (matrix.length != matrix[0].length) {
            return false;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isDiagonal(int[][] matrix) { //checks if diagona;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i != j) {
                    if (matrix[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static boolean isIdentity(int[][] matrix) { //checks if identity matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i != j) {
                    if (matrix[i][j] != 0)
                        return false;
                }
                if (i == j) {
                    if (matrix[i][j] != 1)
                        return false;
                }
            }
        }
        return true;
    }
    public static boolean isUpperTriangular(int[][] matrix) { //checks if upper triangular
        if (matrix.length != matrix[0].length) {
            return false;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[i][j] !=  0)
                    return false;
                }
            }
        return true;
    }
    public static boolean isTridiagonal(int[][] matrix) { //checks if tridiagonal
        if (matrix.length != matrix[0].length) {
            return false;
        }

        for (int i = 0; i < matrix.length; i++) { //checks lower to make sure it's zeroes
            for (int j = 0; j < i-1; j++) {
                if (matrix[i][j] !=  0)
                    return false;
            }
        }
        for (int i = 1; i < matrix.length; i++) { //checks lower to makes sure it's not zeroes
                if (matrix[i][i-1] ==  0)
                    return false;
        }
        for (int i = 0; i < matrix.length; i++) { //checks upper to make sure it's zeroes
            for (int j = i+2; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0)
                    return false;
            }
        }
        for (int i = 0; i < matrix.length-1; i++) { //checks upper to makes sure it's not zeroes
            if (matrix[i][i+1] ==  0)
                return false;
        }
        return true;
    }

}

