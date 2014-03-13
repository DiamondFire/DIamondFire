package utm.csc492.diamondfire.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;
import utm.csc492.diamondfire.DiamondFire;


import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by rainsharmin on 2014-02-07.
 */
public class CityOld implements Screen {

    public int year = 1991;
    public String month = "August";

    DiamondFire game;

    private SpriteBatch batch;
    private Texture texture, settingstexture;
    private Sprite sprite, settingssprite;
    private OrthographicCamera camera;
    private Array<Restaurant> restaurants;

    //public static TextureAtlas atlas;
    //public static TextureRegion restaurant;

    private Stage stage;
    private Skin uiSkin;
    private Table table;
    private TextButton button;

    public Restaurant curRestaurant;

    // constructor to keep a reference to the main Game class
    public CityOld(DiamondFire game){
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("shopimg.png"));
        sprite = new Sprite(texture);

        settingstexture = new Texture(Gdx.files.internal("gear.png"));
        settingssprite = new Sprite(settingstexture);

        uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

        restaurants = new Array<Restaurant>();
        stage = new Stage(0,0,false);

        Gdx.input.setInputProcessor(stage);

        /*
        button = new TextButton("HELLO", new TextButton.TextButtonStyle());
        button.setPosition(500,0);
        button.setHeight(100);
        button.setWidth(100);
        button.addListener(new InputListener() {
               public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                   Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **
                   return true;
               }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
            }
        });*/

        //   stage.addActor(button);

        /*
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.setPosition(200,65);

        table.debug();
        table.add(new Label("Choose a command:", uiSkin));
        table.row();
        Label label = new Label("Yes", uiSkin);
        label.setAlignment(Align.bottom, Align.left);
        table.add(label).minWidth(200).minHeight(110).fill();
        table.pack();
        */

        int count = 0;
        for (int x=1; x<=7; x++) {
            for (int y=1; y<=6; y++) {
                count++;
                createRestaurant((x*64)+(64*3),y*64,count);
            }
        }


    }

    @Override
    public void render(float delta) {
        /*
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(texture, 0, 0);
        batch.end();
        */

        //create();

        camera.update();

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        //Table.drawDebug(stage);

        BitmapFont font=uiSkin.getFont("default-font");
        float x=40, y=40;

        Label label = new Label( "Welcome!", uiSkin );
        //welcomeLabel.x = ( ( width - welcomeLabel.width ) / 2 );
        //welcomeLabel.y = ( currentY + 100 );
        label.setAlignment(Align.bottom, Align.left);
        //stage.addActor( label );

        batch.begin();
        batch.draw(settingstexture, 750, 430);
        font.draw(batch, "Choose command:", x, y);
        font.draw(batch, year+" "+month, 20, 460);

        for(Restaurant restaurant: restaurants) {
            batch.draw(texture, restaurant.x, restaurant.y);
        }

        batch.end();

        table = new Table();
        //table.setFillParent(true);
        stage.addActor(table);
        table.setPosition(20,250);

        table.debug();
        table.add(new Label("RESTAURANT #" +  curRestaurant.number + ": Chef " + curRestaurant.owner, uiSkin));
        table.row();
        table.add(new Label("Gold: 999", uiSkin));
        table.row();
        table.add(new Label("Food: 999", uiSkin));
        table.row();
        table.add(new Label("Employees: 999", uiSkin));
        table.row();
        table.add(new Label("Customer Loyalty: 100", uiSkin));
        table.row();
        table.add(new Label("Ingredient Cultivation: 200", uiSkin));
        table.row();
        table.add(new Label("Protection: 200", uiSkin));
        table.row();
        table.pack();

        stage.draw();
    }

    private void createRestaurant(int x, int y, int num) {
        Random rand = new Random();
        String chefNames[] = {"Jiro", "Michelin", "Jin", "Mars", "Night", "Fish"};
        // Jiro Ono = 85 year old sushi master and owner of "Sukiyabashi Jiro"

        Restaurant r = new Restaurant();
        r.x = x;
        r.y = y;
        r.width = 64;
        r.height = 64;
        r.number = num;
        r.owner = chefNames[rand.nextInt(6)];
        restaurants.add(r);
        curRestaurant = r;
    }

    @Override
    public void resize(int width, int height) {
        stage.setViewport(width, height, true);
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
        stage.dispose();
        uiSkin.dispose();
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

    class Restaurant extends Actor {
        public int number;
        public String name;
        public String owner;

        public int x, y, width, height;
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
        } }


        Chef names: Jiro, Michelin, Ajin, Mars, Night, Fish
        */
}

