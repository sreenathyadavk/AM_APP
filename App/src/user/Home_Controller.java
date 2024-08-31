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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import search.search_Writter;

public class Home_Controller implements help_, Initializable {

	Write_Error w = new Write_Error();
	search_Writter s = new search_Writter();

	private String logined_user = System.getProperty("user.home") + "/Desktop/"
			+ "Application DataBase/App Data/logined.properties";
	private String users = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/users/";
	private String user;

	// public String users_data = System.getProperty("user.home") + "/Desktop/" +
	// "Application DataBase/users/";

	@FXML
	private AnchorPane window;

	@FXML
	private Button sell_BTN_home;

	@FXML
	private Button user_Acc;

	@FXML
	private TextField search_Feild;

	@FXML
	private Button search_BTN;

	@FXML
	private Button help;

	@FXML
	private Button click_me;

	@FXML
	private Button cart;

	@FXML
	private ImageView Amazon_Icon;

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	void get_Data(ActionEvent event) {
		try {
			get_Data();
		} catch (IOException e) {

			error(e);
		}
		change_To_Visible();

	}

	/**
	 * Error
	 * 
	 * @param e
	 */
	private void error(IOException e) {
		try {
			FileReader r = new FileReader(logined_user);
			Properties p = new Properties();
			p.load(r);
			user = p.getProperty("Login_User");
			r.close();
			w.FileNotFound(user, e.getMessage());

		} catch (IOException e1) {

			try {
				w.code_Error(null, e1.getMessage());

			} catch (IOException e2) {

				e2.printStackTrace();

			}

			e1.printStackTrace();

		}

		e.printStackTrace();

	}

	/**
	 * this method get's user's name and get's data from the file
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void get_Data() throws FileNotFoundException, IOException {
		FileReader r = new FileReader(logined_user);
		Properties p = new Properties();
		p.load(r);
		user = p.getProperty("Login_User");
		r.close();
	}

	/**
	 * this method will change all buttons and text fields to visible
	 * 
	 * @throws IOException
	 */
	private void change_To_Visible() {
		/*
		 * click_me.setVisible(false); Hello_user.setVisible(true);
		 * Hello_user.setText("Hello " +user); search_Feild.setVisible(true);
		 * search_BTN.setVisible(true); user_Acc.setVisible(true);
		 * sell_BTN_home.setVisible(true);
		 */
		click_me.setVisible(false);

	}

	@FXML
	void sell(ActionEvent event) {

	}

	@FXML
	void user_Acc(ActionEvent event) {
		user_account(event);
	}

	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void set_logo() throws FileNotFoundException, IOException {
		String theme_location = users + user + "/" + "settings.properties";
		FileReader r = new FileReader(theme_location);
		Properties p = new Properties();
		p.load(r);
		String theme = p.getProperty("Theme");
		if (theme.equals("dark")) {
			Image image = new Image("./Files/Amazon logo.png");
			Amazon_Icon.setImage(image);
			Amazon_Icon.setVisible(true);
		}

		if (theme.equals("white")) {
			Image image = new Image("./Files/Amazon logo white.png");
			Amazon_Icon.setImage(image);
			Amazon_Icon.setVisible(true);
		}
	}

	@FXML
	void search(ActionEvent event) {

		// String search = search_Feild.getText();
		// String user_Location = System.getProperty("user.home") + "/Desktop/" +
		// "Application DataBase/users/" + user + "/";
		// try {
		// System.out.println("search btn");
		/// s.write_Search(user_Location, user, search_Feild.getText() /* , true */ );
		// s.last_Search(user_Location, search);

		// } catch (IOException e) {
		// System.out.println("error occured");
		// error(e);
		// e.printStackTrace();
		// }

	}

	/**
	 * this method takes the user to user account window
	 * 
	 * @param event
	 */
	private void user_account(ActionEvent event) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("./Account.fxml"));
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				w.error(e.getMessage());
			} catch (Exception e1) {// Noting to do here
			}
			// e.printStackTrace();
		}

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);

		try {
			set_theme();
		} catch (IOException e) {

			try {
				w.FileNotFound(user, e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		stage.setScene(scene);// important
		stage.show();
	}

	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 * 
	 *                               this method is used to get & set theme for user
	 *                               from user's settings file
	 * 
	 */
	private void set_theme() throws FileNotFoundException, IOException {

		String theme_location = users + user + "/" + "settings.properties";
		FileReader r = new FileReader(theme_location);
		Properties p = new Properties();
		p.load(r);
		String theme = p.getProperty("Theme");
		if (theme.equals("dark")) {
			scene.getStylesheets().add("./dark.css");
		}

		if (theme.equals("white")) {
			scene.getStylesheets().add("./white.css");
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			get_Data();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		search_Feild.setVisible(true);
		search_BTN.setVisible(true);
		cart.setVisible(true);
		user_Acc.setVisible(true);
		sell_BTN_home.setVisible(true);
		help.setVisible(true);

		try {
			set_logo();
		} catch (IOException e) {
			try {
				w.FileNotFound(user, e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void help(ActionEvent event) {

	}

}
