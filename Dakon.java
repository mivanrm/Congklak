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
    public void setBoard(int _turn){
        turn=_turn;
    }
    public void print(){
        for(int i=0;i<3;i++){
            System.out.print(" ");
        }
        for(int i=8;i<15;i++){
            System.out.print(board[i]);
        }
        System.out.println();
        System.out.print(board[7]);
        for(int i=0;i<11;i++){
            System.out.print(" ");
        }
        System.out.println(board[15]);
        for(int i=0;i<3;i++){
            System.out.print(" ");
        }
        for(int i=6;i>=0;i--){
            System.out.print(board[i]);
        }
        System.out.println();
        System.out.println();

    }
    
    public int move(int row){
        int currentPieces;
        boolean finishTurn=false;
        int currentPlaces=row;
        currentPieces=board[row];
        board[row]=0;
            try{
                
                print();
                while(!finishTurn){
                    while(currentPieces>0){
                        System.out.println("Current Pieces="+currentPieces);
                        currentPlaces++;
                        if(currentPlaces==7&&turn!=1){
                            currentPlaces++;
                        }
                        else if(currentPlaces==15&&turn!=2){
                            currentPlaces=0;
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
                        
                        return 0;
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
        test.print();
        test.move(2);
        test.print();
    }
   
}



