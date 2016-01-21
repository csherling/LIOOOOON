import java.util.*; //List, ArrayList

public class AP extends Admin{

    public void addOFD(String osis, String fdigit){
	List<String> temp = new ArrayList<String>();
	temp.add(osis);
	temp.add(fdigit);
	FileMaker.appendLine("osis_fdigit.txt",temp);
    }

    public void addTID(String TID, String fdigit){
	List<String> temp = new ArrayList<String>();
	temp.add(TID);
	temp.add(fdigit);
	FileMaker.appendLine("TID.txt",temp);
    }

}