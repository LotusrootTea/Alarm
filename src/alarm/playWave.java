/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alarm;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.text.Position;

/**
 *
 * @author vinnu
 */
public class playWave extends Thread{
    private static SourceDataLine auline;
    private final int EXTERNAL_BUFFER_SIZE = 524288;
    private byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
    private int nBytesRead = 0;
    private AudioInputStream audioInputStream;
    private String filename;
    private Position curPosition;
    private BooleanControl bol;
    private static boolean playing = false;
    enum Position
    {
        LEFT, RIGHT, NORMAL
    };
    public playWave(String wavfile)
    {
        filename = wavfile;
        curPosition = Position.NORMAL;
    }
    public void mute()
    {
    	bol.setValue(true);
    }
    public void unmute()
    {
    	bol.setValue(false);
    }
    public void stopPlaying()
    {
    	this.interrupt();
    	playing = false;
    	mute();
    	auline.stop();
    	return;
    }
    public void run()
    {
		File soundFile = new File(filename);
		if (!soundFile.exists())
	    {
			System.err.println("Wave file not found: " + filename);
			return;
		}
		try
		{
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		}
		catch (UnsupportedAudioFileException e1)
		{
			e1.printStackTrace();
			return;
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
			return;
		}
		AudioFormat format = audioInputStream.getFormat();
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		try
		{
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
			return;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
		if (auline.isControlSupported(FloatControl.Type.PAN))
		{
			FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
			if (curPosition == Position.RIGHT)
				pan.setValue(1.0f);
			else if (curPosition == Position.LEFT)
				pan.setValue(-1.0f);
		}
		if ( auline.isControlSupported(BooleanControl.Type.MUTE) )
		{
			bol = (BooleanControl) auline.getControl(BooleanControl.Type.MUTE);
			if (bol.getValue())
				bol.setValue(false);
		}
		if ( !isPlaying() )
			play();
    }
    public boolean isPlaying()
	{
		return playing;
	}
    private void play()
    {
    			auline.start();
			    try
		        {
			    	playing = true;
			    	//used for calling Alarm_Clock.adjustTime(h,m,s,false)
		        	//String s = "";
		        	//String m = "";
		        	//String h = "";
		        	//boolean fal = false;
		        	//loop while the auline is open and has data to be read
		            while (nBytesRead != -1)
		            {
		                //Alarm_Clock.adjustTime(s, m, h, fal);
		            	nBytesRead = audioInputStream.read(abData, 0, abData.length);
		                //Alarm_Clock.adjustTime(s, m, h, fal);
		                if (nBytesRead >= 0)
		                    auline.write(abData, 0, nBytesRead);
		                //Alarm_Clock.adjustTime(s, m, h, fal);
		            }
		        } catch (IOException e)
		        {
		            e.printStackTrace();
		            return;
		        } finally
		        {
		        	//drain and close the auline
		        	playing = false;
		            auline.drain();
		            auline.close();
		        }
    }
    
}
