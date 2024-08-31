package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import Error_Writter.Write_Error;
import back_up.back_up_data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Themes {

	String Desktop_Path = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/";

	private Stage stage;
	private Scene scene;
	private Parent root;

	String loginFile =  System.getProperty("user.home") + "/Desktop/" + "Application DataBase/App Data/logined.properties";
	String logined_user_file ;
    @FXML
    private AnchorPane frame;

    @FXML
    private Button Dark_BTN;

    @FXML
    private Button White_Btn;
    
    String user;
    String theme;
    String user_settings_file;
    
	Write_Error w = new Write_Error();
	back_up_data b = new back_up_data();
	
    @FXML
    void dark(ActionEvent event) throws Exception {
    	
    	Get_User();
    	System.out.println("dark");
    	
    	FileInputStream in = new FileInputStream(System.getProperty("user.home") + "/Desktop/" + "Application DataBase/users/"+ user +"/settings.properties");
    	Properties p = new Properties();
    	p.load(in);
    	in.close();

    	FileOutputStream out = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + "Application DataBase/users/"+ user +"/settings.properties");
    	p.setProperty("Theme", "dark");
    	theme = "dark";
    	p.store(out, null);
    	out.close();
    	Home(event);
    }

	/** this method get's user's theme from user's file
	 * @throws IOException
	 */
	private void Get_User() throws IOException {
		Properties p = new Properties();
    	FileReader r = new FileReader(loginFile);
    	p.load(r);
    	user = p.getProperty("Login_User");
	}
	

    @FXML
    void white(ActionEvent event) throws Exception {
    	Get_User();
    	FileInputStream in = new FileInputStream(System.getProperty("user.home") + "/Desktop/" + "Application DataBase/users/"+ user +"/settings.properties");
    	Properties p = new Properties();
    	p.load(in);
    	in.close();

    	FileOutputStream out = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + "Application DataBase/users/"+ user +"/settings.properties");
    	p.setProperty("Theme", "white");
    	theme = "white";
    	p.store(out, null);
    	out.close();
			Home(event);
			
    }

	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void get_data(String user) throws FileNotFoundException, IOException {
		user_settings_file = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/users/"+ user +"/settings.properties";
		String logined_user_file = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/users/" + user + "/" + user + ".properties";
		FileInputStream in = new FileInputStream(logined_user_file);//1
    	Properties p = new Properties();
    	p.load(in);
    	in.close();

    	FileOutputStream out = new FileOutputStream(logined_user_file);//3
		b.create_back_up(user, p.getProperty("ID"), p.getProperty("Mobile_Number"), p.getProperty("Pin"), p.getProperty("DOB"), p.getProperty("Email"), theme);

    	p.store(out, null);
    	out.close();
	}
    

	/**
	 * this method takes the user to home window
	 * @param event
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private void Home( ActionEvent event ) throws FileNotFoundException, IOException {
		
		try {
			get_data(user);
		} catch (Exception e) {
			w.FileNotFound(user, e.getMessage());
		}

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../user/user home.fxml"));	
		try {
			root = loader.load();
		} catch (IOException e) {
			w.Null_Error(user, e.getMessage());
		}	
		
		
		//root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		set_theme();
		stage.setScene(scene);//important
		stage.show();
	}

	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void set_theme() throws FileNotFoundException, IOException {
		FileReader r = new FileReader(user_settings_file);
    	Properties p = new Properties();
    	p.load(r);
    	String theme = p.getProperty("Theme");
    	;
		if(theme.equals("dark")) {
			scene.getStylesheets().add("./dark.css");
		}
		
		if(theme.equals("white")) {		
			scene.getStylesheets().add("./white.css");
		}
		
	}
	
	
	/*
	Properties p = new Properties();
	FileReader r = new FileReader(loginFile);
	p.load(r);
	user = p.getProperty("Login_User");
	System.out.println("dark");

	Properties Dark_p = new Properties();
FileReader Dark_r = new FileReader(System.getProperty("user.home") + "/Desktop/" + "Application DataBase/users/"+ user +"/settings.properties");
	Dark_p.load(Dark_r);
	*/

}
