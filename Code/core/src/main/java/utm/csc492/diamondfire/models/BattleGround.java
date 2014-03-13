package utm.csc492.diamondfire.models;

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

    List<ArrayList<Unit>> players = new ArrayList<ArrayList<Unit>>(2);

    public BattleGround(int width, int height) {
        this.width = width;
        this.height = height;

        map = new char[height][width];
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
        players.get(player).add(unit);
    }
}
