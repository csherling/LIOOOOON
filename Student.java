public class Student extends User{

    protected int _osis;
    protected int _fourdigit;
    

    //********Constructor***********//
    public Student(String name, int OSIS, int fourDigit){
	super(name);
	_osis = super.getOSIS();
	_fourdigit = super.getfourDigit();
    }

    //============ Get Set Methods ===========//
    
    // OSIS & 4digit are inhereted from User
    


    

    
}
