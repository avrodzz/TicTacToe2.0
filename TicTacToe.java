public class TicTacToe{
    private GameLogic logic;

    public TicTacToe(){
        logic = new GameLogic();
    }

    public void run(){
        logic.setUp();
        logic.play();
    }
}