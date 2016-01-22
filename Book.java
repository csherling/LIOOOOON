import java.util.*; //imports List/ArrayList/Scanner

public class Book{

    private static User u;
    private static boolean _Using = true;
    private static boolean _loggedIn = false;

    //returns the logged in state
    public static boolean getLoggedIn(){
	return _loggedIn;
    }
    //changes the logged in state
    public static void setLoggedIn(boolean newLIState){
	_loggedIn = newLIState;
    }
    //returns whether you're using the program
    public static boolean getUsing(){
	return _Using;
    }
    //changes whether you are using the program
    public static void setUsing(boolean newUsing){
	_Using = newUsing;
    }
    //clears the terminal screen like the clear command
    public static void clear(){	
	System.out.print("\033[H\033[2J");
	System.out.flush();
    }
    //initiates the login process
    public static void startLogIn(){
	Scanner s = new Scanner(System.in);
	String x = "";
	clear();
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
	    clear();
	    System.out.println("Login Successful!" + "\n");
	    setLoggedIn(true);
	}
	else{
	    clear();
	    System.out.println("Login failed. Retry." + "\n");
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
			clear();
			System.out.println("Verification Success!\n");
			newStudentAcc(osis, fdigit);
			return true;
		    }
		}
	    }
	    clear();
	    System.out.println("verification unsuccesful. Retry");
	}
    }

    public static boolean verifyTeacher(){
	List<String[]> temp = ReadCSV.read("TID.txt");
	Scanner s = new Scanner(System.in);
	String tid = "";
	String fdigit = "";
	while(true){
	    System.out.println("What is your TID?");
	    if(s.hasNext()){
		tid = (s.nextLine());
	    }   
	    System.out.println("What is your four digit ID?");
	    if(s.hasNext()){
		fdigit = (s.nextLine());
	    }   
	    for(int i = 0; i < temp.size(); i++){
		if(temp.get(i)[0].equals(tid)){
		    if(temp.get(i)[1].equals(fdigit)){
			clear();
			System.out.println("Verification Success!\n");
			newTeacherAcc(tid, fdigit);
			return true;
		    }
		}
	    }
	    clear();
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

    public static void newTeacherAcc(String tid, String fdigit){
	Scanner s = new Scanner(System.in);
	String fname = "";
	String lname = "";
	String subject = "";
	System.out.println("What is your first Name?");
	if(s.hasNext()){
	    fname = (s.nextLine());
	}   		   
	System.out.println("What is your last Name?");
	if(s.hasNext()){
	    lname = (s.nextLine());
	}   		   
	System.out.println("What is your Subject?");
	if(s.hasNext()){
	    subject = (s.nextLine());
	}   		   
	FileMaker.newTeacher(fname, lname, tid, fdigit, subject);
    }

    public static void verify(){
	Scanner s = new Scanner(System.in);
	String type  = "";
	String temp1 = "";
	String temp2 = "";
	clear();
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
	    else if(type.equals("teacher")){
		if(verifyTeacher()){
		    break;
		}
	    }
	    else{
		System.out.println("Please respecify!");
	    }
	}
    }

    public static void studentMenu(){
	Scanner s = new Scanner(System.in);
	String temp = "";
	clear();
	System.out.println("What would you like to do?");
	System.out.println("Options: quit/logout/change pass");
	System.out.println("mkcoursereq/chkcoursereq/chkgrade");

	if(s.hasNext()){
	    temp = (s.nextLine());
	}
	commonMenu(temp);
	if(temp.equals("mkcoursereq")){
	    //	    
	}
	if(temp.equals("chkcoursereq")){
	    //	    
	}
	if(temp.equals("chkgrade")){
	    //	    
	}
    }

    public static void teacherMenu(){
	Scanner s = new Scanner(System.in);
	String temp = "";
	clear();
	System.out.println("What would you like to do?");
	System.out.println("Options: quit/logout/change pass");
	System.out.println("mkcoursereq/chkcoursereq/chkgradebook");

	if(s.hasNext()){
	    temp = (s.nextLine());
	}
	commonMenu(temp);
	if(temp.equals("chkgradebook")){
	    //	    
	}
    }

    public static void overlordMenu(){
	Scanner s = new Scanner(System.in);
	String temp = "";
	clear();
	System.out.println("What would you like to do?");
	System.out.println("Options: quit/logout/change pass/addosisfdigit/addtid");
	System.out.println("mkcoursereq/chkcoursereq/chkgradebook");

	if(s.hasNext()){
	    temp = (s.nextLine());
	}
	commonMenu(temp);
	if(temp.equals("addosisfdigit")){
	    //	    
	}
	if(temp.equals("addtid")){
	    FileMaker.addTID();
	}
    }

    public static void commonMenu(String temp){
	if(temp.equals("change pass")){
	    changePass();
	}
	if(temp.equals("quit")){
	    quit();
	}
	if(temp.equals("logout")){
	    setLoggedIn(false);
	}
    }
    
    public static void menu(){
	Scanner s = new Scanner(System.in);
	String temp = "";
	clear();
	System.out.println("What would you like to do?");
	System.out.println("Options: quit/logout/change pass");

	if(s.hasNext()){
	    temp = (s.nextLine());
	}
	if(temp.equals("change pass")){
	    changePass();
	}
	if(temp.equals("quit")){
	    quit();
	}
	if(temp.equals("logout")){
	    setLoggedIn(false);
	}
    }

    public static void quit(){
	clear();
	System.exit(1);
    }

    public static void startupMenu(){
	Scanner s = new Scanner(System.in);
	clear();
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

    public static void changePass(){
	Scanner s = new Scanner(System.in);
	clear();
	System.out.println("You are able to change your password now!");
	System.out.println("type quit/cancel to exit or cancel respectively");
	while(true){
	    if(s.hasNext()){
		String x = s.nextLine();
		if(u.newPass(x)){
		    break;
		}
		else if(x.equals("quit")){
		    quit();
		}
		else if(x.equals("cancel")){
		    break;
		}
		else{
		    System.out.println("Please retry");
		}
	    }
	}
    }

    public static void main(String[] args){
	while(getUsing()){
	    startupMenu();
	    
	    startLogIn();

	    while(getLoggedIn() == false){
	    doLogin();
	    }

	    while(getLoggedIn()){
		menu();
	    }

	}
    }
}