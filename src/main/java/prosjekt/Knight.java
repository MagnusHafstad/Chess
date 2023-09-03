package prosjekt;

public class Knight extends Piece{        

    public Knight(char colour, int positionx, int positiony, Board board) {
        super(colour, positionx, positiony, board);
    }
    @Override
    public void setBoard(Board board){
        this.board=board;
    }
    @Override
    public void move(int x,int y){
        generalMove(x, y);
    }

    @Override
    public String toString() {
        return "N"  + getColour()+"";
    }

    public boolean isValidMove(int x,int y) {
        if(board.getSquare(y, x)==null || board.getSquare(y, x).getColour()!=this.getColour()){
            if((this.getPositionx()+2==x || this.getPositionx()-2==x ) && (getPositiony()==y-1 ||getPositiony()==y+1)){
                return true; 
            }
            if((this.getPositiony()+2==y || this.getPositiony()-2==y) && (getPositionx()==x-1 ||getPositionx()==x+1)){
                return true;
            }
        }
        return false;
        
    }
}
