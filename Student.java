import java.util.*; //imports Listt/ArrayList/Scanner

public class Student extends User{

    protected int _osis;
    protected int _fourdigit;

    

    //********Constructor***********//
    public Student(String name){
	super(name);
	_osis = getOSIS();
	_fourdigit = getfourDigit();
    }

    //============ Get Set Methods ===========//
    
    // OSIS & 4digit & grad year are inhereted from User -- This overwrites them to call using lfname instead of taking an input, since students can only check their own osis
    //Returns 0 if student info file doesn't exist
    public int getOSIS(){
	if (!super.fexist())
	    return 0;
	List<String[]> temp = ReadCSV.read(_lfname + "Info.txt");
	return Integer.parseInt( temp.get(1)[2]);

    }
    
    public int getfourDigit(){
	if (!super.fexist())
	    return 0;
	List<String[]> temp = ReadCSV.read(_lfname + "Info.txt");
	return Integer.parseInt( temp.get(1)[3]);
    }

    
    public int getGrad(){
	if (!super.fexist())
	    return 0;
	List<String[]> temp = ReadCSV.read(_lfname + "Info.txt");
	return Integer.parseInt( temp.get(1)[4]);
    }

    //Gets First and Last names respectively
    
    public String[] getflname(){
	if (!fexist())
	    return null;
	
	List<String[]> temp = ReadCSV.read(_lfname + "Info.txt");
	String[] retArr = {temp.get(1)[0], temp.get(1)[1]};
	return retArr;
    }
    //============ End GET SET ==================//

    //login
    public boolean login(String name, String password){
	List<String[]> temp = ReadCSV.read("STUDENTUSERS.txt");
	for(int i = 0; i < temp.size(); i++){
	    if(temp.get(i)[0].equals(name))
		if(temp.get(i)[1].equals(password))
		    return true;
	}
	return false;
    }

    protected boolean newPass(String newPass){
    	List<String[]> temp = ReadCSV.read("STUDENTUSERS.txt");
	for(int i = 0; i < temp.size(); i++){
	    if (temp.get(i)[0].equals(_lfname)){
		ArrayList<String> quack = new ArrayList<String>();
		quack.add(temp.get(i)[0]);
		quack.add(newPass);
		FileMaker.changeLine("STUDENTUSERS.txt", i, quack);
		System.out.println("Password Successfully Changed");
		return true;
	    }
	}
	return false;
    	
    }

    //Check Grades section//

    //Checks grades w/ no input-
    //returns average in normal and gpa form

    public String checkGrades(){
	if (!super.fexist())
	    return "Student name invalid. Log out and try again."; 
	List<String[]> temp = ReadCSV.read(_lfname + "Info.txt");
	String[] grades = temp.get(3);
	String retStr = "Transcript Average: \n";
	retStr += grades[0] + "\n";
	double GPA =Double.parseDouble( grades[0]);
	retStr += "and your GPA is:\n" + (GPA/20.0 +1.0);
	return retStr;
    }


    //Takes a class' name as an input, returns average for that class and grade breakdowns
    public String checkGrades(String className){
	//This is to check if the String is actually a department, in which case the student's average for that department will be returned.
	List<String[]> temp = ReadCSV.read(_lfname + "Info.txt");
	String[] grades = temp.get(3);
	for (int i = 0; i<9; i++)
	    if (className.toLowerCase().equals(super.DEPARTMENTS[i])){
		return ("Your "+ super.DEPARTMENTS[i] + " average is:\n" +  grades[i+1]);
	    }
	//Checks classes' existence and then returns the grade one line down
	String[] classes = temp.get(2);
	for(int i = 1; i < classes.length;i++)
	    if (className.toLowerCase().equals(classes[i]))
		return "Your " + className + " average is:\n" + grades[i];
	return "Invalid class name/ Class does not exist";
    }
	    
    // Specific Check Grades Section //

    //Checks the breakdown in the following format:
    // |Class Name | Class Average |
    // |Test Average |
    //       |Test 1|
    //       |Test 2|
    // |Particiption |

    /*
      1:depName, Coursename
      2:Teacher, Section, # of students
      3: Studentname, TestAv, Test1,Test2, etc.., Participation, Project, Project 1, project 2 etc
    */
    public void checkBreakDown(String courseName, String sectionNum){
	//This part isolates the line in the course file that has that student's grade breakdown
	List<String[]> temp = ReadCSV.read(courseName + ".txt");
	int lineNum = 2;
	for (int i = 1; i < temp.size(); i += Integer.parseInt(temp.get(i)[2])+1){	    
	    if (temp.get(i)[1].equals(sectionNum)){
		lineNum = i+1;
		for (int stud = lineNum; stud< (lineNum + Integer.parseInt( temp.get((lineNum-1))[2])+1); stud+=2){
		    if (temp.get(stud)[0].equals(_lfname)){
			lineNum = stud;
			break;
		    }
		}
		break;
	    }	    
	}
/*
	String error ="Error Course Name or Section Number Invalid";
	if (!(temp.get(lineNum)[0].equals(_lfname))){
	    
	    System.out.println( error);
	    return;
	}
*/
	String[] studGradesLabel = temp.get(lineNum);
	String[] studGrades = temp.get(lineNum+1);
	String retStr = "\n|"+ courseName+"|";

	//This section is to get average for that class
	List<String[]> temp1 = ReadCSV.read(_lfname + "Info.txt");
	String[] grades = temp1.get(3);
	String[] classes = temp1.get(2);
	for(int i = 1; i < classes.length;i++)
	    if (courseName.toLowerCase().equals(classes[i]))
	        retStr +=  grades[i] + "|\n";
	
	int placeHolder = 1;
	//For loop for test scores
	for (; (!studGradesLabel[placeHolder].equals("participation")); placeHolder++){
	    if (placeHolder == 1)
		retStr += "|Test Average : " + studGrades[1] +"|\n";
	    else
		retStr += "\t|" + studGradesLabel[placeHolder] + "|" + studGrades[placeHolder]+"|\n";
	}

	//Adds Participation grade

	retStr += "|Participation| " + studGrades[placeHolder] + " |\n";
	placeHolder += 1;
	int projectSpot = placeHolder;
	//For loop 4 Project Scores
	
	for (; (!studGradesLabel[placeHolder].equals("homeworkav")); placeHolder++){
	    if (placeHolder == projectSpot)
		retStr += "|Project  Average : " + studGrades[placeHolder] +"|\n";
	    else
		retStr += "\t|" + studGradesLabel[placeHolder] + "|" + studGrades[placeHolder]+"|\n";
	}	    

	//For loop 4 homework Scores
	int homeworkSpot = placeHolder;
	
	for (; placeHolder < studGradesLabel.length; placeHolder++){
	    if (placeHolder == homeworkSpot)
		retStr += "|Homework  Average : " + studGrades[placeHolder] +"|\n";
	    else
		retStr += "\t|" + studGradesLabel[placeHolder] + "|" + studGrades[placeHolder]+"|\n";
	}

	System.out.println( retStr);
	
    }

    //Overwritten TOSTRING

    public String toString(){
	if (!fexist())
	    return "No Student Information Availible";
	List<String[]> temp = ReadCSV.read(_lfname + "Info.txt");
	
	String retStr = ("First Name: " + getflname()[0] + "\nLast Name: " + getflname()[1]+ "\n");
	retStr += "OSIS: " + getOSIS() + "\nFour Digit: " + getfourDigit()+ "\nGraduation Year: " + getGrad() + "\n";
	String[] grades = temp.get(3);
	retStr += "Average: " + grades[0]+ "\n";
	String[] classes = temp.get(2);
	for (int i = 0; i< 8; i++)
	    retStr += super.DEPARTMENTS[i] + " average: " + grades[i+1] + "\n";
	return retStr;


    }

	
}
