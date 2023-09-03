package prosjekt;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import prosjekt.Board;
import prosjekt.SaveHandler;


public class FileWritingTest {
    
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


    @Test
    public void LoadGameFromStringTest() {
        Board LoadedBoard= SaveHandler.LoadGameFromString("RB,NB,BB,QB,KB,BB,NB,RB,PB,PB,PB,PB,PB,PB,PB,PB,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,XX,PW,PW,PW,PW,PW,PW,PW,PW,RW,NW,BW,QW,KW,BW,NW,RW,0");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board.getSquare(i, j)!=null){
                    Assertions.assertEquals(board.getSquare(i, j).toString(), LoadedBoard.getSquare(i, j).toString());
                }
            }   
        }
    }

    //Loading tests
    @Test
    public void InformationSavedTest() {
        Board loadedBoard = sh.load();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board.getSquare(i, j)!=null){
                    Assertions.assertEquals(board.getSquare(i, j).getColour()
                    ,loadedBoard.getSquare(i, j).getColour(), "Tests same color after loading");
                    Assertions.assertEquals(board.getSquare(i, j).getPositionx()
                    ,loadedBoard.getSquare(i, j).getPositionx(), "Tests same own x is the same after loading");
                    Assertions.assertEquals(board.getSquare(i, j).getPositiony()
                    ,loadedBoard.getSquare(i, j).getPositiony(), "Tests same own y is the same after loading");
                    Assertions.assertEquals(board.getSquare(i, j).getClass(),loadedBoard.getSquare(i,j).getClass(),"Tests that all squares have the same pieces");
                }
            }  
        }
    }

    @Test
    public void sameTurnTest() {
        Board loadedBoard = sh.load();
        Assertions.assertEquals(board.getTurnInt(), loadedBoard.getTurnInt());
    }

    @Test
    public void PartiallyPlayedGametest(){
        board.getSquare(6, 4).move(4, 4);
        board.getSquare(1, 4).move(4, 3);
        board.getSquare(7, 6).move(5, 5);
        board.getSquare(0, 1).move(2, 2);
        board.getSquare(7, 5).move(2, 4);
        sh.save(board);                     //This has to work for this test to work so it is tested indirectly
        InformationSavedTest();

    }






 

}
