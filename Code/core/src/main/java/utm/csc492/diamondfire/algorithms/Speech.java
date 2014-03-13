package utm.csc492.diamondfire.algorithms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;

/**
 * Created by yasith on 2014-03-12.
 */
public class Speech {

    public static void speak(String text) {
        String[] textWords  = text.split(" ");
        ArrayList<Sound> spokenWords = new ArrayList<Sound>();

        for(String word: textWords) {
            spokenWords.add(Gdx.audio.newSound(Gdx.files.internal("speech/" + word + ".mp3")));
        }

        for(Sound word: spokenWords) {
            SpeechTask task = new SpeechTask(word);
            Thread speechThread = new Thread(task);
            speechThread.start();
            try {
                speechThread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class SpeechTask implements Runnable {

    private Sound sound;

    @Override
    public void run() {
        sound.play();
    }

    public SpeechTask(Sound sound) {
        this.sound = sound;
    }
}

