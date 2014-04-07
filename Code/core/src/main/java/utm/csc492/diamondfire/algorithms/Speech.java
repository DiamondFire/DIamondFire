package utm.csc492.diamondfire.algorithms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.TimeUtils;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * Created by yasith on 2014-03-12.
 */
public class Speech {

    private static Speech instance;

    private LinkedList<Sound> words;
    private LinkedList<Double> lengths;
    private long lastSpokenTime = 0;
    private long speechThreshold;

    private Speech() {
        words = new LinkedList<Sound>();
        lengths = new LinkedList<Double>();
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
        Double length;

        for(String word: textWords) {
            try {
                length = new Double(getLength("speech/wav/" + word + ".wav") * 880);
            } catch (Exception e) {
                length = 0.00;
                e.printStackTrace();
            }
            this.words.add(Gdx.audio.newSound(Gdx.files.internal("speech/wav/" + word + ".wav")));
            this.lengths.add(length);
        }
    }

    public void update() {
        if(TimeUtils.millis() - lastSpokenTime > speechThreshold) {

            if(!words.isEmpty()) {
                speechThreshold = lengths.pop().longValue();
                Sound current = words.pop();
                current.play();
            }
            lastSpokenTime = TimeUtils.millis();
        }
    }

        public static double getLength(String path) throws Exception {

            AudioInputStream stream;
            stream = AudioSystem.getAudioInputStream(new File(path).toURI().toURL());
            AudioFormat format = stream.getFormat();

            // must be converted to PCM_SIGNED
            if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                        format.getSampleRate(),
                        format.getSampleSizeInBits() * 2,
                        format.getChannels(),
                        format.getFrameSize() * 2,
                        format.getFrameRate(), true); // big endian
                stream = AudioSystem.getAudioInputStream(format, stream);
            }

            DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat(),
                    ((int) stream.getFrameLength() * format.getFrameSize()));

            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.close();

            return clip.getBufferSize()
                    / (clip.getFormat().getFrameSize() * clip.getFormat().getFrameRate());
        }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}

