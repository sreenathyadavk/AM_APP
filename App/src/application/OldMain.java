package application;
	
import java.io.File;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class OldMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AppHome.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String Desktop_Path = System.getProperty("user.home") + "/Desktop/" + "Application DataBase";

		File file= new File(Desktop_Path);
		if(file.exists()) {
			
		}else {
			JOptionPane.showMessageDialog(null, "Application Files Not Found", "Error", JOptionPane.ERROR_MESSAGE);

		}
		
		launch(args);
	}
}
