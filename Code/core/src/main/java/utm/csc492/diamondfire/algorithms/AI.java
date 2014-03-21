package utm.csc492.diamondfire.algorithms;

import com.badlogic.gdx.utils.TimeUtils;
import utm.csc492.diamondfire.models.BattleGround;
import utm.csc492.diamondfire.models.Knight;
import utm.csc492.diamondfire.models.Unit;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by yasith on 2014-03-13.
 */
public class AI {
    private static long lastMoveTime = 0;
    private static long moveThreshold = 2000;
    private static int unitIndex  = 0;


    public static void makeMove(BattleGround ground) {

        if(TimeUtils.millis() - lastMoveTime > moveThreshold) {

            ArrayList<Unit> units = ground.getUnits(2);

            Unit unit = units.get(unitIndex);
            Knight knight = (Knight) unit;
            Random random = new Random();
            int moveX = random.nextInt(knight.getMove());
            int moveY = random.nextInt(knight.getMove() - moveX);
            knight.move(-moveX, moveY);

            unitIndex += 1;
            lastMoveTime = TimeUtils.millis();
            moveThreshold = 2000;
        }
    }

    public static void reset(){
        unitIndex = 0;
        lastMoveTime = TimeUtils.millis();
        moveThreshold = 5000;
    }
}
