package utm.csc492.diamondfire.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import utm.csc492.diamondfire.GameState;
import utm.csc492.diamondfire.algorithms.Speech;

/**
 * Created by yasith on 2/12/2014.
 */
public class Knight extends Unit{

    private float lastTouchX;
    private float lastTouchY;

    private SequenceAction moveSeq;

    private Knight(Sprite sprite) {
        super(sprite);

        this.name = "Knight";
        this.shortName = 'K';
        this.health = 100;
        this.range = 1;
        this.move = 3;
    }

    @Override
    public void onTouchUp(float x, float y) {
        if(moved) return;
        move(toSquare(x), toSquare(y));
    }

    @Override
    public void onTouchDragged(float x, float y) {
        if(moved) return;
        if(toSquare(x) != toSquare(lastTouchX) || toSquare(y) != toSquare(lastTouchY)) {
            System.out.println(toSquare(x) + " " + toSquare(y));
            lastTouchX = x;
            lastTouchY = y;

            Speech speech = Speech.getInstance();
            speech.speak(getLetter(getPosY() + toSquare(y)) + " " + (getPosX() + 1 + toSquare(x)));
        }
    }

    @Override
    public void onTouchDown(float x, float y) {
        if(moved) {
            String message = "you have already moved this unit";
            Speech.getInstance().speak(message);
            return;
        }

        String message = "select " + this.name + " at " + getLetter(getPosY()) + " " + Integer.toString(getPosX()+1);
        Speech.getInstance().speak(message);

        lastTouchX = x;
        lastTouchY = y;
    }

    private int toSquare(float f) {
        return (int)f / 32;
    }

    public static Knight createKnight(int x, int y) {
        Sprite sprite = GameState.getInstance().atlas.createSprite("Knight");
        Knight knight = new Knight(sprite);
        knight.setPosition(x, y);

        float size = 32.0f;
        // Update the bounds for this unit, in screen position coordinates
        knight.setBounds(x * size, y * size, size, size);
        return knight;
    }

    @Override
    public void act(float delta) {
        if(moveSeq != null) {
            moveSeq.act(delta);
        }
    }

    private char getLetter(int x) {
        return (char)((int)'A' + x);
    }

    public void announcePosition() {
        String message = this.name + " " + "at" + " " + getLetter(getPosY()) + " " + Integer.toString(getPosX() + 1);
        Speech.getInstance().speak(message);
    }

    public void move(int x, int y) {

        float size = 32.0f;
        // Distance in pixels
        float dx = size * x;
        float dy = size * y;

        int destX = getPosX() + x;
        int destY = getPosY() + y;

        MoveByAction moveX = new MoveByAction();
        moveX.setAmountX(dx);
        moveX.setDuration(Math.abs(x) * 0.1f);

        MoveByAction moveY = new MoveByAction();
        moveY.setAmountY(dy);
        moveY.setDuration(Math.abs(y) * 0.1f);

        moveSeq = new SequenceAction(moveX, moveY);
        Speech.getInstance().speak("move " + this.name + " to " + getLetter(destY) + " " + (destX + 1));

        this.setPosition(destY, destX);
        this.addAction(moveSeq);

        moved = true;
        GameState.getInstance().addMove();
    }
}
