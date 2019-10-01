import java.util.Scanner;

public class Dakon{
    static DakonBoard move(DakonBoard dakonboard,int idx) {
        boolean turnplayer1 = dakonboard.getPlayer1Turn();
        int[] tempboard = new int[14];
        int[] player = new int[2];
        player[0] = dakonboard.getPlayer1();
        player[1] = dakonboard.getPlayer2();
        for(int i=0; i<14; i++){
           tempboard[i] = dakonboard.getinBoard(i);
        }
        if (turnplayer1 && idx > 6) {
            return dakonboard;
        } else if (!turnplayer1 && idx < 7){
            return dakonboard;
        }
        if (tempboard[idx] == 0) {
            return dakonboard;
        }
        int space = (idx + 1) % 14;
        boolean lastStore = false;
        // Jika ada biji
        while (tempboard[idx] != 0) {
            tempboard[idx] = tempboard[idx]-1;
            if (!lastStore && turnplayer1 && space == 6) {
                player[0]++;
                lastStore = true;
            } else if (!lastStore && !turnplayer1 && space == 0) {
                player[1]++;
                lastStore = true;
            } else {
                tempboard[space]++;
                space = (space + 1) % 14;
                lastStore = false;
            }
        }
        // checks if dropped in your own store on last move, do not go past
        if (lastStore) {
            return new DakonBoard(tempboard, player[0], player[1], turnplayer1);
        }
        // set to last space
        space = Math.floorMod(space - 1, 14);
        // checks if dropped in an empty hole on your side, and there are pieces across
        if (turnplayer1 && space < 7 && tempboard[space] == 1 && tempboard[13 - space] > 0) {
            player[0] += tempboard[13 - space] + 1;
            tempboard[13 - space] = 0;
            tempboard[space] = 0;
        } else if (!turnplayer1 && space > 5 && tempboard[space] == 1 && tempboard[13 - space] > 0) {
            player[1] += tempboard[13 - space] + 1;
            tempboard[13 - space] = 0;
            tempboard[space] = 0;
        }
        // set to next player's move
        turnplayer1 = !turnplayer1;
        return new DakonBoard(tempboard, player[0], player[1], turnplayer1);
    }

    static int mini_max(DakonBoard dakonboard, int depth) {
        if (depth == 0 || dakonboard.is_No_Move()) {
            return dakonboard.getHeuristic();
        }
        if (dakonboard.getPlayer1Turn()) {
            int bestVal = Integer.MIN_VALUE;
            for (int i = 0; i < 7; i++) {
                DakonBoard newboard = move(dakonboard,i);
                if (!newboard.equals(dakonboard)) {
                    int val = mini_max(newboard, depth - 1);
                    bestVal = Math.max(val, bestVal);
                }
            }
            return bestVal;
        } else {
            int bestVal = Integer.MAX_VALUE;
            for (int i = 7; i < 14; i++) {
                DakonBoard newboard = move(dakonboard,i);
                if (!newboard.equals(dakonboard)) {
                    int val = mini_max(newboard, depth - 1);
                    bestVal = Math.min(val, bestVal);
                }
            }
            return bestVal;
        }
    }

    static int alphabeta(DakonBoard dakonboard, int depth, int alpha, int beta) {
        if (depth == 0 || dakonboard.is_No_Move()) {
            return dakonboard.getHeuristic();
        }
        if (dakonboard.getPlayer1Turn()) {
            int bestVal = Integer.MIN_VALUE;
            for (int i = 0; i < 7; i++) {
                DakonBoard newboard = move(dakonboard,i);
                if (!newboard.equals(dakonboard)) {
                    int val = alphabeta(newboard, depth - 1, alpha, beta);
                    bestVal = Math.max(val, bestVal);
                    alpha = Math.max(alpha, val);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return bestVal;
        } else {
            int bestVal = Integer.MAX_VALUE;
            for (int i = 7; i < 14; i++) {
                DakonBoard newboard = move(dakonboard,i);
                if (!newboard.equals(dakonboard)) {
                    int val = alphabeta(newboard, depth - 1, alpha, beta);
                    bestVal = Math.min(val, bestVal);
                    beta = Math.min(beta, val);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return bestVal;
        }
    }

    static int chooseMove(DakonBoard dakonboard) {
        // p1's turn
        if (dakonboard.getPlayer1Turn()) {
            int bestVal = Integer.MIN_VALUE;
            int max = 0;
            for (int i = 0; i < 7; i++) {
                DakonBoard newboard = move(dakonboard,i);
                if (!newboard.equals(dakonboard)) {
                    int val = alphabeta(newboard, 10, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (val > bestVal) {
                        max = i;
                        bestVal = val;
                    }
                }
            }
            return max;
        } else {
            int bestVal = Integer.MAX_VALUE;
            int min = 0;
            for (int i = 7; i < 14; i++) {
                DakonBoard newboard = move(dakonboard,i);
                if (!newboard.equals(dakonboard)) {
                    int val = alphabeta(newboard, 10, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (val < bestVal) {
                        min = i;
                        bestVal = val;
                    }
                }
            }
            return min;
        }
    }

    public static void main(String[] args) {
        DakonBoard dakonboard = new DakonBoard();
        Scanner s = new Scanner(System.in);
        System.out.print("Pilih sebagai Player 1 atau Player 2 : ");
        int player = s.nextInt();
        while(!dakonboard.is_No_Move()) {
            dakonboard.print();
            if ((player == 1 && dakonboard.getPlayer1Turn()) || (player == 2 && !dakonboard.getPlayer1Turn())) {
                System.out.println("Your turn: ");
                int move = s.nextInt();
                DakonBoard next = Dakon.move(dakonboard,move);
                if (dakonboard.equals(next)) {
                    System.out.println("Move invalid!");
                } else {
                    dakonboard = next;
                }
            } else {
                int move = Dakon.chooseMove(dakonboard);
                System.out.println(move);
                dakonboard = move(dakonboard,move);
            }
        }
        dakonboard.print();
    }

}
