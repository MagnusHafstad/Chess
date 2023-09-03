package prosjekt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class PieceTest {

    Board board= new Board();
    SaveHandler sh = new SaveHandler();


    @BeforeEach
    private void setUp() {
        board = new Board();

    }


    @Test
    public void correctPositionTest() {
        for (Piece piece : board) {
            if(piece!=null){
                Assertions.assertEquals(piece, board.getSquare(piece.getPositiony(), piece.getPositionx()),"Board and piece should agree on positions");

            }
        }
    }

    @Test
    public void isCheckTest(){
        //The following moves are illegal because it is check
        board=sh.LoadGameFromString("RB,NB,BB,QB,XX,BB,NB,RB,PB,PB,PB,PB,XX,PB,PB,PB,XX,XX,XX,KB,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,XX,XX,XX,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,KW,XX,XX,XX,XX,PW,PW,PW,PW,XX,PW,PW,PW,RW,NW,BW,QW,XX,BW,NW,RW,6");
        Assertions.assertFalse(board.getSquare(5, 3).generalTest(3, 4), "king can't move into check");


        board=sh.LoadGameFromString("RB,NB,BB,XX,KB,BB,NB,RB,PB,PB,PB,PB,XX,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,XX,XX,XX,XX,XX,XX,PW,XX,XX,QB,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,PW,PW,XX,PW,PW,XX,RW,NW,BW,QW,KW,BW,NW,RW,4");
        Assertions.assertFalse(board.getSquare(6, 5).generalTest(5, 5), "Can't move piece to reveal king");


        board=sh.LoadGameFromString("RB,NB,BB,XX,KB,BB,NB,RB,PB,PB,PB,PB,XX,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,XX,XX,XX,XX,XX,XX,PW,XX,XX,XX,PW,XX,XX,XX,XX,XX,XX,PW,XX,PW,PW,PW,XX,QB,PW,XX,RW,NW,BW,QW,KW,BW,NW,RW,6");
        Assertions.assertFalse(board.getSquare(7,7).generalTest(7, 6), "Can't make move that does not remove check when in check");
        
    }






}





