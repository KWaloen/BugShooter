package no.uib.inf101.sem2.Model.Characters;

import no.uib.inf101.sem2.Controller.GameHandler;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;

public class BugModel {
    public double bugX;
    public double bugY;
    double playerX;
    double playerY;
    public double bugFaceAngle;
    public Image image;

    public BugModel(double playerX, double playerY, double bugX, double bugY, double bugFaceAngle, Image image) {
        this.bugX = bugX;
        this.bugY = bugY;
        this.playerX = playerX;
        this.playerY = playerY;
        this.bugFaceAngle = bugFaceAngle;
        this.image = image;
    }

    /**
     * Sets random spawn points for the bug within -35 and 1000 on both the X and Y axis.
     *
     * @return
     */
    private static double randomSpawn() {
        Random spawnPoint = new Random();
        double randomSpawn = spawnPoint.nextDouble(1000 + 200) + (-200);
        while (randomSpawn > -35 && randomSpawn < 800) {
            randomSpawn = spawnPoint.nextDouble(1000 + 200) + (-200);
        }
        return randomSpawn;
    }

    /**
     * After retrieving a bug X and Y spawn position, save all necessary parameters
     * to the object and add bug to the activeEnemies arrayList.
     */
    public static BugModel spawnBug(double playerX, double playerY, double faceAngle, Image bugImage) {
        double randomBugX = randomSpawn();
        double randomBugY = randomSpawn();
        return new BugModel(playerX, playerY, randomBugX, randomBugY, 0.5, bugImage);
    }

    /**
     * Check if the bug has been hit by the bullet by calculating an area around the
     * bug. If the bullet passes through the area, the bug is removed from the
     * ArrayList of activeEnemies.
     */
    public static void checkBugShot() {
        for (Bullet bullet : GameHandler.activeBullets) {
            Iterator<BugModel> enemyIterator = GameHandler.activeEnemies.iterator();
            while (enemyIterator.hasNext()) {
                BugModel bug = enemyIterator.next();
                double xHitBoxPos = bug.bugX + 25;
                double xHitBoxNeg = bug.bugX - 25;
                double yHitBoxPos = bug.bugY + 25;
                double yHitBoxNeg = bug.bugY - 25;

                if (bullet.bulletX <= 800 && bullet.bulletX >= 0 && bullet.bulletY <= 600 && bullet.bulletY >= 0) {
                    if (bullet.bulletX <= xHitBoxPos && bullet.bulletX >= xHitBoxNeg && bullet.bulletY <= yHitBoxPos && bullet.bulletY >= yHitBoxNeg) {
                        GameHandler.scoreCounter += 1;
                        enemyIterator.remove();
                    }
                }
            }
        }
    }
}