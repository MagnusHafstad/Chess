package prosjekt;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class BoardTest {
    Board board;
    SaveHandler sh = new SaveHandler();

    @BeforeEach
    public void setUp(){
        this.board=new Board();
    }

    
    @Test
    public void getOwnkingTest(){
        Assertions.assertEquals(7, board.getKingposy());
        Assertions.assertEquals(4, board.getKingposx());
        this.board= SaveHandler.LoadGameFromString("RB,XX,BB,QB,XX,BB,NB,RB,PB,PB,PB,PB,XX,PB,PB,PB,XX,XX,NB,XX,XX,XX,XX,XX,XX,XX,KB,XX,PB,XX,XX,XX,XX,XX,XX,XX,PW,XX,XX,XX,XX,XX,KW,XX,XX,NW,XX,XX,PW,PW,PW,PW,XX,PW,PW,PW,RW,NW,BW,QW,XX,BW,XX,RW,11");
        Assertions.assertEquals(3, board.getKingposy());
        Assertions.assertEquals(2, board.getKingposx());

    }

    @Test
    public void TurnTest(){
        Assertions.assertEquals('W', board.getTurn(),"First move is white");
        board.getSquare(6, 4).move(4, 4);
        Assertions.assertEquals('B', board.getTurn(),"Second move is blacks");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board=new Board(); //Resets board for guarnteed test independence
                if (board.getSquare(i, j)!=null){
                    board.getSquare(i, j).move(j, i);
                    Assertions.assertEquals('W', board.getTurn(),"(zugzwang) Pieces should not be able to move to their own square to waste a turn");
                
                    //Runs through possible moves
                    for (int j2 = 0; j2 < 8; j2++) {
                        for (int k = 0; k < 8; k++) {
                            if (!board.getSquare(i, j).isValidMove(k, j2)){
                                board.getSquare(i, j).move(k, j2);
                                Assertions.assertEquals('W', board.getTurn(),"Invalid moves shuld not waste turns");
                
                            }
                        
                        }
                    }
                }

            }
        }
    }

    @Test
    public void GameEndedTest() {
        board=SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,XX,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PB,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,PW,PW,XX,XX,PW,RW,NW,BW,QW,KW,BW,NW,RW,3");
        board.getSquare(0, 3).move(7, 4);
        Assertions.assertTrue(board.gameEndedTest()==1, "Should return 1 as this is checkmate"); //This is checkmate

        board=SaveHandler.LoadGameFromString("XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,KB,XX,XX,XX,XX,QB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,KW,13");
        board.getSquare(6, 3).move(5, 6);
        Assertions.assertTrue(board.gameEndedTest()==2, "Should return 2 as this is Stalemate");
    }
}
