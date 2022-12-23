package employeeLogin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
	List<UserDetails> loginDetailsList = null;
	UserDetails userDetails = null;
	String loginDetalisFilePath = "D://EmloyeeManagement//employeeLoginDetails.csv" ;
	public List<UserDetails> getLoginDetails() throws  IOException{
		loginDetailsList = new ArrayList<>();
		String loginDetails = null;
		String[] loginData  = null;
		int[] data = null;
		File file = new File(loginDetalisFilePath);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		for(int j =1 ;(loginDetails = bufferedReader.readLine()) != null;j++ ) {
			while((loginDetails  = bufferedReader.readLine()) != null) {
				loginData = loginDetails .split("\\,");
				data = new int[loginData .length];
				data[0]= Integer.parseInt(loginData[0]);
				userDetails = new UserDetails(data[0],loginData[1],loginData[2]);
				loginDetailsList.add(userDetails);
			}
		}
		fileReader.close();
		return loginDetailsList;

	}

	public boolean checkUserValidOrNot(String userName) throws IOException {
		boolean result = false;
		loginDetailsList = getLoginDetails();
		for (int i=0 ; i<loginDetailsList.size();i++) {
			userDetails = loginDetailsList.get(i);
			String tempUserName = userDetails.getUserName();
			if(tempUserName.toLowerCase().equals(userName.toLowerCase())) {
				result = true;
			}
		}
		return result;
	}



	public void createLogin(int empid,String userName,String password) throws IOException {

		File file = new File(loginDetalisFilePath);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file,true);
		String loginUserDetails = "\r\n"+empid+","+userName+","+password;
		fileWriter.write(loginUserDetails);
		fileWriter.close();

	}
	
	public boolean signInOrLogIN(String userName,String password) throws IOException {
		boolean result = false;
		loginDetailsList = getLoginDetails();
		for (int i=0 ; i<loginDetailsList.size();i++) {
			userDetails = loginDetailsList.get(i);
			String tempUserName = userDetails.getUserName();
			if(tempUserName.toLowerCase().equals(userName.toLowerCase())) {
				String tempPassword = userDetails.getPassword();
				if (tempPassword.toLowerCase().equals(password.toLowerCase())) {
					result = true;
				}
			}
		}
		return result;
	}


	public static void main(String[] args) throws IOException {

		Login login = new Login();
		try (Scanner stringScanner = new Scanner(System.in)) {
			try (Scanner intScanner = new Scanner(System.in)) {
				int userChoice = -1;
				do {
					System.out.println("--------------------------------------------------------------------------");
					System.out.println("Select which operation should be perfomed :  ");
					System.out.println("--------------------------------------------------------------------------");

					System.out.println("1: Login / signin ");
					System.out.println("2: Create Account/ SignUp ");
					System.out.println("3: Valid user or not ");
					System.out.println("0: Exit ");


					System.out.println("--------------------------------------------------------------------------");
					System.out.println("UserOption: ");
					userChoice = intScanner.nextInt();
					System.out.println("--------------------------------------------------------------------------");
					switch(userChoice) {
					case 1:
						System.out.println("                   *        Login           *                  ");
						System.out.println("                  -----------------------------            ");
						System.out.println("Enter the UserName: ");
						String userName = stringScanner.nextLine();
						System.out.println("Enter Strong Password with atleast one special symblos: ");
						String password = stringScanner.nextLine();
						System.out.println("                 --------------------------------              ");
						boolean signin =login.signInOrLogIN( userName, password);
						if(signin == true) {
							System.out.println("              **   Successfully Logined   **               ");
						}else {
							System.out.println("              **     Logined Failed!      **               ");
						}
						
						break;
					case 2:
						System.out.println("                      * Create your Login *                  " );
						System.out.println("                  -----------------------------            ");
						System.out.println("Enter the EmpId:");
						int empid =  intScanner.nextInt();
						System.out.println("Enter the UserName: ");
						String userName1 = stringScanner.nextLine();
						System.out.println("Enter Strong Password with atleast one special symblos: ");
						String password1 = stringScanner.nextLine();
						System.out.println("                 --------------------------------              ");
						boolean result = login.checkUserValidOrNot(userName1);
						if(result == false) {
							login.createLogin(empid,userName1,password1);
							System.out.println("                      **  Successfully Created ! **            ");
						}else {
							System.out.println("          **  error: Username allready exit!  **             ");
						}

						break;
					case 3:
						System.out.println("                      **  User Is Valid Or Not  **                  " );
						System.out.println( "Enter the UserName:");
						String userName2 = stringScanner.nextLine();
						System.out.println("              -------------------------------                     ");
						boolean value = login.checkUserValidOrNot(userName2); 
						if(value == true) {
							System.out.println("            **   Hey! It's valid user   **                         ");
						}else {
							System.out.println("            **   Incorrect User/Password  **                        ");
						}
						break;

					}
				}while(userChoice != 0);
			}
			catch (IOException e) {
				e.printStackTrace();	
			}
		}

	}

}
