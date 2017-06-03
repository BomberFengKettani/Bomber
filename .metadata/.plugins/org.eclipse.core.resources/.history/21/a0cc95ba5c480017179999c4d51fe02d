package Model;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Son{
	
	public static synchronized void jouerSon(String nom){
		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Son.class.getResourceAsStream(nom)));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength());
			clip.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}
