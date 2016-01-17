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

    public static void startLogIn(){
	Scanner s = new Scanner(System.in);
	String x = "";
	System.out.println("What would you like to log in as? student/teacher/ap?");
	while(true){
	    if(s.hasNext()){
		x = (s.nextLine());
	    }   
	    if(x.equals("student")){
		u = new Student();
		break;
	    }
	    else if(x.equals("teacher")){
		u = new Teacher();
		break;
	    }
	    else if(x.equals("ap")){
		u = new AP();
		break;
	    }
	    else if(x.equals("overlord")){
		u = new Overlord();
		break;
	    }
	    else{
		System.out.println("Please re-specify");
	    }
	}
    }

    public static boolean verifyStudent(){
	List<String[]> temp = ReadCSV.read("osis_fdigit.txt");
	Scanner s = new Scanner(System.in);
	String osis = "";
	String fdigit = "";
	while(true){
	    System.out.println("What is your OSIS?");
	    if(s.hasNext()){
		osis = (s.nextLine());
	    }   
	    System.out.println("What is your four digit ID?");
	    if(s.hasNext()){
		fdigit = (s.nextLine());
	    }   
	    for(int i = 0; i < temp.size(); i++){
		if(temp.get(i)[0].equals(osis)){
		    if(temp.get(i)[1].equals(fdigit)){
			System.out.println("Verification Success!\n");
			newStudentAcc(osis, fdigit);
			return true;
		    }
		}
	    }
	    System.out.println("verification unsuccesful. Retry");
	}
    }

    public static void newStudentAcc(String osis, String fdigit){
	Scanner s = new Scanner(System.in);
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
	FileMaker.newStudent(fname, lname, osis, fdigit, year);
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
		if(verifyStudent()){
		    break;
		}
	    }
	}
    }
    
    public static void menu(){
	Scanner s = new Scanner(System.in);
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

    public static void startupMenu(){
	Scanner s = new Scanner(System.in);
	System.out.println("Welcome!");
	System.out.println("What would you like to do? verify/login/quit");
	while(true){
	    if(s.hasNext()){
		String x = s.nextLine();
		if(x.equals("verify")){
		    verify();
		    break;
		}
		else if(x.equals("login")){
		    break;
		}
		else if(x.equals("quit")){
		    quit();
		}
		else{
		    System.out.println("Not a valid option. Please retry");
		}
	    }
	}
    }

    public static void doLogin(){
	Scanner s = new Scanner(System.in);
	String username = "";
	String password = "";
	System.out.println("Username");
	if(s.hasNext()){
	    username = s.nextLine();
	}
	System.out.println("Password");
	if(s.hasNext()){
	    password = s.nextLine();
	}
	if(u.login(username, password)){
	    u.setName(username);
	    System.out.println("Login Successful!" + "\n");
	    setLoggedIn(true);
	}
	else{
	    System.out.println("Login failed. Retry." + "\n");
	}
    }

    public static void main(String[] args){
	while(getUsing()){
	    startupMenu();
	    
	    startLogIn();

	    doLogin();

	    while(getLoggedIn()){
		menu();
	    }

	}
    }
}