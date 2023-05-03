package no.uib.inf101.sem2.Controller;

import no.uib.inf101.sem2.Model.Characters.PlayerModel;

import java.awt.event.*;

/**
 * This is the controller class. The player is controlled by WSAD and the player looks at the mouse.
 * This controller class was adapted from the blinking ball course material.
 */
public class KeyBoardControls implements KeyListener {
    boolean pressedW;
    boolean pressedS;
    boolean pressedA;
    boolean pressedD;
    boolean pressedR;


    PlayerModel player;

    /**
     * The controller constructor takes in a playerModel and the gameView.
     *
     * @param player player
     */
    public KeyBoardControls(PlayerModel player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.pressedW = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            this.pressedS = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            this.pressedA = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            this.pressedD = true;
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            this.pressedR = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.pressedW = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            this.pressedS = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            this.pressedA = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            this.pressedD = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    } //Ignore

}

