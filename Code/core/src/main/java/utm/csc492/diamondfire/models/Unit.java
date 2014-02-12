package utm.csc492.diamondfire.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by yasith on 2/12/2014.
 */
public abstract class Unit extends Actor {

    private String name;
    private int range;
    private int move;
    private int health;

    private Sprite sprite;

    // Positions on the battleground
    private int x;
    private int y;

    public Unit(Sprite sprite, int range, int move, int health) {
        this.sprite = sprite;
        this.range = range;
        this.move = move;
        this.health = health;
    }


    /**
     * Reset the position of the sprite, then draw
     */
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        // Prepare the sprite before drawing
        sprite.setPosition(x,y);
        sprite.setScale(scaleX, scaleY);

        sprite.draw(batch, parentAlpha);
    }

    /**
     * Touch detection for the units.
     *
     * Not implemented yet
     */
    @Override
    public Actor hit(float x, float y) {}

    /**
     * Gets called before draw, should update the states of the Actor here.
     */
    @Override
    public abstract void act(float delta);


    /**
     * Sets the position on the battleground
     */
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getRange() {
        return range;
    }

    public int getMove() {
        return move;
    }
}
