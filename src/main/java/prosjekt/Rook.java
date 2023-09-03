package prosjekt;

public class Rook extends Piece{        
    private boolean hasMoved = false;   //Note: This attribute reset to false if you reload a game as this is not saved. 


    public Rook(char colour, int positionx, int positiony, Board board) {
        super(colour, positionx, positiony, board);
        this.board=board;
    }

    @Override
    public void move(int x,int y){
        if (generalMove(x, y))              //Moves general pieces
            this.setHasMoved();             //It is relevant weather a rook has moved or not this is saved here. 
        }
        

    @Override
    public String toString() {
        return "R"  + getColour()+"";
    }


    private boolean stepOver(int x, int y){
        if (this.getPositionx()==x && this.getPositiony()<y){ //Checks which diretion the rook is moving
            for (int i = getPositiony()+1; i < y; i++) {        //Loops through the sqares between start and finish
                if(board.getSquare(i, x) !=null){             //checks the sqares
                    return true;
                }
            }
        }
        if (this.getPositionx()==x && this.getPositiony()>y){ //Checks which diretion the rook is moving
            for (int i = getPositiony()-1; i > y; i-=1) {       //Loops through the sqares between start and finish
                if(board.getSquare(i, x) !=null){             //checks the sqares
                    return true;
                }
            }
        }
        if (this.getPositiony()==y && this.getPositionx()<x){ //Checks which diretion the rook is moving
            for (int i = getPositionx()+1; i < x; i++) {      //Loops through the sqares between start and finish
                if(board.getSquare(y, i) !=null){             //checks the sqares
                    return true;
                }
            }
        }
        if (this.getPositiony()==y && this.getPositionx()>x){ //Checks which diretion the rook is moving
            for (int i = getPositionx()-1; i > x; i--) {      //Loops through the sqares between start and finish
                if(board.getSquare(y, i) !=null){             //checks the sqares
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isValidMove(int x,int y){
        if (this.getPositionx()==x ||this.getPositiony()==y){
            if(stepOver(x,y)==false && (board.getSquare(y, x)==null || board.getSquare(y, x).getColour()!=this.getColour())){
                return true;
            }
        }
        return false;
    }
    public boolean getHasMoved() {
        return hasMoved;
    }
    public void setHasMoved() {
        this.hasMoved = true;
    }

    

}
