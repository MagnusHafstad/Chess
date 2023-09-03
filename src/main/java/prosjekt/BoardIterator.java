package prosjekt;

import java.util.Iterator;



public class BoardIterator implements Iterator<Piece> {

    private int currentColumn;
    private int currentRow;
    private Board board;

    public BoardIterator(Board board){
        this.board=board;
    }
    @Override
    public boolean hasNext() {
        if (getCurrentRow()<8 && getCurrentColumn()<8){
            return true;
        }
        else return false;
    }

    @Override
    public Piece next() {
        Piece result= board.getSquare(this.getCurrentRow(),this.getCurrentColumn());
        if (getCurrentColumn()==7 && getCurrentRow()<7){
            currentColumn=0;
            currentRow++;
        }
        else{
            currentColumn++;
        }

        return result;
    }
    
    private int getCurrentColumn() {
        return currentColumn;
    }
    private int getCurrentRow() {
        return currentRow;
    }

}
