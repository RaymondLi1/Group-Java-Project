// Import ArrayList
import java.util.ArrayList;

// Courses class.
public class Courses {

	// Course variables.
	String courseNumber;
	String courseName;
	String day;
	String startTime;
	String endTime;
	ArrayList<Assignment> assignments = new ArrayList<Assignment>(10);
	
	// Default constructor.
	Courses() {
		
	}
	
	// Main constructor.
	Courses(String courseNumber, String courseName, String day, String startTime, String endTime) {
		this.courseNumber = courseNumber;
		this.courseName = courseName;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	// Method to create new Assignment objects in the assignments ArrayList based on the studentInfo.txt file. 
	void setAssignments(int studentID, double grade1, double grade2, double grade3) {
		assignments.add(new Assignment(studentID, grade1, grade2, grade3));
	}
	
	// getInfo method to print/format the Courses information when called.
	void getInfo() {
		System.out.print("\nCourse Number: " + courseNumber + "\nCourse Name: " + courseName + "\nSchedule: " + day + " " + startTime + " - " + endTime);
	}
}