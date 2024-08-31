package application;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class NameController {
	
	

	String Desktop_Path = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/";
	
	String name;
	int randomName;
	String MergeName;
	
	String InputName;

    @FXML
    private Label User_ID;
    @FXML
    private Button close_BTN;
    @FXML
    private Label Name1;
    @FXML
    private Button view_Details_BTN;
	@FXML
    private Button copy_BTN;
	
    @FXML
    private Label Message;
    

	private Stage stage;
	private Scene scene;
	private Parent root;
    
    @FXML
    void close(ActionEvent event) {
    	Stage stage = (Stage) close_BTN.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    void Copy(ActionEvent event) throws IOException {
    	Message.setText("Turn on the Application Again To Countinue");
    	String copy = MergeName;
    	StringSelection stringSelection = new StringSelection(copy);
    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	clipboard.setContents(stringSelection, null);
    	
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("./Create_In.fxml"));
		root = loader.load();
		Create_Controller_2 CA = loader.getController();
		CA.inScene(copy);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
    	    
    }
	
	public void GenrateName(String id) throws Exception {
		InputName = id;
		
	}

    @FXML
    void view_Info(ActionEvent event) throws Exception {
		FileReader reader = new FileReader(Desktop_Path + "App Data/Genrate User Name.properties" );
		Properties p = new Properties();
		p.load(reader);
		name = p.getProperty("User_Name");
		User_ID.setText(name + " is UnAvalabile!!!");
		Random r = new Random(/*System.currentTimeMillis()*/);
		randomName =r.nextInt(1000);
		MergeName = name + String.valueOf(randomName);
		Name1.setText(MergeName);
		copy_BTN.setVisible(true);
		//User_ID.setText(InputName);
    	
		
		
    }
    
}
