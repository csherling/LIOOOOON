import java.util.*;

public class User{

    // Returns true if login succesful, false if not
    public static boolean login(String name, String password){
	List<String[]> temp = ReadCSV.read("USERS.txt");
	for(int i = 0; i < temp.size(); i++){
	    if(temp.get(i)[0].equals(name)){
		return temp.get(i)[1].equals(password);
	    }
	}
	return false;
    }

    public static void main(String[] args){
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
	    loggedIn = (login(username, password));
	    System.out.println(loggedIn + "\n");
	}

    }

}