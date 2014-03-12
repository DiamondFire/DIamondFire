package utm.csc492.diamondfire.algorithms;

import utm.csc492.diamondfire.models.BattleGround;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by yasith on 2/28/2014.
 */

public class GroundMovement {

    private final static int DIRECTIONS[][] = new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    /**
     * Implemented using a BFS
     */
    public static ArrayList<Point> moveTo(BattleGround ground, int startx, int starty, int endx, int endy) {

        LinkedList<Point> queue = new LinkedList<Point>();
        Point startLocation = new Point(startx, starty);

        queue.add(startLocation);
        boolean visited[][] = new boolean[ground.width][ground.height];

        while(!queue.isEmpty()) {
            Point point = queue.pop();
            visited[point.x][point.y] = true;
            System.out.println("Visiting " + point.x + " " + point.y);

            if(point.x == endx && point.y == endy) {
                return point.getPath();
            }

            for(int i = 0; i < 4; i++) {
                int dx = DIRECTIONS[i][0];
                int dy = DIRECTIONS[i][1];

                Point neighbor = point.getPoint(dx, dy);

                if(neighbor.x >= ground.width || neighbor.y >= ground.height || neighbor.x < 0 || neighbor.y < 0) continue;
                if(visited[neighbor.x][neighbor.y]) continue;

                // y is the row, and x is the column
                // TODO: Change x/y, row/col system to be just one
                if(ground.get(neighbor.y, neighbor.x) != BattleGround.WALL) {
                    neighbor.setParent(point);
                    queue.add(neighbor);
                }
            }
        }

        return null;
    }
}
