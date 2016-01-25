import java.util.*;//List, ArrayList, Scanner

public class Overlord extends AP{

    public Overlord(String name){
	super(name);
    }

    public static void addAPID(){
	String apid = "";
	String fdigit = "";
	Scanner s = new Scanner(System.in);
	List<String> line = new ArrayList<String>();
	System.out.println("6 digit APID");
	if(s.hasNext()){
	    apid = (s.nextLine());
	}
	System.out.println("4 digit");
	if(s.hasNext()){
	    fdigit = (s.nextLine());
	}
	line.add(apid);
	line.add(fdigit);
	FileMaker.appendLine("APID.txt", line);
    }

    public boolean login(String name, String password){
	List<String[]> temp = ReadCSV.read("OVUSERS.txt");
	for(int i = 0; i < temp.size(); i++){
	    if(temp.get(i)[0].equals(name))
		if(temp.get(i)[1].equals(password))
		    return true;
	}
	return false;
    }

    protected boolean newPass(String newPass){
    	List<String[]> temp = ReadCSV.read("OVUSERS.txt");
	for(int i = 0; i < temp.size(); i++){
	    if (temp.get(i)[0].equals(_lfname)){
		ArrayList<String> quack = new ArrayList<String>();
		quack.add(temp.get(i)[0]);
		quack.add(newPass);
		FileMaker.changeLine("OVUSERS.txt", i, quack);
		System.out.println("Password Successfully Changed");
		return true;
	    }
	}
	return false;
    	
    }

}