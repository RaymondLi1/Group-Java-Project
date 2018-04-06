// Necessary utilities.
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

// Login interface.
interface Login {
    public void loadDB();
    public void validateUsername();
    public void validatePassword();
    public void mainMenu();
}

// Main class that implements the Login interface.
public class Main implements Login {

	// Static arrays and variables to be used throughout the Main class.
	private static ArrayList<Students> student = new ArrayList<Students>(10);
	private static ArrayList<Courses> course = new ArrayList<Courses>();
	private static ArrayList<Instructor> instructor = new ArrayList<Instructor>();
	private static Scanner input2 = new Scanner(System.in);
	private static int username;
	private static int i;
	private static boolean cont = true;
	private static int passwordAttempts = 1;
	
	// Main method.
	public static void main(String[] args) {
		
		// new Main object named main, so that we can use the Login interface.
		Main main = new Main();
		
		// Load the StudentInfo.txt file into the student ArrayList, the Courses.txt file into the course ArrayList, and the InstructorInfo.txt file into the instructor ArrayList.
		main.loadDB();
		
		// Prompt the user to sign in using the validateUsername method, and make sure it exists.
		main.validateUsername();
		
		// Prompt user to enter their password using the validatePassword method, and make sure it corresponds to the entered username.
		main.validatePassword();
		
		// Once successfully signed in, display the main menu text.
		System.out.println("\n\tMAIN MENU" + "\n1. My Information" + "\n2. View Enrolled Courses"
		+ "\n3. Change Courses" + "\n4. Logout" + "\n5. Exit");
		
		// Once displayed, the user is entered into a mainMenu loop until they logout/exit the program.
		while (cont == true) {
		main.mainMenu();
		}
	}
	
	// LoadDB method to load our databases, and create objects based on the files.
	public void loadDB() {
		
		// Load the Courses.txt file
		String data = "Courses.txt";
		File database = new File(data);
		int a = 0;
		// Scanner/variable to be used in all database imports.
		Scanner input = null;
		// Try-catch to catch FileNotFoundException and notify the user/close the program.
		try {
			// Loop to create new Courses objects given the text file and constructor, as well as the replace the _ in courseName/CourseNumber with a space.
			input = new Scanner(database);
			while (input.hasNext()) {
				course.add(new Courses(input.next(), input.next(), input.next(), input.next(), input.next()));
				course.get(a).courseName = course.get(a).courseName.replace("_", " ");
				course.get(a).courseNumber = course.get(a).courseNumber.replace("_", " ");
				a++;
			}
		}
		// If the file does not exist, close.
		catch (FileNotFoundException e) {
			System.out.println("Source file doesn't exist.");
			System.exit(0);
		}
				
		// Load the StudentInfo.txt file
		data = "StudentInfo.txt";
		database = new File(data);
		
		// Try-catch to catch FileNotFoundException and notify the user/close the program.
		try {
			// Loop to create new Students objects given the text file and constructor, as well as to replace the _ in courseName with a space.
			a = 0;
			input = new Scanner(database);
			while (input.hasNext() && (student.size() < 10)) {
				student.add(new Students(input.nextInt(), input.next(), input.next(), input.next(), input.next(), input.next(), input.next()));
				student.get(a).courses[0] = student.get(a).courses[0].replace("_", " ");
				student.get(a).courses[1] = student.get(a).courses[1].replace("_", " ");
				
				// Find the students first course, and create a new assignment object in that course based on the next 3 lines of input and their student ID.
				int j;
				for (j = 0; j < course.size(); j++) {
					if (student.get(a).courses[0].equals(course.get(j).courseNumber)) {
						course.get(j).setAssignments(student.get(a).studentID, input.nextDouble(), input.nextDouble(), input.nextDouble());
						break;
					}
					// If the course equals null, dispose of the next 3 lines.
					else if(student.get(a).courses[0].equals("null")) {
						double h = input.nextDouble();
						h = input.nextDouble();
						h = input.nextDouble();
						break;
					}
				}
				
				// Find the students second course, and create a new assignment object in that course based on the next 3 lines of input and their student ID.
				for (j = 0; j < course.size(); j++) {
					if (student.get(a).courses[1].equals(course.get(j).courseNumber)) {
						course.get(j).setAssignments(student.get(a).studentID, input.nextDouble(), input.nextDouble(), input.nextDouble());
						break;
					}
					// If the course equals null, dispose of the next 3 lines.
					else if(student.get(a).courses[1].equals("null")) {
						double h = input.nextDouble();
						h = input.nextDouble();
						h = input.nextDouble();
						break;
					}
				}
				a++;
			}
		} 
		// If the file does not exist, close.
		catch (FileNotFoundException e) {
			System.out.println("Source file doesn't exist.");
			System.exit(0);
		}
		
		// Load the InstructorInfo.txt file
		data = "InstructorInfo.txt";
		database = new File(data);
			
		// Try-catch to catch FileNotFoundException and notify the user/close the program.
		try {
			// Loop to create new Instructor objects given the text file and constructor, as well as to replace the _ in courseName with a space.
			a = 0;
			input = new Scanner(database);
			while (input.hasNext()) {
				instructor.add(new Instructor(input.nextInt(), input.next(), input.next(), input.next(), input.next(), input.next()));
				instructor.get(a).courses[0] = instructor.get(a).courses[0].replace("_", " ");
				instructor.get(a).courses[1] = instructor.get(a).courses[1].replace("_", " ");
				a++;
			}
		} 
		// If the file does not exist, close.
		catch (FileNotFoundException e) {
			System.out.println("Source file doesn't exist.");
			System.exit(0);
		}
		// Close the scanner once there is no more input to be read.
		finally {
			if (input != null) {
				input.close();
			}
		}
	}
	
	// This method asks the user to enter their username, and validates whether it exists or not, and handles any unexpected inputs.
	public void validateUsername() {
		
		// Prompt user to enter their username.
		System.out.print("Welcome to Moodletron, please enter your username: ");
		
		// Try catch for unexpected input.
		try {
		
		// Store the users input (if an int value) into the username variable.
		username = input2.nextInt();
		}
		
		// Catch the exception, and print out why it there was an error. Clear the scanner, and call the method again to allow them to attempt once more.
		catch(Exception e) {
			System.out.println("Only integer input is accepted, please try again.\n");
			input2.nextLine();
			validateUsername();
		}
		
		// Check to see if the username exists, if not allow them to retry. Else, continue.
		for (i = 0; i < student.size(); i++) {
			if (student.get(i).studentID == username) {
				break;
			}
		}
		if (i == student.size()) {
			System.out.println("This username doesn't seem to exist, please try again.\n");
			validateUsername();
		}
	}
	
	// This method is called once the username is entered/validated. It asks the user for their password, and compares it to the entered usernames correct password.
	public void validatePassword() {
		
		// Prompt the user to enter their password, and store it into a String variable named password. Inform the user that we are "checking authorization".
		System.out.print("Please enter your password: ");
		String password = input2.next();
		System.out.println("\n...Checking Authorization...\n");
			
		// Nested if-else statements to decipher if the user has entered their password incorrect too many times (resulting in program termination), or if they get another attempt, or if the password is correct.
		if (passwordAttempts >= 3) {
			System.out.println("You've tried too many times, program self-destructing.");
			System.exit(0);
		}
		else {
			if (password.equals(student.get(i).password)) {
			System.out.println("Authorization granted!");
			}
			else {
				System.out.println("Hmm, that wasn't quite right. Here, try again.");
				passwordAttempts++;
				validatePassword();
			}
		}
	}
	
	// This method is called once you are fully signed in and the menu options have been printed. It takes the user input in the form of an int, and uses the switch statement that follows to decide what to do next.
	public void mainMenu() {
		
		// Try catch to handle any input mismatch exceptions and allow the user to try again/notify them if there's an exception.
		try {
			// Prompt for a selection and store it in "selection".
			System.out.print("\nPlease select an option: ");
			int selection = input2.nextInt();
			
			// Switch statement to handle options.
			switch(selection) {
			
			// Display the currently signed in (variable i) students general information.
			case 1:	student.get(i).getInfo();
					break;
				
			// Call the courseInfo method to display info on the courses you are currently taking (if any).
			case 2: courseInfo();
					break;
				
			// Call the changeCourse method to allow the user to either drop, swap, or add a course.
			case 3: // If both of the users courses are "null", output that they are empty.
					if (student.get(i).courses[0].equals("null") && student.get(i).courses[1].equals("null")) {
						System.out.println("\n1: Empty" + "\n\n2: Empty");
					}
					// If slot 1 is "null", and slot 2 is not "null", output that slot 1 is empty, and output their second course.
					else if (student.get(i).courses[0].equals("null") && !student.get(i).courses[1].equals("null")) {
						System.out.println("\n1: Empty" + "\n\n2: " + student.get(i).courses[1]);
					}
					// If slot 2 is "null", and slot 1 is not "null", output that slot 2 is empty, and output their first course.
					else if (!student.get(i).courses[0].equals("null") && student.get(i).courses[1].equals("null")) {
						System.out.println("\n1: " + student.get(i).courses[0] + "\n\n2: Empty");
					}
					// Otherwise (if both courses are not null), output both courses.
					else {
						System.out.print("\n1: " + student.get(i).courses[0] + "\n\n2: " + student.get(i).courses[1]);
					}
					// Call the changeCourse method.
					changeCourse();
					break;
				
			// Call the validateUsername and validatePassword methods to sign in as someone else.
			case 4:	System.out.println();
					validateUsername();
					validatePassword();
					// Once successfully re-signed in, display the main menu text again.
					System.out.println("\n\tMAIN MENU" + "\n1. My Information" + "\n2. View Enrolled Courses"
					+ "\n3. Change Courses" + "\n4. Logout" + "\n5. Exit");
					break;
				
			// Thank the user by name for using the program, and terminate.
			case 5: System.out.println("\nThank you for using Moodletron " + student.get(i).nameF + " " + student.get(i).nameL + ", see you next time! \nShutting down.... ");
					cont = false;
					break;
			
			// If the integer was not one of the above integers, allow them to try again.
			default:System.out.println("Incorrect option, please try again.");
				 
			}
		}
		// Inform the user of an error, clear the scanner, and let them try again.
		catch(Exception e) {
			System.out.println("Only integer input is accepted, please try again.");
			input2.next();
			mainMenu();
		}
	}
	
	// This method displays the current users course information.
	public void courseInfo() {
		
		/* For loop with nested for loops and if-else statements to get the students course[0] and compare it with course courseNumber to find a match, if the students course name is "null", print "Course 1: Empty".
	 	If a match is found, print the Courses getInfo method, then find the matching instructor and print their getInfo method, then find the students grades in the Courses Assignment object ArrayList, and print them. */
		int j;
		int k;
		for (j = 0; j < course.size(); j++) {
			if (student.get(i).courses[0].equals(course.get(j).courseNumber)) {
				course.get(j).getInfo();
				// If the first course slot was not "null", continue.
				if (!student.get(i).courses[0].equals("null")) {
					// For loop to get which instructor matches the selected courseNumber and then once found, print the instructor getInfo method.
					for (k = 0; k < instructor.size(); k++) {
						if(instructor.get(k).courses[0].equals(course.get(j).courseNumber) || instructor.get(k).courses[1].equals(course.get(j).courseNumber)) {
							instructor.get(k).getInfo();
							break;
						}
					}
				}
				// For loop to grab the students grades/assignments
				for (k = 0; k < course.get(j).assignments.size(); k++) {
					if (student.get(i).studentID == (course.get(j).assignments.get(k).studentID)) {
						course.get(j).assignments.get(k).getGrades();
						break;
					}
					else {
						System.out.println("");
					}
				}
				break;
			}
			else if(student.get(i).courses[0].equals("null")) {
				System.out.println("\nCourse 1: Empty");
				break;
			}
		}
		
		/* For loop with nested for loops and if-else statements to get the students course[1] and compare it with course courseNumber to find a match, if the students course name is "null", print "Course 2: Empty".
	 	If a match is found, print the Courses getInfo method, then find the matching instructor and print their getInfo method, then find the students grades in the Courses Assignment object ArrayList, and print them. */
		for (j = 0; j < course.size(); j++) {
			if (student.get(i).courses[1].equals(course.get(j).courseNumber)) {
				course.get(j).getInfo();
				// If the second course slot was not "null", continue.
				if (!student.get(i).courses[1].equals("null")) {
					// For loop to get which instructor matches the selected courseNumber and then once found, print the instructor getInfo meethod.
					for (k = 0; k < instructor.size(); k++) {
						if(instructor.get(k).courses[0].equals(course.get(j).courseNumber) || instructor.get(k).courses[1].equals(course.get(j).courseNumber)) {
							instructor.get(k).getInfo();
							System.out.println("");
							break;
						}
					}
				}
				// For loop to grab the students grades/assignments
				for (k = 0; k < course.get(j).assignments.size(); k++) {
					if (student.get(i).studentID == (course.get(j).assignments.get(k).studentID)) {
						course.get(j).assignments.get(k).getGrades();
						break;
					}
				}
				break;
			}
			else if(student.get(i).courses[1].equals("null")) {
				System.out.println("\nCourse 2: Empty");
				break;
			}
		}
	}
	
	// This method allows the signed in user to add, drop, or swap an enrolled course for a different (or same) one out of a list of options.
	public void changeCourse() {
		
		// Ask the user which course they want to replace out of the given 2 options.
		System.out.print("\n\nWhich course do you want to replace?: ");
		
		// Try-catch to catch an input mismatch and allow the user to try again/notify them of the error.
		try {
			
			// Accept user input, and if it's greater than 2, or less than 1, notify the user and allow another attempt.
			int selection = input2.nextInt();
			if (selection > 2 || selection < 1) {
				System.out.print("\nPlease either enter 1, or 2.");
				changeCourse();
			}
			// Else, continue.
			else {
				// Ask the user which of the courses they would like to replace their selected course with, and run a for loop to display all available courses.
				System.out.println("\nWhich of these courses do you want to replace course " + selection + " with?");
				int j = 0;
				for (j = 0; j < course.size(); j++) {
					System.out.print("\nOption: " + (j+1));
					course.get(j).getInfo();
					System.out.println();
				}
				// Once all the courses are displayed, add a "Drop Class" option to the end.
				System.out.println("\nOption: " + (j+1) + "\nDrop Class " + selection + "\n");
		
				// Prompt user to select one of the above options and store their selection in the "selection2" variable.
				System.out.print("Selection: ");
				int selection2 = input2.nextInt();
		
				// If statement to replace their selected course, with the other course they selected.
				if (selection2 <= j && selection2 > 0) {
					student.get(i).courses[selection-1] = course.get(selection2-1).courseNumber;
				}
				// If the last option is selected, replace their course with "null".
				else if (selection2 == j+1) {
					student.get(i).courses[selection-1] = "null";
				}
				// Otherwise, they selected an invalid option and allow them to retry.
				else {
					System.out.println("Invalid option, try again.");
					changeCourse();
				}
			}
		}
		// If there's an exception, give an error message, clear the scanner, and allow another attempt.
		catch(Exception e) {
			System.out.print("\nOnly integer input is accepted, please try again.");
			input2.next();
			changeCourse();
		}
	}
}