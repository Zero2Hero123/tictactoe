package tictactoe;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        char[][] chars2d = {
            {' ',' ',' '},
            {' ',' ',' '},
            {' ',' ',' '}
        };

        boolean gameEnded = false;
        char turn = 'X';

        printBoard(chars2d);

        while(!gameEnded){
            int x = 0;
            int y = 0;
            boolean isValid = false;

            while(!isValid){
                try {
                    x = input.nextInt();
                    y = input.nextInt();
    
                    if((x < 1 || x > 3) || (y < 1 || y > 3)){
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else if(chars2d[x-1][y-1] != ' '){
                        System.out.println("This cell is occupied! Choose another one!"); 
                    } else {
                        isValid = true;
                    }
                    
                } catch (InputMismatchException e) {
                    System.out.println("You should enter numbers!");
                }
            }

            chars2d[x-1][y-1] = turn;
            printBoard(chars2d);

            if(turn == 'X'){
                turn = 'O';
            } else {
                turn = 'X';
            }

            if(checkForWin(flattened(chars2d),'X')) {
                System.out.println("X wins");
                gameEnded = true;
            } else if (checkForWin(flattened(chars2d),'O')) {
                System.out.println("O wins");
                gameEnded = true;
            } else if(isFull(chars2d)) {
                System.out.println("Draw");
                gameEnded = true;
            }
        }

    }

    public static void printBoard(char[][] chars) {
        System.out.println("---------");
        for(int i = 0; i < chars.length; i++) {
            System.out.print("| ");   
            for(int j = 0; j < chars[0].length; j++) {
            
                System.out.print(chars[i][j]+" ");
                
            }
            System.out.println("| ");
        }
        System.out.println("---------");
    }

    public static boolean checkForWin(char[] symbols,char targetSymbol){
        // check by row
        for(int i = 0; i < symbols.length; i += 3){
            if(symbols[i] == targetSymbol && symbols[i+1] == targetSymbol && symbols[i+2] == targetSymbol){
                return true;
            }
        }

        // check by column
        for(int i = 0; i < 3; i++){
            if(symbols[i] == targetSymbol && symbols[i+3] == targetSymbol && symbols[i+6] == targetSymbol){
                return true;
            }
        }

        // check by diagonal
        if(symbols[0] == targetSymbol && symbols[4] == targetSymbol && symbols[8] == targetSymbol){
            return true;
        }
        if(symbols[2] == targetSymbol && symbols[4] == targetSymbol && symbols[6] == targetSymbol){
            return true;
        }

        return false;
    }

    public static boolean isFull(char[][] board){
        for(char[] row : board) {
            for(char symbol : row) {
                if(symbol == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    public static char[] flattened(char[][] board){
        char[] flat = new char[9];

        char[] firstRow = board[0];
        char[] secondRow = board[1];
        char[] thirdRow = board[2];

        for(int i = 0; i < 3; i++){
            flat[i] = firstRow[i];
        }
        for(int i = 3; i < 6; i++){
            flat[i] = secondRow[i-3];
        }
        for(int i = 6; i < 9; i++){
            flat[i] = thirdRow[i-6];
        }

        return flat;
    }
}
