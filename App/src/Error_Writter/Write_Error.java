package Error_Writter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Write_Error {

	String Code_Error_Path = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/App Data/Errors/Code Errors.properties";
	String Null_Error_Path = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/App Data/Errors/Null Errors.properties";
	String File_Error_Path = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/App Data/Errors/File Errors.properties";
	String Error_Path = System.getProperty("user.home") + "/Desktop/" + "Application DataBase/App Data/Errors/Errors.properties";

	/**
	 * @param user
	 * @throws FileNotFoundException
	 * @throws IOException
	 * this method helps to improve the application and send's the user faced errors
	 */
	public void code_Error(String user, String mes) throws FileNotFoundException, IOException {
		System.out.println("white");
    	FileInputStream in = new FileInputStream(Code_Error_Path);
    	Properties p = new Properties();
    	p.load(in);
    	in.close();
    	FileOutputStream out = new FileOutputStream(Code_Error_Path);
    	p.setProperty(user, "code error  "+mes);
    	p.store(out, null);
    	out.close();
	}

	/**
	 * @param user , mess
	 * @throws FileNotFoundException
	 * @throws IOException
	 * this method helps to improve the application and send's the user faced errors for Null errors
	 */
	public void Null_Error(String user, String mess) throws FileNotFoundException, IOException {
    	FileInputStream in = new FileInputStream(Null_Error_Path);
    	Properties p = new Properties();
    	p.load(in);
    	in.close();
    	FileOutputStream out = new FileOutputStream(Null_Error_Path);
    	p.setProperty(user, "@null error   "+mess);
    	p.store(out, null);
    	out.close();
	}
	
	


	/**
	 * @param user , mess
	 * @throws FileNotFoundException
	 * @throws IOException
	 * this method helps to improve the application and send's the user faced errors like file not found 
	 */
	public void FileNotFound(String user, String mess) throws FileNotFoundException, IOException {
    	FileInputStream in = new FileInputStream(File_Error_Path);
    	Properties p = new Properties();
    	p.load(in);
    	in.close();
    	FileOutputStream out = new FileOutputStream(File_Error_Path);
    	p.setProperty(user, "@file not found    "+ mess);
    	p.store(out, null);
    	out.close();
	}

	/**
	 * @param user , mess
	 * @throws FileNotFoundException
	 * @throws IOException
	 * this method helps to improve the application and send's the user faced errors 
	 * this method is used for common errors
	 */
	
	public void error(String user, String mess) throws FileNotFoundException, IOException  {
		FileInputStream in = new FileInputStream(Error_Path);
    	Properties p = new Properties();
    	p.load(in);
    	in.close();
    	FileOutputStream out = new FileOutputStream(Error_Path);
    	p.setProperty(user, "@Error    "+ mess);
    	p.store(out, null);
    	out.close();
	}
	/**
	 * @param user , mess
	 * @throws FileNotFoundException
	 * @throws IOException
	 * this method helps to improve the application and send's the user faced errors like file not found 
	 * Especially before login or after sign out
	 * this method is used for common errors
	 */
	public void error(String mess) throws FileNotFoundException, IOException  {
		FileInputStream in = new FileInputStream(Error_Path);
    	Properties p = new Properties();
    	p.load(in);
    	in.close();
    	FileOutputStream out = new FileOutputStream(Error_Path);
    	p.setProperty("@Error", "@Error    "+ mess);
    	p.store(out, null);
    	out.close();
	}
}
