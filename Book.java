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