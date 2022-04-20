public class Board {
    private char[][] board;
    private int numOfPlayers;
    private int boardSize;
    private static final char SPACE = ' ';
    private static final int MIN_NUM_OF_PLAYERS = 3, MAX_NUM_OF_PLAYERS = 10;

    /**
     * Default Constructor
     */
    Board() {
        /*
         * 1. Set number of players equal to zero to start
         */
        this.numOfPlayers = 0;
    }

    /**
     * Overloaded Constructor
     * 
     * @param numOfPlayers the number of players in the game
     */
    Board(int numOfPlayers) {
        /*
         * 1. Check if numOfPlayers passed to the method is valid (between MIN and MAX
         * number of players)
         * a. Valid
         * i. Set numOfPlayers instance variable to numOfPlayers
         * ii. Set boardSize equal to numOfPlayers + 1
         * iii. Construct and initialize the board with spaces and a board size
         * b. Not Valid
         * i. Tell the user that the numOfPlayers passed is invalid
         * ii. Set number of players equal to zero
         */
        if (numOfPlayers >= MIN_NUM_OF_PLAYERS && numOfPlayers <= MAX_NUM_OF_PLAYERS) {
            this.numOfPlayers = numOfPlayers;
            this.boardSize = this.numOfPlayers + 1;
            board = new char[this.boardSize][this.boardSize];
            this.initBoardSpaces();
        } else {
            System.out.println("Invalid number of players. Number of players must be between " + MIN_NUM_OF_PLAYERS
                    + "-" + MAX_NUM_OF_PLAYERS);
            this.numOfPlayers = 0;
        }
    }

    /**
     * Accessor - Getter
     * Gets the game board char 2D array.
     * 
     * @return the game board
     */
    public char[][] getBoard() {
        return this.board;
    }

    /**
     * Accessor - Getter
     * Gets the number of players in the game
     * 
     * @return the number of players
     */
    public int getNumOfPlayers() {
        return this.numOfPlayers;
    }

    /**
     * Mutator - Setter
     * Sets the number of players in the game and takes care of reconstructing the
     * board accordingly.
     * 
     * @param numOfPlayers the number of players in the game
     */
    public void setNumOfPlayers(int numOfPlayers) {
        /*
         * 1. Check if numOfPlayers passed to the method is valid (between MIN and MAX
         * number of players)
         * a. Valid
         * i. Set numOfPlayers instance variable to numOfPlayers
         * ii. Set boardSize equal to numOfPlayers + 1
         * iii. Construct and initialize the board with spaces and a board size
         * b. Not Valid
         * i. Tell the user that the numOfPlayers passed is invalid
         * ii. Set number of players equal to zero
         */
        if (numOfPlayers < MIN_NUM_OF_PLAYERS || numOfPlayers > MAX_NUM_OF_PLAYERS) {
            System.out.println("Invalid number of players. Number of players must be between " + MIN_NUM_OF_PLAYERS
                    + "-" + MAX_NUM_OF_PLAYERS);
        } else {
            this.numOfPlayers = numOfPlayers;
            this.boardSize = this.numOfPlayers + 1;
            board = new char[this.boardSize][this.boardSize];
            this.initBoardSpaces();
        }
    }

    /**
     * Accessor - Getter
     * Gets the size of the board. (square board: this value accounts for width and
     * height of board)
     * 
     * @return the size of the board
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * Mutator
     * Sets the values in the char[][] to spaces, so that the player can know where
     * they can place their next piece in the game.
     */
    private void initBoardSpaces() {
        /*
         * 1. Loop through each index in the char[][]
         * 2. Set each index value in the char[][] to a space
         */
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                this.board[i][j] = SPACE;
            }
        }
    }

    /**
     * Mutator
     * Place a player piece on the board space passed (row,col)
     * 
     * @param row    the row index in the board
     * @param col    the column index in the board
     * @param player the char player piece in the game
     */
    public void setPlayerOnBoardSpace(int row, int col, char player) {
        /*
         * 1. Check if the row and col is a valid space
         * a. True
         * i. Check if the value at that row and col is a space
         * ii. If it is, then change that row col value in board to player piece
         * iii. Otherwise, print out a message saying that the row and col are already
         * taken
         * b. False
         * i. Print out a message saying that the row and col are not valid for this
         * board
         */
        if (this.isValidSpace(row, col)) {
            if (this.board[row][col] != SPACE) {
                System.out.println("Invalid. Row: " + row + " Col: " + col + " is occupied!");
            } else {
                this.board[row][col] = player;
            }
        } else {
            System.out.println("Invalid row or column. Row: " + row + " Col: " + col + " do not exist!");
        }
    }

    /**
     * Accessor - Getter
     * 
     * @param row the row index in the board
     * @param col the column index in the board
     * @return the player piece at that row and col combination
     */
    public char charOnBoardSpace(int row, int col) {
        /*
         * 1. Check if the row and col is a valid space
         * a. True
         * i. Return value at that row and col in board
         * b. False
         * i. Print a message saying that the row and col are not valid in the board
         */
        if (this.isValidSpace(row, col)) {
            return this.board[row][col];
        } else {
            System.out.println("Invalid row or column. Row: " + row + " Col: " + col + " do not exist!");
            return ' ';
        }
    }

    /**
     * Accessor - Getter
     * Checks if the row and col passed is not going out of range in the char[][]
     * board
     * 
     * @param row the row index in the board
     * @param col the column index in the board
     * @return true if the row and col combo are valid, otherwise false
     */
    public boolean isValidSpace(int row, int col) {
        // Will return true if row and col are greater than or equal to 0; and row and
        // col must be less than the board's size
        return ((row >= 0 && row < this.boardSize) && (col >= 0 && col < this.boardSize));
    }

    /**
     * Mutator
     * Prints the board to the console.
     */
    public void printBoard() {
        /*
         * 1. Header of Board
         * 2. Body of Board
         * 3. Do not print the board if the number of players is not at least the
         * MIN_NUM_OF_PLAYERS
         */
        if (this.numOfPlayers >= MIN_NUM_OF_PLAYERS) {

            // First Attempt at printing the board (what was I thinking lol, it works
            // though)
            System.out.print("| ");
            for (int i = 0; i < this.boardSize; i++) {
                if (i > 9) {
                    System.out.print("|" + i);
                } else {
                    System.out.print("|" + i + " ");
                }
                if (i == this.boardSize - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();

            for (int i = 0; i < this.boardSize + 1; i++) {
                System.out.print("-");
                System.out.print("-");
                System.out.print("-");
            }
            System.out.println();

            for (int i = 0; i < this.boardSize; i++) {
                if (i > 9) {
                    System.out.print(i + "|");
                } else {
                    System.out.print(i + " |");
                }
                for (int j = 0; j < this.boardSize; j++) {
                    System.out.print(this.board[i][j] + " |");
                }
                System.out.println();
                for (int k = 0; k < (this.boardSize + 1) * 1.5; k++) {
                    System.out.print("-");
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        } else {
            System.out.println("Invalid board. Cannot print board to console. Number of players is not sufficient.");
        }

        // // This is how I was going to clean up my board, but I did not have time.
        // if(this.numOfPlayers >= MIN_NUM_OF_PLAYERS){
        // // this.printBoardHeader();
        // // this.printBoardBody();
        // }
        // else{
        // System.out.println("Invalid board. Cannot print board to console. Number of
        // players is not sufficient.");
        // }
    }

    // /**
    // * Helper Function
    // * - Work on this at the end of the semester
    // * - I was looking for a cleaner way to print out the board, but time was of
    // the
    // * essence.
    // */
    // private void printBoardHeader() {
    // System.out.println();

    // for (int i = -1; i < this.boardSize; i++) {
    // if (i > -1) {
    // System.out.print("|" + i + "|");
    // } else {
    // System.out.print("| |");
    // }
    // }
    // System.out.println();
    // }

    // /**
    // * Helper Function
    // * - Work on this at the end of the semester
    // * - I was looking for a cleaner way to print out the board, but time was of
    // the essence.
    // */
    // private void printBoardBody() {
    // for (int i = 0; i < this.boardSize; i++) {
    // for (int j = -1; j < this.boardSize; j++) {
    // if (j > -1) {
    // if (j < MAX_NUM_OF_PLAYERS) {
    // System.out.print("|" + this.board[i][j] + "|");
    // } else {
    // System.out.print("|" + this.board[i][j] + " |");
    // }
    // } else {
    // if (i < MAX_NUM_OF_PLAYERS) {
    // System.out.print("|" + i + " |");
    // } else {
    // System.out.print("|" + i + "|");
    // }
    // }
    // }
    // System.out.println();
    // }
    // System.out.println();
    // }
}
