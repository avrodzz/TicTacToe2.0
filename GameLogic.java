import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameLogic {
    private Board board = new Board();
    private ArrayList<Player> players = new ArrayList<Player>();
    private Scanner in = new Scanner(System.in);
    private int numToWin = 0;

    /**
     * Default Constructor
     */
    public GameLogic() {

    }

    /**
     * Accessor - Getter
     * Checks if the number of players is between MIN_NUM_OF_PLAYERS and MAX_NUM_OF_PLAYERS
     * @return True, if numOfPlayers is between MIN_NUM_OF_PLAYERS and MAX_NUM_OF_PLAYERS, otherwise false
     */
    private boolean isValidNumOfPlayers() {
        return (this.board.getNumOfPlayers() >= this.board.MIN_NUM_OF_PLAYERS
                && this.board.getNumOfPlayers() <= this.board.MAX_NUM_OF_PLAYERS);
    }

    /**
     *  Mutator
     *  Validates user input for how many players will be playing in the game
     */
    public void validateNumOfPlayersPlaying() {
        /*
            1. Print out a message asking the player to enter in how many players will be playing in the game
            2. Get and validate user input
                a. Make sure that the input is an integer
                b. Make sure that the input is with in the number of players range
                c. If it is not, then continue to ask the user until they give a valid input
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
            
        */
        char piece = ' ';
        for (int i = 0; i < this.board.getNumOfPlayers(); i++) {
            do {
                System.out.println("Enter a player piece: ");
                piece = in.next().charAt(0);

            } while (!this.isValidPlayerPiece(piece));

            players.add(new Player(piece));
        }

        System.out.println("Here is the current player pieces list: " + (this.getPlayersList()));
    }

    public boolean isValidPlayerPiece(char piece) {
        if(isSpace(piece)){
        return false;
        }

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayer() == piece) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @param piece
     * @return
     */
    public boolean isSpace(char piece) {
        return (piece == this.board.SPACE);
    }

    private String getPlayersList() {
        String playersList = "";
        for (int i = 0; i < players.size(); i++) {
            playersList += players.get(i).getPlayer();
            playersList += ' ';
        }
        return playersList;
    }

    public void validateNumToWin(){
        System.out.println("How many slots does it take to win? (Min: " + this.board.getNumOfPlayers() + " Max: " + this.board.getBoardSize() + ")");
        do {
            try {
                System.out.println("Enter in a number of slots to win: ");
                this.numToWin = in.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Invalid input. Number of slots to win must be (Min: " + this.board.getNumOfPlayers() + " Max: " + this.board.getBoardSize() + ")");
            }
            in.nextLine();
        } while (!this.isNumToWinValid());
    }  
    
    private boolean isNumToWinValid(){
        return(this.numToWin >= this.board.getNumOfPlayers() && this.numToWin <= this.board.getBoardSize());
    }

    public void setUp() {
        System.out.println("GameLogic setUp");
        this.validateNumOfPlayersPlaying();
        // System.out.println(this.board.getNumOfPlayers());

        this.validatePlayerPieces();
        // System.out.println(this.getPlayersList());

        this.validateNumToWin();
        System.out.println(this.isNumToWinValid());
    }

    public void play() {
        System.out.println("GameLogic play");
    }
}
