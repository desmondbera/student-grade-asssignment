package com.perscholas.student_grade_app;

public class OptionMenu {

	public void displayOptions() {
		System.out.println("Please chose an option: ");
		System.out.println("1. Create Student Account");
		System.out.println("2. Enter student grades");
		System.out.println("3. Get average score of the whole class");
		System.out.println("4. Get the min and the max grade for each subject");
	}
	
	public void requestStudentName() {
		System.out.println("Enter the student's name: ");
	}
	
	public void requestStudentEmail() {
		System.out.println("Enter the student's email: ");
	}
	
	public void showSuccessOrNot(boolean success, String name) {
		if(success) {
			System.out.println("Successfully created account for " + name);
		} else {
			System.out.println("Student Account already created for " + name);
		}
	}
	
	public void requestGrade(String studentName, String subject) {
		System.out.println("What numerical grade did " + studentName + " get in " + subject + "?");
	}
	
	
	
}
