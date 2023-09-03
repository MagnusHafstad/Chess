
package prosjekt;

import java.util.Arrays;
import java.util.Iterator;

public class Board implements Iterable<Piece> {
    private Piece[][]squares;
    private int turn;
    private int kingposx;
    private int kingposy;
    

    public Board(){
        this.squares=new Piece[8][8];
        setBoard();
    } 


    private void setBoard(){                             //Sets board for new game
        //Pawns 
        for (int i = 0; i < 8; i++) {
            setSquare(6, i, new Pawn('W',i,6,this));
            setSquare(1, i, new Pawn('B',i,1,this));
        }
        ////Knights
        setSquare(7, 1, new Knight('W', 1, 7,this));
        setSquare(7, 6, new Knight('W', 6, 7,this));
        setSquare(0, 1, new Knight('B', 1, 0,this));
        setSquare(0, 6, new Knight('B', 6, 0,this));
        //Rooks
        setSquare(7, 7, new Rook('W', 7, 7,this));
        setSquare(7, 0, new Rook('W', 0, 7,this));
        setSquare(0, 7, new Rook('B', 7, 0,this));
        setSquare(0, 0, new Rook('B', 0, 0,this));
        //Bishops
        setSquare(7, 2, new Bishop('W', 2, 7,this));
        setSquare(7, 5, new Bishop('W', 5, 7,this));
        setSquare(0, 2, new Bishop('B', 2, 0,this));
        setSquare(0, 5, new Bishop('B', 5, 0,this));
        ////Qweens
        setSquare(7, 3, new Qween('W', 3, 7,this));
        setSquare(0, 3, new Qween('B', 3, 0,this));
        ////Kings
        setSquare(7, 4, new King('W', 4, 7,this));
        setSquare(0, 4, new King('B', 4, 0,this));
    }

    public Piece getSquare(int y, int x){       //The fact this funtion and setSquare has the y as first input
        return squares[y][x];                   //is absolutly rediculus, but at this point it would require a lot of unnessesary work to fix.
    }                                          

    public void setSquare(int y, int x, Piece piece) {
        this.squares[y][x]=piece;
    }

    public void printBoard(Board board){                       //This is exclusivly a development tool allowing for an easy print to console, and has no inpact on the app as it is never used
        for (int i = 0; i < squares.length; i++) {
            System.out.println(i+Arrays.toString(squares[i]));
        }
    }

    //Functions for keeping track of turns
    public char getTurn(){
        if (turn%2==0){
            return 'W';
        }
        else{
            return 'B';
        }
    }
    public int getTurnInt(){
        return turn;
    }
    public void incrementTurn(){
        turn+=1;
    }
    public void lastTurn(){
        turn-=1;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }

    //Keeps track of king positsions
    private void getOwnKing(){
        for (int i=0 ; i<8 ; i++){
            for (int j=0 ; j<8 ; j++){
                if (this.getSquare(i,j) instanceof King && this.getSquare(i,j).getColour()==this.getTurn()){
                    this.kingposx=j;
                    this.kingposy=i;
         
                }
            }
        }
    }
    public int getKingposx() { //This implementation is not best practice
        getOwnKing();
        return kingposx;
    }
    public int getKingposy() { //This implementation is not best practice
        getOwnKing();
        return kingposy;
    }


    @Override
    public Iterator<Piece> iterator() {
        return new BoardIterator(this);
    }

    public int gameEndedTest() {
        //Is there a leagal move?
        for (Piece piece : this) {
            if (piece != null){
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if(piece.generalTest(j, i)){ //Some move is leagal --> Game not over
                            return 0;
                        }
                    }
                }
            }
        }
        //The game is over but is it checkmate or draw?
        int kinPosx = getKingposx();
        int kinPosy = getKingposy();
        incrementTurn();
        for (Piece piece : this){
            if(piece != null && piece.generalTest(kinPosx, kinPosy)){   //Can a piece take the king, if yes it is checkmate
                lastTurn();
                return 1;
            }
        }
        lastTurn();
        return 2; //There is no leagal move, but king is not in check --> Stalemate
    }

}

