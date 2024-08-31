package back_up;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import Error_Writter.Write_Error;

public class back_up_data {
	String back_up_Path = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/users/";
	Write_Error w = new Write_Error();

	/**this method write's data
	 * @param user id , number , pin , dob , email , theme
	 * may  @throws FileNotFoundException
	 * @throws IOException
	 * this method create a back up folder 
	 */
	public void create_back_up(String user , String id , String number , String pin , String dob , String email , String theme){
    	
		File folder = new File(back_up_Path + id + "/" + "back up");
		File file = new File(back_up_Path + id + "/" + "back up" + "/" + "settings.properties");
		
		folder.mkdirs();
		
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

    	try {
			write_Data(user, id, number, pin, dob, email, theme);
		} catch (IOException e) {
			try {
				w.FileNotFound(id, e.getMessage());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Application Files Corrected or not found , Try : Reinstall The Application and its Files", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**this method write's data
	 * @param user
	 * @throws FileNotFoundException
	 * @throws IOException
	 * here we can't use file not found method because it throws Exception @ line 1 , 2 , 3
	 */
	public void write_Data(String user , String id , String number , String pin , String dob , String email , String theme) throws FileNotFoundException, IOException {
    	String back_up_folder = back_up_Path + id + "/" + "back up/settings.properties";

		FileInputStream in = new FileInputStream(back_up_folder);//1
    	Properties p = new Properties();
    	p.load(in);//2
    	in.close();

    	FileOutputStream out = new FileOutputStream(back_up_folder);//3
    	p.setProperty("User_Name",user);
    	p.setProperty("ID",id);
    	p.setProperty("Mobile_Number",number);
    	p.setProperty("Pin",pin);
    	p.setProperty("DOB",dob);
    	p.setProperty("Email",email);
    	p.setProperty("theme",theme);

    	p.store(out, null);//3
    	out.close();
	}
	
}
