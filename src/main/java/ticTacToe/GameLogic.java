/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

enum Player {
    X("X"),
    O("O");

    String value;

    Player(String value) {
        this.value = value;
    }
}

public class GameLogic {

    private Player currentPlayer = Player.X;
    private final int playField = 3;
    private String[][] matrix = new String[playField][playField];
    private static GameLogic instance = new GameLogic();

    private GameLogic() {
        clear();
    }

    public void clear() {

        for (String[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                matrix1[j] = " ";
            }
        }
        currentPlayer = Player.X;

    }

    public void setField(int x, int y, String value) {
        matrix[x][y] = value;
    }

    public String getField(int x, int y) {
        return matrix[x][y];
    }

    public boolean calculateWin() {
        String matchWith = currentPlayer.value;

        //Matching rows
        for (String[] matrix1 : matrix) {
            int matches = 0;
            for (String matrix11 : matrix1) {
                if (matrix11.equals(matchWith)) {
                    matches++;
                    if (matches == matrix1.length) {
                        return true;
                    }
                }
            }
        }
        //Matching cols - 0 should be generalized somehow
        for (int i = 0; i < matrix[0].length; i++) {
            int matches = 0;
            for (String[] matrix1 : matrix) {
                if (matrix1[i].equals(matchWith)) {
                    matches++;
                    if (matches == matrix[i].length) {
                        return true;
                    }
                }
            }
        }
        //Diagonal match
        int matches = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][i].equals(matchWith)) {
                ++matches;
            }
        }
        if (matches == matrix.length) {
            return true;
        }

        matches = 0;
        int length = matrix.length - 1;
        for (String[] matrix1 : matrix) {
            if (matrix1[length].equals(matchWith)) {
                length--;
                matches++;
            }
        }
        return matches == matrix.length;
    }

    public void changePlayer() {
        if (currentPlayer == Player.X) {
            currentPlayer = Player.O;
        } else {
            currentPlayer = Player.X;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static GameLogic getInstance() {
        return instance;
    }

    //debug method
    public void printField() {
        for (String[] matrix1 : matrix) {
            for (String matrix11 : matrix1) {
                System.out.print(matrix11 + " ");
            }
            System.out.println("");
        }
    }

}
