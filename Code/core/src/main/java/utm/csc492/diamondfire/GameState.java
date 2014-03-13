package utm.csc492.diamondfire;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by yasith on 2/12/2014.
 */
public class GameState {

    private static GameState instance = null;
    private DiamondFire game;

    private boolean attackOn = false;
    private RestaurantActor currentRestaurant;
    private RestaurantActor opposingRestaurant;
    private boolean moveOn = false;

    // Reason for not keeping this private:
    // This will be used a lot for loading sprites, throughout the game
    // having a getter would just make the code longer without any benefit.
    public TextureAtlas atlas;

    // Private constructor prevents initialization from outside
    private GameState() {
        //atlas = new TextureAtlas(Gdx.files.internal("sprites/battlesprites.txt"));
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

    public void setAttackState(boolean state) {  this.attackOn = state; }

    public boolean getAttackState() {
        return this.attackOn;
    }

    public void setMoveState(boolean state) {  this.moveOn = state; }

    public boolean getMoveState() {
        return this.moveOn;
    }

    public void setCurrentRestaurant(RestaurantActor r) {
        this.currentRestaurant = r;
    }

    public RestaurantActor getCurrentRestaurant() {
        return this.currentRestaurant;
    }

    public void setOpposingRestaurant(RestaurantActor r) {
        this.opposingRestaurant = r;
    }

    public RestaurantActor getOpposingRestaurant() {
        return this.opposingRestaurant;
    }
}
