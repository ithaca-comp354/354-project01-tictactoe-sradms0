package edu.ithaca.dragon.games.tictactoe.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import edu.ithaca.dragon.games.tictactoe.board.TwoDArrayBoard;

public class RuleBasedPlayerTest {
    
    private boolean isCorner(Pair<Integer, Integer> pair) {
        return pair.equals(new Pair<>(0,0)) || 
               pair.equals(new Pair<>(2,0)) || 
               pair.equals(new Pair<>(0,2)) ||
               pair.equals(new Pair<>(2,2));
    }

    private boolean isMiddle(Pair<Integer, Integer> pair) {
        return pair.equals(new Pair<>(1,1));
    }

    @Test
    public void chooseSquareTest() {
        RuleBasedPlayer player = new RuleBasedPlayer();
            // --checking for agents possible win--
            // horizontal
            assertEquals(new Pair<>(2,0), player.chooseSquare(new TwoDArrayBoard("XX       "), 'X'));
            assertEquals(new Pair<>(1,0), player.chooseSquare(new TwoDArrayBoard("X X      "), 'X'));
            // vertical
            assertEquals(new Pair<>(0,2), player.chooseSquare(new TwoDArrayBoard("X  X     "), 'X'));
            assertEquals(new Pair<>(0,1), player.chooseSquare(new TwoDArrayBoard("X     X  "), 'X'));
            //left diagonal
            assertEquals(new Pair<>(2,2), player.chooseSquare(new TwoDArrayBoard("X   X    "), 'X'));
            assertEquals(new Pair<>(1,1), player.chooseSquare(new TwoDArrayBoard("X       X"), 'X'));
            // right diagonal
            assertEquals(new Pair<>(0,2), player.chooseSquare(new TwoDArrayBoard("  X X    "), 'X'));
            assertEquals(new Pair<>(1,1), player.chooseSquare(new TwoDArrayBoard("  X   X  "), 'X'));

            // --check for corner selection--
            assertTrue(isCorner(player.chooseSquare(new TwoDArrayBoard("X        "), 'X')));
            assertTrue(isCorner(player.chooseSquare(new TwoDArrayBoard("  X      "), 'X')));
            assertTrue(isCorner(player.chooseSquare(new TwoDArrayBoard("        X"), 'X')));
            assertTrue(isCorner(player.chooseSquare(new TwoDArrayBoard("      X  "), 'X')));

            // -- check for middle selection only if second
            assertFalse(isMiddle(player.chooseSquare(new TwoDArrayBoard("O        "), 'X')));
            assertTrue(isMiddle(player.chooseSquare(new TwoDArrayBoard("         "), 'X')));

            // --checking for agents defense--
            // horizontal
            assertEquals(new Pair<>(2,0), player.chooseSquare(new TwoDArrayBoard("OO       "), 'X'));
            assertEquals(new Pair<>(1,0), player.chooseSquare(new TwoDArrayBoard("O O      "), 'X'));
            // vertical
            assertEquals(new Pair<>(0,2), player.chooseSquare(new TwoDArrayBoard("O  O     "), 'X'));
            assertEquals(new Pair<>(0,1), player.chooseSquare(new TwoDArrayBoard("O     O  "), 'X'));
            //left diagonal
            assertEquals(new Pair<>(2,2), player.chooseSquare(new TwoDArrayBoard("O   O    "), 'X'));
            assertEquals(new Pair<>(1,1), player.chooseSquare(new TwoDArrayBoard("O       O"), 'X'));
            // right diagonal
            assertEquals(new Pair<>(0,2), player.chooseSquare(new TwoDArrayBoard("  O O    "), 'X'));
            assertEquals(new Pair<>(1,1), player.chooseSquare(new TwoDArrayBoard("  O   O  "), 'X'));
    }

}
