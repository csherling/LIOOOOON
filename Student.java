public class Student extends User{

    protected int _osis;
    protected int _fourdigit;
    public final String[] DEPARTMENTS = {"math", "biology", "english","history","special", "physics", "chemistry", "compSci"};
    

    //********Constructor***********//
    public Student(String name){
	super(name);
	_osis = getOSIS();
	_fourdigit = getfourDigit();
    }

    //============ Get Set Methods ===========//
    
    // OSIS & 4digit & grad year are inhereted from User -- This overwrites them to call using lfname instead of taking an input, since students can only check their own osis
    //Returns 0 if student info file doesn't exist
    protected int getOSIS(){
	if (!super.fexist())
	    return 0;
	List<String[]> temp = ReadCSV.read(_lfname + "info.txt");
	return Integer.parseInt( temp.get(1)[2]);

    }
    
    protected int getfourDigit(){
	if (!super.fexist())
	    return 0;
	List<String[]> temp = ReadCSV.read(_lfname + "info.txt");
	return Integer.parseInt( temp.get(1)[3]);
    }

    
    protected int getGrad(){
	if (!super.fexist())
	    return 0;
	List<String[]> temp = ReadCSV.read(_lfname + "info.txt");
	return Integer.parseInt( temp.get(1)[4]);
    }

    //Gets First and Last names respectively
    
    protected String[] getflname(){
	if (!fexist())
	    return null;
	
	List<String[]> temp = ReadCSV.read(_lfname + "info.txt");
	String[] retArr = {temp.get(1)[0], temp.get(1)[1]};
	return retArr;
    }
    //============ End GET SET ==================//

    //Check Grades section//

    //Checks grades w/ no input-
    //returns average in normal and gpa form

    public String checkGrades(){
	if (!super.fexist())
	    return "Student name invalid. Log out and try again."; 
	List<String[]> temp = ReadCSV.read(_lfname + "info.txt");
	String[] grades = temp.get(3);
	String retStr = "Transcript Average: \n";
	retStr += grades[0];
	retstr =+ " and your GPA is:\n" + grades[0]/20 +1;
	return retStr;
    }


    //Takes a class' name as an input, returns average for that class and grade breakdowns
    public String checkGrades(String className){
	//This is to check if the String is actually a department, in which case the student's average for that department will be returned.
	List<String[]> temp = ReadCSV.read(_lfname + "info.txt");
	String[] grades = temp.get(3);
	for (int i = 0; i<9; i++)
	    if (className.toLowerCase().equals(DEPARTMENTS[i])){
		return ("Your "+ DEPARTMENTS(depNum) + " average is:\n" +  grades[depNum+1]);
	    }
	//Checks classes' existence and then returns the grade one line down
	String[] classes = temp.get(2);
	for(int i = 1; i < classes.length();i++)
	    if (className.toLowerCase().equals(classes[i]))
		return "Your " + className + " average is:\n" + grades[i];
	return "Invalid class name/ Class does not exist";
    }
	    


    //Overwritten TOSTRING

    public String toString(){
	if (!fexist())
	    return "No Student Information Availible";
	List<String[]> temp = ReadCSV.read(_lfname + "info.txt");
	
	retStr = "First Name: " + getflname()[0] + "\nLast Name: " + getflname()[1]+ "\n";
	retStr += "OSIS: " + getOSIS() + "\nFour Digit: " + getfourDigit()+ "\nGraduation Year: " + getGrad() + "\n";
	String[] grades = temp.get(3);
	retStr += "Average: " + grades[0]+ "\n";
	String[] classes = temp.get(2);
	for (int i = 1; i< 9; i++)
	    retStr += DEPARTMENTS[i-1] + " average: " + grades[i+1];
	


    }

	
}
