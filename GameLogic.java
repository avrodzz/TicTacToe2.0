import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameLogic {
    private Board board = new Board();
    private ArrayList<Player> players = new ArrayList<Player>();
    private Scanner in = new Scanner(System.in);
    private Scorer scorer = new Scorer();
    private int currentRow = 0, currentColumn = 0;
    private static final int MIN_NUM_OF_WINS = 3;
    private static final String GAME_TITLE = "Tic Tac Toe 2.0";

    /**
     * Default Constructor
     */
    public GameLogic() {

    }

    /**
     * Accessor - Getter
     * Checks if the number of players is between MIN_NUM_OF_PLAYERS and
     * MAX_NUM_OF_PLAYERS
     * 
     * @return True, if numOfPlayers is between MIN_NUM_OF_PLAYERS and
     *         MAX_NUM_OF_PLAYERS, otherwise false
     */
    private boolean isValidNumOfPlayers() {
        return (this.board.getNumOfPlayers() >= this.board.MIN_NUM_OF_PLAYERS
                && this.board.getNumOfPlayers() <= this.board.MAX_NUM_OF_PLAYERS);
    }

    /**
     * Mutator
     * Validates user input for how many players will be playing in the game
     */
    public void validateNumOfPlayersPlaying() {
        /*
         * 1. Print out a message asking the player to enter in how many players will be
         * playing in the game
         * 2. Get and validate user input
         * a. Make sure that the input is an integer
         * b. Make sure that the input is with in the number of players range
         * c. If it is not, then continue to ask the user until they give a valid input
         */
        System.out.println("How many players will be playing?");
        do {
            try {
                this.board.setNumOfPlayers(in.nextInt());
            } catch (InputMismatchException exception) { // Throws exception if input is anything other than an integer
                System.out.println("Invalid input. Number of players must be between " + this.board.MIN_NUM_OF_PLAYERS
                        + " and " + this.board.MAX_NUM_OF_PLAYERS + ".");
            }
            in.nextLine(); // clear the line
        } while (!this.isValidNumOfPlayers());

        System.out.println("There are " + this.board.getNumOfPlayers() + " players playing in this iteration of "
                + this.GAME_TITLE + ".");
    }

    /**
     * Mutator
     * Validates user input for each player piece that will be in the game
     * No piece can be the same, any character beside the space is valid
     */
    public void validatePlayerPieces() {
        /*
         * 1. Prints out a message asking the player for X number of unique player piece
         * characters
         * 2. Get and validate user input
         * a. Keep asking the player for a player piece character that is valid
         * b. Spaces and multiples of the same character are not valid
         */
        char piece = ' ';

        // Iterate through until each Player has a character player piece
        for (int i = 0; i < this.board.getNumOfPlayers(); i++) {
            do {
                System.out.println("Enter a player piece: ");
                piece = in.next().charAt(0); // Gets the first character in the inputted string

            } while (!this.isValidPlayerPiece(piece));

            // Add the player pieces to the Players (ArrayList)
            players.add(new Player(piece));
        }

        // Prints out a string of the different players
        System.out.println("Here is the current player pieces list: " + (this.getPlayersList()));
    }

    /**
     * Accessor
     * Checks if the player piece character is valid to add to the players ArrayList
     * of players
     * 
     * @param piece the character player piece in question
     * @return true if the piece is not in the list, otherwise false
     */
    public boolean isValidPlayerPiece(char piece) {
        /*
         * 1. Check whether the character piece is in the ArrayList players
         * 2. If it is, then return false, otherwise return true
         */

        // Checks if the piece is a space - this check is kind of redundant
        if (isSpace(piece)) {
            return false;
        }

        // Iterate through the players ArrayList and check if the piece is already in
        // the list
        for (int i = 0; i < players.size(); i++) {
            // When the piece is in the list already, return false
            if (players.get(i).getPlayer() == piece) {
                return false;
            }
        }

        // The piece is not in the list, return true
        return true;
    }

    /**
     * Accessor
     * Checks if the player piece character is a space
     * 
     * @param piece the character player piece in question
     * @return true if the piece is a space, otherwise false
     */
    public boolean isSpace(char piece) {
        return (piece == this.board.SPACE);
    }

    /**
     * Accessor
     * Constructs a string that holds each possible player piece in the game
     * 
     * @return the string of player piece characters
     */
    private String getPlayersList() {
        /*
         * 1. Create a temp string that is an empty string
         * 2. Loop through players ArrayList and add characters to the temp string
         * 3. Return that temp string
         */
        String playersList = "";

        // Iterate through the players ArrayList and add the player object's char to the
        // playersList string
        for (int i = 0; i < players.size(); i++) {
            playersList += players.get(i).getPlayer();
            playersList += ' ';
        }

        return playersList;
    }

    /**
     * Mutator
     * Validates user input for the amount of slots that it takes to win a game
     */
    public void validateNumToWin() {
        /*
         * 1. Prints out a message asking the player for X number of slots to win
         * 2. Get and validate user input
         * a. Keep asking the player for a number that is valid
         * b. Minimum: number of players | Maximum: num of players + 1
         */
        System.out.println("How many slots does it take to win? (Min: " + this.MIN_NUM_OF_WINS + " Max: "
                + this.board.getBoardSize() + ")");
        do {
            try {
                System.out.println("Enter in a number of slots to win: ");
                this.scorer.setNumToWin(in.nextInt());
            } catch (InputMismatchException exception) { // Throws exception if input is anything other than an integer
                System.out.println("Invalid input. Number of slots to win must be (Min: " + this.MIN_NUM_OF_WINS
                        + " Max: " + this.board.getBoardSize() + ")");
            }
            in.nextLine(); // clears the line
        } while (!this.isNumToWinValid());

        System.out.println("The number of slots needed to win is " + this.scorer.getNumToWin() + ".");
    }

    /**
     * Accessor
     * Checks if the number of slots to win is valid. Must be between 3
     * and number of player + 1
     * 
     * @return true if the number is valid, otherwise false
     */
    private boolean isNumToWinValid() {
        return (this.scorer.getNumToWin() >= this.MIN_NUM_OF_WINS
                && this.scorer.getNumToWin() <= this.board.getBoardSize());
    }

    /**
     * Mutator
     * Takes row and col from user input and validates it and sets the player's
     * character on the board.
     * 
     * @param player the player
     */
    public void takePlayerMoves(Player player) {
        /*
         * 1. Get row and col from user input
         * 2. Make sure the input is validated
         * 3. Add the player's character piece to the board
         */

        int row, col;

        do {
            try {
                // Getting the row from the user
                System.out.println("Enter in the row (0 - " + this.board.getNumOfPlayers() + "): ");
                row = in.nextInt();
                if (row < 0 || row > this.board.getNumOfPlayers()) {
                    System.out.println("Invalid Row.");
                }

                // Getting the colum from the user
                System.out.println("Enter in the col (0 - " + this.board.getNumOfPlayers() + "): ");
                col = in.nextInt();
                if (col < 0 || col > this.board.getNumOfPlayers()) {
                    System.out.println("Invalid Column.");
                }
                this.board.printBoard();

            } catch (InputMismatchException exception) {
                System.out.println("Invalid input. Possible row indices (0 - " + this.board.getNumOfPlayers()
                        + "). Possible col indices (0 - " + this.board.getNumOfPlayers() + ").");
                // Provides an invalid row and column, so the loop continues
                row = -1;
                col = -1;
                this.board.printBoard();
            }
            in.nextLine();

        } while (!this.isRowColComboValid(row, col));

        // Sets the player's character on the board
        this.board.setPlayerOnBoardSpace(row, col, player.getPlayer());
        this.currentRow = row;
        this.currentColumn = col;
    }

    /**
     * Accessor
     * Get whether the row and col provided in available, so the player can place
     * their character piece there
     * 
     * @param row the row in the game
     * @param col the column in the game
     * @return True, if the board space is empty or available, otherwise false
     */
    public boolean isRowColComboValid(int row, int col) {
        return (this.board.isValidSpace(row, col) && this.board.charOnBoardSpace(row, col) == this.board.SPACE);
    }

    /**
     * Prints the game title
     */
    public void printGameHeader() {
        System.out.println(this.GAME_TITLE);
    }

    /**
     * Mutator
     * Sets up the game. Number of players, player pieces, and number of slots to
     * win is decided here.
     */
    public void setUp() {
        /*
         * 1. Ask the user for the number of players playing in the game
         * 2. Ask the user for the player piece characters that will correspond to the
         * players playing
         * 3. Ask the user for the number of slots that are needed to win
         * 4. Print the initial board
         */
        this.printGameHeader();

        this.validateNumOfPlayersPlaying();
        // System.out.println(this.board.getNumOfPlayers()); // test

        this.validatePlayerPieces();
        // System.out.println(this.getPlayersList()); // test

        this.validateNumToWin();
        // System.out.println(this.isNumToWinValid()); // test

        this.board.printBoard();
    }

    /**
     * Mutator
     * Plays the game. The game should continue to run until someone wins or ties.
     */
    public void play() {
        /*
         * 1. Game loop goes here
         * 2. Check for wins
         * 3. Print a win message
         */
        System.out.println("Let's Play!");

        // Keeps track of which player goes next
        int playerCount = 0;

        // Game loop
        do {
            // Reinitialize playerCount to 0, so that the players keep going one after the
            // other
            if (playerCount == this.board.getNumOfPlayers()) {
                playerCount = 0;
            }

            // Created a temp variable for the currentPlayer, so that I would not have to
            // write players.get(playerCount) a bunch of times
            Player currentPlayer = players.get(playerCount);

            // Print to console the player that is currently playing
            System.out.println("Player " + currentPlayer.getPlayer());

            // Allow and validate the player to row and column moves
            this.takePlayerMoves(currentPlayer);

            // Print the board after every move
            this.board.printBoard();

            // Check for tie or any horizontal, vertical, or diagonal wins made by a player
            this.scorer.scoreBoard(this.board, currentPlayer, this.currentRow, this.currentColumn);

            playerCount++;

        } while (!this.scorer.getIsWin());

        System.out.println("Game Over!");
    }
}
