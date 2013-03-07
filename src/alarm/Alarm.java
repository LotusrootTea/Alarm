package alarm;

import java.io.File;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vinnu
 */
public class Alarm {

    private static String alarmTime = "-1";
    private boolean playFile;
    private String formatAlarmTime = "-1";
    private static playMP3 mp3Trigger = null;
    private static playWave trigger = new playWave("default_alarms" + File.separator + "alarm1.wav");

    public Alarm() {
        //sets default alarm time of -1
        alarmTime = "-1";
    }

    public Alarm(int h, int m, boolean ampm, String amorpm) {
        playFile = false;
        String hour = "";
        String minute = "";
        if (amorpm.equals("pm") && ampm) {
            //if it's pm
            if (h != 12) {
                h += 12;
            }
            hour = h + "";
            if (m < 10) {
                minute = "0" + m;
            } else {
                minute = m + "";
            }
            if (h > 23 || m > 59 || h < 0 || m < 0) {
                alarmTime = "-1";
            } else {
                alarmTime = hour + ":" + minute;
                formatAlarmTime = h - 12 + ":" + minute + " pm";
            }
        } else if (amorpm.equals("am") && ampm) {
            if (h < 10) {
                hour = "0" + h;
            } else {
                hour = h + "";
            }
            if (m < 10) {
                minute = "0" + m;
            } else {
                minute = m + "";
            }
            if (h > 23 || m > 59 || h < 0 || m < 0) {
                alarmTime = "-1";
            } else {
                alarmTime = hour + ":" + minute;
                formatAlarmTime = hour + ":" + minute + " am";
            }
        } else {
            if (h < 10) {
                hour = "0" + h;
            } else {
                hour = h + "";
            }
            if (m < 10) {
                minute = "0" + m;
            } else {
                minute = m + "";
            }
            alarmTime = hour + ":" + minute;
            formatAlarmTime = alarmTime;
        }

    }

    public String getFormatAlarm() {
        return formatAlarmTime;
    }

    public String getAlarmTime() {
        return alarmTime;
    }
    public void mute(boolean mute)
	{
		if (mute && mp3Trigger == null)
		{
			trigger.mute();
		}
		else if (!mute && mp3Trigger == null)
		{
			trigger.unmute();
		}
	}
    public void stopAlarm()
	{
		// Modification by Jake Rooney
		if( mp3Trigger == null )
		{
			trigger.stopPlaying();
			playFile = false;
			return;
		}
		else {
			mp3Trigger.stop();
			playFile = false;
			mp3Trigger = null;
			return;
		}
	}
}
