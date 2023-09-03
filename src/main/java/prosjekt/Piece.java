package prosjekt;

public abstract class Piece {
    private char colour;
    private int positionx;
    private int positiony;
    public Board board;        
    

    
    public Piece(char colour, int positionx, int positiony, Board board) {
        this.colour = colour;
        this.positionx = positionx;
        this.positiony = positiony;
        this.board=board;
    }

    public Piece(char colour, int positionx, int positiony) {
        this.colour = colour;
        this.positionx = positionx;
        this.positiony = positiony;
    }

    public void setBoard(Board board){
        this.board=board;
    }
    public int getPositionx() {
        return positionx;
    }
    public int getPositiony() {
        return positiony;
    }
    public char getColour() {
        return colour;
    }
    private void setPositionx(int positionx) {
        this.positionx = positionx;
    }

    private void setPositiony(int positiony) {
        this.positiony = positiony;
    }
    
    public abstract void move(int x, int y);         
    public abstract boolean isValidMove(int x, int y);

    //The function under deals with the parts of movement that are universal to all pieces and is used in all polymorfisms of pieces. 
    public boolean generalMove(int x, int y){
        if (generalTest(x, y)){                                             //Checks move        
            board.setSquare(y,x,this);                                      //Puts piece on new sqare
            board.setSquare(getPositiony(), getPositionx(), null);    //Removes piece
            setPositionx(x);                                                //Updates piece on own posision
            setPositiony(y);                                                //Updates piece on own posision
            board.incrementTurn();
            return true;
        }
        else {
            System.out.println("invalid move");
            return false;
        }
    }
    /*All moves (except casteling) have the same three validity requirments:
    - it is your turn,
    - it does not put you in check,
    - and the movementpattern is possible with the piece in question*/
    public boolean generalTest(int x, int y) {                                              //This is public to be used in controller
        return (isValidMove(x, y) && !isCheck(x,y) && this.getColour() == board.getTurn());
        
    }


    //Checks if a move puts yourself in check
    private boolean isCheck(int x, int y){      
        Board tempBoard = new Board() ;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tempBoard.setSquare(i, j, null);
                if (board.getSquare(i, j)!=null)
                    tempBoard.setSquare(i, j, board.getSquare(i, j));
            }
        }
        //This makes the move on the temporary board while saving the information needed to not destroy the actual one.
        tempBoard.setSquare(y, x, this);                                            //Makes the move
        int actualx=tempBoard.getSquare(y, x).getPositionx();                       //Saves actual posistion
        int actualy=tempBoard.getSquare(y, x).getPositiony();                       //Saves actual posistion  
        int actualTurn=board.getTurnInt();                                             //Saves actual turn
        tempBoard.setSquare(getPositiony(), getPositionx(), null);                  //Makes the move
        tempBoard.getSquare(y, x).setPositionx(x);                                  //Makes the move     
        tempBoard.getSquare(y, x).setPositiony(y);                                  //Makes the move                                  
        tempBoard.setTurn(actualTurn);  
        int tempKingx=tempBoard.getKingposx();                                      //Saves the kings position
        int tempKingy=tempBoard.getKingposy();                                      //Saves the kings position
        tempBoard.incrementTurn();
        
        for (Piece piece : tempBoard) {
            if((piece!=null)){
                Board save=board;
                piece.setBoard(tempBoard);                               //Tells the piece in question it is on the tempBoard
                if (piece.isValidMove(tempKingx, tempKingy)){             //Checks if and piece can take 
                    piece.setBoard(save);                                                           //Returns acurate board information to piece<
                    tempBoard.getSquare(y, x).setPositionx(actualx);                                                    //Returns the acual posistions to the piece    
                    tempBoard.getSquare(y, x).setPositiony(actualy);                                                    //Returns the acual posistions to the piece
                    tempBoard.setTurn(actualTurn);                                                                      //Sets the turn back
                    return true;
                }
                piece.setBoard(save);
            }
        }
        tempBoard.getSquare(y, x).setPositionx(actualx);                    //Returns acurate information to board.
        tempBoard.getSquare(y, x).setPositiony(actualy);                    //Returns acurate information to board.
        tempBoard.setTurn(actualTurn);                                      //Returns acurate information to board.
        return false; 
    }

}
