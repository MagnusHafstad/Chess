package prosjekt;


public class Pawn extends Piece{
    

    public Pawn(char colour, int positionx, int positiony, Board board) {
        super(colour, positionx, positiony, board);
    }

    
    @Override
    public String toString() {
        return "P" + getColour()+"";
    }



    public boolean isValidMove(int x,int y){   
        if (this.getColour()=='W'&& board.getTurn()==this.getColour()){
            if (this.getPositionx()==x && getPositiony()-1==y){
                if(board.getSquare(y,x)== null){
                    return true;
                }
            }
            if (getPositiony()==6){                            //kan flytte 2 første runde
                if (this.getPositionx()==x && this.getPositiony()-2==y && board.getSquare((y+1),x)==null){ // må sjekke at ikke hopper over
                   if(board.getSquare(y,x)==null){                                                         // Kan ikke ta fremover                        
                        return true;}
                    }
            }    
            if (board.getSquare(y,x)!=null && board.getSquare(y,x).getColour()=='B' ){
                if ((this.getPositionx()+1==x && getPositiony()-1==y)||(this.getPositionx()-1==x && getPositiony()-1==y))
                    return true;
            }
        }
        // For svart
        if (this.getColour()=='B'&& board.getTurn()==getColour()){
            if (this.getPositionx()==x && getPositiony()+1==y){
                if(board.getSquare(y,x)==null){
                    return true;
                }
            }
            if (getPositiony()==1){                            //kan flytte 2 første runde, 
                if (this.getPositionx()==x && this.getPositiony()+2==y && board.getSquare((y-1),x)==null){
                   if(board.getSquare(y,x)==null){
                        return true;
                    }
                }
            }
            if (board.getSquare(y,x)!=null && board.getSquare(y,x).getColour()=='W' ){
                if ((this.getPositionx()+1==x && getPositiony()+1==y)||(this.getPositionx()-1==x && getPositiony()+1==y)){
                    return true;
                }
            }
        }
        
        return false; //Hvis trekket ikke er lovlig kommer den hit
    }
    @Override
    public void move(int x,int y){
            generalMove(x, y);
            if (this.getPositiony()==0||this.getPositiony()==7){
                board.setSquare(y, x, new Qween(this.getColour(), x, y, board));
            }
            
    }






}