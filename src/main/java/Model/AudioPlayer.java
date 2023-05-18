package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * This class allows the program to play sounds. It uses Clip and AudioInputStream.
 *
 * @author Edin Jahic and Adel Mohammed Abo Khamis.
 */
public class AudioPlayer {

    /**
     * A clip object which contains audio data and can be started and stopped.
     */
    private Clip clip;

    /**
     * An input stream which obtains an audio input stream from an external audio file.
     */
    private AudioInputStream audioInputStream;

    /**
     * This method is called when a sound is supposed to play. It takes a filepath as a parameter
     * and plays the sound using the AudioInputStream and a Clip.
     *
     * @param filePath a filepath to the sound file.
     */
    public void playSound(String filePath) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip =AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception e ) {
            e.printStackTrace();
        }
        clip.start();
    }

}
