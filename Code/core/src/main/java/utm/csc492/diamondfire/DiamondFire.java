
package utm.csc492.diamondfire;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL10;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import utm.csc492.diamondfire.screens.CityScreen;


public class DiamondFire extends ApplicationAdapter {

    private Screen screen;

	//SpriteBatch batch;
	//Texture img;
	
	@Override
	public void create () {
		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
//		try {
//			new FreeTypeFontGenerator(Gdx.files.internal("test.fnt"));
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		Bullet.init();

        setScreen(new CityScreen());
	}

	@Override
	public void render () {
		/*
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		*/
        if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
    }

    /** use to set current screen **/
    public void setScreen (Screen screen) {
        if (this.screen != null) this.screen.hide();
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    /** return currently active screen **/
    public Screen getScreen () {
        return screen;
    }
}
