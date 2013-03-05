import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Alarm_Clock {

	/**
	 * @param args
	 */
	private static final JFrame backFrame=new JFrame("Alarm Clock");
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
					"To contact developer mail at sampath.surineni@gmail.com");
			}
		});
                fileMenuItem = new JMenuItem("Cancel Alarm");
		fileMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				backFrame.setTitle("Alarm Clock");
				anAlarm = new Alarm();
			}
		});

	}

}
