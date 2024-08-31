package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import Error_Writter.Write_Error;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Create_Controller_2 {

	Create_Account create = new Create_Account();
	Verification v = new Verification();
	Write_Error w = new Write_Error();


    @FXML
    private TextField User_Mobile;

    @FXML
    private TextField User_Birth;

    @FXML
    private TextField User_Email;

    @FXML
    private Button Sign_Up_BTN;

    @FXML
    private TextField Id;

    @FXML
    private PasswordField Pin;

    @FXML
    private Label uc5;

    @FXML
    private Label invalid_u_n;

    @FXML
    private Label invali_DOB;

    @FXML
    private Button rest_BTN;

    @FXML
    private Label invalid_MN;

    @FXML
    private Label invalid_Email;

    @FXML
    private Label p2;
    
    @FXML
    private Label CYA;

    @FXML
    private Label id_unA;

    @FXML
    private Button cont;

    @FXML
    private Button continue_BTN_Start_Up;
    

	
	  private Stage stage;
	  private Scene scene;
	  private Parent root;
	 
    
	String auto_Fill_File = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/App Data/Auto Fill Data.properties";
	
	String Generated_Id;
	

private boolean isValidUsername(String name) {

	//Regex to check valid username. 
	String contains = "^\\w{5,29}$"; // [A-Za-z]
	//String a = "[a-zA-Z]*";
	//Compile the ReGex 
	Pattern p = Pattern.compile(contains);// or 

	//If the username is empty 
	//return false 
	if (name == null) {
	return false;
	}
	

	Pattern p2 = Pattern.compile("(0/91)?[7-9][0-9]{9}");
	
	if(name.isEmpty() == false) {
	//Pattern class contains matcher() method 
	//to find matching between given number  
	//and regular expression 
	Matcher m = p2.matcher(name);
	return (m.find() && m.group().equals(name));
	
	}
	//hanged by kanna

	if(name.length() >=4)
	{ 
	return true;

	}
	
Matcher m = p.matcher(name);

//Return if the username 
//matched the ReGex 




return m.matches();

}

    @FXML
    void Create_Account(ActionEvent event) throws Exception{


		 String user_Mobile = User_Mobile.getText();
		 String DOB = User_Birth.getText();
		 String Email = User_Email.getText();
		 String id = Id.getText();
		 String pin = Pin.getText();
		 
		if(pin.length() <=0) {
			p2.setVisible(true);
		}

		if( (isValidUsername(id) /*|| v.check_Name(id)) == true*/) ) {
			invalid_u_n.setVisible(true);
		}

		if(v.Email(Email) == false) {
			invalid_Email.setVisible(true);
		}
		
		try {
			if(v.Date_Of_Birth(DOB) == false) {
				invali_DOB.setVisible(true);
			}
		} catch (Exception e) {
			invali_DOB.setVisible(true);
		}
		
		if(v.isValidMobile(user_Mobile) == false) {
			invalid_MN.setVisible(true);
		}
		
		if(v.id(id) == false ) {
			uc5.setVisible(true);
		}
		
		if(pin.length() <=2 ) {
			p2.setVisible(true);
		}
		
		if(id.isEmpty()) {
			
		} else {
			setContinue(id);
		}
		
		if ( v.check_Name(id) && v.Email(Email) && v.Date_Of_Birth(DOB)
				&& v.isValidMobile(user_Mobile) && v.id(id) && pin.length() >=0 && ( create.Check_User(id)==false))
		{
		
		//	if(create.Check_User(id) ) {}
			
				create.Write_Data( User_Mobile.getText(), User_Birth.getText(), id,
					User_Email.getText() , Pin.getText());
				create.Create_User_Files();//this method will create account
				create.User_Logined();

				Home(event);//home

			
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Data Try Again", "Error", JOptionPane.ERROR_MESSAGE);
		}

    }

    private void Home(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("./Select Themes.fxml"));	
		try {
			root = loader.load();
		} catch (IOException e) {
			try {
				w.error(e.getMessage());
			} catch (Exception e1) {//Noting to do here
			}
			e.printStackTrace();
		}	
		
		
		//root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);//important
		stage.show();		
	}

	private void setContinue(String id) throws Exception {
		
		if(create.Check_User(id) ) {
		id_unA.setText( id + " is UnAvailable");//, Do you want to continue press Continue button
		id_unA.setVisible(true);
		cont.setVisible(true);
		}
		
	}

	@FXML
    void continueToSignIn(ActionEvent event) {
    	
    	try {
			auto_Write_Data();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	continue_BTN_Start_Up.setVisible(false);
    	CYA.setVisible(true);
    	Id.setEditable(false);
    	Id.setVisible(true);
    	User_Mobile.setVisible(true);
    	User_Birth.setVisible(true);
    	User_Email.setVisible(true);
    	Pin.setVisible(true);
    	Sign_Up_BTN.setVisible(true);
    	rest_BTN.setVisible(true);
    	
    }

	/**
	 * Auto write the data
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void auto_Write_Data() throws FileNotFoundException, IOException {
		FileReader r = new FileReader(auto_Fill_File);
    	Properties p = new Properties();
    	p.load(r);
    	User_Mobile.setText(p.getProperty("Mobile_Number"));
    	User_Birth.setText(p.getProperty("DOB"));
    	User_Email.setText(p.getProperty("Email"));
    	Id.setText(Generated_Id);
    	r.close();
	}


    @FXML
    void rest(ActionEvent event) {
    	
    	User_Mobile.setText("");

    	User_Birth.setText("");

    	User_Email.setText("");

    	Id.setText("");
    	
    	Pin.setText("");
        
    	uc5.setVisible(false);
    	invalid_u_n.setVisible(false);
    	invali_DOB.setVisible(false);
    	invalid_MN.setVisible(false);
    	invalid_Email.setVisible(false);
    	p2.setVisible(false);
    	id_unA.setVisible(false);
    	cont.setVisible(false);
    }
	
	
	void inScene(String copy) {
		Generated_Id = copy;
	}
	
}
