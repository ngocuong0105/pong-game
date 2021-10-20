import javax.sound.sampled.*;
import java.io.File;

public class PongGame {
    public static void main(String[] args) throws Exception {
        // music
        File file = new File("context/Blue Dream - Cheel.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        // game
        new GameFrame();
        
    }
}
