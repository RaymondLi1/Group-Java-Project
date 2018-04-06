// Instructor subclass.
public class Instructor extends Member {

	// Instructor variables.
	int instructorID;
	
	
	// Default constructor.
	Instructor() {
		
	}
	
	// Main constructor.
	Instructor(int instructorID, String nameF, String nameL, String email, String course1, String course2) {
		this.instructorID = instructorID;
		this.nameF = nameF;
		this.nameL = nameL;
		this.email = email;
		courses[0] = course1;
		courses[1] = course2;
	}
	
	// getInfo method to print/format the Instructor information when called. This also overrides the parents getInfo method.
	void getInfo() {
		System.out.print("\nInstructor Name: " + nameF + " " + nameL + "\nEmail: " + email);
	}
}