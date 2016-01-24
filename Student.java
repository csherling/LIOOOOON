public class Student extends User{

    protected int _osis;
    protected int _fourdigit;
    

    //********Constructor***********//
    public Student(String name, int OSIS, int fourDigit){
	super(name);
	_osis = getOSIS();
	_fourdigit = getfourDigit();
    }

    //============ Get Set Methods ===========//
    
    // OSIS & 4digit & grad year are inhereted from User -- This overwrites them to call using lfname instead of taking an input, since students can only check their own osis
    
    protected int getOSIS(){

	File use = new File(_lfname+".txt");

	if (!use.exists())//Checks that the file exists b4 running
	    return 0;
	
	List<String[]> temp = ReadCSV.read(_lfname + ".txt");
	return Integer.parseInt( temp.get(1)[2]);

    }
    
    protected int getfourDigit(){
	File use = new File(_lfname+".txt");

	if (!use.exists())//Checks that the file exists b4 running
	    return 0;
	
	List<String[]> temp = ReadCSV.read(_lfname + ".txt");
	return Integer.parseInt( temp.get(1)[3]);
    }

    
    protected int getGrad(){
	File use = new File(_lfname+".txt");

	if (!use.exists())//Checks that the file exists b4 running
	    return 0;
	
	List<String[]> temp = ReadCSV.read(_lfname + ".txt");
	return Integer.parseInt( temp.get(1)[4]);
    }

    
}
