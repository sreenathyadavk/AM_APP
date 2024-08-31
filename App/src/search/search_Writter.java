package search;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class search_Writter {
	String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Calendar.getInstance().getTime());

	/*
	 * this method write search's to user's search file
	 * @param user , search
	 * @throws FileNotFoundException
	 * @throws IOException
	 * */
	String last_searched_num;

	public void write_Search(String user_Path, String user, String search/* , Boolean result */) throws FileNotFoundException , IOException {
			String userLocation = user_Path;
			user_Path = user_Path + "search.properties";
			String search_Number;
	    	FileInputStream in = new FileInputStream(user_Path);
	    	File f = new File(user_Path);
	    	Properties p = new Properties();
	    	p.load(in);
	    	in.close();
	    	FileOutputStream out = new FileOutputStream(user_Path);//if is null else go
	    	if(f.getTotalSpace() <=1) {
			p.setProperty("Google1", timeStamp + "---1" /* + String.valueOf(result) */);
	    	}else {
	    		
			
			get_last_search(userLocation , last_searched_num);
			
	    		search_Number = last_searched_num.substring((last_searched_num.length() - 1), last_searched_num.length());
	    		System.out.println(search_Number);
	    		int change_Number = Integer.parseInt(search_Number) + 1;
	    		System.out.println(change_Number);
	    		p.setProperty(search, timeStamp + "---" + String.valueOf(change_Number));
	    	}
	    	p.store(out, null);
	    	out.close();//TODO
	}
	/**
	 * get's the last searched
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void get_last_search(String user , String search_num) throws FileNotFoundException, IOException {
		FileReader r = new FileReader(user);
		Properties p = new Properties();
		p.load(r);
		search_num = p.getProperty("last1");
		r.close();
	}
	/*
	 * write's the last search
	 * @param user_path , user , search
	 * @throws  FileNotFoundException , IOException
	 * */
	public void last_Search(String user_Path, String user, String search) throws FileNotFoundException , IOException {
		user_Path = user_Path + "last search.properties";
		FileReader f = new FileReader(user_Path);
    	FileOutputStream out = new FileOutputStream(user_Path);
    	Properties p = new Properties();
    	p.load(f);
    	p.setProperty("last", search + "**"+timeStamp);
    	p.store(out, null);//run the code ; i did't tried
	}

	
	/*
	 * @param user_path , user , search
	 * @throws  FileNotFoundException , IOException
	 * */
	public void last_Search(String user, String search) throws FileNotFoundException , IOException {
		user = user + "last search.properties";
		FileReader f = new FileReader(user);
    	FileOutputStream out = new FileOutputStream(user);
    	Properties p = new Properties();
    	p.load(f);
    	p.setProperty(search, timeStamp);
    	p.store(out, null);
    	
	}
	
	
}
