package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioPlayer {
    private Clip clip;
    private String filePath;
    private AudioInputStream ais;

    public AudioPlayer(String filePath) {
        try {
            ais = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip =AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void playSound() {
        clip.start();
    }

    public void stopSound() {
        clip.stop();
        clip.close();
    }
}
