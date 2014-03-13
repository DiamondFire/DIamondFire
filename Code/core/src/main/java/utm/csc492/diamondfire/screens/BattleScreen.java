package utm.csc492.diamondfire.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import utm.csc492.diamondfire.DiamondFire;
import utm.csc492.diamondfire.GameState;
import utm.csc492.diamondfire.algorithms.Point;
import utm.csc492.diamondfire.algorithms.Speech;
import utm.csc492.diamondfire.models.BattleGround;
import utm.csc492.diamondfire.models.Knight;
import utm.csc492.diamondfire.models.Unit;

import java.util.ArrayList;

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

    private Knight knight1;
    private Knight knight2;

    private Speech speech;

    @Override
    public void render(float delta) {
        camera.update();

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.enableBlending();
        spriteBatch.begin();

        AtlasRegion grassTexture = gameState.atlas.findRegion("GroundBlocks");

        float size = 32.0f;
        for(int i = 0; i < gridY; i++) {
            for(int j = 0; j < gridX; j++) {
                spriteBatch.draw(grassTexture, j * size, i * size);
            }
        }

        spriteBatch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.DARK_GRAY);

        for(int i = 0; i < gridY; i++) {
            for(int j = 0; j < gridX; j++) {
                shapeRenderer.rect(j*size, i*size, size, size);
            }
        }

        shapeRenderer.end();


        // Knight knight = Knight.createKnight(5, 5);
        // drawMoveSquares(shapeRenderer, knight);

        //ArrayList<Point> moves = GroundMovement.moveTo(ground, knight1.getPosX(), knight1.getPosY(), knight2.getPosX(), knight2.getPosY());
        //drawMoveSequence(shapeRenderer, moves);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        speech.update();
    }

    private void drawMoveSequence(ShapeRenderer shapeRenderer, ArrayList<Point> moves) {
        Gdx.gl.glEnable(GL10.GL_BLEND);
        Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(54, 202, 220, 0.5f));

        float size = 32.0f;

        for(Point p: moves) {
            shapeRenderer.circle((p.x + 0.5f) * size, (p.y + 0.5f) * size, size/4);
        }

        Gdx.gl.glDisable(GL10.GL_BLEND);

        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.setViewport(width, height, true);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        //camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, 800, 480);
        camera.update();

        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();

        stage = new Stage(800, 480, false, spriteBatch);

        ground = new BattleGround(gridX, gridY);
        for(int i = 0; i < gridY; i++) {
            for(int j = 0; j < gridX; j++) {
                ground.put(BattleGround.GRASS, i, j);
            }
        }

        knight1 = Knight.createKnight(19, 9);
        knight2 = Knight.createKnight(1, 1);
        addUnit(knight1);
        addUnit(knight2);

        Gdx.input.setInputProcessor(stage);

        speech = Speech.getInstance();

        speech.speak("welcome to battle screen");
        
        speech.speak("player 1");
        speech.speak("Knight at B 1");
        speech.speak("player 2");
        speech.speak("Knight at J 19");

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

    public void drawMoveSquares(ShapeRenderer shapeRenderer, Unit unit) {
        Gdx.gl.glEnable(GL10.GL_BLEND);
        Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(54, 202, 220, 0.5f));

        int startx = unit.getPosX() - unit.getMove();
        int starty = unit.getPosY() - unit.getMove();

        int endx = unit.getPosX() + unit.getMove();
        int endy = unit.getPosY() + unit.getMove();

        float size = 32.0f;

        for(int i = starty; i <= endy; i++) {
            for(int j = startx; j <= endx; j++) {
                shapeRenderer.rect(i * size, j * size, size, size);
            }
        }

        Gdx.gl.glDisable(GL10.GL_BLEND);

        shapeRenderer.end();
    }
}
