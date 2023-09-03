
package prosjekt;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

public class ChessController {
    
    
    @FXML private GridPane brett;
    @FXML private Button NewGame;
    @FXML private Button SaveGame;
    @FXML private Button LoadGame;
    @FXML private TextField gameText;
    private Board board;
    private int startx; //Saves posistion form click start to click end. 
    private int starty; //Saves posistion form click start to click end. 
    private int onClickState;

    

    @FXML
    public void init(){
        board = new Board();
        interfaceUpdate();
    }
    
    private void interfaceUpdate(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board.getSquare(i, j)!=null){
                    getNodeByRowColumnIndex(i, j, brett).setText(board.getSquare(i,j).toString()); //Sets the square
                }
                if (board.getSquare(i, j)==null){                                   
                    getNodeByRowColumnIndex(i, j, brett).setText("");   
                    }
                    
            }
        }

    }
    //getNodeByRowColumnIndex hentet fra 21.02.2022: https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column
    //This has to be cast as text in this spesific project
    private Text getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return (Text) result;
    }

    private int getStartx() {
        return startx;
    }
    private int getStarty() {
        return starty;
    }

    //Delen av koden som går på å hente koordinater hentet 21.02.2022: https://stackoverflow.com/questions/45219540/javafx-get-index-row-and-index-col-by-onclick-on-gridpane 
    public void handleMove(MouseEvent e){
        if (onClickState==0){
            Node source = (Node)e.getSource();
            Integer x = GridPane.getColumnIndex(source);
            Integer y = GridPane.getRowIndex(source);
            this.starty=y;
            this.startx=x;
            gameText.setText("");       //Removes previus message
            onClickState=1;
        }
        else {
            Node end = (Node)e.getSource();
            Integer x = GridPane.getColumnIndex(end);
            Integer y = GridPane.getRowIndex(end);
            if (board.getSquare(getStarty(),getStartx())!= null){
                if(!board.getSquare(getStarty(),getStartx()).generalTest(x, y)){
                    gameAlert("Invalid move");                          //Tells you the move is invalid if it's invalid
                    }
                board.getSquare(getStarty(),getStartx()).move(x,y);//Changes board in model
            }
            if (board.gameEndedTest()==1){
                gameAlert("Checkmate!");                //Announces mate
            }
            if (board.gameEndedTest()==2){
                gameAlert("Stalemate!");                //Announces stalemate
            }
            interfaceUpdate();                                      //Updates
            onClickState=0;
        }
    }

    @FXML
    private void handleSaveGame(){
        SaveHandler sh=new SaveHandler();
        sh.save(board);
        gameAlert(sh.getErrorMessage());
        sh.setErrorMessage("");
    }
    @FXML
    private void handleLoadGame(){
        SaveHandler sh=new SaveHandler();
        this.board=sh.load();
        gameAlert(sh.getErrorMessage());
        interfaceUpdate();
        sh.setErrorMessage("");
    }

    public void gameAlert(String message) {
        gameText.setText(message);
    }
    
    
}