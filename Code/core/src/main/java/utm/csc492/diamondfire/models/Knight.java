package utm.csc492.diamondfire.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import utm.csc492.diamondfire.GameState;

/**
 * Created by yasith on 2/12/2014.
 */
public class Knight extends Unit{

    private Knight(Sprite sprite) {
        super(sprite);

        this.name = "Knight";
        this.shortName = 'K';
        this.health = 100;
        this.range = 1;
        this.move = 3;
    }

    public static Knight createKnight(int x, int y) {
        Sprite sprite = GameState.getInstance().atlas.createSprite("Knight");
        Knight knight = new Knight(sprite);
        knight.setPosition(x, y);

        return knight;
    }

    @Override
    public void act(float delta) {
        return;
    }
}
