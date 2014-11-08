package src;

import javax.sound.sampled.*;


public class AudioPlayer {
public static Clip clip;
/**
 * Constructor of AudioPlayer, this handles the mp3 streaming audio.
 * @param s name of mp3 file
 */
	 public AudioPlayer (String s){
		 try{
			 AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
			 
			 AudioFormat audioFormat = ais.getFormat();
			// System.out.println(audioFormat.getSampleRate());
			
			 AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, audioFormat.getSampleRate(), 16, 
					 audioFormat.getChannels(), audioFormat.getChannels()*2, audioFormat.getSampleRate(), false );
			 
			 AudioInputStream adiosystem = AudioSystem.getAudioInputStream(decodeFormat, ais);
			 clip = AudioSystem.getClip();
			 clip.open(adiosystem);  // data structure that hold the audio
		 }
		 
	
		 
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 
	 }
	 /**
	  * Starts the audioplayer.
	  */
	 public void play (){
		 if (clip == null )  return;    // si el file es muy large me aparece null 
		 stop();
		 clip.setFramePosition(0);  // no se que esto hace 
		 clip.start();
		 
	 }
/**
 *  Stops the audioStream.
 */
	public void stop() {
		// TODO Auto-generated method stub
		if(clip.isRunning()){
			clip.stop();}
	}
	/**
	 * 
	 */
	public void close(){
		stop();
		clip.close();
	}
}
