package utm.csc492.diamondfire.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import utm.csc492.diamondfire.DiamondFire;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

/**
 * Created by rainsharmin on 2014-02-07.
 */
public class CityScreen implements Screen {

    DiamondFire game;
    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private OrthographicCamera camera;
    private Array<Rectangle> restaurants;
    //private Stage stage;

    // constructor to keep a reference to the main Game class
    public CityScreen(DiamondFire game){
        this.game = game;
    }

    @Override
    public void render(float delta) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("shopimg.png"));
        sprite = new Sprite(texture);

        restaurants = new Array<Rectangle>();
        //stage = new Stage();
        //Gdx.input.setInputProcessor(stage);

        /*for(int i = 0; i < coordinates.length/2; i=i+2) {
            createRestaurant(coordinates[x],coordinates[x+1]);
        }*/
        int count = 0;
        for (int x=1; x<=10; x++) {
            for (int y=1; y<=6; y++) {
                count++;
                createRestaurant(x*64,y*64,count);
            }
        }

        //Graph cityMap = new Graph(22);

        /*
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(texture, 0, 0);
        batch.end();
        */

        camera.update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
       // stage.act(Gdx.graphics.getDeltaTime());
        //stage.draw();

        batch.begin();
        for(Rectangle restaurant: restaurants) {
            batch.draw(texture, restaurant.x, restaurant.y);
        }
        batch.end();

    }

    private void createRestaurant(int x, int y, int num) {
        Restaurant r = new Restaurant();
        r.x = x;
        r.y = y;
        r.width = 64;
        r.height = 64;
        r.number = num;
        restaurants.add(r);
    }

    @Override
    public void resize(int width, int height) {
        //stage.setViewport(width, height, true);
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
        batch.dispose();
        texture.dispose();
    }

    public boolean inCircle(int circleX, int circleY, int clickX, int clickY, int radius) {
        return java.lang.Math.pow((circleX+radius - clickX),2) + java.lang.Math.pow((circleY+radius -clickY),2) < java.lang.Math.pow(radius,2);
    }

    public void mouseClicked(MouseEvent arg0) {
        if(inCircle(10, 10, arg0.getX(), arg0.getY(), 50)){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    public boolean isAdjacent(Restaurant r1, Restaurant r2) {
        return false;
    }

    class Restaurant extends Rectangle {
        public int number;
        public String name;
        //public Chef owner;

    }

    /*class Graph {
        private int V; // # vertices

        private Map<Integer, List<Integer>> adj;

        public Graph(int V) {
            this.V = V;
            adj = new HashMap<Integer, List<Integer>>();
            for (int i = 1 ; i <= V ; i++)
            {
                adj.put(i, new LinkedList<Integer>());
            }
        }

        // insert v-w, parallel edges allowed
        public void insert(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
            createRestaurant(v,w);
        }
        public Iterable<Integer> adj(int v) { return adj[v];
        } }*/
}
