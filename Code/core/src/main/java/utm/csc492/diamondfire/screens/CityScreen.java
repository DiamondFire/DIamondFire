package utm.csc492.diamondfire.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import utm.csc492.diamondfire.DiamondFire;
import utm.csc492.diamondfire.GameState;
import utm.csc492.diamondfire.RestaurantActor;

import javax.lang.model.type.NullType;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by rainsharmin on 2014-02-07.
 */
public class CityScreen implements Screen {

    private GameState gameState = GameState.getInstance();
    private DiamondFire game = gameState.getGame();

    public static int YEAR = 1991;
    public static String MONTH = "August";
    public static String PLAYERCHEF = "Jiro"; // the chef the user picked to play as
    public Array<RestaurantActor> playerRestaurants = new Array<RestaurantActor>();
    public Array<RestaurantActor> opponentRestaurants = new Array<RestaurantActor>();

    private SpriteBatch batch;
    private Texture texture, settingstexture;
    private Sprite sprite, settingssprite;
    private OrthographicCamera camera;
    private Array<RestaurantActor> restaurants;

    private Stage stage;
    private Skin uiSkin;
    private Table table;

    public TextButton attackButton, troopsButton, endTurnButton;
    public RestaurantActor r; // fix this r

    // constructor to keep a reference to the main Game class
    public CityScreen(DiamondFire game){
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

        restaurants = new Array<RestaurantActor>();
        stage = new Stage(0,0,false);

        Gdx.input.setInputProcessor(stage);

        // Create all the restaurants
        int count = 0;
        for (int x=1; x<=7; x++) {
            for (int y=1; y<=6; y++) {
                count++;
                createRestaurant((x*64)+(64*3),y*64,count,x,y);
            }
        }
        // Assign any of the user's restaurants as the current restaurant for first turn; we'll just take the first one
        gameState.setCurrentRestaurant(playerRestaurants.get(0));

        System.out.println("Welcome to Wasabi Jam! It's your turn!\nYour current restaurant: #" +
                gameState.getCurrentRestaurant().number + " owned by Chef " + gameState.getCurrentRestaurant().owner +
                " at position (" + gameState.getCurrentRestaurant().xCoord + ", " + gameState.getCurrentRestaurant().yCoord
                + ")"
        );
        System.out.println("------------------------------------------------------");
        System.out.println("What would you like to do? (1) Attack (2) Move troops (3) End turn");
    }

    @Override
    public void render(float delta) {

        camera.update();

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        BitmapFont font=uiSkin.getFont("default-font");
        float x=40, y=40;

        Label msgLabel = new Label( "Welcome!", uiSkin );
        msgLabel.setPosition(200, 25);
        //welcomeLabel.y = ( currentY + 100 );
        //label.setAlignment(Align.bottom, Align.left);
        stage.addActor(msgLabel);

        batch.begin();
        batch.draw(settingstexture, 750, 430);
        font.draw(batch, "Choose command:", x, y);
        font.draw(batch, YEAR +" "+ MONTH, 20, 460);
        batch.end();

        for(RestaurantActor restaurant: restaurants) {
            //batch.draw(texture, restaurant.x, restaurant.y);
            stage.addActor(restaurant);
        }

        table = new Table();
        //table.setFillParent(true);
        stage.addActor(table);
        table.setPosition(20,250);

        table.debug();
        table.add(new Label("RESTAURANT #" +  gameState.getCurrentRestaurant().number + ": Chef " + gameState.getCurrentRestaurant().owner, uiSkin));
        table.row();
        /*table.add(new Label("Gold: 999", uiSkin));
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
        */
        table.add(new Label("Number of Troops: ", uiSkin));
        table.row();

        table.pack();

        // Table for the Command buttons
        /*
        tableButtons=new Table();
        tableButtons.setSize(800,480);
        tableButtons.setPosition(20, 350);
        tableButtons.debug();
        */
        attackButton=new TextButton("Attack", uiSkin);
        //tableButtons.add(attackButton).width(200).height(50);
        troopsButton=new TextButton("Move Troops", uiSkin);
        endTurnButton=new TextButton("End Turn", uiSkin);

        //tableButtons.add(troopsButton).width(150).padTop(10).padBottom(3);
        //tableButtons.row();
        //stage.addActor(tableButtons);

        // I was going to use a table for the command buttons but since we only have three
        // buttons, I just added them in individually at least for now. :o
        attackButton.setPosition(200,20);
        troopsButton.setPosition(320,20);
        endTurnButton.setPosition(440,20);
        attackButton.setWidth(100);
        troopsButton.setWidth(100);
        endTurnButton.setWidth(100);

        attackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameState.setAttackState(true);
                attackButton.setVisible(false);
                System.out.println("Which restaurant would you like to attack?");
                System.out.println("You can only attack adjacent enemy restaurants.");
            }
        });

        troopsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Which restaurant would you like to move troops to?");
                System.out.println("You can only move troops to adjacent owned restaurants.");
            }
        });

        stage.addActor(attackButton);
        stage.addActor(troopsButton);
        stage.addActor(endTurnButton);

        stage.draw();
    }

    private void createRestaurant(int x, int y, int num, int xCoord, int yCoord) {
        Random rand = new Random();
        String chefNames[] = {"Jiro", "Michelin", "Jin", "Night", "Fish"};
        // Jiro Ono = 85 year old sushi master and owner of "Sukiyabashi Jiro"
        String textureNames[] = {"shopimg-jiro.png", "shopimg-michelin.png", "shopimg-jin.png", "shopimg-night.png", "shopimg-fish.png"};

        int randNum = rand.nextInt(5);

        r = new RestaurantActor(x,y,64,64,textureNames[randNum]);
        r.number = num;
        r.xCoord = xCoord;
        r.yCoord = yCoord;
        r.owner = chefNames[randNum];
        if (r.owner == PLAYERCHEF) {
            playerRestaurants.add(r);
        } else {
            opponentRestaurants.add(r);
        }

        int adjRestaurants[];
        if ((xCoord+1)%6 == 0) {
            
        }
        /*
        r.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                if ((attackOn = true)) { //separate method? later: add case checking, if r.owner perror, else ..
                    if (e.getRelatedActor().getName() == currentTurn) {
                        System.out.println("You can't attack your own restaurant!");
                    } else {
                        System.out.println(r.owner + "  " + r.number);
                    }
                    System.out.println(e.getRelatedActor().getName());
                }
            };
        });
        */
        restaurants.add(r);
    }

    public void launchAttack(RestaurantActor from, RestaurantActor to) {
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

    public boolean isAdjacent(RestaurantActor r1, RestaurantActor r2) {
        return false;
    }
}