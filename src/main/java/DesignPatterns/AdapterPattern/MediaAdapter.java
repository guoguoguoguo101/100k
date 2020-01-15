package DesignPatterns.AdapterPattern;

public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType){
        if (audioType.equals("vlc")){
            advancedMusicPlayer = new VlcPlayer();
        }else if (audioType.equals("mp4")){
            advancedMusicPlayer = new Mp4Player();
        }


    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equals("vlc")){
            advancedMusicPlayer.playVlc(fileName);
        }else if (audioType.equals("mp4")){
            advancedMusicPlayer.playMp4(fileName);
        }

    }
}
