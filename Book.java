import java.util.*; //imports List/ArrayList/Scanner

public class Book{

    private static User u;
    private static boolean _Using = true;
    private static boolean _loggedIn = false;


    public static boolean getLoggedIn(){
	return _loggedIn;
    }
    public static void setLoggedIn(boolean newLIState){
	_loggedIn = newLIState;
    }
    public static boolean getUsing(){
	return _Using;
    }
    public static void setUsing(boolean newUsing){
	_Using = newUsing;
    }

    public static boolean startLogIn(String s){
	if(s.equals("student")){
	    u = new Student();
	    return true;
	}
	else if(s.equals("teacher")){
	    u = new Teacher();
	    return true;
	}
	else if(s.equals("ap")){
	    u = new AP();
	    return true;
	}
	else if(s.equals("overlord")){
	    u = new Overlord();
	    return true;
	}
	else{
	    return false;
	}
    }

    public static boolean verifyStudent(String osis, String fdigit){
	List<String[]> temp = ReadCSV.read("osis_fdigit.txt");
	for(int i = 0; i < temp.size(); i++){
	    if(temp.get(i)[0].equals(osis)){
		if(temp.get(i)[1].equals(fdigit)){
		    return true;
		}
	    }
	}
	return false;

    }

    public static void verify(){
	Scanner s = new Scanner(System.in);
	String type  = "";
	String temp1 = "";
	String temp2 = "";
	System.out.println("What type of user are you? student/teacher/ap");
	if(s.hasNext()){
	    type = (s.nextLine());
	}   
	while(true){
	    if(type.equals("student")){
		System.out.println("What is your OSIS?");
		if(s.hasNext()){
		    temp1 = (s.nextLine());
		}   
		System.out.println("What is your four digit ID?");
		if(s.hasNext()){
		    temp2 = (s.nextLine());
		}   
		if(verifyStudent(temp1, temp2)){
		    String fname = "";
		    String lname = "";
		    String year = "";
		    System.out.println("What is your first Name?");
		    if(s.hasNext()){
			fname = (s.nextLine());
		    }   		   
    		    System.out.println("What is your last Name?");
		    if(s.hasNext()){
			lname = (s.nextLine());
		    }   		   
    		    System.out.println("What is your Graduation Year?");
		    if(s.hasNext()){
			year = (s.nextLine());
		    }   		   
		    FileMaker.newStudent(fname, lname, temp1, temp2, year);
		    break;
		}
		else{
		    System.out.println("Verification failed. Retry");
		}
	    }
	}
    }
    
    public static void menu(Scanner s){
	String temp = "";
	System.out.println("What would you like to do?");
	System.out.println("Options: quit/logout");

	if(s.hasNext()){
	    temp = (s.nextLine());
	}
	if(temp.equals("quit")){
	    quit();
	}
	if(temp.equals("logout")){
	    setLoggedIn(false);
	}
    }

    public static void quit(){
	System.exit(1);
    }
    

    //public static void login

    public static void main(String[] args){
	Scanner s = new Scanner(System.in);
	String username = "";
	String password = "";

	while(getUsing()){
	    System.out.println("Welcome!");
	    System.out.println("What would you like to do? verify/login");
	    while(true){
		if(s.hasNext()){
		    if(s.nextLine().equals("verify")){
			verify();
			break;
		    }
		    else{
			break;
		    }
		}
	    }
	    
	    System.out.println("What would you like to log in as? student/teacher/ap?");
	    while(true){
		if(startLogIn(s.nextLine())){
		    System.out.println("Please log in! \n");
		    break;
		}
		else{
		    System.out.println("Please re-specify");
		}	   
	    }

	    while(getLoggedIn() == false){
		System.out.println("Username");
		if(s.hasNext()){
		    username = s.nextLine();
		}
		System.out.println("Password");
		if(s.hasNext()){
		    password = s.nextLine();
		}
		Boolean gotIn = u.login(username, password);
		u.setName(username);
		if(gotIn){
		    System.out.println("Login Successful!" + "\n");
		    setLoggedIn(true);
		}
		else{
		    System.out.println("Login failed. Retry." + "\n");
		}

	    }

	    while(getLoggedIn()){
		menu(s);
	    }

	}
    }
}