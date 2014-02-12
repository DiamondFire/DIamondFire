package utm.csc492.diamondfire.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import utm.csc492.diamondfire.GameState;

/**
 * Created by yasith on 2/12/2014.
 */
public class Knight extends Unit{

    private static final int HEALTH = 100;
    private static final int RANGE = 1;
    private static final int MOVE = 3;
    private static final String NAME = "Knight";

    private Knight(Sprite sprite, int range, int move, int health) {
        super(sprite, range, move, health);
    }

    public static Knight createKnight(int x, int y) {
        Sprite sprite = GameState.getInstance().atlas.createSprite("knight");
        Knight knight = new Knight(sprite, RANGE, MOVE, HEALTH);
        knight.setPosition(x, y);

        return knight;
    }

    @Override
    public void act(float delta) {
        return;
    }
}
