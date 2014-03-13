package utm.csc492.diamondfire;

import utm.csc492.diamondfire.algorithms.Speech;
import utm.csc492.diamondfire.screens.CityScreen;
import utm.csc492.diamondfire.DiamondFire;
import utm.csc492.diamondfire.GameState;

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
        speech = new Speech();
        if (gameState.getMoveState()) {
            //update troops
            gameState.getCurrentRestaurant().numWorkers -= numTroops;
            gameState.getOpposingRestaurant().numWorkers += numTroops;
            return;
        } else if (gameState.getAttackState()) {
            //launch attack
            speech.speak("attack"); //attacking
        }
    }
}
