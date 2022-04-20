public class Player {
    char player;

    /**
     * Default Constructor
     */
    public Player(){

    }

    /**
     * Overloaded Constructor
     * @param player the player's game piece
     */
    public Player(char player){
        this.player = player;
    }
    
    /**
     * Accessor - Getter
     * Gets the player's game piece.
     * @return the player's game piece
     */
    public char getPlayer(){
        return this.player;
    }

    /**
     * Mutator - Setter
     * Sets the player's game piece to the character that is passed in
     * @param player the player's game piece
     */
    public void setPlayer(char player){
        this.player = player;
    }
}
