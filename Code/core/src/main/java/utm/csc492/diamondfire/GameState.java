package utm.csc492.diamondfire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by yasith on 2/12/2014.
 */
public class GameState {

    private static GameState instance = null;
    private DiamondFire game;

    // Reason for not keeping this private:
    // This will be used a lot for loading sprites, throughout the game
    // having a getter would just make the code longer without any benefit.
    public TextureAtlas atlas;

    // Private constructor prevents initialization from outside
    private GameState() {
        atlas = new TextureAtlas(Gdx.files.internal("sprites/battlesprites.txt"));
    }

    public static synchronized GameState getInstance() {
        if(instance == null) {
            instance = new GameState();
        }

        return instance;
    }

    public DiamondFire getGame() {
        return game;
    }

    public void setGame(DiamondFire game) {
        this.game = game;
    }
}
