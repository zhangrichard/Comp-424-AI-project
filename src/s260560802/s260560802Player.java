package s260560802;



import boardgame.Board;
import boardgame.BoardState;
import boardgame.Move;
import boardgame.Player;
import omweso.CCBoardState;
import omweso.CCBoard;
import omweso.CCMove;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;



/** A random Omweso player. */
public class s260560802Player extends Player {
    Random rand = new Random();
    CCMove move;
    /** You must provide a default public constructor like this,
     * which does nothing but call the base-class constructor with
     * your student number. */
    public s260560802Player() { super("s260560802"); }
    public s260560802Player(String s) { super(s); }

    /** Leave this method unchanged. */
    public Board createBoard() { return new CCBoard(); }

    /** Use this method to take actions when the game is over. */
    public void gameOver( String msg, BoardState bs) {
        CCBoardState board_state = (CCBoardState) bs;

        if(board_state.haveWon()){
            System.out.println("I won!");
        }else if(board_state.haveLost()){
            System.out.println("I lost!");
        }else if(board_state.tieGame()){
            System.out.println("Draw!");
        }else{
            System.out.println("Undecided!");
        }
    }

    /** Implement a very stupid way of picking moves. */
    public Move chooseMove(BoardState bs)
    {
        // Cast the arguments to the objects we want to work with.
        CCBoardState board_state = (CCBoardState) bs;

        // Get the contents of the pits so we can use it to make decisions.
        int[][] pits = board_state.getBoard();

        // Our pits in first row of array, opponent pits in second row.
        int[] my_pits = pits[0];
        int[] op_pits = pits[1];

        // Use my tool to do precisely nothing
//        MyTools.getSomething();

        if(!board_state.isInitialized()){
            // Code for setting up our initial position. Also, if your agent needs to be
            // set-up in any way (e.g. loading data from files) you should do it here.

            //CCBoardState.SIZE is the width of the board, in pits.
            int[] initial_pits = new int[2 * CCBoardState.SIZE];

            // Make sure your initialization move includes the proper number of seeds.
            int num_seeds = CCBoardState.NUM_INITIAL_SEEDS;

            if(board_state.playFirst()){
                // If we're going to play first in this game, choose one random way of setting up.
                // Throw each seed in a random pit that we control.
                for(int i = 0; i < num_seeds; i++){
                    int pit = rand.nextInt(2 * CCBoardState.SIZE);
                    initial_pits[pit]++;
                }
            }else{
                // If we're going to play second this game, choose another random way of setting up.
                // Throw each seed in a random pit that we control, but in the row closest to us.
                for(int i = 0; i < num_seeds; i++){
                    int pit = rand.nextInt(CCBoardState.SIZE);
                    initial_pits[pit]++;
                }
            }

            return new CCMove(initial_pits);
        }else{
            // get the info from the board the play strategy 
//            ArrayList<CCMove> moves = board_state.getLegalMoves();
            //using min max algorthm
            //choose the best move that can max(capture)
            int depth = 4;
            
            int moveIndex = minimax(board_state, depth, true);
            //using  same algorithm for oppenent to compute min(capture)            
            // Play a normal turn. Choose a random pit to play.
            return move;
        }
        // return the best move index
        
      
    }
    public int minimax(CCBoardState nodeBoard, int depth, boolean maximizingPlayer){
        if (depth == 0|| nodeBoard.haveWon()||nodeBoard.haveLost()){
            return heuristic(nodeBoard);
        }
        int bestValue;
         if (maximizingPlayer){
            bestValue = Integer.MIN_VALUE;
            HashMap<Integer,CCMove> moveScore = new HashMap<>();
            for (CCMove childMove : nodeBoard.getLegalMoves()){
            
                CCBoardState child = (CCBoardState) nodeBoard.clone();
                child.move(childMove);
                
                int val = minimax(child, depth - 1, false);
                moveScore.put(new Integer(val),childMove);  
            }
            Integer index= Collections.max(moveScore.keySet()); 
            move = moveScore.get(index);
            moveScore = null;
            return index;
         }
        else
            bestValue  = Integer.MAX_VALUE;
            HashMap<Integer,CCMove> moveScore = new HashMap<>();
            for (CCMove childMove : nodeBoard.getLegalMoves()){
                CCBoardState child = (CCBoardState) nodeBoard.clone();
                child.move(childMove);
                int val = minimax(child, depth - 1, true);
                moveScore.put(new Integer(val),childMove); 
            }
            
            Integer index= Collections.min(moveScore.keySet()); 
            moveScore = null;
            return index;
        }
    private int heuristic(CCBoardState nodeBoard) {
        // TODO Auto-generated method stub
        if (nodeBoard.haveWon()){
            return 100;
        }
        else if (nodeBoard.haveLost()){
            return -100;
        }
        else {
            int[] is = nodeBoard.getBoard()[0];
            int bit = 0;
            for (int i : is) {
                bit+=i;
            }
            bit=bit-32;
            return bit;
        }
    }
}