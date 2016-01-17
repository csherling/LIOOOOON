import java.io.*; //BufferedWriter, File, FileWriter, IOException
import java.util.*; // List, ArrayList

public class FileMaker {

    //precond, none
    //postcond, new studentInfo file with basic info
    public static void newStudent(String username, String osis, String fourdigit, String year){
        try{
            File file = new File(username + "Info.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Studentname,studentosis,studentid,studentgradyear\n");
            bw.write(username + "," + osis + "," + fourdigit + "," + year);
            bw.write("\n");
            bw.flush();
            bw.close();

        }catch(IOException e){
        e.printStackTrace();
        }
    }


    //preconds, all arrays same length.
    //postconds, creates multiple new studentInfo files with basic info
    public static void newStudents(List<String> username, List<String> osis, List<String> fourdigit, List<String> year){
	
	for(int i = 0; i < username.size(); i++){//goes through the ArrayLists
	    try{	
		File file = new File(username.get(i) + "Info.txt");
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Studentname,studentosis,studentid,studentgradyear\n");
		bw.write(username.get(i) + "," + osis.get(i) + "," + fourdigit.get(i) + "," + year.get(i));
		bw.write("\n");
		bw.flush();
		bw.close();
		
	    }catch(IOException e){
		e.printStackTrace();
	    }
	}
    }

    //preconds, filename is full, including .txt.
    //postconds, file line changed
    public static void changeLine(String fileName, int lineNum, List<String> newLine){
        try{
	    List<String[]> tempFile = ReadCSV.read(fileName);
	    File newF = new File(fileName);
	    FileWriter fw = new FileWriter(newF);
	    BufferedWriter bw = new BufferedWriter(fw);
	    String[] tempNew = new String[newLine.size()];
	    System.out.println(fileName);
	    System.out.println(tempFile);
	    for(int i = 0; i < newLine.size(); i++){
		tempNew[i] = newLine.get(i);
	    }
	    tempFile.set(lineNum, tempNew);
	    for(int i = 0; i < tempFile.size(); i++){
		String tempLine = "";
		for(int j = 0; j < tempFile.get(i).length; j++){
		    if(j < tempFile.get(i).length - 1){
			tempLine += tempFile.get(i)[j] + ",";
		    }
		    else{
			tempLine += tempFile.get(i)[j] + "\n";
		    }
		}
		bw.write(tempLine);
	    }
	    bw.flush();
	    bw.close();
	}
        catch(IOException e){
        e.printStackTrace();
        }
	
    }

    //working tests
    /*
    public static void main(String[] args) {
		newStudent("sherlingchristopher", "205704083", "3750", "2017");
	List<String> un = new ArrayList<String>();
	List<String> os = new ArrayList<String>();
	List<String> fd = new ArrayList<String>();
	List<String> sy = new ArrayList<String>();
	un.add("lob");
	un.add("lol");
	un.add("kek");
	un.add("tek");
	un.add("lok");
	for(int i = 0; i < 5; i++){
	    os.add(((int)(Math.random() * 900000000) + 100000000) + "");
	    fd.add(((int)(Math.random() * 9000) + 1000) + "");
	    sy.add(((int)(Math.random() * 4) + 2016) + "");
	}
		newStudents(un, os, fd, sy);
	changeLine("lobInfo.txt", 0, un);
    }
    */
}
