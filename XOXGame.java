import java.util.ArrayList;

public class XOXGame {
    public static final int X = 1;
    public static final int O = 0;
    public static final int EMPTY = 2;
    
    public static final int XWINS = 1;
    public static final int OWINS = 0;
    public static final int TIE = 2;
    public static final int KEEP = 3;
    
    private int[] board;
    
    public XOXGame() {
        board = new int[9];
        for(int i = 0; i < 9; i++)
            board[i] = EMPTY;
    }
    
    public int[] getBoard() {
        return board;
    }
    
    public String toString() {
        String boardString = "";
        for (int i = 0; i < 9; i++) {
            if (board[i] == X)
                boardString = boardString + "X";
            else if (board[i] == O)
                boardString = boardString + "O";
            else if (board[i] == EMPTY)
                boardString = boardString + " ";
            boardString = boardString + " ";
            
            if (i % 3 == 2 && i != 8)
                boardString = boardString + '\n';
        }
        return boardString;
    }
    
    public boolean put(int symbol, int position) {
        if (board[position] == EMPTY) {
            board[position] = symbol;
            return true;
        }
        return false;
    }
    
    public int check() {
        if (board[0] != EMPTY && (board[0] == board[1]) && (board[0] == board[2])) // Horizontal
            return board[0];
        else if (board[3] != EMPTY && (board[3] == board[4]) && (board[3] == board[5]))
            return board[3];
        else if (board[6] != EMPTY && (board[6] == board[7]) && (board[6] == board[8]))
            return board[6];
        else if (board[0] != EMPTY && (board[0] == board[3]) && (board[0] == board[6])) // Vertical
            return board[0];
        else if (board[1] != EMPTY && (board[1] == board[4]) && (board[1] == board[7]))
            return board[1];
        else if (board[2] != EMPTY && (board[2] == board[5]) && (board[2] == board[8]))
            return board[2];
        else if (board[0] != EMPTY && (board[0] == board[4]) && (board[0] == board[8])) // Diagonal
            return board[0];
        else if (board[2] != EMPTY && (board[2] == board[4]) && (board[2] == board[6]))
            return board[2];
        
        for (int i = 0; i < 9; i++)
            if (board[i] == EMPTY)
            return KEEP;
        return TIE;
    }
    
    public ArrayList<Integer> getWinnerSquares() {
        ArrayList<Integer> winners = new ArrayList<Integer>();
        
        if (board[0] != EMPTY && (board[0] == board[1]) && (board[0] == board[2])) { // Horizontal
            if (winners.indexOf(0) == -1) winners.add(0);
            if (winners.indexOf(1) == -1) winners.add(1);
            if (winners.indexOf(2) == -1) winners.add(2);
        }
        if (board[3] != EMPTY && (board[3] == board[4]) && (board[3] == board[5])) {
            if (winners.indexOf(3) == -1) winners.add(3);
            if (winners.indexOf(4) == -1) winners.add(4);
            if (winners.indexOf(5) == -1) winners.add(5);
        }
        if (board[6] != EMPTY && (board[6] == board[7]) && (board[6] == board[8])) {
            if (winners.indexOf(6) == -1) winners.add(6);
            if (winners.indexOf(7) == -1) winners.add(7);
            if (winners.indexOf(8) == -1) winners.add(8);
        }
        if (board[0] != EMPTY && (board[0] == board[3]) && (board[0] == board[6])) { // Vertical
            if (winners.indexOf(0) == -1) winners.add(0);
            if (winners.indexOf(3) == -1) winners.add(3);
            if (winners.indexOf(6) == -1) winners.add(6);
        }
        if (board[1] != EMPTY && (board[1] == board[4]) && (board[1] == board[7])) {
            if (winners.indexOf(1) == -1) winners.add(1);
            if (winners.indexOf(4) == -1) winners.add(4);
            if (winners.indexOf(7) == -1) winners.add(7);
        }
        if (board[2] != EMPTY && (board[2] == board[5]) && (board[2] == board[8])) {
            if (winners.indexOf(2) == -1) winners.add(2);
            if (winners.indexOf(5) == -1) winners.add(5);
            if (winners.indexOf(8) == -1) winners.add(8);
        }
        if (board[0] != EMPTY && (board[0] == board[4]) && (board[0] == board[8])) { // Diagonal
            if (winners.indexOf(0) == -1) winners.add(0);
            if (winners.indexOf(4) == -1) winners.add(4);
            if (winners.indexOf(8) == -1) winners.add(8);
        }
        if (board[2] != EMPTY && (board[2] == board[4]) && (board[2] == board[6])) {
            if (winners.indexOf(2) == -1) winners.add(2);
            if (winners.indexOf(4) == -1) winners.add(4);
            if (winners.indexOf(6) == -1) winners.add(6);
        }
        
        return winners;
    }
}