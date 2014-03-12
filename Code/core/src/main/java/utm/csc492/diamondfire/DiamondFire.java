package utm.csc492.diamondfire;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import utm.csc492.diamondfire.screens.CityScreen;


public class DiamondFire extends ApplicationAdapter {

    private Screen screen;

    @Override
    public void create () {
        GameState.getInstance().setGame(this);
        setScreen(new CityScreen(this));
        //setScreen(new BattleScreen());
    }

    @Override
    public void render () {
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

