import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DrawerComponent extends JComponent {
    
    public static Font statusFont;
    public static Font smallStatusFont;
    public static Font meterFont;
    
    public XOXGame xox;
    
    public ArrayList<Integer> winnerSquares;
    
    public boolean hiddenSymbols;
    
    public boolean turn;
    public boolean waitingTime;
    public boolean waitingForNext;
    public boolean blink;
    
    public BufferedImage boardImage;
    public BufferedImage uiImage;
    public BufferedImage clearBoardImage;
    public BufferedImage xImage;
    public BufferedImage oImage;
    public BufferedImage xImageOriginal;
    public BufferedImage oImageOriginal;
    public BufferedImage unknownImage;
    
    public double fakeYouTime;
    public double realYouTime;
    public double oppTime;
    
    public int currentRound;
    public int totalRound;
    public int playerSymbol;
    public int opponentSymbol;
    public int youGame;
    public int oppGame;
    public int youPoint;
    public int oppPoint;
    public int unhiddenSquare;
    
    public String statusMessage;
    
    public DrawerComponent() {
        try {
            uiImage = ImageIO.read(DrawerComponent.class.getResource("boardui.png"));
            boardImage = ImageIO.read(DrawerComponent.class.getResource("board.png"));
            clearBoardImage = ImageIO.read(DrawerComponent.class.getResource("board.png"));
            
            xImageOriginal = ImageIO.read(DrawerComponent.class.getResource("x.png"));
            oImageOriginal = ImageIO.read(DrawerComponent.class.getResource("o.png"));
            unknownImage = ImageIO.read(DrawerComponent.class.getResource("unknown.png"));
            
            xImage = xImageOriginal;
            oImage = oImageOriginal;
            
            statusFont = new Font("Tahoma", 0, 40);
            meterFont = new Font("Tahoma", 0, 28);
            smallStatusFont = new Font("Tahoma", 0, 25);
        }catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "An error occured while loading the source files...",
                                          "IOException", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void paintComponent(Graphics gDraw) {
        Graphics2D g = (Graphics2D) gDraw;
        g.drawImage(uiImage, 10, 10, 800, 700, Color.WHITE, this);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g.setColor(Color.BLACK);
       
        g.setFont(statusFont);
        if (statusMessage.length() > 30)
            g.setFont(smallStatusFont);
        g.drawString(statusMessage, 217, 656);
        
        g.setFont(meterFont);
        g.drawString(currentRound + " / " + totalRound, 110, 81);
        g.drawString(String.format("%.3f", fakeYouTime), 93, 208);
        g.drawString(String.format("%.3f", oppTime), 93, 246);
        g.drawString("" + youGame, 119, 398);
        g.drawString("" + oppGame, 119, 436);
        g.drawString("" + youPoint, 119, 588);
        g.drawString("" + oppPoint, 119, 626);
        
        Graphics2D bImage = boardImage.createGraphics();
        
        drawSymbols(bImage);
        
        if (waitingForNext)
            blinkWinnerSquares(bImage, winnerSquares);
        
        if (!waitingTime)
            if ((turn && playerSymbol == XOXGame.X) || (!turn && opponentSymbol == XOXGame.X))
            g.drawImage(xImage, 568, 621, 60, 60, Color.WHITE, this);
        else if ((turn && playerSymbol == XOXGame.O) || (!turn && opponentSymbol == XOXGame.O))
            g.drawImage(oImage, 568, 621, 60, 60, Color.WHITE, this);
        
        gDraw.drawImage(boardImage, 226, 25, 570, 570, Color.WHITE, this);
    }
    
    public void drawSymbols(Graphics2D bImage) {
        int[] board = xox.getBoard();
        
        if      (board[0] == XOXGame.X) bImage.drawImage(xImage, 11, 11, 160, 160, Color.WHITE, this);
        else if (board[0] == XOXGame.O) bImage.drawImage(oImage, 11, 11, 160, 160, Color.WHITE, this);
        
        if      (board[1] == XOXGame.X) bImage.drawImage(xImage, 205, 11, 160, 160, Color.WHITE, this);
        else if (board[1] == XOXGame.O) bImage.drawImage(oImage, 205, 11, 160, 160, Color.WHITE, this);
        
        if      (board[2] == XOXGame.X) bImage.drawImage(xImage, 399, 11, 160, 160, Color.WHITE, this);
        else if (board[2] == XOXGame.O) bImage.drawImage(oImage, 399, 11, 160, 160, Color.WHITE, this);
        
        if      (board[3] == XOXGame.X) bImage.drawImage(xImage, 11, 205, 160, 160, Color.WHITE, this);
        else if (board[3] == XOXGame.O) bImage.drawImage(oImage, 11, 205, 160, 160, Color.WHITE, this);
        
        if      (board[4] == XOXGame.X) bImage.drawImage(xImage, 205, 205, 160, 160, Color.WHITE, this);
        else if (board[4] == XOXGame.O) bImage.drawImage(oImage, 205, 205, 160, 160, Color.WHITE, this);
        
        if      (board[5] == XOXGame.X) bImage.drawImage(xImage, 399, 205, 160, 160, Color.WHITE, this);
        else if (board[5] == XOXGame.O) bImage.drawImage(oImage, 399, 205, 160, 160, Color.WHITE, this);
        
        if      (board[6] == XOXGame.X) bImage.drawImage(xImage, 11, 399, 160, 160, Color.WHITE, this);
        else if (board[6] == XOXGame.O) bImage.drawImage(oImage, 11, 399, 160, 160, Color.WHITE, this);
        
        if      (board[7] == XOXGame.X) bImage.drawImage(xImage, 205, 399, 160, 160, Color.WHITE, this);
        else if (board[7] == XOXGame.O) bImage.drawImage(oImage, 205, 399, 160, 160, Color.WHITE, this);
        
        if      (board[8] == XOXGame.X) bImage.drawImage(xImage, 399, 399, 160, 160, Color.WHITE, this);
        else if (board[8] == XOXGame.O) bImage.drawImage(oImage, 399, 399, 160, 160, Color.WHITE, this);
        
        if (hiddenSymbols && !waitingForNext){
            if (unhiddenSquare != 0) bImage.fillRect(11, 11, 160, 160);
            if (unhiddenSquare != 1) bImage.fillRect(205, 11, 160, 160);
            if (unhiddenSquare != 2) bImage.fillRect(399, 11, 160, 160);
            if (unhiddenSquare != 3) bImage.fillRect(11, 205, 160, 160);
            if (unhiddenSquare != 4) bImage.fillRect(205, 205, 160, 160);
            if (unhiddenSquare != 5) bImage.fillRect(399, 205, 160, 160);
            if (unhiddenSquare != 6) bImage.fillRect(11, 399, 160, 160);
            if (unhiddenSquare != 7) bImage.fillRect(205, 399, 160, 160);
            if (unhiddenSquare != 8) bImage.fillRect(399, 399, 160, 160);
        }
    }
    
    public void blinkWinnerSquares(Graphics2D bImage, ArrayList<Integer> winners) {
        bImage.setColor(Color.WHITE);
        if (blink) {
            if (winners.indexOf(0) != -1) bImage.fillRect(11, 11, 160, 160);
            if (winners.indexOf(1) != -1) bImage.fillRect(205, 11, 160, 160);
            if (winners.indexOf(2) != -1) bImage.fillRect(399, 11, 160, 160);
            if (winners.indexOf(3) != -1) bImage.fillRect(11, 205, 160, 160);
            if (winners.indexOf(4) != -1) bImage.fillRect(205, 205, 160, 160);
            if (winners.indexOf(5) != -1) bImage.fillRect(399, 205, 160, 160);
            if (winners.indexOf(6) != -1) bImage.fillRect(11, 399, 160, 160);
            if (winners.indexOf(7) != -1) bImage.fillRect(205, 399, 160, 160);
            if (winners.indexOf(8) != -1) bImage.fillRect(399, 399, 160, 160);
        }
    }
    
    public void clearBoard() {
        boardImage = new BufferedImage(clearBoardImage.getColorModel(), clearBoardImage.copyData(null), false, null);
    }
    
    public void makeSymbolsUnknown() {
        xImage = oImage = unknownImage;
    }
    
    public void makeSymbolsKnown() {
        xImage = xImageOriginal;
        oImage = oImageOriginal;
    }
}