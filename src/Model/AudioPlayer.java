package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * The Audioplayer class is responsible for playing sound effects
 *
 * @author Adel Mohammed Abo khamis.
 * @author Edin Jahic
 */

public class AudioPlayer {

    /**
     * Plays a sound file specified by the file path.
     * @param filePath the path of the sound file to be played.
     */
    public void playSound(String filePath) {
        Clip clip = null;

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        clip.start();
    }

}
