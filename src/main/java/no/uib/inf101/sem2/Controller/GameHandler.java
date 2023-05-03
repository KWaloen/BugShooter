package no.uib.inf101.sem2.Controller;

import no.uib.inf101.sem2.Model.Characters.Bullet;
import no.uib.inf101.sem2.Model.Characters.PlayerModel;
import no.uib.inf101.sem2.Model.Characters.BugModel;
import no.uib.inf101.sem2.Model.GameModel;
import no.uib.inf101.sem2.Model.GameState;
import no.uib.inf101.sem2.View.GameView;
import no.uib.inf101.sem2.View.Inf101Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The GameHandler is the heart of the game. It is the only class in the game that has
 * a timer. The timer triggers and update function that moves the game along. The GameHandler
 * handles methods vital to the game working such as collision and movement.
 * There are methods here involving the bugs and bullets however they have
 * to be here because the bugs and bullets objects have parameters and are stored in arrayLists.
 * Due to the parameters it is not possible to have the objects passed into other classes.
 * To get around this, I used some static methods, arrayLists and classes.
 * This class was inspired by this video by RyiSnow https://www.youtube.com/watch?v=VpH33Uw-_0E
 */

public class GameHandler implements KeyListener, MouseListener, MouseMotionListener, ActionListener {
    PlayerModel player;
    GameView gameView;
    GameModel gameModel;

    //Static variables used here to have only one mouseListener and timer in the program
    public static int mouseX;
    public static int mouseY;
    double bulletX;
    double bulletY;
    int bulletSpeed = 40;
    int bugSpeed = 5;

    //Static variables used here due to complications with passing parameters of bug and bullet models
    public static ArrayList<Bullet> activeBullets = new ArrayList<>();
    public static ArrayList<BugModel> activeEnemies = new ArrayList<>();
    KeyBoardControls controller = new KeyBoardControls(player);
    Timer timer;

    //This variable is static because GameHandler cannot be passed into GameView
    public static int scoreCounter;
    int difficulty;


    public GameHandler(GameView gameView, PlayerModel player, GameModel gameModel) {
        this.player = player;
        this.gameView = gameView;
        this.gameModel = gameModel;
        this.gameView.setFocusable(true);
        this.gameView.addKeyListener(controller);
        this.gameView.addMouseMotionListener(this);
        this.gameView.addMouseListener(this);
        this.timer = new Timer(1, this);
        this.timer.start();
        this.difficulty = 1;

    }

    /**
     * Here is the gameLoop. This method makes a call to update methods in other classes.
     * This is the lifeblood of the game.
     */
    private void update() {

        //checks if the player ran away from the fight
        checkPlayerOnScreen();

        //checks if the bug is touching the player
        checkBugCollision();

        //checks if the bug has been shot
        BugModel.checkBugShot();

        //store first 50 bullets then remove the first
        Bullet.removeBullet();

        //This method adds more bugs as more bugs die
        if (activeEnemies.size() < (scoreCounter + difficulty)) {
            spawnBug();
        }
        //update bug pos
        moveBug();

        //update bullet pos
        Bullet.moveBullets();

        //monitor WASD input
        double xAxis = 0;
        double yAxis = 0;

        //monitor any input from the keyboard.
        if (controller.pressedW) {
            yAxis -= gameModel.getPlayerSpeed();
        }
        if (controller.pressedS) {
            yAxis += gameModel.getPlayerSpeed();
        }
        if (controller.pressedA) {
            xAxis -= gameModel.getPlayerSpeed();
        }
        if (controller.pressedD) {
            xAxis += gameModel.getPlayerSpeed();
        }

        //move player in that direction
        this.player.moveTowards(xAxis, yAxis);

        //repaint board
        gameView.repaint();

    }

    /**
     * This method resets the game.
     * Removes all active enemies and active bullets.
     * Replaces the player on the default point of 400, 300.
     * Rests game state to ACTIVE GAME
     * Resets score counter
     * sets controller.pressedR to false to prevent repeated firing.
     */
    private void resetGame() {
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
        player.setPlayerX(400);
        player.setPlayerY(300);
        gameModel.gameState = GameState.ACTIVE_GAME;
        controller.pressedR = false;
        scoreCounter = 0;
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    /**
     * This method spawns the bugs. The bugs are added into the activeEnemies
     * ArrayList and the sprite is also added to the bug here.
     */
    private void spawnBug() {
        Image bugImage = Inf101Graphics.loadImageFromResources("/bug.png");
        activeEnemies.add(BugModel.spawnBug(gameModel.getPlayerX(), gameModel.getPlayerY(), 0.2, bugImage));
    }

    /**
     * Vector calculations. This code was repeated and so set as its own method.
     *
     * @param vectorX
     * @param vectorY
     * @return
     */
    private static double vectorMagnitude(double vectorX, double vectorY) {
        return Math.sqrt(Math.pow(vectorX, 2) + Math.pow(vectorY, 2));
    }

    /**
     * This method calculates the movement of an object by calculating the vector.
     * @param goalX
     * @param goalY
     * @param originX
     * @param originY
     * @param speed
     * @return
     */

    public static ArrayList<Double> movementCalculations(double goalX, double goalY, double originX, double originY, double speed) {

        ArrayList<Double> directions = new ArrayList<Double>();

        double vectorX = goalX - originX;
        double vectorY = goalY - originY;

        double vectorMagnitude = vectorMagnitude(vectorX, vectorY);

        double vectorUnitX = vectorX / vectorMagnitude;
        double vectorUnitY = vectorY / vectorMagnitude;

        double directionX = vectorUnitX * speed;
        double directionY = vectorUnitY * speed;

        directions.add(vectorUnitX); //get(0)
        directions.add(vectorUnitY); //get(1)
        directions.add(directionX); //get(2)
        directions.add(directionY); //get(3)

        return directions;

    }

    /**
     * This method moves the bug and sets the bug face angle. This method also
     * makes a call to the checkIfBugsStacked() method however this is commented
     * out as it causes performance issues.
     */
    private void moveBug() {
        if (activeEnemies.size() > 0) {
            Iterator<BugModel> iterator = activeEnemies.iterator();
                while (iterator.hasNext()) {
                    BugModel bug = iterator.next();

                    ArrayList<Double> movementCalculations = movementCalculations(gameModel.getPlayerX(), gameModel.getPlayerY(), bug.bugX, bug.bugY, this.bugSpeed);

                    bug.bugFaceAngle = Math.atan2(movementCalculations.get(1), movementCalculations.get(0));

                    bug.bugX += movementCalculations.get(2);
                    bug.bugY += movementCalculations.get(3);

                }
            }

    }

    /**
     * This method checks if the bug collides with the player. If so, gamestatus is set
     * to GameOver.
     */
    private void checkBugCollision() {
        for (BugModel bug : activeEnemies) {
            double playerXHitBoxPos = gameModel.getPlayerX() + 25;
            double playerXHitBoxNeg = gameModel.getPlayerX() - 25;
            double playerYHitBoxPos = gameModel.getPlayerY() + 25;
            double playerYHitBoxNeg = gameModel.getPlayerY() - 25;
            if (bug.bugX <= playerXHitBoxPos && bug.bugX >= playerXHitBoxNeg && bug.bugY <= playerYHitBoxPos && bug.bugY >= playerYHitBoxNeg) {
                gameModel.gameOver();
            }
        }
    }

    /**
     * Calls playerOffScreen in the GameModel.
     */
    private void checkPlayerOnScreen() {
        gameModel.playerOffScreen();
    }

    /**
     * Gets score count
     *
     * @return
     */
    public static int getScoreCount() {
        return scoreCounter;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.bulletX = gameModel.getPlayerX();
        this.bulletY = gameModel.getPlayerY();

        //adds bullets to the activeBullets ArrayList.
        activeBullets.add(Bullet.createBullet(bulletX, bulletY, mouseX, mouseY, bulletSpeed));

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameModel.gameState == GameState.ACTIVE_GAME) {
            controller.pressedR = false; //safety mechanism
            update();
        }
        if (gameModel.gameState != GameState.ACTIVE_GAME) {
            if (controller.pressedR) {
                resetGame();
                controller.pressedR = false;
            }
        }
    }
}


