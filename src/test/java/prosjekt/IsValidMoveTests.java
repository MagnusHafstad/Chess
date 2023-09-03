
package prosjekt;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IsValidMoveTests {
    
    /*
     All tests here tests the validity of moves for the major pieces
     It does not test pawn as pawns have enough unike content that I will test the class seperatly 
    */
    
    Board board = new Board();
    

    @BeforeEach
    public void setup() {
        Board board=new Board();
    }



    @Test
    public void knightTest(){
        //Valid moves
        Assertions.assertTrue(board.getSquare(7, 6).isValidMove(5, 5));
        board.getSquare(7, 6).move(5, 5);
        Assertions.assertTrue(board.getSquare(0, 1).isValidMove(2, 2));
        board.getSquare(0, 1).move(2, 2);
        Assertions.assertTrue(board.getSquare(5, 5).isValidMove(7, 4),"Should be valid");
        Assertions.assertTrue(board.getSquare(5, 5).isValidMove(3, 4),"Should be valid");
        Assertions.assertTrue(board.getSquare(5, 5).isValidMove(4, 3),"Should be valid");
        Assertions.assertTrue(board.getSquare(5, 5).isValidMove(6, 3),"Should be valid");
        Assertions.assertTrue(board.getSquare(5, 5).isValidMove(6, 7),"Should be valid");
        //Invalid moves
        Assertions.assertFalse(board.getSquare(5, 5).isValidMove(5, 5), "Can't stand still as move");
        Assertions.assertFalse(board.getSquare(5, 5).isValidMove(7, 0), "Not correct movement patterns");
        Assertions.assertFalse(board.getSquare(5, 5).isValidMove(6, 5), "Not correct movement patterns");
        Assertions.assertFalse(board.getSquare(5, 5).isValidMove(3, 6), "Can't take own piece");
    }

    @Test
    public void bishopTest() {
        this.board= SaveHandler.LoadGameFromString("RB,NB,XX,QB,KB,BB,NB,RB,PB,PB,PB,XX,PB,PB,PB,PB,XX,XX,XX,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,BB,XX,XX,XX,XX,BW,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,XX,XX,XX,PW,PW,PW,PW,XX,PW,PW,PW,RW,NW,BW,QW,KW,XX,NW,RW,4");
        //Valid moves
        Assertions.assertTrue(board.getSquare(4, 2).isValidMove(4, 6),"Should be valid");
        Assertions.assertTrue(board.getSquare(4, 2).isValidMove(4, 2),"Should be valid");
        Assertions.assertTrue(board.getSquare(4, 2).isValidMove(0, 2),"Should be valid");
        Assertions.assertTrue(board.getSquare(4, 2).isValidMove(1, 5),"Should be valid");
        //Invalid moves
        Assertions.assertFalse(board.getSquare(4, 2).isValidMove(2, 4),"Can't stand still as move");
        Assertions.assertFalse(board.getSquare(4, 2).isValidMove(4, 4),"Not a diagonal");
        this.board=SaveHandler.LoadGameFromString("RB,NB,XX,QB,KB,BB,NB,RB,PB,PB,PB,XX,PB,PB,PB,PB,XX,XX,XX,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,BB,XX,XX,XX,XX,BW,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,PW,XX,PW,PW,PW,RW,NW,BW,QW,KW,XX,NW,RW,5");
        Assertions.assertFalse(board.getSquare(4, 2).isValidMove(4, 6),"No longer whites turn");
        Assertions.assertFalse(board.getSquare(3, 5).isValidMove(3, 5),"Bishops can't jump pieces");
    }

    @Test
    public void rookTest() {
        //Valid moves
        this.board=SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,PB,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,RW,XX,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,PW,PW,PW,PW,XX,NW,BW,QW,KW,BW,NW,RW,8");
        Assertions.assertTrue(board.getSquare(3, 3).isValidMove(3, 5),"Should be valid");
        Assertions.assertTrue(board.getSquare(3, 3).isValidMove(3, 2),"Should be valid");
        Assertions.assertTrue(board.getSquare(3, 3).isValidMove(0, 3),"Should be valid");

        Assertions.assertTrue(board.getSquare(3, 3).isValidMove(7, 3),"Should be valid");
        //Invalid moves
        this.board=SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,XX,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,RW,PB,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,XX,NW,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,PW,PW,PW,PW,XX,XX,BW,QW,KW,BW,NW,RW,10");
        Assertions.assertFalse(board.getSquare(3, 3).isValidMove(3, 3),"Standing still is not a valid move");
        Assertions.assertFalse(board.getSquare(3, 3).isValidMove(7, 3),"Rooks can't jump pieces");
        Assertions.assertFalse(board.getSquare(3, 3).isValidMove(7, 4),"Shouldn't valid movement pattern");
        Assertions.assertFalse(board.getSquare(3, 3).isValidMove(2, 4),"Shouldn't valid movement pattern");
    }   

    @Test
    public void qweenTest() {
        this.board= SaveHandler.LoadGameFromString("RB,NB,XX,QB,KB,BB,NB,RB,PB,PB,PB,XX,PB,PB,PB,PB,XX,XX,XX,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,BB,XX,XX,XX,XX,BW,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,XX,XX,XX,PW,PW,PW,PW,XX,PW,PW,PW,RW,NW,BW,QW,KW,XX,NW,RW,4");
        //Valid moves
        Assertions.assertTrue(board.getSquare(4, 2).isValidMove(4, 6),"Should be valid");
        Assertions.assertTrue(board.getSquare(4, 2).isValidMove(4, 2),"Should be valid");
        Assertions.assertTrue(board.getSquare(4, 2).isValidMove(0, 2),"Should be valid");
        Assertions.assertTrue(board.getSquare(4, 2).isValidMove(1, 5),"Should be valid");
        this.board=SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,PB,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,RW,XX,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,PW,PW,PW,PW,XX,NW,BW,QW,KW,BW,NW,RW,8");
        Assertions.assertTrue(board.getSquare(3, 3).isValidMove(3, 5),"Should be valid");
        Assertions.assertTrue(board.getSquare(3, 3).isValidMove(3, 2),"Should be valid");
        Assertions.assertTrue(board.getSquare(3, 3).isValidMove(0, 3),"Should be valid");
        Assertions.assertTrue(board.getSquare(3, 3).isValidMove(7, 3),"Should be valid");
        //Invalid moves
        this.board= SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,XX,PB,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,XX,XX,QW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,XX,XX,XX,PW,PW,PW,PW,XX,PW,PW,PW,RW,NW,BW,XX,KW,BW,NW,RW,3");
        Assertions.assertFalse(board.getSquare(3, 7).isValidMove(0, 3),"qweens can't jump pieces");
        Assertions.assertFalse(board.getSquare(3, 7).isValidMove(5, 4),"invalid movementpattern");
        Assertions.assertFalse(board.getSquare(3, 7).isValidMove(7, 3),"Standing still is not a valid move");
    }

    @Test 
    public void kingTest() {
        this.board=SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,XX,XX,RB,PB,PB,PB,PB,NB,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,XX,XX,XX,XX,BW,XX,PW,XX,XX,XX,BB,XX,XX,XX,XX,NW,XX,XX,PW,PW,PW,PW,XX,PW,PW,PW,RW,NW,BW,QW,KW,XX,XX,RW,6");
        //Valid moves
        Assertions.assertTrue(board.getSquare(7, 4).isValidMove(4,6),"King can move forward one square");
        Assertions.assertTrue(board.getSquare(7, 4).isValidMove(6,7),"Casteling should be leagal in this position");

        //Invalid moves
        this.board=SaveHandler.LoadGameFromString("RB,NB,BB,XX,KB,XX,XX,RB,PB,PB,XX,PB,NB,PB,PB,PB,XX,XX,PB,XX,XX,XX,XX,XX,XX,QB,XX,BW,PB,XX,XX,XX,XX,XX,XX,XX,PW,XX,XX,XX,PW,XX,XX,XX,XX,NW,XX,PW,PW,XX,PW,PW,XX,PW,PW,XX,RW,NW,BW,QW,KW,XX,XX,RW,12");
        Assertions.assertFalse(board.getSquare(7, 4).isValidMove(6,7),"Casteling should be illeagal when square f1 is protected");


    }






}
