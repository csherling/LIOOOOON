public class Admin extends User{

    protected int _TID;
    protected int _Tfourdigit;
    public String[] _coursesTeaching = new String[10];

    //============Constructor=============//
    public Admin(String name){
	super(name);
	List<String[]> temp = ReadCSV.read(name + "Teacherinfo.txt");
	String[] info = temp.get(1);
	_TID = info[2];
	_Tfourdigit = info[3];
        for (int i = 5; i <  Integer.parseInt(info[4]) ; i++)
	    _coursesTeaching[i-4] = info[i];
    }

    //========== Specific Student Check Infos============//

    //Check Transcript average of any student
    public String checkGrades(String name){
	if (!super.fexist(name))
	    return "Student name invalid. Log out and try again."; 
	List<String[]> temp = ReadCSV.read(name + "info.txt");
	String[] grades = temp.get(3);
	String retStr = "Transcript Average: \n";
	retStr += grades[0];
	return retStr;
    }


    //Takes a class' name as an input, returns average for that class and grade breakdowns
    public String checkGrades(String name, String className){
	//This is to check if the String is actually a department, in which case the student's average for that department will be returned.
	List<String[]> temp = ReadCSV.read(name + "info.txt");
	String[] grades = temp.get(3);
	for (int i = 0; i<9; i++)
	    if (className.toLowerCase().equals(DEPARTMENTS[i])){
		return (DEPARTMENTS(depNum) + " average is:\n" +  grades[depNum+1]);
	    }
	//Checks classes' existence and then returns the grade one line down
	String[] classes = temp.get(2);
	for(int i = 1; i < classes.length;i++)
	    if (className.toLowerCase().equals(classes[i]))
		return className + " average is:\n" + grades[i];
	return "Invalid class name/ Class does not exist";
    }

    //To print all student info 
    public void studentInfo(String lfname){
	Student temp = new Student(lfname);
	System.out.println( temp.toString());
    }
	
    //====================Class Scores================//

    //returns gradebook for a specific class in the following format
    // StudentName | CourseAverage | Participation | Homework | Tests | Projects
    //NOTE: SECTION NUMBER INPUT IS A STRING ON PURPOSE
    //don't use parseInt when taking the command line input for sectionNumber
    //ya feel

    /*
      1:depName, Coursename
      2:Teacher, Section, # of students
      3: Studentname, TestAv, Test1,Test2, etc.., Participation, ProjectAV, Project 1, project 2 etc
      4: Studentname, actualinfo....
    */
    protected void classBook(String courseName, String sectionNum){
	String retStr = "| StudentName | CourseAverage | Tests | Projects | Participation | Homework | \n\n";


	//Isolates where that section begins
	List<String[]> temp = ReadCSV.read(courseName + ".txt");
	int lineNum = 2;
	for (int i = 2; i < temp.size(); i += temp.get(i)[2]+1){	    
	    if (temp.get(i)[1].equals(sectionNum)){
		lineNum = i;
		break;
	    }	    
	}

	String error ="Section Number Invalid";
	if (!(temp.get(lineNum)[1].equals(sectionNum))){
	    System.out.println( error);
	    return;
	}
	int endNum = temp.get(lineNum)[2] + lineNum;//This is the end line of that section and is inclusive

	//BIGASS loop that goes thru each student
	for (int i = lineNum+1; i <= endNum ; i+=2 ){
	    //Gets name
	    String[] student = temp.get(i);
	    String[] studentGrade= temp.get(i+1);
	    retStr += "| " + student[0] + " | ";

	    //Gets Course Average
	    List<String[]> temp1 = ReadCSV.read(_lfname + "info.txt");
	    String[] grades = temp1.get(3);
	    String[] classes = temp1.get(2);
	    for(int i = 1; i < classes.length;i++)
		if (className.toLowerCase().equals(classes[i]))
		    retStr +=  grades[i] + " | ";

	    //Gets Each section 
	    
	    for ( int j = 1; j < student.length; j++){
		if (student[j].toLowerCase().equals("testav"))
		    retStr+= studentGrade[j] + " | ";
		else if (student[j].toLowerCase().equals("participation"))
		    retStr+= studentGrade[j] + " | ";
		else if (student[j].toLowerCase().equals("homework"))
		    retStr+= studentGrade[j] + " | ";
		else if (student[j].toLowerCase().equals("projectav")){
		    retStr+= studentGrade[j] + " | \n";
		    break;
		}
	    }
	}
	System.out.println( retStr );

    }


    // Break for insiration
    //ROAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR
    //WHAT TEAM
    //WILDCATS
    //WHAT TEAM
    //WILDCATS

    //returns Gradebook but with specific assignments ie:
    //|Student Name | Test Average | Test 1 | Test 2|
    //Which part can either be - Test , Participation, Homework,  Project
    //EACH STUDENT MUST HAVE SAME TESTS AND PROJECTS

    /*
      1:depName, Coursename
      2:Teacher, Section, # of students
      3: Studentname, TestAv, Test1,Test2, etc.., Participation, ProjectAV, Project 1, project 2 etc
      4: Studentname, actualinfo....
    */
    
    protected void breakDown(String courseName, string SectionNumber, String whichPart){
	String retStr = "| StudentName | "+ which Part + " | ";


	//Isolates where that section begins
	List<String[]> temp = ReadCSV.read(courseName + ".txt");
	int lineNum = 2;
	for (int i = 2; i < temp.size(); i += temp.get(i)[2]+1){	    
	    if (temp.get(i)[1].equals(sectionNum)){
		lineNum = i;
		break;
	    }	    
	}

	String error ="Section Number Invalid";
	if (!(temp.get(lineNum)[1].equals(sectionNum))){
	    System.out.println( error);
	    return;
	}
	int endNum = temp.get(lineNum)[2] + lineNum;//This is the end line of that section and is inclusive

	// Loop thru that specific section to get the labeling at the top
	//Does this by finding the start and end column of the requested info
	int startCol = 1;
	for(; !(temp.get(lineNum+1)[startCol].equals(whichPart)); startCol++){
	}
	int endCol = startCol + 1; // END COL IS INCLUSIVE
	int sectNum = 0;
	String[] sects = {"testav","participation","projectav","homework"};
	if (whichPart.toLowerCase().equals("participation")){
	    endCol = startCol;
	    sectNum = 1;
	}
	//Determines which section it is
	else if (whichPart.toLowerCase().equals("testav"))
	    sectNum = 0;
	else if (whichPart.toLowerCase().equals("projectav"))
	    sectNum = 2;
	else if (whichPart.toLowerCase().equals("homework")){
	    sectNum = 3;
	    endCol = temp.get(lineNum+1).length -1;
	}
	    
	//Loop for endCol	
        if (!(sectNum == 1) && !(sectNum == 3))
	    for(;  !(temp.get(lineNum+1)[endCol].equals(sect[sectNum+1))); endCol ++){
		retStr += temp.get(lineNum+1)[endCol] + " | ";
	    }
	retStr += "\n";
	
	//BIGASS loop that goes thru each student
	for (int i = lineNum+1; i <= endNum ; i+=2 ){
	    //Gets name
	    String[] student = temp.get(i);
	    String[] studentGrade= temp.get(i+1);
	    retStr += "| " + student[0] + " | ";

	    for
	 

	    
	   
	}
	System.out.println( retStr );


    }

    //CLASS STATS
    // Returns stats for classes - like average median low high




    //Class stats for specific Tests/Projects/Homework

    


    // ==================== Change Grades Methods =========================//


    // Make new Test/ Project / Particiption / Homework Score section in gradebook
    // Sets all to 0 until you change - will not update transcript until changed from 0
    


    // Change individual scores




    //============================== UPDATE METHODS ** MAJOR KEY ALERT ================================//

    public static void transUpdate();


    public static void courseUpdate();



    //IE: TEst score
    public static void indiScoreUpdate();

	

    //========= Get & Set methods============//


    
    /*
      public static int checkStudentGrade(String name){
      }
      public static Schedule checkSchedule(User name){ // overloaded from user
      }
     */

}
