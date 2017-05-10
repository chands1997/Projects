import java.util.Random;
import java.util.Scanner;

public class TwentyFortyEight {
    //This instance variable represents the board. Use this to make changes.
    private int[][] board;
    //This variable keeps track of the current score.
    private int score;

    //Constructor
    public TwentyFortyEight(int boardWidth){
        this.board = new int[boardWidth][boardWidth];
        this.score = 0;
        placeRandom();
    }

    //This function resets the board to its initial state
    public void reset() {
        for(int i=0; i<board.length; i++){
            for (int j=0; j<board.length; j++){
                board[i][j]=0;
            }
        }
        score = 0;
        placeRandom();
    }

    //This function returns the total number of blank spaces on the board
    public int numBlanks() {
        int count = 0;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board.length; j++){
                if(board[i][j]== 0){
                    count++;
                }
            }
        }
        return count;
    }

    //This function places a 2 at a random blank space on the board.
    //It does nothing if there are no blank spaces.
    public void placeRandom(){
        if(this.numBlanks()==0){
            return;
        }
        Random rnd = new Random();
        int check = 0;
        do{
            int x = rnd.nextInt(board.length);
            int y = rnd.nextInt(board.length);
            if(board[x][y]==0){
                board[x][y]=2;
                check = 1;
            }
        }while(check == 0);

    }

    //This function attempts to move a cell at coordinates fromRow,fromCol to
    //the cell toRow,toCol. Refer to the handout for movement rules.
    public boolean moveTo(int fromRow, int fromCol, int toRow, int toCol) {
        if(fromRow>=board.length||fromCol>=board.length||toRow>=board.length||toCol>=board.length||fromRow<0||fromCol<0
                ||toRow<0||toCol<0||toRow>=fromRow+2||toCol>=fromCol+2){
            return false;
        }
        if(board[fromRow][fromCol]==0){
            return false;
        }
        else if(board[toRow][toCol]==board[fromRow][fromCol]){
            board[toRow][toCol]=2*board[toRow][toCol];
            board[fromRow][fromCol]=0;
            score += board[toRow][toCol];
            return true;
        }
        else if(board[toRow][toCol]==0){
            board[toRow][toCol]=board[fromRow][fromCol];
            board[fromRow][fromCol]=0;
            return true;
        }
        else if(board[toRow][toCol]!=board[fromRow][fromCol] && board[toRow][toCol]>0 && board[fromRow][fromCol]>0){
            return false;
        }
        return false;
    }

    //The following four functions move the board in a single direction.
    public boolean moveUp(){
        int check = 0;
        boolean cond;
        for(int i=0; i<board.length; i++) {
            for (int j = 0; j<board.length; j++) {
                cond = moveTo(i+1, j, i, j);
                if (cond == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean moveDown() {
        int check = 0;
        boolean cond;
        for(int i=board.length-1; i>=0; i--) {
            for (int j = 0; j < board.length; j++) {
                cond = moveTo(i-1, j, i, j);
                if (cond == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean moveRight() {
        boolean cond;
        for (int i = 0; i < board.length; i++) {//row
            for (int j = board.length-1; j>=0; j--) {
                cond = moveTo(i, j-1, i, j);
                if (cond == true)
                    return true;
            }
        }
        return false;
    }

    public boolean moveLeft() {
        int check = 0;
        boolean cond;
        for(int i=0; i<board.length; i++) {//row
            for (int j = 0; j<board.length; j++) {
                cond = moveTo(i, j+1, i, j);
                if (cond == true) {
                    return true;
                }
            }
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////
    // Do not edit the methods below, they are for testing and running the
    // program.
    ////////////////////////////////////////////////////////////////////////
    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] newBoard) {
        board = newBoard;
    }


    /**
     * The main will allow you to play the game.
     *
     * @param args
     */
    public static void main(String args[]) {
        TwentyFortyEight tfe = new TwentyFortyEight(4);


        Scanner s = new Scanner(System.in);
        int bestScore = 0;
        boolean resetFlag = false;

        while(true) {
            System.out.println("Current score: " + tfe.getScore() + " | Best score: " + bestScore);
            tfe.showBoard(4);

            System.out.println("Enter up, down, left or right to move in that direction.");
            System.out.println("Enter reset to reset the game or quit to exit.");
            String line = s.nextLine();

            switch(line){
                case "up":
                    while(tfe.moveUp()){
                        // do nothing
                    }
                    break;
                case "down":
                    while(tfe.moveDown()){
                        // do nothing
                    }
                    break;
                case "left":
                    while(tfe.moveLeft()){
                        // do nothing
                    }
                    break;
                case "right":
                    while(tfe.moveRight()){
                        // do nothing
                    }
                    break;
                case "reset":
                    tfe.reset();
                    resetFlag = true;
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Invalid move, Please try again!!!!\n");
                    continue;

            }

            bestScore = Math.max(bestScore, tfe.getScore());
            if(!resetFlag) {
                tfe.placeRandom();
                resetFlag = false;
            }
        }
    }



    public void showBoard(int boardWidth) {
        for(int x = 0; x < boardWidth * 6 + 1; x++){
            System.out.print("-");
        }
        System.out.println();

        for(int x = 0; x < boardWidth; x++) {
            for(int y = 0; y < boardWidth; y++) {
                String square = (board[x][y] == 0)? "":board[x][y] + "";
                System.out.printf("|%5s", square);
            }
            System.out.println("|");

            for(int y = 0; y < boardWidth * 6 + 1; y++){
                System.out.print("-");
            }
            System.out.println();
        }
    }

    public int getScore() {
        return score;
    }

}
