package Background_Creator_Pkg;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//--------------------------------------MUSIC CLASS----------------------------------//
class Music{
	private
	Clip clip;
	boolean playing=false; //Prevent additional songs playing at the same time
	public void playMusic(String songName){ //Plays Audio
		if (playing==true) {
			clip.stop();
			playing=false;
		}
		try {
	        File file = new File(songName);
	        if (file.exists()) {
	            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
	            clip = AudioSystem.getClip();
	            clip.open(sound);
	            clip.start();
	            playing=true;
	        }else{throw new RuntimeException("Sound: file not found");}
	    }catch (MalformedURLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Sound: Malformed URL: " + e);
	    }catch (UnsupportedAudioFileException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Sound: Unsupported Audio File: " + e);
	    }catch (IOException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Sound: Input/Output Error: " + e);
	    }catch (LineUnavailableException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);}
	}
	public void stopMusic(){ //Stops Audio
		clip.close();
	}
	public void loopMusic(){ //Loops Audio
		if (playing==true){
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	public void loopStop() { //Stops Loop
		clip.loop(0);
	}
}

