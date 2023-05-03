package no.uib.inf101.sem2.Model;

import no.uib.inf101.sem2.Model.Characters.PlayerModel;

public class GameModel {
    PlayerModel player;
    double playerX;
    double playerSpeed;
    double playerY;
    double playerFaceAngle;
    public GameState gameState;

    public GameModel(PlayerModel player) {
        this.player = player;
        this.playerSpeed = player.getSpeed();
        this.playerX = player.getX();
        this.playerY = player.getY();
        this.playerFaceAngle = player.getPlayerAngleFace();
        this.gameState = GameState.ACTIVE_GAME;
    }

    /**
     * Checks if the player is off of the screen. If so, it will set the GameState to OFF SCREEN.
     */
    public void playerOffScreen() {
        getPlayerX();
        getPlayerY();
        if (playerX > 830 || playerX < -30 || playerY > 630 || playerY < -30) {
            this.gameState = GameState.OFF_SCREEN;
        }
    }

    /**
     * Getter for the X coordinate of the player from the Player class and saves the
     * new coordinate in the gameModel as an instance variable.
     *
     * @return Player X coordinate
     */
    public double getPlayerX() {
        this.playerX = player.getX();
        return playerX;
    }

    /**
     * Getter for the Y coordinate of the player from the Player class and saves the
     * new coordinate in the gameModel as an instance variable.
     *
     * @return Player Y coordinate
     */
    public double getPlayerY() {
        this.playerY = player.getY();
        return playerY;
    }


    /**
     * Getter for the faceAngle of the player from the Player class and saves the new
     * angle (in radians) in the gameModel as an instance variable
     *
     * @return
     */
    public double getPlayerFaceAngle() {
        this.playerFaceAngle = player.getPlayerAngleFace();
        return playerFaceAngle;
    }

    /**
     * Gets the speed of the player.
     *
     * @return
     */
    public double getPlayerSpeed() {
        return playerSpeed;
    }

    /**
     * Changes GameState to GameOver.
     *
     * @return
     */
    public void gameOver() {
        this.gameState = GameState.GAME_OVER;
    }

    /**
     * Gets Gamestate.
     *
     * @return
     */
    public GameState getGameState() {
        return this.gameState;
    }
}