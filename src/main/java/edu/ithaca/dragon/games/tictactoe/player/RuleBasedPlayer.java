package edu.ithaca.dragon.games.tictactoe.player;

import java.util.ArrayList;
import java.util.Random;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

public class RuleBasedPlayer implements TicTacToePlayer {

    private boolean yourSymbolIsAt(String boardString, int[][] symbolMap, int x, int y, char yourSymbol) {
        return boardString.charAt(symbolMap[x][y]) == yourSymbol;
    }

    private boolean isCorner(int y, int x) {
        return (y == 0 && x == 0) ||
               (y == 2 && x == 0) ||
               (y == 0 && x == 2) ||
               (y == 2 && x == 2);
    }

    @Override
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
        String curBoardString = curBoard.buildSquaresString();
        int[][] symbolMap = {
            {0,1,2},
            {3,4,5},
            {6,7,8}
        };
        // horizontal check
        int count = 0;
        for (int x = 0; x < 3; x++){
            for(int y = 0; y < 3 && count < 2; y++) {
                if (yourSymbolIsAt(curBoardString, symbolMap, x, y, yourSymbol)) {
                    count++;
                }
            }
            if (count == 2) {
                // find empty spot in row
                for (int i = 0; i < 3; i++) {
                    if (curBoard.isSquareOpen(new Pair<>(i,x))) {
                        return new Pair<>(i,x);
                    }
                }
            } else {
                count = 0;
            }
        }
        // vertical check
        count = 0;
        for (int y = 0; y < 3; y++){
            for(int x = 0; x < 3 && count < 2; x++) {
                if (yourSymbolIsAt(curBoardString, symbolMap, x, y, yourSymbol)) {
                    count++;
                }
            }
            if (count == 2) {
                // find empty spot in column
                for (int i = 0; i < 3; i++) {
                    if (curBoard.isSquareOpen(new Pair<>(y,i))) {
                        return new Pair<>(y,i);
                    }
                }
            } else {
                count = 0;
            }
        }

        // left diagonal check
        for (int x1 = 0, y1 = 0; x1 < 3 && y1 < 3; x1++, y1++)  {
            if (yourSymbolIsAt(curBoardString, symbolMap, x1, y1, yourSymbol)) {
                count++;
            }
            if (count == 2) {
                // find empty spot
                for (int x2 = 0, y2 = 0; x2 < 3 && y2 < 3; x2++, y2++)  {
                    if (curBoard.isSquareOpen(new Pair<>(y2,x2))) {
                        return new Pair<>(y2,x2);
                    } 
                }
            }
        }

        // right diagonal check
        for (int x1 = 0, y1 = 2; x1 < 3 && y1 >= 0; x1++, y1--)  {
            if (yourSymbolIsAt(curBoardString, symbolMap, x1, y1, yourSymbol)) {
                count++;
            }
            if (count == 2) {
                // find empty spot
                for (int x2 = 0, y2 = 2; x2 < 3 && y2 >= 0; x2++, y2--)  {
                    if (curBoard.isSquareOpen(new Pair<>(y2,x2))) {
                        return new Pair<>(y2,x2);
                    } 
                }
            }
        }
        // take corners if no win is available
        Random rand = new Random();
        ArrayList<Pair<Integer,Integer>> availCorners = new ArrayList<>();
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (curBoard.isSquareOpen(new Pair<>(y,x)) && isCorner(y, x)) {
                    availCorners.add(new Pair<>(y,x));
                }
            }
        }
        if (!availCorners.isEmpty()) 
            return availCorners.get(rand.nextInt(availCorners.size()));

        // otherwise, be random
        int x;
        int y;
        do {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        } while (!curBoard.isSquareOpen(new Pair<>(y,x)));
        return new Pair<>(y,x);
    }
}
