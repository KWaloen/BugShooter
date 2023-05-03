package Model;

import no.uib.inf101.sem2.Model.Characters.BugModel;
import no.uib.inf101.sem2.Model.Characters.Bullet;
import no.uib.inf101.sem2.Controller.GameHandler;
import no.uib.inf101.sem2.Model.Characters.PlayerModel;
import no.uib.inf101.sem2.View.Inf101Graphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BugTest {

    public void emptyLists() {
        if (GameHandler.activeEnemies.size() != 0) {
            for (int i = GameHandler.activeEnemies.size(); i > 0; i--) {
                GameHandler.activeEnemies.remove(GameHandler.activeEnemies.size() - 1);
            }

            if (GameHandler.activeBullets.size() != 0) {
                for (int i = GameHandler.activeBullets.size(); i > 0; i--) {
                    GameHandler.activeBullets.remove(GameHandler.activeBullets.size() - 1);
                }
            }
        }
    }

    @Test
    public void spawnBugTest() {
        emptyLists();
        Image bugImage = Inf101Graphics.loadImageFromResources("/bug.png");
        PlayerModel player = new PlayerModel();
        Assertions.assertNotEquals(BugModel.spawnBug(player.getX(), player.getY(), 0.5, bugImage), BugModel.spawnBug(player.getX(), player.getY(), 0.5, bugImage));
        Assertions.assertNotEquals(BugModel.spawnBug(player.getX(), player.getY(), 0.5, bugImage), BugModel.spawnBug(player.getX(), player.getY(), 0.5, bugImage));
        Assertions.assertNotEquals(BugModel.spawnBug(player.getX(), player.getY(), 0.5, bugImage), BugModel.spawnBug(player.getX(), player.getY(), 0.5, bugImage));
        Assertions.assertNotEquals(BugModel.spawnBug(player.getX(), player.getY(), 0.5, bugImage), BugModel.spawnBug(player.getX(), player.getY(), 0.5, bugImage));
        Assertions.assertNotEquals(BugModel.spawnBug(player.getX(), player.getY(), 0.5, bugImage), BugModel.spawnBug(player.getX(), player.getY(), 0.5, bugImage));
            }

    @Test
    public void checkBugShotTest() {

        emptyLists();
        GameHandler.activeBullets.add(Bullet.createBullet(400, 100, 100, 100, 4));
        Image bugImage = Inf101Graphics.loadImageFromResources("/bug.png");

        BugModel bug1 = new BugModel(600, 100, 600, 100, 0.5, bugImage);
        GameHandler.activeEnemies.add(bug1);

        BugModel bug2 = new BugModel(600, 100, 400, 100, 0.5, bugImage);
        GameHandler.activeEnemies.add(bug2);

        assertEquals(2, GameHandler.activeEnemies.size());

        BugModel.checkBugShot();

        //bug has been shot and removed
        assertEquals(1, GameHandler.activeEnemies.size());

        GameHandler.activeBullets.remove(0);
        emptyLists();
    }

    //Bugs Manual test
    //Steps to carry out
    //- Open the program
    //- Move the player around the gameboard in any direction
    //- Visually confirm that the bugs are actively following the player in any direction
    //- Once the previous step is confirmed, allow the bugs to touch the player
    //- Visually confirm GameOver screen.

    //Expected outcome
    //- Bugs spawn
    //- Bugs chase the player in any direction
    //- When the bugs touch the player, GameOver text appears on the screen


}
