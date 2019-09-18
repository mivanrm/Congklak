import java.util.*;
class Dakon{
    private int board[];
    private int turn;
    Dakon(){
        board=new int[16];
        turn=1;
        for(int i=0;i<board.length;i++){
            if(i!=7&&i!=15){
                board[i]=7;
            }
            else{
                board[i]=0;
            }
        }
    }
    public int getBoard(int place){
        return board[place];
    }
    public void setBoard(int place,int value){
        board[place]=value;
    }
    public int getTurn(){
        return turn;
    }
    public void setTurn(int _turn){
        turn=_turn;
    }
    public void print(){
        for(int i=0;i<3;i++){
            System.out.print(" ");
        }
        for(int i=8;i<15;i++){
            System.out.print(board[i]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.print(board[7]);
        for(int i=0;i<17;i++){
            System.out.print(" ");
        }
        System.out.println(board[15]);
        for(int i=0;i<3;i++){
            System.out.print(" ");
        }
        for(int i=6;i>=0;i--){
            System.out.print(board[i]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println();

    }
    public static boolean checkMove(int turn,int position){
        if(turn==1){
            return position==0||position==1||position==2||position==3||position==4||position==5||position==6;
        }
        else
            return position==8||position==9||position==10||position==11||position==12||position==13||position==14;
    }
    
    public int move(int row){
        int currentPieces;
        boolean finishTurn=false;
        boolean alreadyOneTurn=false;
        int currentPlaces=row;
        currentPieces=board[row];
        board[row]=0;
            try{
                while(!finishTurn){
                    while(currentPieces>0){
                        System.out.println("Current Pieces="+currentPieces);
                        currentPlaces++;
                        if(currentPlaces==7&&turn!=1){
                            currentPlaces++;
                            alreadyOneTurn=true;
                        }
                        else if(currentPlaces==15&&turn!=2){
                            currentPlaces=0;
                            alreadyOneTurn=true;
                        }
                        if(currentPlaces==16){
                            currentPlaces=0;
                        }
                        board[currentPlaces]++;
                        currentPieces--;
                        Thread.sleep(1000);
                        
                        print();
                    }
                    if(turn==1&&currentPlaces==7){
                        return 1;
                        
                    }
                    else if(turn==2&&currentPlaces==15){
                        
                        return 2;
                    }
                    else if(board[currentPlaces]==1){
                        
                        if(turn==1){
                            if(checkMove(turn,currentPlaces)){
                                if(alreadyOneTurn){
                                    board[7]+=board[7+(7-currentPlaces)];
                                    board[7+(7-currentPlaces)]=0;
                                }
                            }
                            return 2;               
                        }
                        else{
                            if(checkMove(turn,currentPlaces)){
                                if(alreadyOneTurn){
                                    board[15]+=board[7-(currentPlaces-7)];
                                    board[7-(currentPlaces-7)]=0;
                                }
                            }
                            return 1;
                        }
                    }
                    currentPieces=board[currentPlaces];
                    board[currentPlaces]=0;
                    
                }
            }
            catch (InterruptedException e) {
                System.err.format("IOException: %s%n", e);
            }
        return 0;
    }
    public static void main(String[] args) {
        Dakon test=new Dakon();
        Scanner scan =new Scanner(System.in);
        test.print();
        System.out.println("Masukkan Pilihan Kotak");
        System.out.println("Sekarang Giliran player"+test.getTurn());
        int selectTile=scan.nextInt();
        while(selectTile!=999){
            
            test.print();
            if(checkMove(test.getTurn(), selectTile)){
                test.setTurn(test.move(selectTile));
                
            }
            else{
                System.out.println("Move Tidak Valid");
                
            }
            test.print();
            System.out.println("Sekarang Giliran player"+test.getTurn());
            selectTile=scan.nextInt();
        }

            
    }
   
}



