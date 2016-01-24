public class Admin extends User{





    //========= Get & Set methods============//
 //Get OSIS & fourdigit &grad year - takes student name as input
    //Overwritten in Student
    protected int getOSIS(String name){

	File use = new File(name+".txt");
	if (!use.exists())//checks that the file exists b4 running
	    return 0;
	
	List<String[]> temp = ReadCSV.read(name + ".txt");
	return Integer.parseInt( temp.get(1)[2]);

    }

    protected int getfourDigit(String name){
		File use = new File(name+".txt");
	if (!use.exists())//Checks that the file exists b4 running
	    return 0;
	
	List<String[]> temp = ReadCSV.read(name + ".txt");
	return Integer.parseInt( temp.get(1)[3]);
    }

    protected int getGrad(String name){
		File use = new File(name+".txt");
	if (!use.exists())//Checks that the file exists b4 running
	    return 0;
	
	List<String[]> temp = ReadCSV.read(name + ".txt");
	return Integer.parseInt( temp.get(1)[4]);
    }



    
    /*
      public static int checkStudentGrade(String name){
      }
      public static Schedule checkSchedule(User name){ // overloaded from user
      }
     */

}
