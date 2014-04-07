package utm.csc492.diamondfire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.sun.swing.internal.plaf.synth.resources.synth_sv;
import utm.csc492.diamondfire.algorithms.Speech;

public class RestaurantActor extends Actor {

    public int x, y, width, height, xCoord, yCoord;
    private Texture texture;
    public int number, numWorkers;
    public String name;
    public String owner;
    public Speech speech;

    private GameState gameState = GameState.getInstance();

    public RestaurantActor (int x, int y, int width, int height, String textureName) {
        texture = new Texture(Gdx.files.internal(textureName));
        setWidth(width);
        setHeight(height);
        setPosition(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        setColor(Color.YELLOW);
        //region = new TextureRegion();
        speech = Speech.getInstance();

        ClickListener clickListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                if (gameState.isAttackOn() == true || gameState.isMoveOn() == true) {

                    System.out.println("You clicked on Restaurant #" + number + " owned by Chef " + owner +
                            " at position (" + xCoord + ", " + yCoord +")");

                    speech.speak("position " + xCoord + " " + yCoord + " owned by chef " + owner);

                    // check if restaurant is the same as current restaurant
                    if (number == gameState.getCurrentRestaurant().number) {
                        System.out.println("You can't attack your own restaurant! :O");
                        speech.speak("that is current restaurant"); // the
                    } else {

                        // check if restaurant is adjacent
                        int oppXCoord, oppYCoord;
                        oppXCoord = gameState.getCurrentRestaurant().xCoord;
                        oppYCoord = gameState.getCurrentRestaurant().yCoord;

                        if (appropriateOwner()) {
                            if ((xCoord == oppXCoord-1 || xCoord == oppXCoord || xCoord == oppXCoord+1)
                                    && (yCoord == oppYCoord-1 || yCoord == oppYCoord || yCoord == oppYCoord+1)) {
                                //gameState.setOpposingRestaurant();
                                if (gameState.getAct().equals("rest " + String.valueOf(number))) {
                                    int numTroops = 0;

                                    if (gameState.isMoveOn()) {
                                        numTroops = (int)(Math.floor((double)gameState.getCurrentRestaurant().numWorkers/2));
                                    }
                                    opposeRestaurant(numTroops);
                                    gameState.setAct("");
                                } else {
                                    gameState.setAct("rest " + String.valueOf(number));
                                    speech.speak("tap again to confirm");
                                }

                            } else {
                                System.out.println("That restaurant is too far away!");
                                speech.speak("too far");
                            }

                        }

                    }}}
        };

        this.addListener(clickListener);
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        batch.draw(texture, x, y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        //System.out.println("touch down");
        return super.hit(x, y, touchable);
    }

    @Override
    public boolean addListener(EventListener listener) {
        return super.addListener(listener);
    }

    @Override
    public boolean removeListener(EventListener listener) {
        return super.removeListener(listener);
    }

    public boolean appropriateOwner() {
        if (owner == gameState.getCurrentRestaurant().owner) {
            if (gameState.isMoveOn()) {
                return true;
            } else if (gameState.isAttackOn()){
                speech.speak("you can not attack your own restaurant");
                return false;
            }
        } else {
            if (gameState.isMoveOn()) {
                speech.speak("you can not move to opponent restaurant");
                return false;
            } else if (gameState.isAttackOn()){
                return true;
            }
        }
        return false;
    }

    public void opposeRestaurant(int numTroops) {
        gameState.setOpposingRestaurant(this);
        GameFunctions.takeAction(numTroops);
    }

    //public Chef owner;

}
/*import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

/**
 * Created by rainsharmin on 2014-02-09.
 */
/*
public class RestaurantActor extends Actor {

    private Texture texture;
    public int number;
    public String name;

    public RestaurantActor () {
        texture = new Texture(Gdx.files.internal("shopimg.png"));

        //region = new TextureRegion();
    }

    @Override
    public void draw (SpriteBatch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.begin();
        for(RestaurantActor restaurant: restaurants) {
            batch.draw(texture, restaurant.x, restaurant.y);
        }
        batch.end();    */    //batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
          //      getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
 //   }

    /*public Actor hit (float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return x >= 0 && x < width && y >= 0 && y < height ? this : null;
    }*/
//}
