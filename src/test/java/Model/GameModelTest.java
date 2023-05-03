package Model;

import no.uib.inf101.sem2.Model.Characters.PlayerModel;
import no.uib.inf101.sem2.Model.GameModel;
import no.uib.inf101.sem2.Model.GameState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameModelTest {
    PlayerModel player = new PlayerModel();
    GameModel gameModel = new GameModel(player);
    @Test
    public void playerOffScreenTest() {
        //default player pos is 400, 300

        gameModel.playerOffScreen();
        assertEquals(GameState.ACTIVE_GAME, gameModel.getGameState());

        player.setPlayerX(829);
        player.setPlayerY(10);
        gameModel.playerOffScreen();
        assertEquals(GameState.ACTIVE_GAME, gameModel.getGameState());

        player.setPlayerX(831);
        player.setPlayerY(10);
        gameModel.playerOffScreen();
        assertEquals(GameState.OFF_SCREEN, gameModel.getGameState());

        player.setPlayerX(1000);
        player.setPlayerY(10);
        gameModel.playerOffScreen();
        assertEquals(GameState.OFF_SCREEN, gameModel.getGameState());

        player.setPlayerX(829);
        player.setPlayerY(631);
        gameModel.playerOffScreen();
        assertEquals(GameState.OFF_SCREEN, gameModel.getGameState());

        player.setPlayerX(829);
        player.setPlayerY(-31);
        gameModel.playerOffScreen();
        assertEquals(GameState.OFF_SCREEN, gameModel.getGameState());
    }
    @Test
    public void gameOver() {
        assertEquals(GameState.ACTIVE_GAME, gameModel.getGameState());
        gameModel.gameOver();
        assertEquals(GameState.GAME_OVER, gameModel.getGameState());
    }
}
