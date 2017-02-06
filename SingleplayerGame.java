import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class SingleplayerGame extends JFrame {
    
    public static final int COUNTER_DELAY = 100;
    public static final int END_OF_ROUND_ANIMATION_DELAY = 300;
    public static final int END_OF_ROUND_BLINK_TIME = 13;

    public double decisionTime;
    
    public int difficulty;
    
    private XOXGame xox;
    
    private boolean rotatingBoard;
    private boolean flippingBoard;
    private boolean unknownSymbols;
    private boolean hiddenSymbols;
    
    private boolean roundStarted;
    private boolean quitGame;
    
    private double opponentMoveTime;
    private double opponentRoundTime;
    private double playerRoundTime;
    
    private int endOfRoundBlinkTime;
    
    private long playerStartTime;
    private long playerEndTime;
    
    private Timer opponentTimer;
    private Timer playerTimer;
    private Timer endOfRoundAnimationTimer;
    
    private DrawerComponent myDrawer = new DrawerComponent();
    
    public SingleplayerGame(int duration, boolean[] modes) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("RefleXOX");
        setSize(822, 756);
        add(myDrawer);
        myDrawer.setBounds(-10, -10, 810, 710);
        
        try {
            setIconImage(ImageIO.read(SingleplayerGame.class.getResource("icon.png")));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occured while loading the source files...",
                                          "IOException", JOptionPane.ERROR_MESSAGE);
        }
        
        ActionListener myPlayerListener = new PlayerListener();
        playerTimer = new Timer(COUNTER_DELAY, myPlayerListener);
        ActionListener myOpponentListener = new OpponentListener();
        opponentTimer = new Timer(COUNTER_DELAY, myOpponentListener);
        ActionListener myEndOfRoundAnimationListener = new EndOfRoundAnimationListener();
        endOfRoundAnimationTimer = new Timer(END_OF_ROUND_ANIMATION_DELAY, myEndOfRoundAnimationListener);
        
        MouseListener listener = new MyMouseListener();
        myDrawer.addMouseListener(listener);
        
        rotatingBoard = modes[0];
        flippingBoard = modes[1];
        unknownSymbols = modes[2];
        hiddenSymbols = modes[3];
        
        if (unknownSymbols)
            myDrawer.makeSymbolsUnknown();
        
        if (hiddenSymbols)
            myDrawer.hiddenSymbols = true;
        
        myDrawer.totalRound = duration;
        myDrawer.currentRound = 0;
        myDrawer.playerSymbol = XOXGame.X;
        myDrawer.opponentSymbol = XOXGame.O;
        quitGame = false;
        
        prepareRound();
    }
    
    class MyMouseListener implements MouseListener {
        public void mousePressed(MouseEvent event) {
            int x = event.getX() - 10;
            int y = event.getY() - 10;
            
            if ((200 <= x && x < 406) && (0 <= y && y < 205))
                attemptMove(0);
            else if ((406 <= x && x < 595) && (0 <= y && y < 205))
                attemptMove(1);
            else if ((595 <= x && x < 800) && (0 <= y && y < 205))
                attemptMove(2);
            else if ((200 <= x && x < 406) && (205 <= y && y < 394))
                attemptMove(3);
            else if ((406 <= x && x < 595) && (205 <= y && y < 394))
                attemptMove(4);
            else if ((595 <= x && x < 800) && (205 <= y && y < 394))
                attemptMove(5);
            else if ((200 <= x && x < 406) && (394 <= y && y < 600))
                attemptMove(6);
            else if ((406 <= x && x < 595) && (394 <= y && y < 600))
                attemptMove(7);
            else if ((595 <= x && x < 800) && (394 <= y && y < 600))
                attemptMove(8);

            if (quitGame)
                goBack();
            else if ((0 <= x && x < 78) && (0 <= y && y < 78))
                confirmGoingBack();
        }
        public void mouseReleased(MouseEvent event) {}
        public void mouseClicked(MouseEvent event) {}
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
    }
    
    class OpponentListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (roundStarted) {
                myDrawer.oppTime += 0.1;
                opponentRoundTime += 0.1;
            }
            opponentMoveTime += 0.1;
            myDrawer.repaint();
            if (Math.abs(opponentMoveTime - decisionTime) < 0.05) {
                opponentTimer.stop();
                opponentMove();
                roundStarted = true;
            }
        }
    }
    
    class PlayerListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            myDrawer.fakeYouTime += 0.1;
            myDrawer.repaint();
        }
    }
    
    class EndOfRoundAnimationListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            myDrawer.blink = !myDrawer.blink;
            endOfRoundBlinkTime++;
            if (endOfRoundBlinkTime >= END_OF_ROUND_BLINK_TIME) {
                endOfRoundAnimationTimer.stop();
                endOfRoundBlinkTime = 0;
                if (gameOver())
                    announceWinner();
                else
                    prepareRound();
            }
            myDrawer.repaint();
        }
    }
    
    public void prepareRound() {
        myDrawer.currentRound++;
        roundStarted = false;
        xox = new XOXGame();
        myDrawer.xox = xox;
        myDrawer.waitingTime = true;
        opponentRoundTime = 0;
        playerRoundTime = 0;
        myDrawer.waitingForNext = false;
        myDrawer.blink = false;
        myDrawer.clearBoard();
        myDrawer.unhiddenSquare = -1;
        
        if (unknownSymbols)
            myDrawer.makeSymbolsUnknown();
        
        if (myDrawer.currentRound % 2 == 1) {
            myDrawer.statusMessage = "The round will start with your first move...";
            myDrawer.turn = true;
        }
        else {
            myDrawer.statusMessage = "Waiting for the opponent's first move...";
            myDrawer.turn = false;
            opponentThink();
        }
    }
    
    public void startRound() {
        myDrawer.waitingTime = false;
        roundStarted = true;
        opponentThink();
    }
    
    public void waitMove() {
        playerStartTime = System.nanoTime();
        playerTimer.start();
        myDrawer.statusMessage = "Your turn!";
        myDrawer.turn = true;
    }
    
    public void attemptMove(int position) {
        if (!myDrawer.waitingForNext && myDrawer.turn && xox.put(myDrawer.playerSymbol, position)) {
            playerEndTime = System.nanoTime();
            playerTimer.stop();
            
            myDrawer.unhiddenSquare = position;
            if (roundStarted) {
                myDrawer.realYouTime += (double) (playerEndTime - playerStartTime) / Math.pow(10, 9);
                playerRoundTime += (double) (playerEndTime - playerStartTime) / Math.pow(10, 9);
                myDrawer.fakeYouTime = myDrawer.realYouTime;
                myDrawer.repaint();
                
                if (checkRound())
                    opponentThink();
                else
                    return;
            }
            else
                startRound();
            
            myDrawer.statusMessage = "Opponent's turn...";
            myDrawer.turn = false;
        }
    }
    
    public void opponentThink() {
        opponentTimer.start();
        opponentMoveTime = 0;
    }
    
    public void opponentMove() {
        int move = AIMoves.getMove(xox.getBoard(), difficulty);
        xox.put(myDrawer.opponentSymbol, move);
        myDrawer.unhiddenSquare = move;
        if (myDrawer.waitingTime)
            myDrawer.waitingTime = false;
        if (checkRound())
            waitMove();
    }
    
    public boolean checkRound() {
        int situation = xox.check();
        if (situation == myDrawer.playerSymbol) {
            myDrawer.statusMessage = "You won the round! ";
            myDrawer.youGame++;
            myDrawer.youPoint += 100;
            myDrawer.realYouTime -= playerRoundTime;
            myDrawer.fakeYouTime = myDrawer.realYouTime;
            if (gameOver()) endOfGame();
            else endOfRound();
        }
        else if (situation == myDrawer.opponentSymbol) {
            myDrawer.statusMessage = "You lost the round! ";
            myDrawer.oppGame++;
            myDrawer.oppPoint += 100;
            myDrawer.oppTime -= opponentRoundTime;
            if (gameOver()) endOfGame();
            else endOfRound();
        }
        else if (situation == XOXGame.TIE) {
            myDrawer.statusMessage = "The round is a draw! ";
            if (gameOver()) endOfGame();
            else endOfRound();
        }
        return situation == XOXGame.KEEP;
    }
    
    public boolean gameOver() {
        return myDrawer.currentRound == myDrawer.totalRound;
    }
    
    public void endOfRound() {
        myDrawer.statusMessage += "The next one will begin now...";
        myDrawer.waitingTime = true;
        myDrawer.waitingForNext = true;
        myDrawer.winnerSquares = xox.getWinnerSquares();
        endOfRoundBlinkTime = 0;
        endOfRoundAnimationTimer.start();
        if (unknownSymbols)
            myDrawer.makeSymbolsKnown();
    }
    
    public void endOfGame() {
        myDrawer.statusMessage += "The rounds are over...";
        if (myDrawer.realYouTime > myDrawer.oppTime)
            myDrawer.oppPoint += (int) ((myDrawer.realYouTime - myDrawer.oppTime) * 30);
        else if (myDrawer.oppTime > myDrawer.realYouTime)
            myDrawer.youPoint += (int) ((myDrawer.oppTime - myDrawer.realYouTime) * 30);
        myDrawer.waitingTime = true;
        myDrawer.waitingForNext = true;
        myDrawer.winnerSquares = xox.getWinnerSquares();
        endOfRoundBlinkTime = 0;
        endOfRoundAnimationTimer.start();
        if (unknownSymbols)
            myDrawer.makeSymbolsKnown();
    }
    
    public void announceWinner() {
        if (myDrawer.youPoint > myDrawer.oppPoint)
            myDrawer.statusMessage = "You won! Click to go to the main menu.";
        else if (myDrawer.youPoint < myDrawer.oppPoint)
            myDrawer.statusMessage = "Unluckily, you lost... Click to go to the main menu.";
        else if (myDrawer.youPoint == myDrawer.oppPoint)
            myDrawer.statusMessage = "This game is a draw... Click to go to the main menu.";
        quitGame = true;
    }
    
    public void confirmGoingBack() {
        int selection = JOptionPane.showConfirmDialog(myDrawer, "Are you sure want to quit?",
                                                      "Back to main menu...",
                                                      JOptionPane.OK_CANCEL_OPTION,
                                                      JOptionPane.QUESTION_MESSAGE);
        if (selection == JOptionPane.OK_OPTION)
            goBack();
    }
    
    public void goBack() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setLocationRelativeTo(this);
        this.setVisible(false);
        this.dispose();
    }
    
    public static void main(String[] args) {
        SingleplayerGame singleplayerGame = new SingleplayerGame(6, new boolean[] {false, false, false, false});
    }
}