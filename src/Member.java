// Abstract parent class of Instructor and Students.
public abstract class Member {
	
	// Common variables to be passed down to subclasses.
	String nameF;
    String nameL;
    String email;
    String[] courses = new String[2];
    
    // getInfo method to display that nothing seems to be here.
    void getInfo() {
    	System.out.println("There doesn't seem to be anything here.");
    }
}
