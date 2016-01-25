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




}