// Students subclass.
public class Students extends Member {

	// Student variables.
	int studentID;
	String password;
	
	
	// Default constructor.
	Students() {
		
	}
	
	// Main constructor.
	Students(int studentID, String nameF, String nameL, String password, String email, String course1, String course2) {
		this.studentID = studentID;
		this.nameF = nameF;
		this.nameL = nameL;
		this.password = password;
		this.email = email;
		courses[0] = course1;
		courses[1] = course2;
	}
	
	// getInfo method to print/format the Students information when called. This also overrides the parents getInfo method.
	void getInfo() {
		System.out.println("\nName: " + nameF + " " + nameL + "\nStudentID: " + studentID + "\nEmail: " + email);
	}
}