package application;

import java.io.IOException;

import back_up.help_;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AppHomecontroller implements help_{


	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button create_BTN;

    @FXML
    private Button login_BTN;


    @FXML
    private Button help;


    @FXML
    void Create(ActionEvent event) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Create.fxml"));	
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		//root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add("./App.css");
		stage.setScene(scene);
		stage.show();

		
    }

    @FXML
    void Login(ActionEvent event) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../login/login.fxml"));	
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		//root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		//scene.getStylesheets().add("./login/User%20Acc%20Home.css");
		scene.getStylesheets().add("./App.css");
		stage.setScene(scene);
		stage.show();

		
    }
    
    @FXML
    void help_btn(ActionEvent event) {

    	help(event);
    }

	@Override
	public void help(ActionEvent event) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../back_up/help.fxml"));	
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		//root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		//scene.getStylesheets().add("./help.css");
		scene.getStylesheets().add("./App.css");
		stage.setScene(scene);
		stage.show();
	}
}
