package application;

import java.io.File;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class hmain extends Application {
	String Desktop_Path = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/App Data/dark.css";

	@Override
	public void start(Stage primaryStage) {
		try {
//		Parent root = FXMLLoader.load(getClass().getResource("Create_In.fxml"));
		Parent root = FXMLLoader.load(getClass().getResource("AppHome.fxml"));
		//Parent root = FXMLLoader.load(getClass().getResource("../user/user home.fxml"));
	//	File f = new File(Desktop_Path);
		//Parent root = FXMLLoader.load(getClass().getResource("Shopping.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("../dark.css").toExternalForm());
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.getStylesheets().add("./App.css");

			//scene.getStylesheets().add(/* Desktop_Path + */ f.getAbsolutePath() .replace("\\", "/")  );
			//scene.getStylesheets().add(getClass().getResource("../white.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	public static void main(String[] args) {
		String Desktop_Path = System.getProperty("user.home") + "/Desktop/" + "Application DataBase";
		
		System.out.println(Desktop_Path);
		
		File file= new File(Desktop_Path);
		if(file.exists()) {
			System.out.println(Desktop_Path);
		}else {
			JOptionPane.showMessageDialog(null, "Application Files Not Found", "Error", JOptionPane.ERROR_MESSAGE);

		}
		
		launch(args);
	}
	}
//