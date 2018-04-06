// Assignment class
public class Assignment {
	
	// Variables to hold students grades.
	int studentID;
	double grade1;
	double grade2;
	double grade3;
	double finalGrade;

	// Default constructor.
	Assignment() {
		
	}
	
	// Main constructor.
	Assignment(int studentID, double grade1, double grade2, double grade3) {
		this.studentID = studentID; 
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.grade3 = grade3;
		finalGrade = (grade1 + grade2 + grade3) / 3;
	}
	
	// Method to print students grades.
	void getGrades() {
		System.out.println("Assignment 1: " + grade1 + "\nAssignment 2: " + grade2 + "\nAssignment 3: " + grade3 + "\nFinal Grade: " + finalGrade);
	}
}
