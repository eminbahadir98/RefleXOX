import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AboutAndCredits extends JFrame {
    public AboutAndCredits() {
        setLayout(null);
        setSize(822, 756);
        setVisible(true);
        setTitle("RefleXOX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AboutDrawerComponent myDrawer = new AboutDrawerComponent();
        add(myDrawer);
        myDrawer.setBounds(-10, -10, 810, 710);
        MouseListener listener = new MainMouseListener();
        myDrawer.addMouseListener(listener);
    }
    
    class AboutDrawerComponent extends JComponent {
        BufferedImage image;
        public AboutDrawerComponent() {
            try {
                image = ImageIO.read(AboutAndCredits.class.getResource("aboutui.png"));
                setIconImage(ImageIO.read(AboutAndCredits.class.getResource("icon.png")));
            } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occured while loading the source files...",
                                          "IOException", JOptionPane.ERROR_MESSAGE);
            }
        }
        public void paintComponent(Graphics g) {
            g.drawImage(image, 10, 10, 800, 700, Color.WHITE, this);
        }
    }
    
    class MainMouseListener implements MouseListener {
        public void mousePressed(MouseEvent event) {
            int x = event.getX() - 10;
            int y = event.getY() - 10;
            
            if ((228 <= x && x <= 575) && (441 <= y && y <= 509)) {
                try{
                    Desktop.getDesktop().browse(new URI("https://goo.gl/forms/gZNwCgEFickV7AxP2"));
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(AboutAndCredits.this, "An error occured while opening the browser...",
                                                  "IOException", JOptionPane.ERROR_MESSAGE);
                } catch (URISyntaxException e) {
                    JOptionPane.showMessageDialog(AboutAndCredits.this, "An error occured while opening the browser...",
                                                  "URISyntaxException", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
                goBack();
        }
        public void mouseReleased(MouseEvent event) {}
        public void mouseClicked(MouseEvent event) {}
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
    }
    
    public void goBack() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setLocationRelativeTo(this);
        this.setVisible(false);
        this.dispose();
    }
    
    public static void main(String[]args) {
        AboutAndCredits aboutAndCredits = new AboutAndCredits();
    }
}