public class Admin extends User{

    protected int TID;
    protected int Tfourdigit;
    public String[] coursesTeaching = new String[10];
    
    public Admin(String name){
	super(name);
	List<String[]> temp = ReadCSV.read(name + "Teacherinfo.txt");
	String[] info = temp.get(1);
	TID = info[2];
	Tfourdigit = info[3];
        for (int i = 5; i <  Integer.parseInt(info[4]) ; i++)
	    coursesTeaching[i-4] = info[i];
    }





    public void studentInfo(String lfname){
	Student temp = new Student(lfname);
	System.out.println( temp.toString());
    }
	
	    



    //========= Get & Set methods============//


    
    /*
      public static int checkStudentGrade(String name){
      }
      public static Schedule checkSchedule(User name){ // overloaded from user
      }
     */

}
