public class Scorer {
    private boolean isWin = false;
    private int numToWin = 0;

    public Scorer() {

    }

    public int getNumToWin() {
        return this.numToWin;
    }

    public void setNumToWin(int numToWin) {
        this.numToWin = numToWin;
    }

    public boolean getIsWin() {
        return this.isWin;
    }

    public boolean isTie(Board board) {
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                if (board.charOnBoardSpace(i, j) == board.SPACE) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkHorizontal(Board board, Player p, int row, int col) {
        return true;
    }

    public boolean checkVertical(Board board, Player p, int row, int col) {
        return true;
    }

    public boolean checkDiagonal(Board board, Player p, int row, int col) {
        return true;
    }

    public void scoreBoard(Board board, Player player, int row, int col) {
        if (this.checkHorizontal(board, player, row, col) || this.checkVertical(board, player, row, col)
                || this.checkDiagonal(board, player, row, col)) {
            System.out.println("Player " + player.getPlayer() + " wins!");
            this.isWin = true;
        }

        if (this.isTie(board)) {
            System.out.println("Tie!");
            this.isWin = true;
        }
    }

}
