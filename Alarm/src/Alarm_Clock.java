package alarm;

import java.awt.Font;
import java.awt.GridLayout;
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

public class Alarm_Clock {

    /**
     * @param args
     */
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
        bottom.setLayout(new GridLayout(1, 2, 5, 0));
        vBottom.setLayout(new GridLayout(1, 1, 3, 3));
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
        helpMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(backFrame,
                        "To contact developer mail at sampath.surineni@gmail.com");
            }
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

    }
}
