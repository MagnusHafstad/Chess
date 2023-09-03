

package prosjekt;

public class King extends Piece{        
    private boolean hasMoved =false;
    private boolean longCastle=false;
    private boolean shortCastle=false;


    public King(char colour, int positionx, int positiony, Board board) {
        super(colour, positionx, positiony, board);
    }
    

    @Override
    public void move(int x,int y){
        if (generalMove(x, y)){
            setHasMoved();
            if (shortCastle==true){
                board.setSquare(y,5,new Rook(this.getColour(), 5, y, board));                             //Puts piece on new square
                board.setSquare(y, 7, null);                                                              //Removes piece
                shortCastle=false;
            }
            if (longCastle==true){
                board.setSquare(y,3,new Rook(this.getColour(), 3, y, board));                             //Puts piece on new square
                board.setSquare(y, 0, null);                                                              //Removes piece
                longCastle=false;
            }             
        }
    }
        

    @Override
    public String toString() {
        return "K"  + getColour()+"";
    }

    public boolean isValidMove(int x, int y){
        if(getPositionx()==x+1 && getPositiony()==y ||      //Tests moves
        getPositionx()==x+1 && getPositiony()==y+1  ||
        getPositionx()==x+1 && getPositiony()==y-1  ||
        getPositionx()==x-1 && getPositiony()==y    ||
        getPositionx()==x-1 && getPositiony()==y+1  ||
        getPositionx()==x-1 && getPositiony()==y-1  ||
        getPositionx()== x && getPositiony()==y+1   ||
        getPositionx()== x && getPositiony()==y-1){
            if (board.getSquare(y, x)==null || board.getSquare(y, x).getColour()!=board.getSquare(getPositiony(), getPositionx()).getColour()){
                return true;
            }
        }
        
        //Tests casteling
        if (!this.getHasMoved() && this.getPositiony()==y && !castelingBlocked(this.getPositionx(),this.getPositiony())){ //Checks that king has not moved (Indirectly also tests position along x axis), is casteling on its own side of the board and not currently in check.
            if (x==2){
                if (board.getSquare(y, 0) instanceof Rook && ((Rook) board.getSquare(y, 0)).getHasMoved()==false){  //Checks that there is a rook that has not moved in the correct square
                    if (board.getSquare(y, 1)==null && board.getSquare(y, 2)==null && board.getSquare(y, 3)==null){ //Checks that no pices are moved over, since the squares are few and konstant i decided to hardcode them
                        if (!castelingBlocked(3, y)){                                                                //Checks that king isn't moving over coverd field 
                            this.longCastle=true;
                            return true;
                            }
                        }
                    }
                }
            
        
            if(x==6 ){
                if (board.getSquare(y, 7) instanceof Rook && ((Rook) board.getSquare(y, 7)).getHasMoved()==false){
                    if (board.getSquare(y, 5)==null && board.getSquare(y, 6)==null){
                        if (!castelingBlocked(5, y)){                                                                //Checks that king isn't moving over coverd field 
                            this.shortCastle=true;
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;       //If move is not leagal 
    }



    private boolean getHasMoved(){
        return this.hasMoved;
    }
    private void setHasMoved(){
        this.hasMoved=true;
    }

    //This is a modification of the isCheck()  function in Piece that is nessesary for preventing casteling over covered squares
    private boolean castelingBlocked(int x, int y){
        for (Piece piece : board) {
                if((piece!=null)){
                    board.incrementTurn();
                    if (piece.getColour()==board.getTurn() && piece.isValidMove(x,y)){             //Checks if and piece can take                                                                   
                        board.lastTurn();
                        return true;
                    }
                    board.lastTurn();
                }
            }
        
        return false; 
    }


}


