public class DakonBoard{
    private int[] board;
    private boolean turnplayer1;
    private int[] player;

    public DakonBoard() {
        board = new int[14];
        player = new int[2];
        for (int i = 0; i < board.length; i++) {
            board[i] = 7;
        }
        turnplayer1 = true;
        player[0] = 0;
        player[1] = 0;
    }

    public DakonBoard(int[] board, int poin1, int poin2, boolean turnplayer1) {
        this.board = board;
        this.turnplayer1 = turnplayer1;
        this.player[0] = poin1;
        this.player[1] = poin2;

    }

    public int[] getBoard() {
        return board;
    }

    public int[] getPlayer(){
        return player;
    }

    public int getinBoard(int place){
        return board[place];
    }

    public void setBoard(int place,int value){
        board[place]=value;
    }

    public boolean getPlayer1Turn(){
        return turnplayer1;
    }

    public void setPlayer1Turn(boolean value){
        turnplayer1 = value;
    }

    boolean is_No_Move() {
        int idx = 0;
        while (idx < 7 && getinBoard(idx) == 0) {
            idx++;
        }
        if (idx == 7) {
            for (int i = 7; i < board.length; i++) {
                player[1] = player[1] + getinBoard(i);
            }
            return true;
        }
        idx = 7;
        while (idx < board.length && getinBoard(idx) == 0) {
            idx++;
        }
        if (idx == board.length) {
            for (int i = 0; i < 7; i++) {
                player[0] = player[0] + getinBoard(i);
            }
            return true;
        }
        return false;
    }

    int getHeuristic() {
        int sump1 = 0;
        int sump2 = 0;
        for(int i=0; i<7; i++){
            sump1+=getinBoard(i);
            sump1+=player[0];
        }
        for(int i=8; i<14; i++){
            sump2+=getinBoard(i);
            sump2+=player[1];
        }
        return sump1 - sump2;
    }

    void print() {
        for (int i = 7; i < 14; i++) {
            System.out.print(board[i] + "\t");
        }
        System.out.println();
        for (int i = 6; i >= 0; i--) {
            System.out.print(board[i] + "\t");
        }
        System.out.println();
        System.out.println("P1: " + player[0]);
        System.out.println("P2: " + player[1]);
        System.out.println("P1 move: " + getPlayer1Turn());
    }

}