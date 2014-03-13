package utm.csc492.diamondfire.models;

import com.badlogic.gdx.scenes.scene2d.Stage;
import utm.csc492.diamondfire.algorithms.Speech;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yasith on 2/27/2014.
 */
public class BattleGround {
    public static final char KNIGHT = 'K';
    public static final char GRASS = 'G';
    public static final char WALL = 'W';
    public static final char FLAG = 'F';

    private char map[][];

    public int width;
    public int height;

    List<ArrayList<Unit>> players = new ArrayList<ArrayList<Unit>>();

    public BattleGround(int width, int height) {
        this.width = width;
        this.height = height;

        map = new char[height][width];
        players.add(new ArrayList<Unit>());
        players.add(new ArrayList<Unit>());
    }

    public char get(int row, int col){
        return map[row][col];
    }

    public void put(char unit, int row, int col) {
        map[row][col] = unit;
    }

    public void putUnit(Unit unit) {
        map[unit.getPosY()][unit.getPosX()] = unit.getShortName();
    }

    public void addUnit(int player, Unit unit) {
        putUnit(unit);
        players.get(player-1).add(unit);

    }

    public void setUp(Stage stage) {
        Speech speech = Speech.getInstance();

        // For Player 1
        for(int i = 0; i < 4; i++) {
            Knight knight = Knight.createKnight(0, 4+i);
            addUnit(1, knight);
            stage.addActor(knight);
        }

        // For Player 2
        for(int i = 0; i < 4; i++) {
            Knight knight = Knight.createKnight(19, 4+i);
            addUnit(2, knight);
            stage.addActor(knight);
        }
    }

    public void announceUnits(){
        Speech speech = Speech.getInstance();
        for(int i = 0; i < 2; i++) {
            speech.speak("player " + (i + 1));

            for(Unit unit: players.get(i)) {
                Knight knight = (Knight) unit;
                knight.announcePosition();
            }
        }
    }
}
