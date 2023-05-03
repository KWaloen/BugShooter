package Model;

import no.uib.inf101.sem2.Model.Characters.Bullet;
import no.uib.inf101.sem2.Controller.GameHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BulletTest {

    @Test
    public void createBulletTest() {

        GameHandler.activeBullets.add(Bullet.createBullet(400, 100, 100, 100, 4));
        assertEquals(1, GameHandler.activeBullets.size());
        GameHandler.activeBullets.add(Bullet.createBullet(400, 100, 100, 100, 4));
        assertEquals(2, GameHandler.activeBullets.size());
        GameHandler.activeBullets.add(Bullet.createBullet(400, 100, 100, 100, 4));
        assertEquals(3, GameHandler.activeBullets.size());
        GameHandler.activeBullets.add(Bullet.createBullet(400, 100, 100, 100, 4));
        assertEquals(4, GameHandler.activeBullets.size());
        GameHandler.activeBullets.add(Bullet.createBullet(400, 100, 100, 100, 4));
        assertEquals(5, GameHandler.activeBullets.size());
    }


    @Test
    public void moveBulletsTest() {
        GameHandler.activeBullets.add(Bullet.createBullet(400, 100, 200, 100, 4));
        //Mouse is straight above the bullet
        //Therefore the bullet is moving straight up at the bulletSpeed.

        Bullet.moveBullets();
        for (Bullet bullets : GameHandler.activeBullets) {
            assertEquals(396.0, bullets.bulletX);
            assertEquals(100.0, bullets.bulletY);
        }
        Bullet.moveBullets();
        for (Bullet bullets : GameHandler.activeBullets) {
            assertEquals(392.0, bullets.bulletX);
            assertEquals(100.0, bullets.bulletY);
        }
        Bullet.moveBullets();
        for (Bullet bullets : GameHandler.activeBullets) {
            assertEquals(388.0, bullets.bulletX);
            assertEquals(100.0, bullets.bulletY);
        }
    }

    @Test
    public void removeBulletTest() {
        //45 because there are existing bullets from the createBullets Test
        for (int i = 0; i <45; i++) {
            GameHandler.activeBullets.add(Bullet.createBullet(400, 100, 100, 100, 4));
        }

        assertEquals(51, GameHandler.activeBullets.size());
        Bullet.removeBullet();
        assertEquals(50, GameHandler.activeBullets.size());
        GameHandler.activeBullets.add(Bullet.createBullet(400, 100, 100, 100, 4));
        assertEquals(51, GameHandler.activeBullets.size());
        Bullet.removeBullet();
        assertEquals(50, GameHandler.activeBullets.size());
        GameHandler.activeBullets.add(Bullet.createBullet(400, 100, 100, 100, 4));
        assertEquals(51, GameHandler.activeBullets.size());
        Bullet.removeBullet();
        assertEquals(50, GameHandler.activeBullets.size());
        GameHandler.activeBullets.add(Bullet.createBullet(400, 100, 100, 100, 4));
        assertEquals(51, GameHandler.activeBullets.size());
        Bullet.removeBullet();
        assertEquals(50, GameHandler.activeBullets.size());
    }

    //Bullets Manual test
    //Steps to carry out
    //- Open the program
    //- Pick a random direction and left click
    //- Pick another random direction and left click and move the mouse while the bullet is travelling
    //- In any direction click the left mouse button as fast as possible 10 times

    //Expected outcome
    //- A bullet will spawn on the player
    //- The bullet will travel in a straight line towards the mouse location off of the screen.
    //- The bullet will continue travelling in a straight line even if the mouse has been moved.
    //- 10 bullets will spawn as the mouse is clicked and each bullet will travel off of the screen in a line without disappearing

}
