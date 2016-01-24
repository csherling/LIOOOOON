public class Admin extends User{






    public String studentInfo(String lfname){
	Student temp = new Student(lfname);
	return temp.toString();
    }
	
	    



    //========= Get & Set methods============//


    
    /*
      public static int checkStudentGrade(String name){
      }
      public static Schedule checkSchedule(User name){ // overloaded from user
      }
     */

}
