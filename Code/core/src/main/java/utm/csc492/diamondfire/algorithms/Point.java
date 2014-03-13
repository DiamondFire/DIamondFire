package utm.csc492.diamondfire.algorithms;

import java.util.ArrayList;

/**
 * Created by yasith on 2014-03-11.
 */
public class Point {

    public int x;
    public int y;

    public Point parent;

    public Point(int x, int y){
        this.x = x;
        this.y = y;

        this.parent = null;
    }

    /**
     * returns a new Point dx,dy away from this point
     */
    public Point getPoint(int dx, int dy) {
        return new Point(x+dx, y+dy);
    }

    public void setParent(Point parent) {
        this.parent = parent;
    }

    public ArrayList<Point> getPath() {
        ArrayList<Point> path = new ArrayList<Point>();

        Point next = this;
        do{
            path.add(0, next);
            next = next.parent;
        } while(next != null);

        return path;
    }
}
