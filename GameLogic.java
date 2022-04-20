import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameLogic {
    private Board board = new Board();
    private ArrayList<Player> players = new ArrayList<Player>();
    private Scanner in = new Scanner(System.in);
    private int numToWin = 0;
    private static final int MIN_NUM_OF_WINS = 3;

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
                this.numToWin = in.nextInt();
            } catch (InputMismatchException exception) { // Throws exception if input is anything other than an integer
                System.out.println("Invalid input. Number of slots to win must be (Min: " + this.MIN_NUM_OF_WINS
                        + " Max: " + this.board.getBoardSize() + ")");
            }
            in.nextLine(); // clears the line
        } while (!this.isNumToWinValid());
    }

    /**
     * Accessor
     * Checks if the number of slots to win is valid. Must be between 3
     * and number of player + 1
     * 
     * @return true if the number is valid, otherwise false
     */
    private boolean isNumToWinValid() {
        return (this.numToWin >= this.MIN_NUM_OF_WINS && this.numToWin <= this.board.getBoardSize());
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
         */
        System.out.println("GameLogic setUp");

        this.validateNumOfPlayersPlaying();
        // System.out.println(this.board.getNumOfPlayers());

        this.validatePlayerPieces();
        // System.out.println(this.getPlayersList());

        this.validateNumToWin();
        System.out.println(this.isNumToWinValid());
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
        System.out.println("GameLogic play");
    }
}
