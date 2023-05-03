package Controls;

import no.uib.inf101.sem2.Controller.GameHandler;
import no.uib.inf101.sem2.Model.Characters.PlayerModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameHandlerTest {

    //GameTest
    //Steps
    //- Open program
    //- Play the game as normal
    //- Check that the player moves around the screen
    //- Check that going off the screen triggers OFFSCREEN
    //- Check that touching the bug triggers GAMEOVER
    //- Check that bullets fly in a straight line
    //- Check that bullets kill bugs
    //- Check that bugs keep coming
    //- Check that score counter works properly

    //Expected outcome
    //- The game should run smoothly
    //- There should be no noticable issues
    //- All steps with "check" should do as they describe

    //RestartTest
    //Steps
    //- Open program
    //- Press "R"
    //- Press "R" then go off screen
    //- Restart game
    //- Press "R" then touch a bug
    //- Restart game
    //- Go off screen then press "R"
    //- Touch a zombie then press "R"

    //Expected outcome
    //- Pressing "R" alone should do nothing
    //- Pressing "R" before going off screen does nothing
    //- Pressing "R" before touching a bug does nothing
    //- Pressing "R" after going off screen restarts the game
    //- Pressing "R" after touching a bug restarts the game

    @Test
    public void movementCalculationsTest() {
        PlayerModel player = new PlayerModel(); //400, 300
        ArrayList<Double> movement = GameHandler.movementCalculations(player.getX(), player.getY(), 400, 100, 5);
        assertEquals(0.0, movement.get(0));
        assertEquals(1.0, movement.get(1));
        assertEquals(0.0, movement.get(2));
        assertEquals(5.0, movement.get(3));

    }
}
