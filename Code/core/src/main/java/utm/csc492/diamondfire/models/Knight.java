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
        MoveByAction moveX = new MoveByAction();
        moveX.setAmountX(32.0f * toSquare(x));
        moveX.setDuration(Math.abs(toSquare(x)) * 0.1f);

        MoveByAction moveY = new MoveByAction();
        moveY.setAmountY(32.0f * toSquare(y));
        moveY.setDuration(Math.abs(toSquare(y)) * 0.1f);

        moveSeq = new SequenceAction(moveX, moveY);
        Speech.getInstance().speak("move " + this.name + " to " + getLetter(toSquare(y) + getPosY()) + " " + (getPosX() + 1 + toSquare(x)));

        this.setPosition(getPosY() + toSquare(y), getPosX() + toSquare(x));
        this.addAction(moveSeq);
    }

    @Override
    public void onTouchDragged(float x, float y) {
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
}
