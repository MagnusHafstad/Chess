package prosjekt;

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class SaveHandler implements ISaveHandler{
    private String ErrorMessage;

    public static String getFilePath(){
        String path = SaveHandler.class.getResource("saves/").getFile() + "game.txt";   //Because this method saves things in the target file restarting vscode will remove save
        return path;
    }

    public void save(Board board){
        try (PrintWriter writer = new PrintWriter(new File(getFilePath()))) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8 ; j++) {
                    if (board.getSquare(i, j)==null){
                        writer.print("XX,");
                    }
                    else{
                        writer.print(board.getSquare(i, j).toString()+","); 
                    }
                }
            }
        writer.print(board.getTurnInt());
        }   
        catch (FileNotFoundException e) {
            setErrorMessage("File not found by saver");
        } 
        catch (NullPointerException e) {
            setErrorMessage("No board to save");
        } 
    }

    //Loads a game from a file with a properly formatted string
    public Board load() {
        try (Scanner scanner = new Scanner(new File(getFilePath()))){
            String s = scanner.nextLine();
            return LoadGameFromString(s);
        }      
        catch (FileNotFoundException e) {
            setErrorMessage("File not found by loader");
            return new Board();
        }
        catch(ArrayIndexOutOfBoundsException f){
            setErrorMessage("Invalid string in save file");
            return new Board();
        }
        
    }
    
    //Loads a game from an approrpriate given string
    public static Board LoadGameFromString(String s) throws ArrayIndexOutOfBoundsException {          
        Board board = new Board();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    board.setSquare(j, i, null);
                }
            }
        
        String[] gameElements = s.split(",");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int nr = 8*i+j;
                // test switchccase
                switch (gameElements[nr].charAt(0)) {
                    case 'K':
                        board.setSquare(i, j, new King(gameElements[nr].charAt(1), j, i, board));
                        break;
                    case 'P':
                        board.setSquare(i, j, new Pawn(gameElements[nr].charAt(1), j, i, board));
                        break;
                    case 'R':
                        board.setSquare(i, j, new Rook(gameElements[nr].charAt(1), j, i, board));
                        break;
                    case 'N':
                        board.setSquare(i, j, new Knight(gameElements[nr].charAt(1), j, i, board));
                        break;
                    case 'B':
                        board.setSquare(i, j, new Bishop(gameElements[nr].charAt(1), j, i, board));
                        break;
                    case 'Q':
                        board.setSquare(i, j, new Qween(gameElements[nr].charAt(1), j, i, board));
                        break;
                
                }
            }
        }
        board.setTurn(Integer.valueOf(gameElements[64]));
        return board;}


    
    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }
            
    
    


    

}

