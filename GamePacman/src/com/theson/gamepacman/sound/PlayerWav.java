package com.theson.gamepacman.sound;


import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class PlayerWav {

    private Clip clip;

    public PlayerWav(String fileName) {
        try {
            URL url = getClass().getResource("/res/sounds/" + fileName);
            clip = AudioSystem.getClip();
            AudioInputStream input = AudioSystem.getAudioInputStream(url);
            clip.open(input);
        } catch (LineUnavailableException
                | UnsupportedAudioFileException
                | IOException e) {
            e.printStackTrace();
        }
    }


    public void play() {
        if (clip.isOpen() && !clip.isRunning()) {
            clip.start();
        }
    }

    public void stop() {
        clip.stop();
    }

    public void loop(int count){
        clip.loop(count);
    }

    public boolean running(){
        return clip.isRunning();
    }
}
