import java.util.*; //imports List/ArrayList/Scanner

public class User{

    // states whether a user is logged in
    protected boolean _isLoggedIn;
    protected String _lfname; //last, first name

    // Constructor, sets _isLoggedIn to false
    public User(){
	_isLoggedIn = false;
    }

    // Accesor for _isLoggedIn;
    public boolean getIsIn(){
	return _isLoggedIn;
    }

    // Mutator for _isLoggedIn;
    public void setIsIn(boolean newState){
	_isLoggedIn = newState;
    }

    public String getName(){
	return _lfname;
    }

    public void setName(String newName){
	_lfname = newName;
    }

    // Returns true if login succesful, false if not
    public boolean login(String name, String password){
	List<String[]> temp = ReadCSV.read("USERS.txt");
	for(int i = 0; i < temp.size(); i++){
	    if(temp.get(i)[0].equals(name)){
		if(temp.get(i)[1].equals(password)){
		    setIsIn(true);
		    return true;
		}
	    }
	}
	setIsIn(false);
	return false;
    }

    // logs out, turns _isLoggedIn to false
    public void logout(){
	setIsIn(false);
    }

    // to work on, check schedule
    /*
      public Schedule checkSchedule()
     */

    public static void main(String[] args){
	User u = new User();
	Scanner s = new Scanner(System.in);
	String username = "";
	String password = "";
	Boolean loggedIn = false;

	while(loggedIn != true){
	    System.out.println("Username");
	    if(s.hasNext()){
		username = s.nextLine();
	    }
	    System.out.println("Password");
	    if(s.hasNext()){
		password = s.nextLine();
	    }
	    loggedIn = (u.login(username, password));
	    System.out.println(loggedIn + "\n");
	}

	while(u.getIsIn()){
	    System.out.println("Do you want to log out? y/n");
	    if(s.nextLine().equals("y")){
		u.setIsIn(false);
	    }
	}

    }

}