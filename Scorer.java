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
    public boolean checkHorizontal(Board board) {
        /*
         * 1. Check the horizontal that the players move is in for a win
         */

        int winningCount = 0;

        // Iterate through each cell in the board
        for (int i = 0; i < board.getBoardSize(); i++) {
            char temp = ' ';
            for (int j = 0; j < board.getBoardSize(); j++) {
                char current = board.charOnBoardSpace(i, j);

                if (current == temp) {
                    if (current != board.SPACE) {
                        winningCount++;
                    }
                } else {
                    winningCount = 1;
                    temp = current;
                }
                if (winningCount == this.numToWin) {
                    return true;
                }
            }
        }

        return false;
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
    public boolean checkVertical(Board board) {
        /*
         * 1. Check the vertical that the players move is in for a win
         */

        int winningCount = 0;

        for (int i = 0; i < board.getBoardSize(); i++) {
            char temp = ' ';
            for (int j = 0; j < board.getBoardSize(); j++) {
                char current = board.charOnBoardSpace(j, i);

                if (current == temp) {
                    if (current != board.SPACE) {
                        winningCount++;
                    }
                } else {
                    winningCount = 1;
                    temp = current;
                }
                if (winningCount == this.numToWin) {
                    return true;
                }
            }
        }

        return false;
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
    public boolean checkDiagonal(Board board) {
        /*
         * 1. Check the diagonal that the players move is in for a win
         */

        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                // Top Left
                if (this.checkDiagonal(board, i, j, -1, -1)) {
                    return true;
                }
                // Bottom Left
                if (this.checkDiagonal(board, i, j, -1, 1)) {
                    return true;
                }
                // Top Right
                if (this.checkDiagonal(board, i, j, 1, -1)) {
                    return true;
                }
                // Bottom Right
                if (this.checkDiagonal(board, i, j, 1, 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Accessor
     * Checks the damn diagonals.
     * 
     * @param board the board
     * @param x     the i index
     * @param y     the j index
     * @param xMove the x direction of diagonal
     * @param yMove the y direction of diagonal
     * @return true if diagonal exists
     */
    public boolean checkDiagonal(Board board, int x, int y, int xMove, int yMove) {
        /*
         * 1. Make sure not to go out of bounds with x and y
         * 2. Check the values at the index against player character
         * 3. Return true or false if there is a diagonal
         */

        int winningCount = 0;
        char temp = ' ';
        while (x >= 0 && x < board.getBoardSize() && y >= 0 && y < board.getBoardSize()) {
            char current = board.charOnBoardSpace(x, y);

            if (current == temp) {
                if (current != board.SPACE) {
                    winningCount++;
                }
            } else {
                winningCount = 1;
                temp = current;
            }
            if (winningCount == this.numToWin) {
                return true;
            }
            x += xMove;
            y += yMove;
        }
        return false;
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
        if (this.checkHorizontal(board) || this.checkVertical(board)
                || this.checkDiagonal(board)) {
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
