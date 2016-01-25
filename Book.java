import java.util.*; //imports List/ArrayList/Scanner

public class Book{

    private static User u;
    private static String _usertype;
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
		_usertype = x;
		break;
	    }
	    else if(x.equals("teacher")){
		_usertype = x;
		break;
	    }
	    else if(x.equals("ap")){
		_usertype = x;
		break;
	    }
	    else if(x.equals("overlord")){
		_usertype = x;
		break;
	    }
	    else{
		System.out.println("Please re-specify");
	    }
	}
    }

    public static void instantiate(String lfname){
	if(_usertype.equals("student")){
	    u = new Student(lfname);
	}
	if(_usertype.equals("teacher")){
	    u = new Teacher(lfname);
	}
	if(_usertype.equals("ap")){
	    u = new AP(lfname);
	}
	if(_usertype.equals("overlord")){
	    u = new Overlord(lfname);
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
	instantiate(username);

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

    public static boolean verifyAP(){
	List<String[]> temp = ReadCSV.read("APID.txt");
	Scanner s = new Scanner(System.in);
	String apid = "";
	String fdigit = "";
	while(true){
	    System.out.println("What is your APID?");
	    if(s.hasNext()){
		apid = (s.nextLine());
	    }   
	    System.out.println("What is your four digit ID?");
	    if(s.hasNext()){
		fdigit = (s.nextLine());
	    }   
	    for(int i = 0; i < temp.size(); i++){
		if(temp.get(i)[0].equals(apid)){
		    if(temp.get(i)[1].equals(fdigit)){
			clear();
			System.out.println("Verification Success!\n");
			newTeacherAcc(apid, fdigit);
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

    public static void newAPAcc(String apid, String fdigit){
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
	FileMaker.newTeacher(fname, lname, apid, fdigit, subject);
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
	    else if(type.equals("ap")){
		if(verifyAP()){
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
	System.out.println("What would you like to do?");
	System.out.println("Options: quit/logout/change pass/myInfo/check grades/check class grade");
	System.out.println("course breakdown");

	if(s.hasNext()){
	    temp = (s.nextLine());
	}
	commonMenu(temp);
	if(temp.equals("myInfo")){
	    System.out.println((Student)u);
	}    
	if(temp.equals("check grades")){
	    System.out.println(((Student)u).checkGrades());
	    stall();
	}
	if(temp.equals("check class grade")){
	    clear();
	    System.out.println("Which class?");
	    if(s.hasNext()){
		System.out.println(((Student)u).checkGrades(s.nextLine()));
	    }
	    stall();
	}
	if(temp.equals("course breakdown")){
	    //
	}

    }

    public static void teacherMenu(){
	Scanner s = new Scanner(System.in);
	String temp = "";
	System.out.println("What would you like to do?");
	System.out.println("Options: quit/logout/change pass");
	System.out.println("check student transcript/check student class grade/student info");

	if(s.hasNext()){
	    temp = (s.nextLine());
	}
	commonMenu(temp);
	if(temp.equals("check student transcript")){
	    String fname = "";
	    String lname = "";
	    System.out.println("Student's First name?");
	    if(s.hasNext()){
		fname = (s.nextLine());
	    }	    
	    System.out.println("Student's Last name?");
	    if(s.hasNext()){
		lname = (s.nextLine());
	    }
	    fname.toLowerCase();
	    lname.toLowerCase();
	    System.out.println(((Teacher)u).checkGrades(lname + fname));
	    stall();
	}
	if(temp.equals("check student class grade")){
	    //	    
	}
	if(temp.equals("student info")){
	    //	    
	}
    }

    public static void apMenu(){
	Scanner s = new Scanner(System.in);
	String temp = "";

	System.out.println("What would you like to do?");
	System.out.println("Options: quit/logout/change pass/addtid");
	System.out.println("mkcoursereq/chkcoursereq/chkgradebook/addosisfdigit");
	
	if(s.hasNext()){
	    temp = (s.nextLine());
	}
	commonMenu(temp);
	if(temp.equals("addosisfdigit")){
	    AP.addOFD();
	}
	if(temp.equals("addtid")){
	    AP.addTID();
	}
	
    }

    public static void overlordMenu(){
	Scanner s = new Scanner(System.in);
	String temp = "";

	System.out.println("What would you like to do?");
	System.out.println("Options: quit/logout/change pass/addtid");
	System.out.println("mkcoursereq/chkcoursereq/chkgradebook/addosisfdigit");
	
	if(s.hasNext()){
	    temp = (s.nextLine());
	}
	commonMenu(temp);
	if(temp.equals("addosisfdigit")){
	    Overlord.addOFD();
	}
	if(temp.equals("addtid")){
	    Overlord.addTID();
	}
	if(temp.equals("addapid")){
	    Overlord.addAPID();
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

	if(u instanceof Student){
	    studentMenu();
	}
	if(u instanceof Teacher){
	    teacherMenu();
	}
	if(u instanceof AP){
	    //apMenu();
	}
	if(u instanceof Overlord){
	    overlordMenu();
	}
    }

    public static void stall(){
	Scanner s = new Scanner(System.in);
	System.out.println("Type anything and press enter to continue");
	if(s.hasNext()){
   
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