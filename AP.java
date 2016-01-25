import java.util.*; //List, ArrayList, Scanner

public class AP extends Teacher{

    public AP(String name){
	super(name);
    }

    public static void addOFD(){
	String osis = "";
	String fdigit = "";
	Scanner s = new Scanner(System.in);
	List<String> line = new ArrayList<String>();
	System.out.println("OSIS");
	if(s.hasNext()){
	    osis = (s.nextLine());
	}
	System.out.println("4 digit");
	if(s.hasNext()){
	    fdigit = (s.nextLine());
	}
	line.add(osis);
	line.add(fdigit);
	FileMaker.appendLine("osis_fdigit.txt", line);
    }

    public static void addTID(){
	String tid = "";
	String fdigit = "";
	Scanner s = new Scanner(System.in);
	List<String> line = new ArrayList<String>();
	System.out.println("6 digit TID");
	if(s.hasNext()){
	    tid = (s.nextLine());
	}
	System.out.println("4 digit");
	if(s.hasNext()){
	    fdigit = (s.nextLine());
	}
	line.add(tid);
	line.add(fdigit);
	FileMaker.appendLine("TID.txt", line);
    }

    public boolean login(String name, String password){
	List<String[]> temp = ReadCSV.read("APUSERS.txt");
	for(int i = 0; i < temp.size(); i++){
	    if(temp.get(i)[0].equals(name))
		if(temp.get(i)[1].equals(password))
		    return true;
	}
	return false;
    }

}
