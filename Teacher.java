import java.util.*; //imports Listt/ArrayList/Scanner

public class Teacher extends Admin{

    public Teacher(String name){
	super(name);
    }

    public boolean login(String name, String password){
	List<String[]> temp = ReadCSV.read("TEACHERUSERS.txt");
	for(int i = 0; i < temp.size(); i++){
	    if(temp.get(i)[0].equals(name))
		if(temp.get(i)[1].equals(password))
		    return true;
	}
	return false;
    }

    protected boolean newPass(String newPass){
    	List<String[]> temp = ReadCSV.read("TEACHERUSERS.txt");
	for(int i = 0; i < temp.size(); i++){
	    if (temp.get(i)[0].equals(_lfname)){
		ArrayList<String> quack = new ArrayList<String>();
		quack.add(temp.get(i)[0]);
		quack.add(newPass);
		FileMaker.changeLine("TEACHERUSERS.txt", i, quack);
		System.out.println("Password Successfully Changed");
		return true;
	    }
	}
	return false;
    	
    }

}