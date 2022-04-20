public class Main {
    public static void main(String[] args) {
        // Instantiate an instance of Player with player piece = 'a'
        Player p1 = new Player('a');

        // Prints out p1 object's player piece
        System.out.println(p1.getPlayer());

        // Set p1 object's player piece to 'b'
        p1.setPlayer('b');

        // Prints out p1 object's player piece
        System.out.println(p1.getPlayer());
    }
}