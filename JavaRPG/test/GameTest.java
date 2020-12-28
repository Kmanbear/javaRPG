import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {
    
    private static int[][] board3Empty = new int[][] {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    
    //playTurn tests
    //test playTurn on empty board
    @Test
    public void playTurnEmpty() {
        MapModel model = new MapModel(10);
        model.playerMovement(0, 0);
//        assertTrue(Arrays.deepEquals(model.getWorldMap(), (new int[][] {
//            {1, 0, 0},
//            {0, 0, 0},
//            {0, 0, 0}
//        })));
    }
}
