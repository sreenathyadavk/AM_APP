package application;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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

public class Create_Controller {

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
    
    /*From here to down all id's are for errors*/

    @FXML
    private Label uc5;//it is a label ; it is used for {user id must contain 5 characters}

    @FXML
    private Label invalid_u_n;//it is a label ; it is used for {invalid user name}

    @FXML
    private Label invali_DOB;//it is a label ; it is used for {invalid date of birth}

    @FXML
    private Button rest_BTN;//it is a label ; it is used for {reset the text fields}

    @FXML
    private Label invalid_MN;//it is a label ; it is used for {if mobile number is invalid}

    @FXML
    private Label invalid_Email;//it is a label ; it is used for {if Email is invalid }

    @FXML
    private Label p2;// it is a label ; it is used for {if password is less than 2 characters}
    
    /*all error's id's over*/

    @FXML
    private Label id_unA;// it is label ; it is used for{if the user id is taken or is used or is present}--user id is unAvailable

    @FXML
    private Button cont;// it is a button; used for continue
    
    @FXML
    private Button back;// it is a button; used to go back

    @FXML
    private Button help;//it is a help button

    @FXML
    private Label IvP;// it is a label invalid password
    /*------------------------------------------------*/
	Create_Account create = new Create_Account();
	Verification v = new Verification();
	Write_Error w = new Write_Error();


	private Stage stage;
	private Scene scene;
	private Parent root;
	
	String auto_Fill_File = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/App Data/Auto Fill Data.properties";


	@FXML
	void Create_Account(ActionEvent event)  throws Exception  {
		System.out.println("========================");
		System.out.println(User_Mobile.getText());
		System.out.println(User_Birth.getText());
		System.out.println(User_Email.getText());
		System.out.println("=========================");

		 String user_Mobile = User_Mobile.getText();
		 String DOB = User_Birth.getText();
		 String Email = User_Email.getText();
		 String id = Id.getText();
		 String pin = Pin.getText();
		 
		if (pin.length() <= 0  ||pin.length() <=2  ) {
			p2.setVisible(true);
		}else {
			p2.setVisible(false);
		}

		if( (v.isValidUsername(id) || v.check_Name(id)) == false ) {
			invalid_u_n.setVisible(true);
		}else {
			invalid_u_n.setVisible(false);
		}

		if(v.Email(Email) == false) {
			invalid_Email.setVisible(true);
		}else {
			invalid_Email.setVisible(false);
		}
		
		try {
			if(v.Date_Of_Birth(DOB)) {
				invali_DOB.setVisible(false);
			}else {
				invali_DOB.setVisible(true);
			}
		} catch (Exception e) {
			invali_DOB.setVisible(true);
		}
		
		if(v.isValidMobile(user_Mobile) == false) {
			invalid_MN.setVisible(true);
		}else {
			invalid_MN.setVisible(false);
		}
		
		if(v.id(id) == false ) {
			uc5.setVisible(true);
		}else {
			uc5.setVisible(false);
		}
		
		if(pin.length() <=2) {
			p2.setVisible(true);
		}else {
			p2.setVisible(false);
		}
		
		if(v.check_password(pin) ==false ) {
			IvP.setVisible(true);
		}else {
			IvP.setVisible(false);
		}
		
		if(id.isEmpty()) {
			
		} else {
			setContinue(id);
		}
		
		if (v.isValidUsername(id) && v.check_Name(id) && v.Email(Email) && v.Date_Of_Birth(DOB)
				&& v.isValidMobile(user_Mobile) && v.id(id) && pin.length() >=0 && ( create.Check_User(id) == false )
				&& ( v.check_password(pin))
				)
		{
		

			
				create.Write_Data( User_Mobile.getText(), User_Birth.getText(), id,
					User_Email.getText() , Pin.getText());
				create.Create_User_Files();//this method will create account
				create.User_Logined();

				Home(event);//home

			
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Data Try Again", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}


	/**
	 * @param id
	 * @throws Exception
	 */
	private void setContinue(String id) throws Exception {
		if(create.Check_User(id) ) {
		id_unA.setText( id + " is UnAvailable, Do you want to continue press Continue button");
		id_unA.setVisible(true);
		cont.setVisible(true);
}
	}
	

    @FXML
    void generate_BTN(ActionEvent event) {
    	
    	try {
			auto_Write_Data();
		} catch (IOException e2) {
			
			try {
				w.error(e2.getMessage());
			} catch (IOException e) {}
			
			e2.printStackTrace();
		}
    	
		try {
			Generate_User_Name(event, Id.getText());//generate user name
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			try {
				w.error(e.getMessage());
			} catch (IOException e1) {}
			
			e.printStackTrace();
		}

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


    @FXML
    void go_back(ActionEvent event) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("AppHome.fxml"));	
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
    void help(ActionEvent event) {

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
		//scene.getStylesheets().add("./login/User%20Acc%20Home.css");
		scene.getStylesheets().add("./App.css");
		stage.setScene(scene);
		stage.show();

    }
    
	/**
	 * this method is used to Auto complete for further user
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void auto_Write_Data() throws FileNotFoundException, IOException {
		FileReader r = new FileReader(auto_Fill_File);
		FileOutputStream outputStrem = new FileOutputStream(auto_Fill_File);//FileOutputStream
    	Properties p = new Properties();
    	p.load(r);
    	p.setProperty("Mobile_Number",User_Mobile.getText());
    	p.setProperty("DOB", User_Birth.getText());
    	p.setProperty("Pin",Pin.getText());
    	p.setProperty("Email", User_Email.getText());
    	p.store(outputStrem, null);
	}
    
	/**
	 * @param event
	 * @param id
	 * @throws IOException
	 * @throws Exception
	 * @throws HeadlessException
	 * this method generate user name only if given user name is not available
	 */
	private void Generate_User_Name(ActionEvent event, String id) throws IOException, Exception, HeadlessException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Name.fxml"));
		root = loader.load();
		NameController nameController = loader.getController();
		nameController.GenrateName(id);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		JOptionPane.showMessageDialog(null, "If you click copy button with out generating Id,Your System will Crash", "Warning", JOptionPane.WARNING_MESSAGE);
		stage.setScene(scene);
	}

	/**
	 * this method takes the user to home window
	 * @param event
	 */
	private void Home(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("./Select Themes.fxml"));	
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	/*
	 * @FXML private Button Name_close;
	 * 
	 * @FXML void close(ActionEvent event) { Stage stage = (Stage)
	 * Name_close.getScene().getWindow(); stage.close(); }
	 */


}
