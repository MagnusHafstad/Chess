package prosjekt;

import java.util.Scanner;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GameTest {
    private Board board;
    private SaveHandler sh;

    @BeforeEach
    private void TestSaveLoad(){
        Board board = new Board();
        this.board=board;
        SaveHandler sh=new SaveHandler();
        this.sh=sh;
        sh.save(board);
    }


    
    public static String loadGameAsString(){    //Used in testing
        try (Scanner scanner = new Scanner(new File(SaveHandler.getFilePath()))){
            return scanner.nextLine();
        }       
        catch (Exception e) {
            System.out.println("From loadGameAsString" + e);
            return "No String returned";
        }
    }

    

    //Pawns
    @Test
    public void PawnForwardMovementTest() {
        board.getSquare(6, 4).move(4, 4);       //Double movment first turn
        board.getSquare(1, 4).move(4, 3);       //Double movment first turn
        board.getSquare(6, 0).move(0, 5);       //Single movment 
        board.getSquare(1, 0).move(0, 2);       //Single movment
        sh.save(board);
        Assertions.assertEquals("RB,NB,BB,QB,KB,BB,NB,RB,XX,PB,PB,PB,XX,PB,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,XX,XX,XX,XX,XX,XX,PW,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,XX,PW,PW,PW,RW,NW,BW,QW,KW,BW,NW,RW,4"
        , loadGameAsString());
    }
    @Test
    public void PawnTakeTest(){
        board=SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,XX,PB,XX,PB,XX,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,PB,XX,PB,XX,XX,PW,XX,PW,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,XX,PW,XX,PW,XX,PW,PW,RW,NW,BW,QW,KW,BW,NW,RW,6");
        board.getSquare(4, 1).move(2, 3);
        board.getSquare(3, 4).move(3, 4);
        sh.save(board);
        Assertions.assertEquals("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,XX,PB,XX,PB,XX,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,XX,XX,XX,PB,XX,XX,XX,XX,PB,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,XX,PW,XX,PW,XX,PW,PW,RW,NW,BW,QW,KW,BW,NW,RW,8"
        ,loadGameAsString());
    }
    @Test
    public void IllegalPawnTake(){
        board= SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,XX,PB,XX,PB,XX,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,XX,XX,XX,PB,XX,XX,XX,XX,PB,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,XX,PW,XX,PW,XX,PW,PW,RW,NW,BW,QW,KW,BW,NW,RW,8");
        board.getSquare(3, 2).move(3, 4); //Can't take backwards
        board.getSquare(6, 0).move(1, 5); //Pawn has to take a piece to move diagonaly. 
        sh.save(board);
        Assertions.assertEquals("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,XX,PB,XX,PB,XX,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,XX,XX,XX,PB,XX,XX,XX,XX,PB,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,XX,PW,XX,PW,XX,PW,PW,RW,NW,BW,QW,KW,BW,NW,RW,8"
        ,loadGameAsString());
    }
    @Test
    public void PromotingPawns(){   //Note: promotion only implemented to qween
        board = SaveHandler.LoadGameFromString("RB,NB,BB,QB,XX,BB,NB,RB,PB,PB,PB,KB,PB,PW,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,PB,PW,XX,PW,PW,XX,RW,NW,BW,QW,KW,BW,NW,RW,10");
        board.getSquare(1, 5).move(6, 0);
        board.getSquare(6, 2).move(3, 7);
        sh.save(board);
        Assertions.assertEquals("RB,NB,BB,QB,XX,BB,QW,RB,PB,PB,PB,KB,PB,XX,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,XX,PW,XX,PW,PW,XX,RW,NW,BW,QB,KW,BW,NW,RW,12"
        ,loadGameAsString());
    }

    //King
    @Test
    public void legalKingMoves(){
        board= SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,XX,XX,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,PB,XX,XX,XX,XX,XX,XX,PW,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,PW,XX,XX,PW,PW,RW,NW,BW,QW,KW,BW,NW,RW,4");
        board.getSquare(7, 4).move(4, 6);
        board.getSquare(0, 4).move(5, 1);
        board.getSquare(6, 4).move(3, 5);
        board.getSquare(1, 5).move(4, 0);
        sh.save(board);
        Assertions.assertEquals("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,XX,XX,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,PB,XX,XX,XX,XX,XX,XX,PW,PW,XX,XX,XX,XX,XX,KW,XX,XX,XX,XX,PW,PW,PW,PW,XX,XX,PW,PW,RW,NW,BW,QW,XX,BW,NW,RW,8"
        , loadGameAsString());
    }
    @Test
    public void KingMoveIntoCheck(){
        board= SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,XX,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,BW,XX,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,XX,PW,PW,PW,PW,RW,NW,XX,QW,KW,BW,NW,RW,3");
        board.getSquare(0, 4).move(4, 1);
        sh.save(board);
        Assertions.assertEquals("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,XX,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,BW,XX,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,XX,PW,PW,PW,PW,RW,NW,XX,QW,KW,BW,NW,RW,3"
        , loadGameAsString());
    }

    //Bishop
    @Test
    public void BishopLegalMoves(){
        board=SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,XX,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,BW,XX,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,XX,PW,PW,PW,PW,RW,NW,XX,QW,KW,BW,NW,RW,3");
        board.getSquare(0, 5).move(0, 5);
        board.getSquare(3, 6).move(7, 4);
        sh.save(board);
        Assertions.assertEquals("RB,NB,BB,QB,KB,XX,NB,RB,PB,PB,PB,PB,XX,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,XX,XX,XX,XX,XX,PW,XX,XX,XX,BW,BB,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,XX,PW,PW,PW,PW,RW,NW,XX,QW,KW,BW,NW,RW,5"
        , loadGameAsString());
    }

    @Test
    public void BishopILLegalMoves(){
        board=SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,XX,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,BW,XX,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,XX,PW,PW,PW,PW,RW,NW,XX,QW,KW,BW,NW,RW,3");
        board.getSquare(0, 5).move(0, 8);
        board.getSquare(3, 6).move(2, 4);
        sh.save(board);
        Assertions.assertEquals("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,XX,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,BW,XX,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,XX,PW,PW,PW,PW,RW,NW,XX,QW,KW,BW,NW,RW,3"
        , loadGameAsString());
    }

    @Test
    public void BishopCantMoveOverPiece(){
        board=SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,XX,XX,PB,PB,XX,XX,XX,XX,XX,PB,XX,XX,XX,XX,XX,XX,PB,XX,BW,XX,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,XX,PW,PW,PW,PW,RW,NW,XX,QW,KW,BW,NW,RW,4");
        board.getSquare(3, 6).move(4, 1);
        sh.save(board);
        Assertions.assertEquals("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,XX,XX,PB,PB,XX,XX,XX,XX,XX,PB,XX,XX,XX,XX,XX,XX,PB,XX,BW,XX,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,XX,PW,PW,PW,PW,RW,NW,XX,QW,KW,BW,NW,RW,4"
        , loadGameAsString());
    }

    //Rook 
    @Test
    public void RookLegalMoves(){
        board= SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,PB,PB,PB,PB,PB,PB,PB,PW,PW,PW,PW,PW,PW,PW,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,RW,NW,BW,QW,KW,BW,NW,RW,16");{
        board.getSquare(7, 0).move(0, 5);
        board.getSquare(0, 7).move(7, 2);
        board.getSquare(5, 0).move(7, 5);
        board.getSquare(2, 7).move(0, 2);   
        sh.save(board);
        Assertions.assertEquals("RB,NB,BB,QB,KB,BB,NB,XX,XX,XX,XX,XX,XX,XX,XX,XX,RB,XX,XX,XX,XX,XX,XX,XX,PB,PB,PB,PB,PB,PB,PB,PB,PW,PW,PW,PW,PW,PW,PW,PW,XX,XX,XX,XX,XX,XX,XX,RW,XX,XX,XX,XX,XX,XX,XX,XX,XX,NW,BW,QW,KW,BW,NW,RW,20"
        , loadGameAsString());
        }
    }


    //knight 
    @Test
    public void KnightTest(){
        board.getSquare(7, 1).move(2, 5);
        board.getSquare(0, 1).move(2, 2);
        board.getSquare(5, 2).move(4, 4); 
        sh.save(board);
        Assertions.assertEquals("RB,XX,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,PB,PB,PB,PB,XX,XX,NB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,NW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,PW,PW,PW,PW,PW,RW,XX,BW,QW,KW,BW,NW,RW,3"
        , loadGameAsString());
    }
    

}