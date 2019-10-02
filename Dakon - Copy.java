import java.util.*;
import java.util.Random; 
class Dakon {
    public static int alpha;
    public static int beta;
    private int board[];
    private int turn;

    Dakon() {
        board = new int[16];
        turn = 1;
        for (int i = 0; i < board.length; i++) {
            if (i != 7 && i != 15) {
                board[i] = 7;
            } else {
                board[i] = 0;
            }
        }
    }

    Dakon(Dakon inputBoard){
        board=new int[16];

        for(int i=0;i<board.length;i++){
            board[i]=inputBoard.board[i];
        this.turn=inputBoard.turn;
    }
}

    public int getBoard(int place) {
        return board[place];
    }

    public void setBoard(int place, int value) {
        board[place] = value;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int _turn) {
        turn = _turn;
    }

    public int evalPoint() {
            return board[15] - board[7];
        }
    

    public void print() {
        for (int i = 0; i < 3; i++) {
            System.out.print(" ");
        }
        for (int i = 8; i < 15; i++) {
            System.out.print(board[i]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.print(board[7]);
        for (int i = 0; i < 17; i++) {
            System.out.print(" ");
        }
        System.out.println(board[15]);
        for (int i = 0; i < 3; i++) {
            System.out.print(" ");
        }
        for (int i = 6; i >= 0; i--) {
            System.out.print(board[i]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println();

    }
    public boolean isFinished(){
        int sum=0;
        int sum1=0;
        for(int i=0;i<7;i++){
            sum+=board[i];
        }
        for(int i=8;i<15;i++){
            sum1+=board[i];
        }
        if(sum1==0||sum==0){
            return true;
        }
        else{
            return false;
        }


    }

    public boolean checkMove(int position) {
        if (board[position] == 0) {
            return false;
        }
        if (turn == 1) {
            return position == 0 || position == 1 || position == 2 || position == 3 || position == 4 || position == 5
                    || position == 6;
        } else
            return position == 8 || position == 9 || position == 10 || position == 11 || position == 12
                    || position == 13 || position == 14;
    }

    public int move(int row) {
        int currentPieces;
        boolean finishTurn = false;
        boolean alreadyOneTurn = false;
        int currentPlaces = row;
        currentPieces = board[row];
        board[row] = 0;
        try {
            while (!finishTurn) {
                while (currentPieces > 0) {
                    currentPlaces++;
                    if (currentPlaces == 7 && turn != 1) {
                        currentPlaces++;
                        alreadyOneTurn = true;
                    } else if (currentPlaces == 15 && turn != 2) {
                        currentPlaces = 0;
                        alreadyOneTurn = true;
                    }
                    if (currentPlaces == 16) {
                        currentPlaces = 0;
                    }
                    board[currentPlaces]++;
                    currentPieces--;
                    Thread.sleep(0);
                }
                if (turn == 1 && currentPlaces == 7) {
                    return 1;

                } else if (turn == 2 && currentPlaces == 15) {

                    return 2;
                } else if (board[currentPlaces] == 1) {

                    if (turn == 1) {
                        if (checkMove(currentPlaces)) {
                            if (alreadyOneTurn) {
                                board[7] += board[7 + (7 - currentPlaces)];
                                board[7 + (7 - currentPlaces)] = 0;
                            }
                        }
                        return 2;
                    } else {
                        if (checkMove(currentPlaces)) {
                            if (alreadyOneTurn) {
                                board[15] += board[7 - (currentPlaces - 7)];
                                board[7 - (currentPlaces - 7)] = 0;
                            }
                        }
                        return 1;
                    }
                }
                currentPieces = board[currentPlaces];
                board[currentPlaces] = 0;

            }
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
        return 0;
    }
    public int moveGUI(int row) {
        int currentPieces;
        boolean finishTurn = false;
        boolean alreadyOneTurn = false;
        int currentPlaces = row;
        currentPieces = board[row];
        board[row] = 0;
        try {
            while (!finishTurn) {
                while (currentPieces > 0) {
                    currentPlaces++;
                    if (currentPlaces == 7 && turn != 1) {
                        currentPlaces++;
                        alreadyOneTurn = true;
                    } else if (currentPlaces == 15 && turn != 2) {
                        currentPlaces = 0;
                        alreadyOneTurn = true;
                    }
                    if (currentPlaces == 16) {
                        currentPlaces = 0;
                    }
                    board[currentPlaces]++;
                    currentPieces--;
                    Thread.sleep(250);
                    print();
                }
                if (turn == 1 && currentPlaces == 7) {
                    return 1;

                } else if (turn == 2 && currentPlaces == 15) {

                    return 2;
                } else if (board[currentPlaces] == 1) {

                    if (turn == 1) {
                        if (checkMove(currentPlaces)) {
                            if (alreadyOneTurn) {
                                board[7] += board[7 + (7 - currentPlaces)];
                                board[7 + (7 - currentPlaces)] = 0;
                            }
                        }
                        return 2;
                    } else {
                        if (checkMove(currentPlaces)) {
                            if (alreadyOneTurn) {
                                board[15] += board[7 - (currentPlaces - 7)];
                                board[7 - (currentPlaces - 7)] = 0;
                            }
                        }
                        return 1;
                    }
                }
                currentPieces = board[currentPlaces];
                board[currentPlaces] = 0;

            }
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
        return 0;
    }

    public static int minMaxAlphaBeta(Dakon board, int depth,int turn) {
        if (depth == 0||board.isFinished()) {
            return board.evalPoint();
            
        }
        if (board.getTurn() == 2) {
            
            int max = -99999;
            for (int i = 8; i < 15; i++) {
                Dakon newBoard = new Dakon(board);
                if (board.checkMove(i)) {
                    newBoard.setTurn(newBoard.move(i));
                    
                    if(!newBoard.equals(board)){      
                        int tempAlpha=alpha;
                        if(depth==5){
                            

                        }               
                        int recursiveScore = minMaxAlphaBeta(newBoard, depth - 1,newBoard.getTurn());

                        if (recursiveScore > max) {
                            max = recursiveScore;
                        }
                        if (alpha < recursiveScore) {
                            alpha=recursiveScore;
                        }
                        if (beta <= alpha) {
                           
                            break;
                        }
                }
            }
        }
            return max;

        
        } else {
            int min = 99999;
            for (int i =0; i <7; i++) {
                Dakon newBoard = new Dakon(board);
                if (board.checkMove(i)) {
                    newBoard.setTurn(newBoard.move(i));
                    
                    int tempbeta=beta;
                    int recursiveScore = minMaxAlphaBeta(newBoard, depth - 1,newBoard.getTurn());
                    if(depth==5){
                       
                    }
                
                    if (recursiveScore < min) {
                        min = recursiveScore;
                    }
                    if (beta > recursiveScore) {
                        beta = recursiveScore;
                    }
                    if (beta <= alpha) {
                        
                        break;
                    }
                }
            }
            return min;
        }

    }

    public static int moveAI(Dakon board) {
        int move=0;
        int max = -99999;
        for (int i = 8; i < 15; i++) {
            
            Dakon newBoard = new Dakon(board);
            if (board.checkMove(i)) {
                newBoard.move(i);
                alpha=-9999999;
                beta=9999999;
                int minmaxvalue = minMaxAlphaBeta(newBoard,5,board.getTurn());
                if (max < minmaxvalue) {
                    max = minmaxvalue;
                    move = i;
                }
        

            }
        }
        return move;

    }


    public static void main(String[] args) {
        Dakon dakonBoard = new Dakon();
        Random rand = new Random(); 
        Scanner scan = new Scanner(System.in);
        dakonBoard.print();
        System.out.println("Masukkan Pilihan Kotak");
        System.out.println("Sekarang Giliran player" + dakonBoard.getTurn());
        while (!dakonBoard.isFinished()) {

            dakonBoard.print();
            if(dakonBoard.turn==1){
                int selectTile = scan.nextInt();
                System.out.println(selectTile);
                if (dakonBoard.checkMove(selectTile)) {
                    dakonBoard.setTurn(dakonBoard.moveGUI(selectTile));

                } else {
                    System.out.println("Move Tidak Valid");

                }
            }
            else{
                int aimoves=moveAI(dakonBoard);
                System.out.println(aimoves);
                dakonBoard.setTurn(dakonBoard.moveGUI(aimoves));
            }
            dakonBoard.print();
            System.out.println("Sekarang Giliran player" + dakonBoard.getTurn());
            
        }

    }

}
