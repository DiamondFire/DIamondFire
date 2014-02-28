package utm.csc492.diamondfire.models;

/**
 * Created by yasith on 2/27/2014.
 */
public class BattleGround {
    public static final char KNIGHT = 'K';
    public static final char GRASS = 'G';
    public static final char WALL = 'W';
    public static final char FLAG = 'F';


    private char map[][];

    private int width;
    private int height;

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
}
