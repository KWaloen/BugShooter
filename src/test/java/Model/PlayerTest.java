package Model;

import no.uib.inf101.sem2.Model.Characters.PlayerModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlayerTest {

    @Test
    public void sanityTest() {
        double startingX = 400;
        double startingY = 300;
        PlayerModel player = new PlayerModel();
        assertEquals(startingX, player.getX());
        assertEquals(startingY, player.getY());
    }

    @Test
    public void testPlayerMoveTowards(){
        double startingX = 400;
        double startingY = 300;
        double moveX = 10;
        double moveY = 10;

        PlayerModel player = new PlayerModel();
        assertEquals(startingX, player.getX());
        assertEquals(startingY, player.getY());

        player.moveTowards(moveX, moveY);

        assertNotEquals(startingX, player.getX());
        assertNotEquals(startingY, player.getY());
        assertEquals(startingX + moveX, startingX + moveX);
        assertEquals(startingY + moveY, startingY + moveY);
    }

}
