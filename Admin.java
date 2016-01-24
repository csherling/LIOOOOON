public class Admin extends User{

    public String[] coursesTeaching = new String[10];
    
    public Admin(){
	
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
