package login;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import back_up.help_;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login_Controller implements help_ {
	public String users_data = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/users/";

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button Login_BTN;// login button

	@FXML
	private TextField Id;// input user name

	@FXML
	private PasswordField Pin;// input password

	@FXML
	private Label User_Not_Found;// User Not Found....

	@FXML
	private Label Wrong_Pass;// InCorrect Password

	@FXML
	private Label I_P_M_F;// Id and Password must fill

	@FXML
	private Button help;

	@FXML
	private Button back;

    @FXML
    private CheckBox RM;

	String theme;
	String logined_user;

	@FXML
	void login(ActionEvent event) {
		String UserFolder = users_data + Id.getText() + "/";
		// String UserFolder = Desktop_Path + Id.getText() ;
		String UserFile = users_data + Id.getText() + "/" + Id.getText() + ".properties";
		String NameFile = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/App Data/" + "logined"
				+ ".properties";
		File folder = new File(UserFolder);

		// if text box have char then go else show password box must fill
		if (Id.getLength() >= 4 && Pin.getLength() >= 1) {
			I_P_M_F.setVisible(false);
			if (folder.exists()) {
				User_Not_Found.setVisible(false);
				File file = new File(UserFile);
				if (file.exists()) {//

					FileReader reader;
					try {
						reader = new FileReader(UserFile);

						Properties p = new Properties();
						p.load(reader);
						String pin = Pin.getText();

						FileReader Namereader = new FileReader(NameFile);
						FileOutputStream outputStrem = new FileOutputStream(NameFile);
						Properties NameP = new Properties();
						NameP.load(Namereader);
						if (pin.equals(p.getProperty("Pin"))) {
							NameP.setProperty("Login_User", Id.getText());
							logined_user = Id.getText();
							// NameP.store(outputStrem, "Warning Name File Do Not Corrupt @ Login
							// Controller");
							NameP.store(outputStrem, null);
							GoToHome(event);

							// TODO

						} else {
							Wrong_Pass.setVisible(true);
							// JOptionPane.showMessageDialog(null, "InCorrect Password", "Warning",
							// JOptionPane.ERROR_MESSAGE);
						}
//
					}

					catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error file names", "Warning", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}

				}

				else {// if user folder is present and user file is absent or not found then show
						// error box
					JOptionPane.showMessageDialog(null, "User Data Corrupted", "Warning", JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(null, "Application Files Corrupted", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				////////////////
			} else {
				User_Not_Found.setVisible(true);
				// JOptionPane.showMessageDialog(null, "User Not Found", "Warning",
				// JOptionPane.ERROR_MESSAGE);
			}
		} else {
			I_P_M_F.setVisible(true);
		}

	}

	@FXML
	void back(ActionEvent event) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/AppHome.fxml"));
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add("./application/application.css");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void help_btn(ActionEvent event) {
		help(event);
	}

    @FXML
    void remember_user(ActionEvent event) {
    	//TODO
    }
	/**
	 * this will set the user's theme
	 * 
	 * @throws Exception
	 * 
	 */
	private Boolean set_Theme(String user) throws Exception {
		try {
			if (theme.equals("dark")) {

				scene.getStylesheets().add("./dark.css");
				return true;

			}
			if (theme.equals("white")) {

				scene.getStylesheets().add("./white.css");
				return true;
			}
			// if (theme.equals(null) || theme.isEmpty() /* || (f.exists() || f.length() < 0
			// || theme.isEmpty()) */ )
			// if(theme.isEmpty())
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Internal Error... Try Again...", "Warning", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return false;
	}

	/**
	 * this method get's the user's selected theme
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void GetThemes(String user) throws FileNotFoundException, IOException {
		String settings_File = users_data + user + "/" + "settings.properties";
		FileReader r = new FileReader(settings_File);
		Properties p = new Properties();
		p.load(r);
		System.out.println(p.getProperty("Theme"));
		theme = p.getProperty("Theme");
	}

	/**
	 * @param event this method change scene to home page
	 */
	private void GoToHome(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../user/user home.fxml"));
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		try {
			GetThemes(logined_user);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (set_Theme(logined_user) == false) {

			} else {
				stage.setScene(scene);
				stage.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	

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

		// root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add("./App.css");
		stage.setScene(scene);
		stage.show();
	}

}
