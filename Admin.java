import java.io.*; //FileNotFoundException (Exception)
import java.util.*; //imports Listt/ArrayList/Scanner
import java.math.BigDecimal;


public class Admin extends User{

    protected int _TID;
    protected int _Tfourdigit;
    public String[] factors = {"testav","participation","projectav","homework"};
    public String[] _coursesTeaching = new String[10];

    //============Constructor=============//
    public Admin(String name){
	super(name);
	List<String[]> temp = ReadCSV.read(name + "TeacherInfo.txt");
	String[] info = temp.get(1);
	_TID = Integer.parseInt(info[2]);
	_Tfourdigit =Integer.parseInt( info[3]);
        for (int i = 5; i <  Integer.parseInt(info[4]) ; i++)
	    _coursesTeaching[i-4] = info[i];
    }

    //========== Specific Student Check Infos============//

    //Check Transcript average of any student
    public String checkGrades(String name){
	try{
	List<String[]> temp = ReadCSV.read(name + "Info.txt");
	String[] grades = temp.get(3);
	String retStr = "Transcript Average: \n";
	retStr += grades[0];
	return retStr;
	}
	catch(Exception e){
	   	    return "Student name invalid. Log out and try again.";
	}
    }


    //Takes a class' name as an input, returns average for that class and grade breakdowns
    public String checkGrades(String name, String className){
	//This is to check if the String is actually a department, in which case the student's average for that department will be returned.
	List<String[]> temp = ReadCSV.read(name + "Info.txt");
	String[] grades = temp.get(3);
	for (int i = 0; i<9; i++)
	    if (className.toLowerCase().equals(super.DEPARTMENTS[i])){
		return (super.DEPARTMENTS[i] + " average is:\n" +  grades[i+1]);
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
    public void classBook(String courseName, String sectionNum){
	String retStr = "| StudentName | CourseAverage | Tests | Projects | Participation | Homework | \n\n";


	//Isolates where that section begins
	List<String[]> temp = ReadCSV.read(courseName + ".txt");
	int lineNum = 2;
	for (int i = 2; i < temp.size(); i += Integer.parseInt( temp.get(i)[2])+1){	    
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
	int endNum = Integer.parseInt(temp.get(lineNum)[2]) + lineNum;//This is the end line of that section and is inclusive

	//BIGASS loop that goes thru each student
	for (int i = lineNum+1; i <= endNum ; i+=2 ){
	    //Gets name
	    String[] student = temp.get(i);
	    String[] studentGrade= temp.get(i+1);
	    retStr += "| " + student[0] + " | ";

	    //Gets Course Average
	    List<String[]> temp1 = ReadCSV.read(_lfname + "Info.txt");
	    String[] grades = temp1.get(3);
	    String[] classes = temp1.get(2);
	    for(int j = 1; i < classes.length;j++)
		if (courseName.toLowerCase().equals(classes[j]))
		    retStr +=  grades[j] + " | ";

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
    
    public void breakDown(String courseName, String sectionNum, String whichPart){
	String retStr = "| StudentName | "+ whichPart + " | ";


	//Isolates where that section begins
	List<String[]> temp = ReadCSV.read(courseName + ".txt");
	int lineNum = 2;
	for (int i = 2; i < temp.size(); i += Integer.parseInt(temp.get(i)[2])+1){	    
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
	int endNum =Integer.parseInt( temp.get(lineNum)[2]) + lineNum;//This is the end line of that section and is inclusive

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
	    for(;  !(temp.get(lineNum+1)[endCol].equals(sects[sectNum+1])); endCol ++){
		retStr += temp.get(lineNum+1)[endCol] + " | ";
	    }
	retStr += "\n";
	
	//BIGASS loop that goes thru each student
	for (int i = lineNum+1; i <= endNum ; i+=2 ){
	    //Gets name
	    String[] student = temp.get(i);
	    String[] studentGrade= temp.get(i+1);
	    retStr += "| " + student[0] + " | ";

	    for (int quack = startCol; quack <= endCol; quack++){
		retStr += student[startCol] + " | ";
	    }
	    retStr += "\n";
	    
	}
	System.out.println( retStr );


    }

    //CLASS STATS
    // Returns stats for classes - like average median low high
    // sectName is either testav,participation,projectav,homework, OR specific tests or projects or homeworks
    //sectName is basically what u are tryna find the average for

    /*
      1:depName, Coursename
      2:Teacher, Section, # of students
      3: Studentname, TestAv, Test1,Test2, etc.., Participation, ProjectAV, Project 1, project 2 etc
      4: Studentname, actualinfo....
    */
    public void classStats(String courseName, String sectionNum, String sectName){
	

	


	//Isolates where that section begins
	List<String[]> temp = ReadCSV.read(courseName + ".txt");
	int lineNum = 2;
	for (int i = 2; i < temp.size(); i += Integer.parseInt(temp.get(i)[2])+1){	    
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


	// Loop thru that specific section to get the labeling at the top
	//Does this by finding the start and end column of the requested info
	int startCol = 1;
	for(; !(temp.get(lineNum+1)[startCol].equals(sectName)); startCol++){
	}

	int[] grades = new int[Integer.parseInt(temp.get(lineNum)[2])];

	for (int i = lineNum +2 ; i <( grades.length+lineNum +1) ; i+=2)
	    grades[i-(lineNum+2)] = Integer.parseInt(temp.get(i)[startCol]);
	
	int[] stats = stats(grades);
	
	String retStr = "The class average for " + sectName + " is: " + stats[0];
	retStr += "\nThe median was: " + stats[1];
	retStr += "\nThe high was " + stats[2] + " and the low was: "+ stats[3];
	
	
    }


    //Takes an int[] full of test scores or some type of scores
    //returns int[] with {average, median, high, low}
    
    public int[] stats(int[] grades){
	int average = 0;
	for (int i = 0; i < grades.length;i++)
	    average += grades[i];
	average /= grades.length;

	int median = 0;
	ArrayList<Integer> gradez = new ArrayList<Integer>();
	for (int i = 0 ; i < grades.length; i++)
	    gradez.add(grades[i]);
	
	bubbleSort(gradez);
	if (gradez.size()%2 == 0)
	    median = gradez.get(gradez.size());
	else
	    median = gradez.get(gradez.size()/2+1);
	int high = gradez.get(gradez.size()-1);
	int low = gradez.get(0);

	int[] statistics= {average, median, high, low};
	return statistics;
    }


    // ===================================HELPER FUNCTIONS================================= //
    //BULBASAUR, I CHOOSE YOU!
 

    public static void swap(ArrayList<Integer> data, int a, int b){
	Integer temp = data.get(a);
	data.set(a, data.get(b));
	data.set(b, temp);
    }
    // VOID version of bubbleSort
    // Rearranges elements of input ArrayList
    // postcondition: data's elements sorted in ascending order
    public static void bubbleSortV( ArrayList<Integer> data ) {
	for(int j = 0; j< data.size() -1; j++)
	    for(int i = data.size()-1; i>0; i--)
		if( data.get(i) < (data.get(i-1)))
		    swap(data, i, i-1);	
    }//end bubbleSortV -- O(n^5)


    // ArrayList-returning bubbleSort
    // postcondition: order of input ArrayList's elements unchanged
    //                Returns sorted copy of input ArrayList.
    public static ArrayList<Integer> bubbleSort( ArrayList<Integer> input ){
	ArrayList<Integer> temp = input;
	bubbleSortV(temp);
	return temp;
    }//end bubbleSort -- O(?)
    


    // ==================== Change Grades Methods =========================//


    // Make new Test/ Project / Particiption / Homework Score section in gradebook
    // Sets all to 0 until you change - will not update transcript until changed from 0

    /*
      1:depName, Coursename
      2:Teacher, Section, # of students
      3: Studentname, TestAv, Test1,Test2, etc.., Participation, ProjectAV, Project 1, project 2 etc
      4: Studentname, actualinfo....
    */
    //    public String[] factors = {"testav","participation","projectav","homework"};
      public void newAssignment(String courseName, String sectionNum, String assignmentType, String assignmentName){
	List<String[]> temp = ReadCSV.read(courseName + ".txt");
	int lineNum = 2;
	for (int i = 2; i < temp.size(); i += Integer.parseInt(temp.get(i)[2])+1){	    
	    if (temp.get(i)[1].equals(sectionNum)){
		lineNum = i;
		break;
	    }	    
	}
	int endLine = lineNum + Integer.parseInt(temp.get(lineNum)[2]) + 1;
	String compare = "";
	int startIndex = 0;
	if (assignmentType.toLowerCase().equals("testav"))
	    compare = "participation";
	else if (assignmentType.toLowerCase().equals("projectAv"))
	    compare = "homework";
	else if (assignmentType.toLowerCase().equals("homework"))
	    startIndex = temp.get(lineNum).length;
	else if(!assignmentType.toLowerCase().equals("participation")){
	    System.out.println(" Error: assignment type not valid");
	    return;
	}

	if (startIndex == 0)
	    for ( int i = 1; !(temp.get(lineNum)[i].equals(compare)); i++){
		startIndex = i;
	    }
	startIndex -= 1;

	//Goes thru every line in the damn file and moves everything over one to make space

	//Shortcut if its a hw just add onto the end
	if (startIndex == temp.get(lineNum).length)
	    for( int i = lineNum; i <=   Integer.parseInt(temp.get(i)[2]) +1;i++ ){
		if (i == lineNum){
		    String[] alpha = new String[temp.get(i).length+1];
		    for (int j = 0; j < temp.get(i).length; j++)
			alpha[j] = temp.get(i)[j];
		    alpha[temp.get(i).length] = assignmentName;
		    temp.set(i, alpha);
		    
		}
		else {
		    String[] beta = new String[temp.get(i).length+1];
		    for (int j = 0; j < temp.get(i).length; j++)
			beta[j] = temp.get(i)[j];
		    beta[temp.get(i).length] = "0";
		    temp.set(i, beta);
		}
	    }
	//normal way to add in the middle    
	else {
	    
	    for( int i = lineNum; i <=   Integer.parseInt(temp.get(i)[2]) +1;i++ ){
		if (i%lineNum != 0){
		    String[] theta = new String[temp.get(i).length+1];
		    //This for loop goes up to insertation
		    for (int j = 0; j < temp.get(i).length; j++){
			if (j == startIndex){
			    theta[j] = assignmentName;
			    break;
			}
			else
			    theta[j] = temp.get(i)[j];
		    }
		    //This goes after insertation
		    for(int j = startIndex + 1; j < temp.get(i).length; j++)
			theta[j] = temp.get(i)[j-1];
		    temp.set(i,theta);


		}
		else {
		    String[] delta = new String[temp.get(i).length+1];
		    //up to insertation
		      for (int j = 0; j < temp.get(i).length; j++){
			if (j == startIndex){
			    delta[j] ="0";
			    break;
			}
			else
			    delta[j] = temp.get(i)[j];
		    }
		    //This goes after insertation
		    for(int j = startIndex + 1; j < temp.get(i).length; j++)
			delta[j] = temp.get(i)[j-1];
		    temp.set(i,delta);
		}
	    }
	}
	FileMaker.writeFile(courseName+".txt", temp);

      }
	
		
		
	    
		



    
    // Change individual scores
    /*
      1:depName, Coursename
      2:Teacher, Section, # of students
      3: Studentname, TestAv, Test1,Test2, etc.., Participation, ProjectAV, Project 1, project 2 etc
      4: Studentname, actualinfo....
    */
    public void changeScore(String courseName, String lfname, String assignment, String newScore){
	List<String[]> temp = ReadCSV.read(courseName + ".txt");
	//just gonna loop thru every damn line until find right name
	int lineNum = 0;
	for (int i = 2; i < temp.size(); i++)
	    if (temp.get(i)[0].equals(lfname))
		lineNum = i+1;
	if (lineNum  == 0){
	    System.out.println("Name invalid");
	    return;
	}
	int colNum = 0;
	//Gotta find the right column now
	for (int i = 1; i < temp.get(lineNum - 1).length; i++)
	    if (temp.get(lineNum - 1)[i].equals(assignment))
		colNum = i;
	
	if (colNum  == 0){
	    System.out.println("Name invalid");
	    return;
	}	

	//this part is light
	//change the actual score
	String[] line = temp.get(lineNum);
	line[colNum] = newScore;
	ArrayList<String> swag = new ArrayList<String>();
	for (int i = 0; i < line.length; i++)
	    swag.set(i, line[i]);
	FileMaker.changeLine(courseName+".txt", lineNum, swag);//changes line in the text file
	
	indiScoreUpdate(lfname, courseName);

    }




    //============================== UPDATE METHODS ** MAJOR KEY ALERT ================================//

    public static void transUpdate(String lfname){
	List<String[]> temp = ReadCSV.read(lfname + "Info.txt");
	ArrayList<String> temp2 = new ArrayList<String>(); // new arraylist to be coded

	int mathAV = 0;
	int mathClassNum = 0;
	int biologyAV = 0;
	int biologyClassNum = 0;
	int englishAV = 0;
	int englishClassNum = 0;
	int historyAV = 0;
	int historyClassNum = 0;
	int specialAV = 0;
	int specialClassNum = 0;
	int physicsAV = 0;
	int physicsClassNum = 0;
	int chemistryAV = 0;
	int chemistryClassNum = 0;
	int compSciAV = 0;
	int compSciClassNum = 0;
	    
	    

	
	for (int i = 9 ; i < temp.get(2).length ; i++){
	    List<String[]> temp1 = ReadCSV.read(temp.get(2)[i] + ".txt");
	    String depName = temp1.get(0)[0];
	    if (depName.equals("math")){
		mathAV += Integer.parseInt(temp.get(3)[i]);
		mathClassNum += 1;
	    }
	    else if (depName.equals("biology")){
	        biologyAV += Integer.parseInt(temp.get(3)[i]);
		biologyClassNum += 1;
	    }
	    else if (depName.equals("english")){
	        englishAV += Integer.parseInt(temp.get(3)[i]);
		englishClassNum += 1;
	    }
	    else if (depName.equals("history")){
	        historyAV += Integer.parseInt(temp.get(3)[i]);
	        historyClassNum += 1;
	    }
	    else if (depName.equals("special")){
	        specialAV += Integer.parseInt(temp.get(3)[i]);
	        specialClassNum += 1;
	    }
	    else if (depName.equals("physics")){
		physicsAV += Integer.parseInt(temp.get(3)[i]);
		physicsClassNum += 1;
	    }
	    else if (depName.equals("chemistry")){
	        chemistryAV += Integer.parseInt(temp.get(3)[i]);
		chemistryClassNum += 1;
	    }
	    else if (depName.equals("compsci")){
	        compSciAV += Integer.parseInt(temp.get(3)[i]);
	        compSciClassNum += 1;
	    }
	    
	}
	 mathAV = mathAV / mathClassNum;

	 biologyAV = biologyAV / biologyClassNum;

	 englishAV = englishAV / englishClassNum;

	 historyAV = historyAV / historyClassNum;

	 specialAV = specialAV / specialClassNum;

	 physicsAV = physicsAV /physicsClassNum;

	 chemistryAV = chemistryAV / chemistryClassNum;

	 compSciAV = compSciAV / compSciClassNum;

	 int TranscriptAv = mathAV + biologyAV + englishAV + historyAV + specialAV + physicsAV + chemistryAV + compSciAV;
	TranscriptAv /= 8;

	temp2.add( "" + TranscriptAv);
	temp2.add( "" + mathAV);
	temp2.add( "" + biologyAV);
	temp2.add( "" + englishAV);
	temp2.add( "" + historyAV);
	temp2.add( "" + specialAV);
	temp2.add( "" + physicsAV);
	temp2.add( "" + chemistryAV);
	temp2.add( "" + compSciAV);
	for (int i = 9 ; i < temp.get(2).length ; i++)
	    temp2.add( "" + Integer.parseInt(temp.get(3)[i]));
	FileMaker.changeLine(lfname+"Info.txt", 3, temp2);//changes line in the text file	
	 
	



	
    }
	


    public static void courseUpdate(String lfname, String courseName){
	List<String[]> temp = ReadCSV.read(courseName + ".txt");
	String department = temp.get(0)[0];
	List<String[]> dep = ReadCSV.read(department + ".txt");
	//Stores department grading scale
	String[] scale = dep.get(3);
	
	//just gonna loop thru every damn line until find right name
	int lineNum = 0;
	for (int i = 2; i < temp.size(); i++)
	    if (temp.get(i)[0].equals(lfname))
		lineNum = i+1;
	if (lineNum  == 0){
	    System.out.println("Name invalid");
	    return;
	}
	String[] grades = temp.get(lineNum);
	int testAv = 0;
	int partAv = 0;
	int projAv = 0;
	int hwAv = 0;
	//Gets averages for that class
	for (int i =1 ; i < temp.get(lineNum-1).length; i++){
	    if (temp.get(lineNum-1)[i].equals("testav"))
		testAv = Integer.parseInt(temp.get(lineNum)[i]);
	    else if (temp.get(lineNum-1)[i].equals("participation"))
		partAv = Integer.parseInt(temp.get(lineNum)[i]);
	    else if (temp.get(lineNum-1)[i].equals("projectav"))
		projAv = Integer.parseInt(temp.get(lineNum)[i]);
	    else if (temp.get(lineNum-1)[i].equals("homework")){
		hwAv = Integer.parseInt(temp.get(lineNum)[i]);
		break;
	    }
	}

	//Calculates class avg using the scale
	BigDecimal average = new BigDecimal((testAv * (Double.parseDouble(scale[0])/100)) + (partAv * (Double.parseDouble(scale[1])/100)) + (projAv * (Double.parseDouble(scale[2])/100)) + (hwAv * (Double.parseDouble(scale[3])/100)));
	int av = average.intValue();

	List<String[]> temp1 = ReadCSV.read(lfname + "Info.txt");
	ArrayList<String> temp2 = new ArrayList<String>();
	for(int i = 0 ; i < temp1.get(3).length ; i++)
	    temp2.add( "" + temp1.get(3)[i]);
	int columnNumber = 0;
	for(int i = 0; i < temp1.get(2).length; i++)
	    if ( temp1.get(2)[i].equals(courseName))
		columnNumber = i;
	temp2.add(columnNumber,""+ av);
	FileMaker.changeLine(lfname+"Info.txt", 3, temp2);//changes line in the text file
	transUpdate(lfname);
    }

    
    /*
      1:depName, Coursename
      2:Teacher, Section, # of students
      3: Studentname, TestAv, Test1,Test2, etc.., Participation, ProjectAV, Project 1, project 2 etc
      4: Studentname, actualinfo....
    */
    //IE: TEst score
    public static void indiScoreUpdate(String lfname, String className){
	List<String[]> temp = ReadCSV.read(className + ".txt");

	//just gonna loop thru every damn line until find right name
	int lineNum = 0;
	for (int i = 2; i < temp.size(); i++)
	    if (temp.get(i)[0].equals(lfname))
		lineNum = i+1;
	if (lineNum  == 0){
	    System.out.println("Name invalid");
	    return;
	}
	String[] grades = temp.get(lineNum);

	//average setter for tests
	int averageCalc = 0;
	int average = 0;
	    
	for(int i = 2; !(temp.get(lineNum-1).equals("participation"));i++){
	    average += Integer.parseInt( temp.get(lineNum)[i]);
	    averageCalc += 1;
	}
	grades[1] = average / averageCalc + "";

	//average setter for projects
	averageCalc = 0; average = 0;
	int colNum = 3;
	for (; !(temp.get(lineNum-1).equals("projectav"));colNum ++){
	}
	colNum += 1;
	int endCol = colNum;
	for(int i = colNum; !temp.get(lineNum-1).equals("homework"); i++){
	    average += Integer.parseInt( temp.get(lineNum)[i]);
	    averageCalc += 1;
	    endCol += 1;
	}
	endCol += 1;
	grades[colNum] ="" + ( average/ averageCalc);
	
	//average setter for homeworks
	averageCalc = 0; average = 0;
	colNum = endCol;
	for(int i = colNum; i < temp.get(lineNum).length; i++){
	    average += Integer.parseInt( temp.get(lineNum)[i]);
	    averageCalc += 1;
	}
	grades[endCol] ="" +( average / averageCalc);
	ArrayList<String> boii = new ArrayList<String>();
	for (int i = 0; i < grades.length; i++)
	    boii.set(i, grades[i]);
	FileMaker.changeLine(className+".txt", lineNum, boii);//changes line in the text file

	
	courseUpdate(lfname, className);
    }


}
