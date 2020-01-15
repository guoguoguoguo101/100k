package DesignPatterns.AdapterPattern;

public class AdapterPatternDemo {
    public static void main(String[] args) {

        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp4","sorry");
    }
}
