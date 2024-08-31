package application;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Verification {
	

	/*
	 * public String randomNumber() { Random AccountNUmber = new
	 * Random(System.currentTimeMillis());
	 * 
	 * long AccNumberLong= AccountNUmber.nextLong() % 1000000000000l; String
	 * AccNumString = String.valueOf(AccNumberLong); return AccNumString; }
	 * 
	 * public String pin() { Random AccountPin = new
	 * Random(System.currentTimeMillis()); long pinLong = AccountPin.nextLong() %
	 * 10000l; String PinString = String.valueOf(pinLong); return PinString; }
	 */
	////////////////////////////////////////////////////////////////
public  boolean isValidMobile(String PhoneNumber) {

	//The given argument to compile() method  
	//is regular expression. With the help of  
	//regular expression we can validate mobile 
	//number.  
	//1) Begins with 0 or 91 
	//2) Then contains 7 or 8 or 9. 
	//3) Then contains 9 digits 
	Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");

	//Pattern class contains matcher() method 
	//to find matching between given number  
	//and regular expression 
	Matcher m = p.matcher(PhoneNumber);
	return (m.find() && m.group().equals(PhoneNumber));
	}
///////////////////////////////////////////////////////
	
public  boolean isValidUsername(String name) {

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

	//hanged by kanna

	
Matcher m = p.matcher(name);

if(name.length() >=4 && m.matches())
{ 
return true;

}

//Return if the username 
//matched the ReGex 




return m.matches();

}
	//////////////////////////////////////////////////////

public static Date StringToDate(String dob) throws Exception {
	// Instantiating the SimpleDateFormat class
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	// Parsing the given String to Date object
	Date date = formatter.parse(dob);
	System.out.println("Date object value: " + date);
	return date;
}
	

	boolean Email(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
		
	}




boolean check_Name(String User_Name) {
	String str = User_Name ;
int count;  
  
//Converts given string into character array  
char[] string1 = str.toCharArray();  
  
//onsole.WriteLine("Duplicate characters in a given string: ");  
//Counts each character present in the string  
for(int i = 0; i <string1.length; i++) {  
    count = 1;  
    for(int j = i+1; j <string1.length; j++) {  
        if(string1[i] == string1[j] && string1[i] != ' ') {  
            count++;  
            //Set string1[j] to 0 to avoid printing visited character  
            string1[j] = '0';  
        }  
    }  
    //A character is considered as duplicate if count is greater than 1  
    // checking repeating char
    if(count > 4 && string1[i] != '0') { 
        //Console.WriteLine(string1[i]);  
    	//num = 5;
    	//System.out.println("[][][][][]");
		JOptionPane.showMessageDialog(null, "System Dictated Some Repeated Characters In Your Name", "error", JOptionPane.ERROR_MESSAGE);
    	return false;

    		}
    /////////////////////////////
    	  }
/////////////////loop out
	
		return true;

}




/* Period  String*/ Boolean Date_Of_Birth(String DOB) throws/* Parse */Exception {
	  	
	if(DOB.contains("/")) {
		return false;
	}

	 String day  = DOB.substring(0, 2);
	 String month = DOB.substring(3, 5);
	 String year = DOB.substring(6, DOB.length());

	 System.out.println(day);
	 System.out.println(month);
	 System.out.println(year);
	 ////////

	 
	 int a = Integer.parseInt(day);//parsing day
	 int b = Integer.parseInt(month);//parsing month
	 int c = Integer.parseInt(year);//parsing year
	 
	 
	  
	 if(a >31 || b > 12 || c >2021 ) {
		 System.out.println("*********************************************");
		 System.out.println("System dictated some error's : ");
		 System.out.println("you entered*********** wrong  day or month or year**********");
		 System.out.println("Please try again...");
		 System.out.println("*********************************************");
		return false;
			
	 }
	 
	 
	 
	//Converting String to Date
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");// converting
	Date date = formatter.parse(DOB);

	//Converting obtained Date object to LocalDate object
	Instant instant = date.toInstant();
	ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
	LocalDate givenDate = zone.toLocalDate();

	//Calculating the difference between given date to current date.
	Period period = Period.between(givenDate, LocalDate.now());
	System.out.println("********period.getDays() *******"+period.getDays() );
	System.out.println("********period.getMonths() *******"+period.getMonths() );

	/// age try catch over
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	System.out.println("checking...");
   String ageInfo = "your current age is: " + period.getYears() + " years " + period.getMonths() + "months"
	+ " and " + period.getDays() + " days" + "(" + period.getYears() + ")" + "yrs";

	System.out.println(ageInfo);
	return true;
}
  
//////////////////////////////////////////////////


Boolean id(String Id) {
	if(Id.isEmpty() || Id.length() < 5) {
		return false;
	}
	return true;
}


boolean check_password(String pin) {
	String str = pin ;
int count;  
  
//Converts given string into character array  
char[] string1 = str.toCharArray();  
  
//onsole.WriteLine("Duplicate characters in a given string: ");  
//Counts each character present in the string  
for(int i = 0; i <string1.length; i++) {  
    count = 1;  
    for(int j = i+1; j <string1.length; j++) {  
        if(string1[i] == string1[j] && string1[i] == ' ') {  
            count++;  
            //Set string1[j] to 0 to avoid printing visited character  
            string1[j] = '0';  
        }  
    }  
    //A character is considered as duplicate if count is greater than 1  
    // checking repeating char
    if(count > 4 && string1[i] != '0') { 
        //Console.WriteLine(string1[i]);  
    	//num = 5;
    	//System.out.println("[][][][][]");
		//JOptionPane.showMessageDialog(null, "System Dictated Some Repeated Characters In Your Name", "error", JOptionPane.ERROR_MESSAGE);
    	return false;

    		}
    /////////////////////////////
    	  }
/////////////////loop out
	
		return true;

}

}
