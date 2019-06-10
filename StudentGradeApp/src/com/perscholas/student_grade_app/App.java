package com.perscholas.student_grade_app;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		System.out.println("================================");
		System.out.println("Welcome to Student Grade Program!");

		boolean isValid = false;
		while(!isValid) {
			OptionMenu menu = new OptionMenu();
			System.out.println("================================");
			menu.displayOptions();
			System.out.println("================================");
			Scanner s = new Scanner(System.in);
			
			String input = s.nextLine();
			System.out.println("You typed: " + input);
			
			if(input.contentEquals("1")) {			
				StudentAccount acct = new StudentAccount();
				
				menu.requestStudentName();
				acct.setStudentName(s.nextLine());
				System.out.println("You entered: " + acct.getStudentName());
				
				menu.requestStudentEmail();
				acct.setStudentEmail(s.nextLine());
				System.out.println("You entered: " + acct.getStudentEmail());
			
				PrintWriter writer;
				Path path = Paths.get(acct.getStudentName() + ".txt");
				
				//If file exists we set our isValid flag to false AND break out
				//It will also display a message that the file name already exists
				while(Files.exists(path)) {
					menu.showSuccessOrNot(false, acct.getStudentName());
					isValid = false;
					break;
				}
				
				//If the file does NOT exist, we will write a a new file!
				if(!Files.exists(path)) {
					try {
						writer = new PrintWriter(acct.getStudentName() + ".txt");
						writer.write(acct.getStudentName());
						writer.write("\n");
						writer.write(acct.getStudentEmail());
						writer.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} finally {
						menu.showSuccessOrNot(true, acct.getStudentName());
						System.out.println("");
					}
				}
					
			} else if(input.contentEquals("2")) {
				System.out.println("Enter a student's name: ");
				String studentName = s.nextLine();
				System.out.println("We are entering grades for " + studentName);
				
				Path path = Paths.get(studentName + ".txt");
				String filePath = studentName + ".txt";
				
				
				if(Files.exists(path)) {
					try {
						BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath, true));
						//1. Get Java Grade
						menu.requestGrade(studentName, "Java");
//						System.out.println("What numerical grade did " + studentName + " get in Java?");
						String javaGrade = s.nextLine();
						fileWriter.newLine();
						fileWriter.write(javaGrade);
						
						//2. Get JSP Grade
						System.out.println("What numerical grade did " + studentName + "get in JSP?");
						String jspGrade = s.nextLine();
						fileWriter.newLine();
						fileWriter.write(jspGrade);
						
						
						//3. Get SQL Grade
						System.out.println("What numerical grade did " + studentName + "get in SQL?");
						String sqlGrade = s.nextLine();
						fileWriter.newLine();
						fileWriter.write(sqlGrade);
						
						
						//4. Get JUNIT Grade
						System.out.println("What numerical grade did " + studentName + "get in JUNIT?");
						String junitGrade = s.nextLine();
						fileWriter.newLine();
						fileWriter.write(junitGrade);
						
						
						fileWriter.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("This student does not have an account. Please create an account first.");
					System.out.println("");
				}
				
				//1. We need to open the file using the student name
				//2. Then we need to ask the user for input for each subject
				//3. Append each score to the file - this will happen 4 times
				
				
			} else if(input.contentEquals("3")) {
				System.out.println("Inside of 2nd else if");
			} else if(input.contentEquals("4")) {
				System.out.println("Inside of 3rd else if");
			}
		}
		
		
		// We keep asking the user for A VALID INPUT iF THE INPUT is not 1,2,3,4
		
		
	}

}
