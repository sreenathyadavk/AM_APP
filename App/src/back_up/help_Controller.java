package back_up;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class help_Controller  implements help_{

    @FXML
    private Button home;

	private Stage stage;
	private Scene scene;
	private Parent root;
	

    @FXML
    void go_To_Home(ActionEvent event) {
    	help(event);
    }


	



	@Override
	public void help(ActionEvent event) {
		// TODO Auto-generated method stub

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/AppHome.fxml"));	

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
	
}
