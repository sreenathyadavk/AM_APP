package security;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import back_up.help_;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Security_Main  implements help_ , Initializable {

    @FXML
    private AnchorPane window;

    @FXML
    private Label sec_text;

    @FXML
    private ImageView sec_icon;


    @FXML
    private Label su_text;

    @FXML
    private Button scan_btn;

    @FXML
    private ImageView result_img;

    @FXML
    private Label ns_text;

    @FXML
    private Button change_pin_btn;

    @FXML
    private ProgressIndicator progress;

    @FXML
    void reset_pin(ActionEvent event) {

    }

    @FXML
    void scan(ActionEvent event) {
    	progress.setProgress(0.46);
    }
//Change Password
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			get_user();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			set_sec_logo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String logined_user = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/App Data/logined.properties";
	private String users = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/users/";
	String user;
	String user_theme;
	
	/**
	 * get's user
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void get_user() throws FileNotFoundException, IOException {
		FileReader r = new FileReader(logined_user);
		Properties p = new Properties();
		p.load(r);
		 user =  p.getProperty("Login_User");
	}
	/**
	 * set's security logo
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void set_sec_logo() throws FileNotFoundException, IOException {
		String theme_location = users + user + "/settings.properties";
		System.out.println(theme_location);
		FileReader r = new FileReader(theme_location);
		Properties p = new Properties();
		p.load(r);
		String theme = p.getProperty("Theme");
		if(theme.equals("dark")) {
		Image image = new Image("./Files/security-configuration.png");
		sec_icon.setImage(image);
		sec_icon.setVisible(true);
		}
		
		if(theme.equals("white")) {
			Image image = new Image("./Files/security-configuration.png");
			sec_icon.setImage(image);
			 sec_icon.setVisible(true);
			}
		System.out.println(theme);
	}

	@Override
	public void help(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

}
