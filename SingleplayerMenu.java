import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class SingleplayerMenu extends JFrame {
    
    private ButtonGroup buttonGroup1;
    private ButtonGroup buttonGroup2;
    private JButton jButton1;
    private JButton jButton2;
    private JCheckBox jCheckBox1;
    private JCheckBox jCheckBox2;
    private JCheckBox jCheckBox3;
    private JCheckBox jCheckBox4;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JRadioButton jRadioButton3;
    private JRadioButton jRadioButton4;
    private JRadioButton jRadioButton5;
    private JRadioButton jRadioButton6;
    private JSlider jSlider1;
    private JTextField jTextField1;
    
    private boolean changedByTextField;
    
    public SingleplayerMenu() {
        setLayout(null);
        setSize(822, 756);
        setVisible(true);
        setTitle("RefleXOX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        buttonGroup1 = new ButtonGroup();
        buttonGroup2 = new ButtonGroup();
        jButton1 = new JButton("< Back");
        jButton2 = new JButton("Start Game");
        jCheckBox1 = new JCheckBox("Rotating Board (upcoming)");
        jCheckBox2 = new JCheckBox("Flipping Board (upcoming)");
        jCheckBox3 = new JCheckBox("Unknown Symbols");
        jCheckBox4 = new JCheckBox("Hidden Symbols");
        jRadioButton1 = new JRadioButton("Short (6 Rounds)");
        jRadioButton2 = new JRadioButton("Classic (12 Rounds)");
        jRadioButton3 = new JRadioButton("Long (20 Rounds)");
        jRadioButton4 = new JRadioButton("Easy");
        jRadioButton5 = new JRadioButton("Medium");
        jRadioButton6 = new JRadioButton("Hard");
        jLabel1 = new JLabel("Singleplayer Practice Settings");
        jLabel2 = new JLabel("Game Modes");
        jLabel3 = new JLabel("Game Duration");  
        jLabel4 = new JLabel("AI Difficulty");
        jLabel5 = new JLabel("AI Decision Time");
        jLabel6 = new JLabel("ms");
        
        jSlider1 = new JSlider();
        
        jTextField1 = new JTextField("1500");
        
        getContentPane().add(jButton1);
        getContentPane().add(jButton2);
        getContentPane().add(jCheckBox1);
        getContentPane().add(jCheckBox2);
        getContentPane().add(jCheckBox3);
        getContentPane().add(jCheckBox4);
        getContentPane().add(jLabel1);
        getContentPane().add(jLabel2);
        getContentPane().add(jLabel3);
        getContentPane().add(jLabel4);
        getContentPane().add(jLabel5);
        getContentPane().add(jLabel6);
        getContentPane().add(jRadioButton1);
        getContentPane().add(jRadioButton2);
        getContentPane().add(jRadioButton3);
        getContentPane().add(jRadioButton4);
        getContentPane().add(jRadioButton5);
        getContentPane().add(jRadioButton6);
        getContentPane().add(jSlider1);
        getContentPane().add(jTextField1);
        
        jSlider1.setPaintTicks(true);
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        buttonGroup2.add(jRadioButton4);
        buttonGroup2.add(jRadioButton5);
        buttonGroup2.add(jRadioButton6);
        jRadioButton2.setSelected(true);
        jRadioButton5.setSelected(true);
        
        Font bigFont = new Font("Tahoma", 0, 48);
        Font normalFont = new Font("Tahoma", 0, 24);
        Font smallFont = new Font("Tahoma", 0, 18);
        Font backFont = new Font("Tahoma", 0, 14);
        Font titleFont = new Font("Tahoma", 1, 24);
        jButton1.setFont(backFont);
        jButton2.setFont(normalFont);
        jCheckBox1.setFont(normalFont);
        jCheckBox2.setFont(normalFont);
        jCheckBox3.setFont(normalFont);
        jCheckBox4.setFont(normalFont);
        jLabel1.setFont(bigFont);
        jLabel2.setFont(titleFont);
        jLabel3.setFont(titleFont);
        jLabel4.setFont(titleFont);
        jLabel5.setFont(titleFont);
        jLabel6.setFont(smallFont);
        jRadioButton1.setFont(normalFont);
        jRadioButton2.setFont(normalFont);
        jRadioButton3.setFont(normalFont);
        jRadioButton4.setFont(normalFont);
        jRadioButton5.setFont(normalFont);
        jRadioButton6.setFont(normalFont);
        jTextField1.setFont(smallFont);
        
        jButton1.setBounds(10, 10, 81, 29);
        jButton2.setBounds(480, 480, 190, 70);
        jCheckBox1.setBounds(70, 190, 311, 37);
        jCheckBox2.setBounds(70, 240, 304, 37);
        jCheckBox3.setBounds(70, 290, 225, 37);
        jCheckBox4.setBounds(70, 340, 201, 37);
        jLabel1.setBounds(110, 20, 621, 58);
        jLabel2.setBounds(70, 150, 153, 29);
        jLabel3.setBounds(70, 420, 180, 29);
        jLabel4.setBounds(510, 150, 144, 29);
        jLabel5.setBounds(490, 331, 216, 29);
        jLabel6.setBounds(710, 375, 23, 22);
        jRadioButton1.setBounds(70, 460, 213, 37);
        jRadioButton2.setBounds(70, 500, 241, 37);
        jRadioButton3.setBounds(70, 540, 221, 37);
        jRadioButton4.setBounds(430, 180, 81, 37);
        jRadioButton5.setBounds(540, 180, 115, 37);
        jRadioButton6.setBounds(680, 180, 83, 37);
        jSlider1.setBounds(440, 373, 200, 41);
        jTextField1.setBounds(650, 370, 60, 30);
        
        jCheckBox1.setEnabled(false);
        jCheckBox2.setEnabled(false);
        jButton1.addActionListener(new jButton1ActionListener());
        jButton2.addActionListener(new jButton2ActionListener());
        jSlider1.addChangeListener(new jSlider1ChangeListener());
        jTextField1.addFocusListener(new jTextField1FocusListener());
        jTextField1.addKeyListener(new jTextField1KeyListener());
        changedByTextField = false;
        
        try {
            setIconImage(ImageIO.read(SingleplayerMenu.class.getResource("icon.png")));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occured while loading the source files...",
                                          "IOException", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    class jButton1ActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            goBack();
        }
    }
    
    class jButton2ActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            startTheGame();
        }
    }
    
    class jSlider1ChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent event) {
            if (!changedByTextField)
                jTextField1.setText("" + (100 * ((29 * jSlider1.getValue() + 100) / 100)));
            changedByTextField = false;
        }
    }
    
    class jTextField1FocusListener implements FocusListener {
        public void focusLost(FocusEvent event) {
            String text = jTextField1.getText().trim();
            int value;
            try {
                value = Integer.parseInt(text);  
            }
            catch(NumberFormatException e){
                value = 1500;
            }
            if (value <= 100) {
                value = 100;
                jSlider1.setValue(0);
            }
            if (value > 10000) {
                value = 10000;
                jSlider1.setValue(100);
            }
            value = 100 * (value / 100);
            jTextField1.setText("" + value);
            
            changedByTextField = true;
            jSlider1.setValue((value - 100) / 29);
        }
        public void focusGained(FocusEvent event) {}
    }
    
    class jTextField1KeyListener implements KeyListener {
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_ENTER)
                jButton2.requestFocus();
        }
        public void keyReleased(KeyEvent event) {}
        public void keyTyped(KeyEvent event) {}
    }
    
    public void startTheGame() {
        int duration = 12;
        if (jRadioButton1.isSelected())
            duration = 6;
        else if (jRadioButton2.isSelected())
            duration = 12;
        else if (jRadioButton3.isSelected())
            duration = 20;
        boolean rotatingBoard = jCheckBox1.isSelected();
        boolean flippingBoard = jCheckBox2.isSelected();
        boolean unknownSymbols = jCheckBox3.isSelected();
        boolean hiddenSymbols = jCheckBox4.isSelected();
        boolean[] modes = {rotatingBoard, flippingBoard, unknownSymbols, hiddenSymbols};
        SingleplayerGame singleplayerGame = new SingleplayerGame(duration, modes);
        singleplayerGame.setLocationRelativeTo(this);
        
        singleplayerGame.decisionTime = (Integer.parseInt(jTextField1.getText()) / 1000.0);
        if (jRadioButton4.isSelected())
            singleplayerGame.difficulty = AIMoves.EASY;
        else if (jRadioButton5.isSelected())
            singleplayerGame.difficulty = AIMoves.MEDIUM;
        else if (jRadioButton6.isSelected())
            singleplayerGame.difficulty = AIMoves.HARD;
        
        this.setVisible(false);
    }
    
    public void goBack() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setLocationRelativeTo(this);
        this.setVisible(false);
        this.dispose();
    }
    
    public static void main(String[] args) {
        SingleplayerMenu singleplayerMenu = new SingleplayerMenu();
    }
}