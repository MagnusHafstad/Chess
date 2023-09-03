package prosjekt;

public class Qween extends Piece{


    public Qween(char colour, int positionx, int positiony, Board board) {
        super(colour, positionx, positiony, board);
        this.board=board;
    }


    // Til Sensor:
    // The qween is just a combination of the Bishop and rook, because of this the only function to differ from these 
    // will be the isValidMove(x,y), move(x,y) and toString(). 
    // All new code for this class will be written in the top of the file. 

    //Also I realised it's spelled queen...


    @Override
    public void move(int x,int y){
        generalMove(x, y);}

    public boolean isValidMove(int x, int y){
        if (isValidMoveBishop(x, y)||isValidMoveRook(x, y)){
            return true;
        }
        else 
            return false;
    }
    @Override
    public String toString() {
        return "Q"  + getColour()+"";
    }




    //Beginning Bishop Code
    //Checks if bishop has to jump pieces in order to get to arrival square
    private boolean moveOverBishop(int x,int y){
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
                for (int i = getPositionx()+1; i < x- getPositionx(); i++) {
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

    //Checks arrival sqare and impliments moveOver() in order to determine if move is valid
    private boolean isValidMoveBishop(int x, int y){
    
        if (getPositionx()<x){                                              //The first two are direction checks, this one is right and up.
            if (getPositiony()>y){
                int temp=x-getPositionx();                                  //Finds out how far it moves on each diagonal
                if (getPositionx()+temp==x && getPositiony()-temp==y){      //Checks if move is on appropriate diagonal
                    if (!moveOverBishop(x,y)){                                    //Checks no jumping pieces
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
                    if (!moveOverBishop(x,y)){                                    //Checks no jumping pieces
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
                    if (!moveOverBishop(x,y)){                                    //Checks no jumping pieces
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
                        if (!moveOverBishop(x,y)){                                    //Checks no jumping pieces
                            //Checks that landing sqare is not own piece:
                            if (board.getSquare(getPositiony()+temp,getPositionx()-temp)==null || board.getSquare(getPositiony()+temp,getPositionx()-temp).getColour() != getColour()){   
                                return true;
                            }   
                        }
                    }
                }
            }
        return false;
    }
    //End Bishop code

    //Beginning rook code
    private boolean stepOverRook(int x, int y){
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


    private boolean isValidMoveRook(int x,int y){
        if (getColour()=='W'){
            if (this.getPositionx()==x ||this.getPositiony()==y){
                if(stepOverRook(x,y)==false && (board.getSquare(y, x)==null || board.getSquare(y, x).getColour()!='W')){
                    return true;
                }
            }
        }
        if (getColour()=='B'){
            if (this.getPositionx()==x ||this.getPositiony()==y){
                if(stepOverRook(x,y)==false &&(board.getSquare(y, x)==null || board.getSquare(y, x).getColour()!='B')){
                    return true;
                }
            }
        }
        return false;
    }
    //End rook code


}
