package alarm;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Alarm_Clock {

	private static Alarm anAlarm;
    private static boolean military = true;
    private static String fileLocation = "default_alarms" + File.separator + "newalarm1.wav";
    private static final JFrame backFrame = new JFrame("Alarm Clock");
    protected static final JLabel time = new JLabel("00:00:00");
    protected static JButton set = new JButton("Set Alarm");
    private static JButton snooze = new JButton("Snooze");
	public static void main(String[] args) {
		backFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel backPane = new JPanel();
    	JPanel timeHold = new JPanel();
    	JPanel bottom = new JPanel();
    	JPanel vBottom = new JPanel();
    	backPane.setLayout(new BoxLayout(backPane, BoxLayout.Y_AXIS));
    	bottom.setLayout(new GridLayout(1,2,5,0));
    	vBottom.setLayout(new GridLayout(1,1,3,3));
    	JButton off = new JButton("Turn Off");
    	off.setMnemonic(KeyEvent.VK_O);
		set.setMnemonic(KeyEvent.VK_A);
		snooze.setMnemonic(KeyEvent.VK_S);
		time.setFont(new Font("DejaVu Sans", Font.BOLD, 36));
                timeHold.add(time);
    	//set up menu bar and menu items
    	JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu;
		JMenu helpMenu;
		JMenuItem helpMenuItem;
		JMenuItem fileMenuItem;
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		//set Mnemonics
		fileMenu.setMnemonic('F');
		helpMenu.setMnemonic('H');
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		helpMenuItem = new JMenuItem("About");
                helpMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(backFrame,
			"you can mail the developer at"
                                        +"sampath.surineni@gmail.com");}
		});
                
                 fileMenuItem = new JMenuItem("Cancel Alarm");
        fileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backFrame.setTitle("Alarm Clock");
                anAlarm = new Alarm();
            }
        });
        fileMenu.add(fileMenuItem);
        JMenuItem toggleFormat = new JMenuItem("Toggle 'Military time'");
        toggleFormat.setMnemonic('T');
        toggleFormat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                military = !military;
            }
        });
        fileMenuItem = new JMenuItem("Set Alarm Ringer");
        fileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //find "ringers" .wav files to use
                File randomFile;
                final JFileChooser chooser = new JFileChooser();
                try {
                    //create a file to get current directory and navigate to the default_alarms page
                    randomFile = new File(new File(".").getCanonicalPath() + File.separator + "default_alarms");
                    //here File it is taking string path name
                    chooser.setCurrentDirectory(randomFile);
                    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                } catch (Exception ex) {
                    //catch if you can't make the file or something went wrong
                    System.err.println("Could not create file at current path");
                    ex.printStackTrace();
                }
                int number = chooser.showOpenDialog(null);
                if (number == JFileChooser.APPROVE_OPTION) {
                    //if you select a file
                    File loadMe = chooser.getSelectedFile();
                    //set fileLocation to that file's location if it is a valid format
                    if (loadMe.getName().contains(".wav") || loadMe.getName().contains(".mp3")) {
                        fileLocation = loadMe.getPath();
                    } else {
                        //display a dialog saying that we can't open that file if it is not a .wav
                        JOptionPane.showMessageDialog(backFrame, "Please select a .wav or .mp3 file to set as your alarm.");
                    }
                    //System.out.println(loadMe.getPath());
                }
            }
        });

        fileMenu.add(fileMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(toggleFormat);
        fileMenu.addSeparator();
        fileMenuItem = new JMenuItem("Exit Program");
        fileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //if you click Exit program... the program exits
                System.exit(0);
            }
        });
        backPane.add(timeHold);
        fileMenu.add(fileMenuItem);
        bottom.add(set);
        bottom.add(off);
        vBottom.add(snooze);
        backPane.add(timeHold);
        backPane.add(bottom);
        backPane.add(vBottom);
        backFrame.add(backPane);
        //add the JMenuBar so you can use it
        backFrame.setJMenuBar(menuBar);
	backFrame.setPreferredSize(new Dimension(290, 175));
    	//pack it up so it's actually that size
    	backFrame.pack();
    	//set location to the middle of the screen
    	backFrame.setLocationRelativeTo(null);
    	//trying to set look and feel

    	//make it all visible so it's interactable
    	backFrame.setVisible(true);
    	backFrame.setResizable(false);
        set.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{final JFrame back = new JFrame("Set Alarm");
    			//make it so the jframe is disposed on close instead of ending program
    			back.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    			JPanel backPanel = new JPanel();
    			JPanel top = new JPanel();
    			JPanel bottom = new JPanel();
    			backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
    			final JLabel info = new JLabel("Input the alarm time below (12 or 24 hour format)");
    			JButton submit = new JButton("Set Alarm");
    			JButton cancel = new JButton("Cancel");
    			final JTextField input = new JTextField("");
				bottom.setLayout(new FlowLayout(FlowLayout.CENTER));
				top.setLayout(new GridLayout(2,1,0,5));
				//add everything
    			top.add(info);
    			top.add(input);
    			bottom.add(submit);
    			bottom.add(cancel);
    			backPanel.add(top);
    			backPanel.add(bottom);
    			back.add(backPanel);
    			//grab location of the main JFrame
    			Point p = new Point(backFrame.getLocationOnScreen());
    			back.pack();
    			//set this new window's location to be on top of the old window's location
    			back.setLocation(p);
    			back.setResizable(false);
    			back.setVisible(true);
    			//set Mnemonic
    			submit.setMnemonic(KeyEvent.VK_S);
    			cancel.setMnemonic(KeyEvent.VK_C);
				//end set up set alarm window
				if (alarmGoOff)
				{
					info.setText("Please stop the alarm that is going off before you try to set a new one");
					back.pack();
					return;
				}
				//add action listeners
				cancel.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						back.dispose();
					}
				});
				submit.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent a)
					{

						if (!setAlarm(input.getText()) == true)
						{
							//if they messed up on entering it, display this new text reminding them to put it in the HH:MM format
							info.setText("Please make sure you enter a valid time. (i.e. 13:00pm is not a valid time)");
							//re pack to make sure the size is right for the new text
							back.pack();
						}
						else
						{
							//when they get it right, dispose of this window, we don't need it anymore
							back.dispose();
						}
					}
				});
    		}
    	});

	}
        private static boolean setAlarm(String alarmTime)
 	{
 		boolean ampm = false;
 		String amorpm = "am";
 		String h = "";
 		String m = "";
 		boolean colon = false;
 		char[] ar = alarmTime.toCharArray();
 		//get the character array and search for what we want
 		for(int i = 0; i<ar.length; i+=1)
 		{
 			//System.out.println(Character.isDigit(ar[i]) + ", character " + ar[i] + " is digit" );
 			if ( ar[i] == ':')
 			{
 				//flip flag for colon
 				colon = true;
 				continue;
 			}
 			if ( Character.isDigit(ar[i]) && !colon )
 			{
 				if ( Character.isDigit(ar[i+1]) )
 				{
 					h = ar[i] + "" +  ar[i+1];
 					i += 1;
 				}
 				else
 				{
 					h = ar[i] + "";
 				}
 				//System.out.println(h);
 				continue;
 			}
 			if ( Character.isDigit(ar[i]) && colon)
 			{
 				if ( Character.isDigit(ar[i+1]) )
 				{
 					m = ar[i] + "" +  ar[i+1];
 					i += 1;
 				}
 				else
 				{
 					m = ar[i] + "";
 				}
 				//System.out.println(m);
 				continue;
 			}
 			if ( ar[i] == 'a' || ar[i] == 'A' || ar[i] == 'p' || ar[i] == 'P')
 			{
 				ampm = true;
 				amorpm = "" + ar[i] + ar[i+1];
 				break;
 			}
 		}
 		//if we don't have a minute given, then we set it to be 0 (i.e. they input 5am)
 		int minute;
 		int hour;
 		try
 		{
 			minute = Integer.parseInt(m);
 		}
 		catch (Exception e)
 		{
 			minute = 00;
 		}
 		try
 		{
 			hour = Integer.parseInt(h);
 		}
 		catch (Exception e)
 		{
 			hour = 00;
 		}
 		if ( hour > 12 && ampm )
 		{
			return false;
 		}
 		if ( hour > 23 )
 		{
 			return false;
 		}

 	//	System.out.println(hour + " hours, " + minute + " minutes, " + ampm + " am or pm was read, " + amorpm);

 		anAlarm = new Alarm(hour, minute, ampm, amorpm);
 		backFrame.setTitle("Alarm: " + anAlarm.getFormatAlarm());
 		System.out.println(anAlarm.getAlarmTime() + " " + anAlarm.getFormatAlarm());
 		return true;

 	}

}
