package utm.csc492.diamondfire;

import com.badlogic.gdx.utils.Timer;
import utm.csc492.diamondfire.algorithms.Speech;

/**
 * Created by rainsharmin on 2014-03-12.
 */
public class GameFunctions {

    private static GameState gameState = GameState.getInstance();
    private DiamondFire game = gameState.getGame();
    private static Speech speech;

    public void launchAttack(RestaurantActor r1, RestaurantActor r2) {
        //setScreen(CityScreen);
    }

    public static void takeAction(int numTroops) {
        speech=Speech.getInstance();
        if (gameState.isMoveOn()) {
            //update troops
            gameState.getCurrentRestaurant().numWorkers -= numTroops;
            gameState.getOpposingRestaurant().numWorkers += numTroops;
            speech.speak("move " + numTroops + " workers done");
            gameState.setMoveState(false);
            endTurn();
        } else if (gameState.isAttackOn()) {
            //launch attack
            speech.speak("attacking done");
            gameState.setAttackState(false);
            endTurn();
        }
    }

    public static void AITurn() {
        speech=Speech.getInstance();

        speech.speak("opponent's turn");
        /*try {
            Thread.currentThread().sleep(3000);

        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }*/
    }

    public static void endTurn() {
        gameState.increaseTurnCount();
        gameState.setMoveState(false);
        gameState.setAttackState(false);
        gameState.paused = true;    // for ai turn
        AITurn();
        Timer.schedule(new Timer.Task(){
            @Override
            public void run(){
                playerTurn();
            }
        }, 5);

    }

    public static void playerTurn() {
        speech=Speech.getInstance();
        gameState.paused = false;

        gameState.setCurrentRestaurant(gameState.getPlayerRestaurants().get(gameState.getTurnCount()));
        speech.speak("your turn at restaurant position " + gameState.getCurrentRestaurant().xCoord + " "
                + gameState.getCurrentRestaurant().yCoord + " number of workers "
                + gameState.getCurrentRestaurant().numWorkers);
    }
}
