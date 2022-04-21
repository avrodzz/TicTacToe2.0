/*
Alexis Rodriguez
Spring 2022 - Tic Tac Toe 2.0
Last Updated: April 20, 2022

How To Play My Game:
- How many players will be playing? (section)
    Enter a single number between 3 and 10. It should catch errors such as out of range numbers and strings.
- Enter a player piece: (section)
    Enter in a character. It can be any character (0-9), lowercase alpha (a-z), uppercase alpha (A-Z) (count as unique in this game), and any other special character as long as it is not a space or a character that has already been added. 
- How many slots does it take to win? (Min: 3 Max: 4) Enter in a number of slots to win: (section)
    Enter a single number between 3 and numberOfPlayers+1. It should catch errors such as out of range numbers and strings.
- Enter in the row (0 - 3): (section)
    Enter in a single number between 0 and boardSize. It will catch you if you enter in strings or rows that are not available. 
- Enter in the col(0 - 3): (section)
    Enter in a single number between 0 and boardSize. It will catch you if you enter in strings or rows that are not available. 
- You will be entering rows and columns until the end of the game. 
- Have fun!

*/

public class TicTacToe {
    private GameLogic logic;

    /**
     * Default Constructor
     */
    public TicTacToe() {
        logic = new GameLogic();
    }

    /**
     * Sets up and plays the Tic Tac Toe 2.0 game
     */
    public void run() {
        /*
         * 1. Set up the game logic
         * 2. PLay the game
         */
        logic.setUp();
        logic.play();
    }
}