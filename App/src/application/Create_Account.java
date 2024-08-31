package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import Error_Writter.Write_Error;
public class Create_Account{

	String Data_Path = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/";
	String user; 
	String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Calendar.getInstance().getTime());

	Write_Error w = new Write_Error();
	
	public void Write_Data(String MobileNumber , String DOB , String UserID , String Email , String Pin) throws Exception{
		String UserFolder = Data_Path + "users/"+ UserID ;
		String UserFile = Data_Path +"users/" + UserID +"/"+UserID+".properties";
		File file = new File(UserFile);
    	File folder = new File(UserFolder);
		try {
			if(folder.mkdir()) {

			if(file.createNewFile()){
				FileReader reader = new FileReader(UserFile);
				FileOutputStream outputStrem = new FileOutputStream(UserFile);//FileOutputStream
				Properties p = new Properties();
				p.load(reader);
				System.out.println("created user file");
				//------------------------------------------------
				user = UserID;
				p.setProperty("Mobile_Number", MobileNumber);
				p.setProperty("DOB", DOB);
				p.setProperty("ID", UserID);
				p.setProperty("Email", Email);
				p.setProperty("Pin", Pin);
				p.store(outputStrem, "user :"+UserID+" 's"+"  application file");//important
			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void Create_User_Files() {
		String Searched_File = Data_Path +"users/" + user +"/"+"search"+".properties";
		File file = new File(Searched_File);

		String Settings_File = Data_Path +"users/" + user +"/"+"settings"+".properties";
		File s = new File(Settings_File);
		
		String last_Searched_File = Data_Path +"users/" + user +"/"+"last search"+".properties";
		File l = new File(last_Searched_File);
		try {
			s.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			l.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			default_search_writter(Searched_File);
		} catch (IOException e) {
			
			try {
				w.FileNotFound(user, e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}

	}

	/**
	 * @param Searched_File
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected void default_search_writter(String Searched_File) throws FileNotFoundException, IOException {
		FileReader r = new FileReader(Searched_File);
		FileOutputStream outputStrem = new FileOutputStream(Searched_File);//FileOutputStream
		Properties p = new Properties();
		p.load(r);
		p.setProperty("Google1",timeStamp);
		p.store(outputStrem, null);
	}
	

	public Boolean Check_User(String UserID) throws Exception {
		
		if(UserID.isEmpty()) {
			return false;
		}
		
		String UserFileName = Data_Path+ "users/" + UserID;
		File file = new File(UserFileName);
		if(file.exists()) {
			FileReader reader = new FileReader(Data_Path + "App Data/Genrate User Name.properties");
			FileOutputStream outputStrem = new FileOutputStream(Data_Path + "App Data/Genrate User Name.properties");//FileOutputStream
			Properties p = new Properties();
			p.load(reader);
			p.setProperty("User_Name", UserID);
			p.store(outputStrem, "Warning Name File Do Not Corrupt @ Create Account");
			return true;
		}
		return false;
	}
	
	public void User_Logined() throws Exception {
		FileOutputStream outputStrem = new FileOutputStream(Data_Path + "App Data/logined.properties");//FileOutputStream
		Properties p = new Properties();
		p.setProperty("Login_User", user);
		p.store(outputStrem, "Warning Name File Do Not Corrupt @ Create Account");
	}
	
}
