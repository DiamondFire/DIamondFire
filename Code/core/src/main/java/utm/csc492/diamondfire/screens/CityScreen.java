package utm.csc492.diamondfire.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by rainsharmin on 2014-02-07.
 */
public class CityScreen implements Screen {

    SpriteBatch batch;
    Texture img;

    @Override
    public void render(float delta) {
        batch = new SpriteBatch();
        img = new Texture("shopimg.png");
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
