package no.uib.inf101.sem2;

import no.uib.inf101.sem2.Controller.KeyBoardControls;
import no.uib.inf101.sem2.Model.Characters.PlayerModel;
import no.uib.inf101.sem2.Controller.GameHandler;
import no.uib.inf101.sem2.Model.GameModel;
import no.uib.inf101.sem2.View.GameView;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        PlayerModel player = new PlayerModel();
        GameModel gameModel = new GameModel(player);
        GameView gameView = new GameView(gameModel);
        GameHandler gameHandler = new GameHandler(gameView, player, gameModel);

        new KeyBoardControls(player);

        JFrame frame = new JFrame();
        frame.setTitle("BugShooter");
        frame.add(gameView); //present gameView
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); //open game in center of the screen
        frame.setResizable(false); //unresizable
    }
}
