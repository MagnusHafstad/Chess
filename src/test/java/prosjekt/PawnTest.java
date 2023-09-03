
package prosjekt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PawnTest {


    Board board = new Board();

    @BeforeEach
    public void setup() {
        this.board=new Board();
    }

    @Test
    public void forewardMovement(){
        //Leagal
        Assertions.assertTrue(board.getSquare(6, 4).isValidMove(4, 5));
        Assertions.assertTrue(board.getSquare(6, 4).isValidMove(4, 4));
        board.incrementTurn();
        Assertions.assertTrue(board.getSquare(1, 4).isValidMove(4, 2));
        Assertions.assertTrue(board.getSquare(1, 4).isValidMove(4, 3));
        //Illegal
    }

    @Test
    public void diagonalMovement() {
        //Leagal
        this.board= SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,XX,PB,PB,XX,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,XX,PB,XX,XX,XX,XX,XX,PW,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,PW,XX,XX,PW,PW,RW,NW,BW,QW,KW,BW,NW,RW,4");
        Assertions.assertTrue(board.getSquare(4, 4).isValidMove(3, 3));
        board.incrementTurn();
        Assertions.assertTrue(board.getSquare(3, 3).isValidMove(4, 4));

        //Illegal
        Assertions.assertFalse(board.getSquare(3, 3).isValidMove(2, 4),"Pawns can only move diagonally if taking a piece");
    }

    @Test
    public void promotionTest() {
        //NOTE: I have only implemented promotions to qween. In actual chess there are more options however it is almost never smart to promote to anyting but a qween.
        this.board=SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,KB,RB,PB,PW,PB,PB,PB,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,PW,PW,PW,PW,RW,NW,BW,QW,KW,BW,NW,RW,8");
        board.getSquare(1, 1).move(0,0);
        Assertions.assertTrue(board.getSquare(0,0) instanceof Qween);
    }

}
