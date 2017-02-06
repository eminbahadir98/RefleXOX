import java.util.Arrays;

public class AIMoves {
    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;
    
    private static final int X = XOXGame.X;
    private static final int O = XOXGame.O;
    private static final int EMPTY = XOXGame.EMPTY;

    private static final int[][] SITUATION_1_1 = {{O, EMPTY, EMPTY, EMPTY, X, EMPTY, EMPTY, EMPTY, X}, {2, 6}};
    private static final int[][] SITUATION_1_2 = {{EMPTY, EMPTY, O, EMPTY, X, EMPTY, X, EMPTY, EMPTY}, {0, 8}};
    private static final int[][] SITUATION_1_3 = {{EMPTY, EMPTY, X, EMPTY, X, EMPTY, O, EMPTY, EMPTY}, {0, 8}};
    private static final int[][] SITUATION_1_4 = {{X, EMPTY, EMPTY, EMPTY, X, EMPTY, EMPTY, EMPTY, O}, {2, 6}};
    
    private static final int[] SITUATION_2_1 = {X, EMPTY, EMPTY, EMPTY, O, EMPTY, EMPTY, EMPTY, X};
    private static final int[] SITUATION_2_2 = {EMPTY, EMPTY, X, EMPTY, O, EMPTY, X, EMPTY, EMPTY};
    
    private static final int[][] SITUATION_3_1 = {{EMPTY, X, EMPTY, EMPTY, O, EMPTY, EMPTY, EMPTY, X}, {0, 2, 3}};
    private static final int[][] SITUATION_3_2 = {{EMPTY, EMPTY, X, X, O, EMPTY, EMPTY, EMPTY, EMPTY}, {0, 6, 7}};
    private static final int[][] SITUATION_3_3 = {{X, EMPTY, EMPTY, EMPTY, O, EMPTY, EMPTY, X, EMPTY}, {5, 6, 8}};
    private static final int[][] SITUATION_3_4 = {{EMPTY, EMPTY, EMPTY, EMPTY, O, X, X, EMPTY, EMPTY}, {1, 2, 8}};
    
    private static int getSituation(int[] board) {
        if (((board[0] == board[1])) && (board[0] == board[2]) && board[0] != EMPTY)
            return board[0];
        else if (((board[3] == board[4]) && (board[3] == board[5])) && board[3] != EMPTY)
            return board[3];
        else if (((board[6] == board[7]) && (board[6] == board[8])) && board[6] != EMPTY)
            return board[6];
        else if (((board[0] == board[3]) && (board[0] == board[6])) && board[0] != EMPTY)
            return board[0];
        else if (((board[1] == board[4]) && (board[1] == board[7])) && board[1] != EMPTY)
            return board[1];
        else if (((board[2] == board[5]) && (board[2] == board[8])) && board[2] != EMPTY)
            return board[2];
        else if (((board[0] == board[4]) && (board[0] == board[8])) && board[0] != EMPTY)
            return board[0];
        else if (((board[2] == board[4]) && (board[2] == board[6])) && board[2] != EMPTY)
            return board[2];
        else 
            return -1;
    }
    
    // If AI prevents opponent's win with this move, it will return true
    private static boolean putPrevent(int position, int[] board) {
        if (board[position] == EMPTY) {
            board[position] = X;
            
            if(getSituation(board) == XOXGame.XWINS){
                board[position] = EMPTY;
                return true;
            }
            else board[position] = EMPTY;
        }    
        return false;
    }
    
    // If AI will win with this move, it will return true
    private static boolean isAIWin(int position, int[] board) {
        if (board[position] == EMPTY){
            board[position] = O;
            if(getSituation(board) == XOXGame.OWINS)
                return true;
            else board[position] = EMPTY;
        }
        return false;
    }
    
    private static int getTurn(int[] board) {
        int turn = 1;
        for (int i : board)
            if (i != EMPTY)
                turn++;
        return turn;
    }
    
    private static int getRandomCorner() {
        int random = (int) (Math.random() * 4);
        if (random == 0) return 0;
        else if (random == 1) return 2;
        else if (random == 2) return 6;
        else return 8;
    }
    
    private static int getRandomMiddleEdge() {
        int random = (int) (Math.random() * 4);
        if (random == 0) return 1;
        else if (random == 1) return 3;
        else if (random == 2) return 5;
        else return 7;
    }
    
    private static int pickRandom(int[] squares) {
        int random = (int) (Math.random() * squares.length);
        return squares[random];
    }
    
    private static int getEasyMove(int[] board) {
        for (int i = 0; i < 9; i++)
            if (isAIWin(i, board)) // First check whether AI has chance to win or not
                return i;   

        for (int i = 0; i < 9; i++)
            if (putPrevent(i, board)) // If AI doesn't have chance to win, try to prevent opponent's win
                return i;

        while (true) {
            int random = (int) (Math.random() * 9);
            if (board[random] == EMPTY) 
                return random;
        }
    }
    
    private static int getMediumMove(int[] board) {
        int turn = getTurn(board);
        if (turn == 1) {
            int random = (int) (Math.random() * 2);
            if (random == 0)
                return 4;
            else
                return getRandomCorner();
        }
        else if (turn == 2) {
            if (board[4] == EMPTY)
                return 4;
            else
                return getRandomCorner();
        }
        else
            return getEasyMove(board);
    }
    
    private static int getHardMove(int[] board) {
        int turn = getTurn(board);

        if (turn == 4){
            if (Arrays.equals(board, SITUATION_1_1[0])) return pickRandom(SITUATION_1_1[1]);
            if (Arrays.equals(board, SITUATION_1_2[0])) return pickRandom(SITUATION_1_2[1]);
            if (Arrays.equals(board, SITUATION_1_3[0])) return pickRandom(SITUATION_1_3[1]);
            if (Arrays.equals(board, SITUATION_1_4[0])) return pickRandom(SITUATION_1_4[1]);

            if (Arrays.equals(board, SITUATION_2_1) || Arrays.equals(board, SITUATION_2_2))
                return getRandomMiddleEdge();
            
            if (Arrays.equals(board, SITUATION_3_1[0])) return pickRandom(SITUATION_3_1[1]);
            if (Arrays.equals(board, SITUATION_3_2[0])) return pickRandom(SITUATION_3_2[1]);
            if (Arrays.equals(board, SITUATION_3_3[0])) return pickRandom(SITUATION_3_3[1]);
            if (Arrays.equals(board, SITUATION_3_4[0])) return pickRandom(SITUATION_3_4[1]);
        }
        
        return getMediumMove(board);
    }
    
    public static int getMove(int[] board, int difficulty) {
        if (difficulty == EASY)
            return getEasyMove(board);
        else if (difficulty == MEDIUM)
            return getMediumMove(board);
        else if (difficulty == HARD)
            return getHardMove(board);
        return -1;
    }
}