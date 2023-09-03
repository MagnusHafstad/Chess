package prosjekt;

public class Bishop extends Piece{        

    public Bishop(char colour, int positionx, int positiony, Board board) {
        super(colour, positionx, positiony, board);
    }

    @Override
    public void move(int x,int y){
        generalMove(x, y);
    }

    @Override
    public String toString() {
        return "B"  + getColour()+"";
    }

    //Checks if bishop has to jump pieces in order to get to arrival square
    private boolean moveOver(int x,int y){
        if (getPositionx()<x){                                                              //Right down
            if (getPositiony()<y){
                for (int i = 1; i < x-getPositionx(); i++) {                        
                    if (board.getSquare(getPositiony()+i,getPositionx()+i) != null){
                        return true;
                    }
                }
            }
        }
        if (getPositionx()<x){                                                              //right up
            if (getPositiony()>y){
                for (int i = 1; i < x- getPositionx(); i++) {
                    if (board.getSquare(getPositiony()-i,getPositionx()+i) != null){
                        return true;
                    }
                }
            }
        }
        if (getPositionx()>x){                                                              //left down
            if (getPositiony()<y){
                for (int i = 1; i < getPositionx()-x; i++) {
                    if (board.getSquare(getPositiony()+i,getPositionx()-i) != null){
                        return true;
                    }
                }
            }
        }
        if (getPositionx()>x){                                                              //left up
            if (getPositiony()>y){
                for (int i =1; i < getPositionx()-x; i++) {
                    if (board.getSquare(getPositiony()-i,getPositionx()-i) != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Checks arrival square and impliments moveOver() in order to determine if move is valid
    public boolean isValidMove(int x, int y){
    if (board.getTurn()==getColour()){
        if (getPositionx()<x){                                              //The first two are direction checks, this one is right and up.
            if (getPositiony()>y){
                int temp=x-getPositionx();                                  //Finds out how far it moves on each diagonal
                if (getPositionx()+temp==x && getPositiony()-temp==y){      //Checks if move is on appropriate diagonal
                    if (!moveOver(x,y)){                                    //Checks no jumping pieces
                        //Checks that landing sqare is not own piece:
                        if (board.getSquare(getPositiony()-temp,getPositionx()+temp)==null || board.getSquare(getPositiony()-temp,getPositionx()+temp).getColour() !=getColour()){   
                            return true;
                        }   
                    }
                }
            }
        }
        if (getPositionx()<x){                                              //Right and down
            if (getPositiony()<y){
                int temp=x-getPositionx();                                  //Finds out how far it moves on each diagonal
                if (getPositionx()+temp==x && getPositiony()+temp==y){      //Checks if move is on appropriate diagonal
                    if (!moveOver(x,y)){                                    //Checks no jumping pieces
                        //Checks that landing sqare is not own piece:
                        if (board.getSquare(getPositiony()+temp,getPositionx()+temp)==null || board.getSquare(getPositiony()+temp,getPositionx()+temp).getColour() != getColour()){   
                            return true;
                        }   
                    }  
                }
            }
        }
        if (getPositionx()>x){                                              //left and up
            if (getPositiony()>y){
                int temp=getPositionx()-x;
                if (getPositionx()-temp==x && getPositiony()-temp==y){      //Checks if move is on appropriate diagonal
                    if (!moveOver(x,y)){                                    //Checks no jumping pieces
                        //Checks that landing sqare is not own piece:
                        if (board.getSquare(getPositiony()-temp,getPositionx()-temp)==null || board.getSquare(getPositiony()-temp,getPositionx()-temp).getColour() != getColour()){   
                            return true;
                        }   
                    }
                }
            }
        }
        if (getPositionx()>x){                                                  //left and down
            if (getPositiony()<y){
                    int temp=getPositionx()-x;
                    if (getPositionx()-temp==x && getPositiony()+temp==y){      //Checks if move is on appropriate diagonal
                        if (!moveOver(x,y)){                                    //Checks no jumping pieces
                            //Checks that landing sqare is not own piece:
                            if (board.getSquare(getPositiony()+temp,getPositionx()-temp)==null || board.getSquare(getPositiony()+temp,getPositionx()-temp).getColour() != getColour()){   
                                return true;
                            }   
                        }
                    }
                }
            }
        }

        return false;


    }
    


}
