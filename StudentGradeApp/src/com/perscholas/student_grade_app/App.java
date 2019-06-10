package com.perscholas.student_grade_app;

import java.io.BufferedWriter;
import java.io.File;
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
				menu.requestStudentName();
				String studentName = s.nextLine();
				
				Path path = Paths.get(studentName + ".txt");
				String filePath = studentName + ".txt";
				
				
				if(Files.exists(path)) {
					try {
						BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath, true));
						//1. Get Java Grade
						menu.requestGrade(studentName, "Java");
						String javaGrade = s.nextLine();
						fileWriter.newLine();
						fileWriter.write(javaGrade);
						
						//2. Get JSP Grade
						menu.requestGrade(studentName, "JSP");
						String jspGrade = s.nextLine();
						fileWriter.newLine();
						fileWriter.write(jspGrade);
						
						//3. Get SQL Grade
						menu.requestGrade(studentName, "SQL");
						String sqlGrade = s.nextLine();
						fileWriter.newLine();
						fileWriter.write(sqlGrade);	
						
						//4. Get JUNIT Grade
						menu.requestGrade(studentName, "JUNIT");
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
			} else if(input.contentEquals("3")) {
				//1. we have to get all the names of the files in our directory - use a loop
				File fileDir = new File("/Users/desmond/git/studentgradeapp/StudentGradeApp/src");
				File[] fileList = fileDir.listFiles();
				for(File name : fileList) {
					if(name.isDirectory()) {
						System.out.println("Our dir is: " + name.getName());
					} else {
						System.out.println("File is: " + name.getName());
					}
				}
				
				//2. Then we open each one and get the 4 numbers; use their common positions 
				//3. We can probably add it to a SUM variable while we loop thru all 4 numbs
				//4. At the end of the loop, we can divide by 4 and we add it to an array list which will have our avgs for every student
				//5. Finally, we loop thru our DOUBLE array list and add it to another SUM variable
				//6. Then we divide it by the total length of array list
			} else if(input.contentEquals("4")) {
				System.out.println("Inside of 3rd else if");
			}
		}
		
		
		// We keep asking the user for A VALID INPUT iF THE INPUT is not 1,2,3,4
		
		
	}

}
