package prosjekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ChessApp extends Application{ 
    @Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("ChessApp");
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Board.fxml"))));
		primaryStage.show();
    }
	
    public static void main(final String[] args) {
		// Application.launch(args);
		Application.launch(ChessApp.class, new String[0]);
		
    }
}