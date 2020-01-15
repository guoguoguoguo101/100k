package DesignPatterns.AdapterPattern;

public class AudioPlayer implements MediaPlayer {
    MediaAdapter adapter;
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equals("mp3")){
            System.out.println("Playing mp3 file. Name: "+ fileName);
        }else if (audioType.equals("vlc")||audioType.equals("mp4")){
            adapter = new MediaAdapter(audioType);
            adapter.play(audioType,fileName);
        }else {
            System.out.println("Invalid media. "+
                    audioType + " format not supported");
        }
    }
}
