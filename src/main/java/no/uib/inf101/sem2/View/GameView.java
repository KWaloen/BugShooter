package no.uib.inf101.sem2.View;

import no.uib.inf101.sem2.Model.Characters.Bullet;
import no.uib.inf101.sem2.Model.Characters.BugModel;
import no.uib.inf101.sem2.Controller.GameHandler;
import no.uib.inf101.sem2.Model.GameModel;
import no.uib.inf101.sem2.Model.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class draws all aspects of the game.
 */

public class GameView extends JPanel {
    GameModel gameModel;
    double modelPlayerX;
    double modelPlayerY;
    ArrayList<Bullet> activeBullets;
    ArrayList<BugModel> activeEnemies;


    public GameView(GameModel gameModel) {
        this.modelPlayerX = gameModel.getPlayerX();
        this.modelPlayerY = gameModel.getPlayerY();
        this.gameModel = gameModel;
        int screenWidth = 800;
        int screenHeight = 600;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.setDoubleBuffered(true);
        this.activeBullets = GameHandler.activeBullets;
        this.activeEnemies = GameHandler.activeEnemies;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create(); //got solution from https://stackoverflow.com/questions/7517688/rotate-a-java-graphics2d-rectangle

        drawGame(g2D);
        repaint();
        g2D.dispose();
    }

    /**
     * This method draws the game elements.
     *
     * @param g2D
     */
    private void drawGame(Graphics2D g2D) {
        g2D.drawImage(Inf101Graphics.loadImageFromResources("/background.png"), 0, 0, null);
        message(g2D);
        showKillCount(g2D);
        drawPlayer(g2D);
        drawBullets(g2D);
        drawBugs(g2D);
        checkGameState(g2D);
    }

    /**
     * Lets draw the player!
     * This method draws the player and rotates the player.
     *
     * @param g2D graphics
     */
    private void drawPlayer(Graphics2D g2D) {
        Image player = Inf101Graphics.loadImageFromResources("/Player.png");
        Inf101Graphics.drawCenteredImage(g2D, player, gameModel.getPlayerX(), gameModel.getPlayerY(), 0.2, gameModel.getPlayerFaceAngle());
    }

    /**
     * This method draws every bullet in the activeBullet arrayList.
     *
     * @param g2D
     */
    private void drawBullets(Graphics2D g2D) {
        for (Bullet bullet : activeBullets) {
            Inf101Graphics.drawCenteredImage(g2D, bullet.image, bullet.bulletX, bullet.bulletY, 0.15, 0.1);

        }
    }

    /**
     * This method draws every bug in the activeEnemies arrayList
     *
     * @param g2D
     */
    private void drawBugs(Graphics2D g2D) {
        for (BugModel bugs : activeEnemies) {
            Inf101Graphics.drawCenteredImage(g2D, bugs.image, bugs.bugX, bugs.bugY, 0.2, bugs.bugFaceAngle);
        }
    }

    /**
     * This method checks the GameState.
     *
     * @param g2D
     */
    private void checkGameState(Graphics2D g2D) {
        if (gameModel.getGameState() == GameState.GAME_OVER) {
            g2D.setColor(Color.GREEN);
            g2D.setFont(new Font("Arial", Font.BOLD, 40));
            g2D.drawString("YOU HAVE BEEN", 220, 250);
            g2D.drawString("OVERWHELMED BY BUGS", 130, 350);

            g2D.setFont(new Font("Arial", Font.BOLD, 20));
            g2D.setColor(Color.CYAN);
            g2D.drawString("The assignment must be completed!", 220, 420);
            g2D.drawString("Press R to regain composure and try again", 180, 450);
        }
        if (gameModel.getGameState() == GameState.OFF_SCREEN) {
            g2D.setColor(Color.GREEN);
            g2D.setFont(new Font("Arial", Font.BOLD, 40));
            g2D.drawString("YOU RAN AWAY", 260, 250);
            g2D.drawString("FROM YOUR ASSIGNMENT!", 140, 350);

            //.setColor(Color.BLACK);
            //g2D.fillRect(170, 410, 460, 120);


            g2D.setFont(new Font("Arial", Font.BOLD, 20));
            g2D.setColor(Color.CYAN);
            g2D.drawString("The assignment must be completed!", 230, 420);
            g2D.drawString("Press R to quit procrastinating and try again", 190, 450);
        }
    }

    /**
     * This method presents the player with a message before the first shot is fired.
     *
     * @param g2D
     */
    private void message(Graphics2D g2D) {
        if (GameHandler.activeBullets.size() == 0 && gameModel.getGameState() == GameState.ACTIVE_GAME) {
            g2D.setColor(Color.GREEN);
            g2D.setFont(new Font("Arial", Font.BOLD, 40));
            g2D.drawString("THERE ARE BUGS EVERYWHERE", 80, 150);
            g2D.drawString("YOUR DEADLINE APPROACHES", 100, 300);
            g2D.drawString("ELIMINATE THE BUGS!", 180, 450);
        }
    }

    /**
     * This method draws the Kill Count of the player.
     *
     * @param g2D
     */
    private void showKillCount(Graphics2D g2D) {
        g2D.setColor(Color.CYAN);
        g2D.setFont(new Font("Arial", Font.BOLD, 20));
        g2D.drawString("Kill Count: " + GameHandler.getScoreCount(), 25, 50);
    }
}
