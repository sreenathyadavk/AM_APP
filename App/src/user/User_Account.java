package user;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import Error_Writter.Write_Error;
import back_up.help_;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class User_Account implements help_ , Initializable {

    @FXML
    private AnchorPane window;

    @FXML
    private Button help;

    @FXML
    private Button del_Acc;

    @FXML
    private Button user_icon;

    @FXML
    private ToggleGroup theme;


    @FXML
    private RadioButton d_radio;
    
    @FXML
    private RadioButton w_radio;
    
    @FXML
    private Button security;

    @FXML
    private ImageView Amazon_Icon;

    @FXML
    private Label un;//user name

    @FXML
    private Label email;//email

    @FXML
    private Label dob;//date of birth

    @FXML
    private Label pin;//password

    @FXML
    private Label mn;////mobile number

    @FXML
    private Label user_info_label;
    
    

    @FXML
    private Line line1;

    @FXML
    private Line line3;

    @FXML
    private Line line2;

    @FXML
    private Label theme_la;

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private String logined_user = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/App Data/logined.properties";
	private String users = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/users/";
	String user;
	String user_theme;
	Write_Error w = new Write_Error();

  



@FXML
void delete_account(ActionEvent event) {

}

@FXML
void help_btn(ActionEvent event) {
	help(event);
}

@FXML
void security(ActionEvent event) {
	try {
		go_to_security(event);
	} catch (IOException e) {
		
		try {
			
			w.error(e.getMessage());
		}
		catch (Exception e1) {//Noting to do here
		}
		e.printStackTrace();
	}
		
}

@FXML
void set_them_to_white(ActionEvent event) {

}

@FXML
void set_theme_to_dark(ActionEvent event) {

}

@FXML
void user(ActionEvent event) {

}



/**
 * @throws FileNotFoundException
 * @throws IOException
 */
private void get_data() throws FileNotFoundException, IOException {
	FileReader r = new FileReader(logined_user);
	Properties p = new Properties();
	p.load(r);
	 user =  p.getProperty("Login_User");
	FileReader user_r = new FileReader(users + user+"/" + user+".properties");
	Properties p2 = new Properties();
	p2.load(user_r);
	String name = p2.getProperty("ID");
	String email_str = p2.getProperty("Email");
	String pin_str = p2.getProperty("Pin");
	String dob_str = p2.getProperty("DOB");
	String mn_str = p2.getProperty("Mobile_Number");
	user_info_label.setText(name + " 's Information");
	un.setText("user name : "+ name);
	email.setText( "Email : "+ email_str);
	dob.setText("Date of Birth : " + dob_str);
	pin.setText("Password : " + pin_str);
	mn.setText("Mobile Number : " + mn_str);
	r.close();


	help.setVisible(true);
	del_Acc.setVisible(true);
	user_icon.setVisible(true);
	d_radio.setVisible(true);
	w_radio.setVisible(true);
	security.setVisible(true);
	
	un.setVisible(true);
	email.setVisible(true);
	dob.setVisible(true);
	pin.setVisible(true);
	mn.setVisible(true);
	
}


private String get_theme() throws FileNotFoundException, IOException {
	FileReader theme_r = new FileReader(users + user+"/" +"settings.properties");
	Properties p = new Properties();
	p.load(theme_r);
	user_theme = p.getProperty("Theme");
	if(user_theme.equals("dark")) {
		theme.selectToggle(d_radio);
	}
	if(user_theme.equals("white")) {
		theme.selectToggle(w_radio);
	}
	return user_theme;
	
}
/**
 * this method set 's the logo
 * @throws FileNotFoundException
 * @throws IOException
 */
private void set_logo() throws FileNotFoundException, IOException {
	String theme_location = users + user + "/settings.properties";
	System.out.println(theme_location);
	FileReader r = new FileReader(theme_location);
	Properties p = new Properties();
	p.load(r);
	String theme = p.getProperty("Theme");
	if(theme.equals("dark")) {
	Image image = new Image("./Files/Amazon logo.png");
	 Amazon_Icon.setImage(image);
	 Amazon_Icon.setVisible(true);
	}
	
	if(theme.equals("white")) {
		Image image = new Image("./Files/Amazon logo white.png");
		 Amazon_Icon.setImage(image);
		 Amazon_Icon.setVisible(true);
		}
	System.out.println(theme);
}




@Override
public void help(ActionEvent event) {

	FXMLLoader loader = new FXMLLoader(getClass().getResource("../back_up/help.fxml"));	
	try {
		root = loader.load();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		try {
			w.error(e.getMessage());
		} catch (Exception e1) {//Noting to do here
		}
		//e.printStackTrace();
	}	
		
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);//important
	stage.show();
	


}

/**
 * @param event
 * @throws IOException 
 */
private void go_to_security(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("../security/security.fxml"));	
	root = loader.load();
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	if (user_theme.equals("dark")) {
		scene.getStylesheets().add("./dark.css");
	}

	if (user_theme.equals("white")) {
		scene.getStylesheets().add("./white.css");
	}
	stage.setScene(scene);//important
	stage.show();
}






@Override
public void initialize(URL location, ResourceBundle resources) {
		/*
		 * System.out.println("start"); try{ FileReader r = new
		 * FileReader(logined_user); Properties p = new Properties(); p.load(r); user =
		 * p.getProperty("Login_User"); }catch(IOException e) { e.printStackTrace();
		 * 
		 * } try{
		 * 
		 * FileReader user_r = new FileReader(users + user+"/" + user+".properties");
		 * Properties p2 = new Properties(); p2.load(user_r); String name =
		 * p2.getProperty("ID"); String email_str = p2.getProperty("Email"); String
		 * pin_str = p2.getProperty("Pin"); String dob_str = p2.getProperty("DOB");
		 * String mn_str = p2.getProperty("Mobile_Number"); user_info_label.setText(name
		 * + " 's Information"); un.setText("user name : "+ name); email.setText(
		 * "Email : "+ email_str); dob.setText("Date of Birth : " + dob_str);
		 * pin.setText("Password : " + pin_str); mn.setText("Mobile Number : " +
		 * mn_str);
		 * 
		 * help.setVisible(true); del_Acc.setVisible(true); user_icon.setVisible(true);
		 * d_radio.setVisible(true); w_radio.setVisible(true);
		 * security.setVisible(true);
		 * 
		 * un.setVisible(true); email.setVisible(true); dob.setVisible(true);
		 * pin.setVisible(true); mn.setVisible(true);
		 * 
		 * System.out.println(name+"\n"+email_str+"\n"+dob_str+"n"+pin_str+"\n"+mn_str);
		 * //r.close(); }catch(IOException e) { e.printStackTrace();
		 * 
		 * }
		 */
		System.out.println("code 1");
		try {
			get_data();
		} catch (IOException e) {
			try {
				w.FileNotFound(user, e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//e.printStackTrace();
		}

		try {
			set_logo();
		} catch (IOException e) {
			try {
				w.FileNotFound(user, e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//e.printStackTrace();
		}
		
		System.out.println("end");

		try {
			user_theme = get_theme();
		} 
		catch (FileNotFoundException e) {
			
			try {
				
				w.FileNotFound(user, e.getMessage());
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
			// e.printStackTrace();
		} catch (IOException e) {

			try {
				w.error(user, e.getMessage());
			} 
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// e.printStackTrace();
		}
}



}

