/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alarm;

import javazoom.jl.player.Player;

/**
 *
 * @author vinnu
 */
public class playMP3 {
    protected Player player = null;
    public void stop()
	{
		if ( player != null )
		{
			player.close();
			player = null;
		}
	}
    
}
