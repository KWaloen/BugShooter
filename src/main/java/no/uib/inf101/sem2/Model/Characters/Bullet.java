package no.uib.inf101.sem2.Model.Characters;

import no.uib.inf101.sem2.Controller.GameHandler;
import no.uib.inf101.sem2.View.Inf101Graphics;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class deals with storing information on the bullet.
 */

public class Bullet {
    public double bulletX;
    public double bulletY;
    double directionX;
    double directionY;
    public Image image;

    public Bullet(double bulletX, double bulletY, double directionX, double directionY, Image image) {

        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.directionX = directionX;
        this.directionY = directionY;
        this.image = image;
    }

    /**
     * This method initializes the bullet by calculating the
     * vector from the bullet origin (player position) to the mouse X and Y. Then the unit
     * vector is calculated and the bullet speed is adjusted from there. Then a new bullet
     * object is created with the X and Y coordinates of the bullet as well as the direction
     * of travel. This object is then added to an arrayList of bullets.
     */
    public static Bullet createBullet(double bulletX, double bulletY, double mouseX, double mouseY, double bulletSpeed) {

        ArrayList<Double> movementCalculations = GameHandler.movementCalculations(mouseX, mouseY, bulletX, bulletY, bulletSpeed);

        Image bulletImage = Inf101Graphics.loadImageFromResources("/Bullet.png");

        return new Bullet(bulletX, bulletY, movementCalculations.get(2), movementCalculations.get(3), bulletImage);

    }

    /**
     * This method iterates through the activeBullet arrayList and moves each bullet.
     * Every update, the bullet moves in the direction of the bullet's travel.
     * The bullets keep moving until they reach certain coordinates designated in the
     * if statement. Then the bullet is stopped.
     */
    public static void moveBullets() {
        for (Bullet bullets : GameHandler.activeBullets) {
            bullets.bulletX += bullets.directionX;
            bullets.bulletY += bullets.directionY;
            if (bullets.bulletX < -10 || bullets.bulletX > 1250 || bullets.bulletY < -10 || bullets.bulletY > 1250) {
                bullets.directionX = 0;
                bullets.directionY = 0;
            }
        }
    }

    /**
     * This method removes stationary bullets. It allows the activeBullet list to retain 50
     * bullets to allow for rapid fire without erasing active bullets. Then the first bullet is
     * removed from the list. This was done to avoid concurrentModificationException.
     */
    public static void removeBullet() {
        if (GameHandler.activeBullets.size() > 50) {
            GameHandler.activeBullets.remove(0);
        }
    }
}