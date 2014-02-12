package utm.csc492.diamondfire.models;

import utm.csc492.diamondfire.GameState;

/**
 * Created by yasith on 2/12/2014.
 */
public class Knight extends Unit{

    private int HEALTH = 100;
    private int RANGE = 1;
    private int MOVE = 3;
    private String NAME = "Knight";

    private Knight() {
        Sprite sprite = GameState.getInstance().atlas.createSprite("knight");
        super(sprite, RANGE, MOVE, HEALTH);
    }

    public static Knight createKnight(int x, int y) {
        Knight knight = new Knight();
        knight.setPosition(x, y);

        return knight;
    }
}
