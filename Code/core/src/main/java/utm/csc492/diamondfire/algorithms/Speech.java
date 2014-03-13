package utm.csc492.diamondfire.algorithms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by yasith on 2014-03-12.
 */
public class Speech {

    private static Speech instance;

    private LinkedList<Sound> words;
    private long lastSpokenTime = 0;
    private long speechThreshold = 1000;

    private Speech() {
        words = new LinkedList<Sound>();
        lastSpokenTime = TimeUtils.millis();
    }

    public static synchronized Speech getInstance() {
        if(instance == null) {
            instance = new Speech();
        }

        return instance;
    }

    public void speak(String text) {
        String[] textWords  = text.split(" ");
        ArrayList<Sound> spokenWords = new ArrayList<Sound>();

        for(String word: textWords) {
            this.words.add(Gdx.audio.newSound(Gdx.files.internal("speech/" + word + ".mp3")));
        }
    }

    public void update() {
        if(TimeUtils.millis() - lastSpokenTime > speechThreshold) {
            if(!words.isEmpty()) {
                Sound current = words.pop();
                current.play();
            }
            lastSpokenTime = TimeUtils.millis();
        }
    }
}

