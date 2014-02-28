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

public class RestaurantActor extends Actor {


    public int x, y, width, height;
    private Texture texture;
    public int number;
    public String name;
    public String owner;

    public RestaurantActor (int x, int y, int width, int height) {
        texture = new Texture(Gdx.files.internal("shopimg.png"));
        setWidth(width);
        setHeight(height);
        setPosition(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        setColor(Color.YELLOW);
        //region = new TextureRegion();
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
