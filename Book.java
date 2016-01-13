import java.util.*; //imports List/ArrayList/Scanner

public class Book{

    private static User u;

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
	else if(s.equals("po")){
	    u = new PO();
	    return true;
	}
	else{
	    return false;
	}

    }

    //public static void login

    public static void main(String[] args){
	Scanner s = new Scanner(System.in);
	String username = "";
	String password = "";

	System.out.println("What would you like to log in as? student/teacher/ap/po?");
	while(true){
	    if(startLogIn(s.nextLine())){
		System.out.println("Please log in! \n");
		break;
	    }
	    else{
		System.out.println("Please re-specify");
	    }
	   
	}

	while(u.getIsIn() != true){
	    System.out.println("Username");
	    if(s.hasNext()){
		username = s.nextLine();
	    }
	    System.out.println("Password");
	    if(s.hasNext()){
		password = s.nextLine();
	    }
	    u.login(username, password);
	    u.setName(username);
	    if(u.getIsIn()){
		System.out.println("Login Successful!" + "\n");
	    }
	    else{
		System.out.println("Login failed. Retry." + "\n");
	    }

	}

    }
}