package utm.csc492.diamondfire.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import utm.csc492.diamondfire.DiamondFire;
import utm.csc492.diamondfire.GameState;
import utm.csc492.diamondfire.models.BattleGround;
import utm.csc492.diamondfire.models.Knight;
import utm.csc492.diamondfire.models.Unit;

/**
 * Created by yasith on 2/12/2014.
 */
public class BattleScreen implements Screen {

    private GameState gameState = GameState.getInstance();
    private DiamondFire game = gameState.getGame();

    private Stage stage;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private int gridX = 20;
    private int gridY = 10;

    private BattleGround ground;

    @Override
    public void render(float delta) {
        camera.update();

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        AtlasRegion grassTexture = gameState.atlas.findRegion("GroundBlocks");

        float size = 32.0f;
        for(int i = 0; i < gridY; i++) {
            for(int j = 0; j < gridX; j++) {
                spriteBatch.draw(grassTexture, j * size, i * size);
            }
        }

        spriteBatch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.setViewport(width, height, true);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();

        stage = new Stage(0, 0, true, spriteBatch);

        ground = new BattleGround(gridX, gridY);
        for(int i = 0; i < gridY; i++) {
            for(int j = 0; j < gridX; j++) {
                ground.put(BattleGround.GRASS, i, j);
            }
        }

        Knight knight1 = Knight.createKnight(5, 5);
        Knight knight2 = Knight.createKnight(3, 4);
        addUnit(knight1);
        addUnit(knight2);

        Gdx.input.setInputProcessor(stage);
    }

    private void addUnit(Unit unit) {
        stage.addActor(unit);
        ground.putUnit(unit);
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
        spriteBatch.dispose();
        stage.dispose();
    }
}
