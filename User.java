import java.util.*; //imports Listt/ArrayList/Scanner

public class User{

    
    protected String _lfname; //last, first name
    public final String[] DEPARTMENTS = {"math", "biology", "english","history","special", "physics", "chemistry", "compSci"};
    // Constructor
    public User(){
    }

    public User(String name){

	setName(name);
    }

    //Does File exist helper method

    public boolean fexist(){
	File use = new File(_lfname+"info.txt");
	return (use.exists());
    }
    
    public boolean fexist(String fileName){
	File use = new File(fileName+".txt");
	return (use.exists());
    }
    
    
    
    //==================================== Get Set Methods ===========================================//
	
    public String getName(){
	return _lfname;
    }

     protected void setName(String newName){
	_lfname = newName;
    }

    //Get OSIS & fourdigit &grad year - takes student name as input
    //Overwritten in Student
    //returns 0 if student info file doesn't exist
    protected int getOSIS(String name){
	if (!fexist(name))
	    return 0;
	
	List<String[]> temp = ReadCSV.read(name + "info.txt");
	return Integer.parseInt( temp.get(1)[2]);

    }

    protected int getfourDigit(String name){
	if (!fexist(name))
	    return 0;
	
	List<String[]> temp = ReadCSV.read(name + "info.txt");
	return Integer.parseInt( temp.get(1)[3]);
    }

    protected int getGrad(String name){
	if (!fexist(name))
	    return 0;
	
	List<String[]> temp = ReadCSV.read(name + "info.txt");
	return Integer.parseInt( temp.get(1)[4]);
    }
    
    //Gets First and Last names respectively
    
    protected String[] getflname(){
	if (!fexist(name))
	    return null;
	
	List<String[]> temp = ReadCSV.read(name + "info.txt");
	String[] retArr = {temp.get(1)[0], temp.get(1)[1]};
	return retArr;
    }
    //==================================== END GET SET ==========================================//

    
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
    
    
      User u = new User("aleks");
    u.getOSIS();
    }
    /*
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
