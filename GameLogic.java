import java.util.ArrayList;
import java.util.Scanner;

public class GameLogic {
    private Board board = new Board();
    private ArrayList<Player> players = new ArrayList<Player>();
    private Scanner in = new Scanner(System.in); 

    public void setUp(){
        System.out.println("GameLogic setUp");
    }

    public void play(){
        System.out.println("GameLogic play");
    }
}
