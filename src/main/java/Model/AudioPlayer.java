package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioPlayer {
    private Clip clip;
    private AudioInputStream audioInputStream;

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
