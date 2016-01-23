import java.util.*; //imports List/ArrayList/Scanner

public class User{

    
    protected String _lfname; //last, first name

    // Constructor
    public User(){
    }

    public User(String name){

	setName(name);
    }
    //============ Get Set Methods ===========//
	
    public String getName(){
	return _lfname;
    }

     protected void setName(String newName){
	_lfname = newName;
    }

    protected String getOSIS(){
	

    }




    
    // Returns true if login succesful, false if not
    public boolean login(String name, String password){
	List<String[]> temp = ReadCSV.read("USERS.txt");
	for(int i = 0; i < temp.size(); i++){
	    if(temp.get(i)[0].equals(name))
		if(temp.get(i)[1].equals(password))
		    return true;
	}
	return false;
    }
    
    // Changes pass of a user
    protected boolean newPass(String newPass){
    	List<String[]> temp = ReadCSV.read("USERS.txt");
	for(int i = 0; i < temp.size(); i++){
	    if (temp.get(i)[0].equals(_lfname)){
		ArrayList<String> quack = new ArrayList<String>();
		quack.add(temp.get(i)[0]);
		quack.add(newPass);
		FileMaker.changeLine("USERS.txt", i, quack);
		System.out.println("Password Successfully Changed");
		return true;
	    }
	}
	return false;
    	
    }

    // logs out, turns _isLoggedIn to false
    public void logout(){
	Book.setLoggedIn(false);
    }

 
    
    public static void main(String[] args){
    }
    /*
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
    */

}
