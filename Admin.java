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
	for(int i = 1; i < classes.length();i++)
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
    protected String[] classBook(String courseName, String sectionNumber){
	


    }


    //returns Gradebook but with specific assignments ie:
    //|Student Name | Test Average | Test 1 | Test 2|
    //Which part can either be - Test , Participation, Homework,  Project
    //EACH STUDENT MUST HAVE SAME TESTS AND PROJECTS
    
    protected String[] breakDown(String courseName, string SectionNumber, String whichPart){
	


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
