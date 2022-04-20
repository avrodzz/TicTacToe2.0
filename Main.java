public class Main {
    public static void main(String[] args) {
        // // Testing Player Class

        // // Instantiate an instance of Player with player piece = 'a'
        // Player p1 = new Player('a');

        // // Prints out p1 object's player piece
        // System.out.println(p1.getPlayer());

        // // Set p1 object's player piece to 'b'
        // p1.setPlayer('b');

        // // Prints out p1 object's player piece
        // System.out.println(p1.getPlayer());

        // Testing Board Class

        // Instantiate an instance of Board with 11 players (invalid numOfPlayers)
        Board board = new Board(11);

        // Board size should be 0, since 11 players is not valid
        System.out.println(board.getBoardSize());

        // Board size should be 0 again, negative players is not valid
        board.setNumOfPlayers(-1);
        System.out.println(board.getBoardSize());

        // Set numOfPlayers to 10 (valid numOfPlayers)
        board.setNumOfPlayers(10);

        // Prints out the numOfPlayers and boardSize
        System.out.println("Num of players: " + board.getNumOfPlayers() + " Board Size: " + board.getBoardSize());

        // Check isValidSpace method is working - expected: false
        System.out.println(board.isValidSpace(-1, 3));

        // Check setPlayerOnBoardSpace is working - expected: false
        board.setPlayerOnBoardSpace(-1, 3, 'c');

        // Check charOnBoardSpace is working - expected: ' '
        System.out.println("Row: 1 Col: 3 Char: " + board.charOnBoardSpace(1, 3));

        // Check printBoard is working - expected print a board that is (numOfPlayers + 1) x (numOfPlayers + 1)
        board.printBoard();

        // Check setPlayerOnBoardSpace with valid row & col
        board.setPlayerOnBoardSpace(3, 4, 'a');

         // Check printBoard is working - expected print a board that is (numOfPlayers + 1) x (numOfPlayers + 1)
         board.printBoard();
    }
}