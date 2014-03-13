package utm.csc492.diamondfire.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by yasith on 2/12/2014.
 */
public abstract class Unit extends Actor {

    protected String name;
    protected char shortName;
    protected int range;
    protected int move;
    protected int health;

    private Sprite sprite;

    // Positions on the battleground
    private int posX;
    private int posY;

    private float size = 32.0f;

    public Unit(Sprite sprite) {
        this.sprite = sprite;


        this.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onTouchDown(x, y);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                onTouchUp(x, y);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                onTouchDragged(x, y);
            }

        });
    }

    public abstract void onTouchDown(float x, float y);
    public abstract void onTouchUp(float x, float y);
    public abstract void onTouchDragged(float x, float y);


    /**
     * Reset the position of the sprite, then draw
     */
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        // Prepare the sprite before drawing
        sprite.setPosition(getX(), getY());
        sprite.setScale(this.getScaleX(), this.getScaleY());
        sprite.draw(batch, parentAlpha);
    }

    /**
     * Touch detection for the units.
     *
     * Not implemented yet
     */
    public Actor hit(float x, float y) {
        return this;
    }

    /**
     * Gets called before draw, should update the states of the Actor here.
     */
    @Override
    public abstract void act(float delta);


    /**
     * Sets the position on the battleground
     */
    public void setPosition(int x, int y){
        this.posX = x;
        this.posY = y;
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

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public char getShortName() {
        return shortName;
    }
}
