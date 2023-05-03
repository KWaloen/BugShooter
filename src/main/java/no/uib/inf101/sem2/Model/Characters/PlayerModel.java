package no.uib.inf101.sem2.Model.Characters;

import no.uib.inf101.sem2.Controller.GameHandler;

/**
 * Say hello to the player.
 * The player has a size, color, x and y coordinates and finally a speed.
 * The default starting position is (400, 300).
 * The default speed is 4.
 */

public class PlayerModel {

    double playerX;
    double playerY;
    public double speed;
    double mouseX;
    double mouseY;
    double faceAngle;


    /**
     * Say hello to the player.
     * The player has a size, color, x and y coordinates and finally a speed.
     * The default starting position is (400, 300).
     * The default speed is 4.
     */
    public PlayerModel() {
        this.playerX = 400; //starting X pos
        this.playerY = 300; //starting Y pos
        this.speed = 5;
    }

    public void moveTowards(double xAxis, double yAxis) {
        this.playerX = this.playerX + xAxis;
        this.playerY = this.playerY + yAxis;
        getMousePos();
        calculateFaceAngle();
    }

    /**
     * Getter for the mouse coordinates. It is fed through from the Controller class.
     */
    private void getMousePos() {
        this.mouseX = GameHandler.mouseX;
        this.mouseY = GameHandler.mouseY;
    }

    /**
     * This method calculates the angle that the player needs to face in order to face the mouse.
     * This method calculates the "faceAngle" of the player.
     * Solution was found here: <a href="https://www.instructables.com/Using-Java-to-Rotate-an-Object-to-Face-the-Mouse/">...</a>
     */
    private void calculateFaceAngle() {
        double distanceX = this.playerX - this.mouseX;
        double distanceY = this.playerY - this.mouseY;
        faceAngle = Math.atan2(distanceY, distanceX);
    }

    /**
     * This is the getter method for the faceAngle.
     *
     * @return faceAngle
     */
    public double getPlayerAngleFace() {
        return faceAngle;
    }

    /**
     * Gets the X coordinate of the player.
     *
     * @return
     */
    public double getX() {
        return this.playerX;
    }

    public void setPlayerX(double playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(double playerY) {
        this.playerY = playerY;
    }

    /**
     * Gets the Y coordinate of the player.
     *
     * @return
     */
    public double getY() {
        return this.playerY;
    }

    /**
     * Gets the speed of the player.
     *
     * @return
     */
    public double getSpeed() {
        return speed;
    }
}


