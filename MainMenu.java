import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainMenu extends JFrame {
    public MainMenu() {
        setLayout(null);
        setSize(822, 756);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("RefleXOX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainDrawerComponent myDrawer = new MainDrawerComponent();
        add(myDrawer);
        myDrawer.setBounds(-10, -10, 810, 710);
        MouseListener listener = new MainMouseListener();
        myDrawer.addMouseListener(listener);
    }
    
    class MainDrawerComponent extends JComponent {
        BufferedImage image;
        public MainDrawerComponent() {
            try {
                image = ImageIO.read(MainMenu.class.getResource("mainui.png"));
                setIconImage(ImageIO.read(MainMenu.class.getResource("icon.png")));
            } catch (Exception e) {}
        }
        public void paintComponent(Graphics g) {
            g.drawImage(image, 10, 10, 800, 700, Color.WHITE, this);
        }
    }
    
    class MainMouseListener implements MouseListener {
        public void mousePressed(MouseEvent event) {
            int x = event.getX() - 10;
            int y = event.getY() - 10;
            
            if ((170 <= x && x <= 606) && (172 <= y && y <= 284))
                startSingleplayerGame();
            if ((170 <= x && x <= 606) && (333 <= y && y <= 446))
                startMultiplayerGame();
            if ((170 <= x && x <= 606) && (494 <= y && y <= 606))
                showAboutAndCredits();
        }
        public void mouseReleased(MouseEvent event) {}
        public void mouseClicked(MouseEvent event) {}
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
    }
    
    public void startSingleplayerGame() {
        SingleplayerMenu singleplayerMenu = new SingleplayerMenu();
        singleplayerMenu.setLocationRelativeTo(this);
        this.setVisible(false);
        this.dispose();
    }
    
    public void startMultiplayerGame() {
        JOptionPane.showMessageDialog(this, "Upcoming on next release...",
                                      "We're working on it...", JOptionPane.INFORMATION_MESSAGE);
        /*
        // a JOptionPane to get the user's name.
        this.setVisible(false);
        this.dispose();
        */
    }
    
    public void showAboutAndCredits() {
        AboutAndCredits aboutAndCredits = new AboutAndCredits();
        aboutAndCredits.setLocationRelativeTo(this);
        this.setVisible(false);
        this.dispose();
    }
    
    public static void main(String[]args) {
        MainMenu mainMenu = new MainMenu();
    }
}