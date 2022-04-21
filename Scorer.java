public class Scorer {
    private boolean isWin = false;
    private int numToWin = 0;

    /**
     * Default Constructor
     */
    public Scorer() {

    }

    /**
     * Accessor - Getter
     * Get the number of slots that need to be filled in order to win
     * 
     * @return the number of slots to win
     */
    public int getNumToWin() {
        return this.numToWin;
    }

    /**
     * Mutator - Setter
     * Set the number of slots that are needed to win
     * 
     * @param numToWin the numer of slots to win
     */
    public void setNumToWin(int numToWin) {
        this.numToWin = numToWin;
    }

    /**
     * Accessor - Getter
     * Get the game state
     * 
     * @return true, the game is done, false the game has not been tied or won by a
     *         player yet
     */
    public boolean getIsWin() {
        return this.isWin;
    }

    /**
     * Accessor
     * Checks if the game has resulted in a tie. No space characters should be on
     * the board
     * 
     * @param board the board that is being checked for the tie
     * @return true, a tie has resulted, otherwise false no tie
     */
    public boolean isTie(Board board) {
        /*
         * 1. Iterate through every cell in the board and check if there is a space
         * a. No spaces - there is a tie (make sure to check for horizontal, vertical,
         * and diagonal wins before checking for tie)
         * b. Yes spaces - no tie
         */

        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                // Check if char at board[i][j] is a space
                if (board.charOnBoardSpace(i, j) == board.SPACE) {
                    return false;
                }
            }
        }
        // A tie has resulted
        return true;
    }

    /**
     * Accessor
     * Checks if the game has resulted in a horizontal win by one of the players.
     * 
     * @param board  the board that is being checked for horizontal wins
     * @param player the player we are checking for
     * @param row    the row
     * @param col    the col
     * @return true, horizontal win, otherwise false no horizontal win
     */
    public boolean checkHorizontal(Board board, Player player, int row, int col) {
        /*
         * 1.
         */
        return true;
    }

    /**
     * Accessor
     * Checks if the game has resulted in a vertical win by one of the players.
     * 
     * @param board  the board that is being checked for vertical wins
     * @param player the player we are checking for
     * @param row    the row
     * @param col    the col
     * @return true, vertical win, otherwise false no vertical win
     */
    public boolean checkVertical(Board board, Player player, int row, int col) {
        /*
         * 1.
         */
        return true;
    }

    /**
     * Accessor
     * Checks if the game has resulted in a diagonal win by one of the players.
     * 
     * @param board  the board that is being checked for diagonal wins
     * @param player the player we are checking for
     * @param row    the row
     * @param col    the col
     * @return true, diagonal win, otherwise false no diagonal win
     */
    public boolean checkDiagonal(Board board, Player player, int row, int col) {
        /*
         * 1.
         */
        return true;
    }

    /**
     * Mutator
     * Scores the board depending on whether or not a horizontal, vertical, or
     * diagonal win occurs. Tie is also checked here.
     * 
     * @param board  the board that is being checked for diagonal wins
     * @param player the player we are checking for
     * @param row    the row
     * @param col    the col
     */
    public void scoreBoard(Board board, Player player, int row, int col) {
        /*
         * 1. Check for horizontal, vertical, diagonal wins
         * 2. If step one is false, then check for tie
         */

        // Checks horizontal, vertical, diagonal win
        if (this.checkHorizontal(board, player, row, col) || this.checkVertical(board, player, row, col)
                || this.checkDiagonal(board, player, row, col)) {
            System.out.println("Player " + player.getPlayer() + " wins!");
            this.isWin = true;
        }

        // Checks for a tie game
        if (this.isTie(board)) {
            System.out.println("Tie!");
            this.isWin = true;
        }
    }

}
