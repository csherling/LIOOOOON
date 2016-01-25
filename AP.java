import java.util.*; //List, ArrayList, Scanner

public class AP extends Teacher{

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

}
