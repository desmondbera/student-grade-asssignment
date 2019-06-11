package com.perscholas.student_grade_app;

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
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
//			System.out.println("You typed: " + input);
			
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
				
				List<Double> StudentsAvgArr = new ArrayList<>();
				Double classAvg = 0.00;
				//1. we have to get all the names of the files in our directory - use a loop
				File dirPath = new File("/Users/desmond/git/studentgradeapp/StudentGradeApp");
				File[] files = dirPath.listFiles();
				File fileReader;
				Scanner fileScan;
				
				for(File name : files) {
					if(!name.isDirectory()) {
						String nameToStr = name.toString();
						if(nameToStr.endsWith(".txt")) {
//							System.out.println("Our dir is: " + name.getName());
							
							try {
								int total = 0;
								double studentAvg = 0.00;
								fileReader = new File(name.getName());
								fileScan = new Scanner(fileReader);
								// Skips the first 2 lines we do not need
								for(int x = 0; x < 2; x++) {
									fileScan.nextLine();
								}
								//Loops from the first grade until the end
								while(fileScan.hasNextLine()) {
									total += fileScan.nextInt();
								}
								studentAvg = (total / 4);
								StudentsAvgArr.add(studentAvg);
							} catch (IOException e) {
								e.printStackTrace();
							}
						};
					} 
				}
				
				Double classTotal = 0.00;
				for(Double avg : StudentsAvgArr) {
					classTotal += avg;
				}
				
				classAvg = (classTotal / StudentsAvgArr.size());
				System.out.printf("Our class avg is: %.2f\n", classAvg);
				//2. Then we open each one and get the 4 numbers; use their common positions 
				//3. We can probably add it to a SUM variable while we loop thru all 4 numbs
				//4. At the end of the loop, we can divide by 4 and we add it to an array list which will have our avgs for every student
				//5. Finally, we loop thru our DOUBLE array list and add it to another SUM variable
				//6. Then we divide it by the total length of array list
			} else if(input.contentEquals("4")) {
				//TODO: refactor a ton of this section - DRY!
				List<Double> scoresForOneSubject = new ArrayList<>();
				
				//1. Get a subject from the user
				System.out.println("Please pick 1 subject (Java, JSP, SQL, JUNIT) to get the MIN and MAX for: ");
				String subject = s.nextLine();
				
				if(subject.equalsIgnoreCase("java")) {
					
					File dirPath = new File("/Users/desmond/git/studentgradeapp/StudentGradeApp");
					File[] files = dirPath.listFiles();
					File fileReader;
					Scanner fileScan;
					
					for(File name : files) {
						if(!name.isDirectory()) {
							String nameToStr = name.toString();
							if(nameToStr.endsWith(".txt")) {
								try {
									fileReader = new File(name.getName());
									fileScan = new Scanner(fileReader);
									
									//skip the first 2 line items
									for(int x = 0; x < 2; x++) {
										fileScan.nextLine();
									}
									
									//Since we are getting MIN and MAX of JAVA, only loop once
									for(int x = 0; x < 1; x++) {
										scoresForOneSubject.add(fileScan.nextDouble());
									}
									
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
					Collections.sort(scoresForOneSubject);
					System.out.println("MIN score for JAVA is: " + scoresForOneSubject.get(0));
					System.out.println("MAX score for JAVA is: " + scoresForOneSubject.get(scoresForOneSubject.size() - 1));
				} else if(subject.equalsIgnoreCase("jsp")) {
					File dirPath = new File("/Users/desmond/git/studentgradeapp/StudentGradeApp");
					File[] files = dirPath.listFiles();
					File fileReader;
					Scanner fileScan;
					for(File name : files) {
						if(!name.isDirectory()) {
							String nameToStr = name.toString();
							if(nameToStr.endsWith(".txt")) {
								try {
									fileReader = new File(name.getName());
									fileScan = new Scanner(fileReader);
									
									//skip the first 3 line items, including JAVA
									for(int x = 0; x < 3; x++) {
										fileScan.nextLine();
									}
									
									//since we are getting MIN and MAX of JSP, only loop once
									for(int x = 0; x < 1; x++) {
										scoresForOneSubject.add(fileScan.nextDouble());
									}
									
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
					Collections.sort(scoresForOneSubject);
					System.out.println("MIN score for JSP is: " + scoresForOneSubject.get(0));
					System.out.println("MAX score for JSP is: " + scoresForOneSubject.get(scoresForOneSubject.size() -1));
				} else if(subject.equalsIgnoreCase("sql")) {
					File dirPath = new File("/Users/desmond/git/studentgradeapp/StudentGradeApp");
					File[] files = dirPath.listFiles();
					File fileReader;
					Scanner fileScan;
					
					for(File name : files) {
						if(!name.isDirectory()) {
							String nameToStr = name.toString();
							if(nameToStr.endsWith(".txt")) {
								try {
									fileReader = new File(name.getName());
									fileScan = new Scanner(fileReader);
									
									//skip the first 4 lines, include java and jsp
									for(int x = 0; x < 4; x++) {
										fileScan.nextLine();
									}
									
									//Only loop once to get SQL min and max
									for(int x = 0; x < 1; x++) {
										scoresForOneSubject.add(fileScan.nextDouble());
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
					Collections.sort(scoresForOneSubject);
					System.out.println("MIN score for SQL is: " + scoresForOneSubject.get(0));
					System.out.println("MAX score for SQL is: " + scoresForOneSubject.get(scoresForOneSubject.size() -1));
					
				} else if(subject.equalsIgnoreCase("junit")) {
					File dirPath = new File("/Users/desmond/git/studentgradeapp/StudentGradeApp");
					File[] files = dirPath.listFiles();
					File fileReader;
					Scanner fileScan;
					
					for(File name : files) {
						if(!name.isDirectory()) {
							String nameToStr = name.toString();
							if(nameToStr.endsWith(".txt")) {
								try {
									fileReader = new File(name.getName());
									fileScan = new Scanner(fileReader);
									
									//skip the first 5 lines, include java, jsp, and sql
									for(int x = 0; x < 5; x++) {
										fileScan.nextLine();
									}
									
									//loop once to get the last item
									for(int x = 0; x < 1; x++) {
										scoresForOneSubject.add(fileScan.nextDouble());
									}
									
								} catch(IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
				Collections.sort(scoresForOneSubject);
				System.out.println("MIN score for Junit is: " + scoresForOneSubject.get(0));
				System.out.println("MAX score for Junit is: " + scoresForOneSubject.get(scoresForOneSubject.size() -1));
				//2. Each subject will be associated with a number. For example, if user inputs JAVA, it will be associated with 1. 
				//3. Once we get the subject, we will iterate thru the directory and find all the .txt files
				//4. Once we filter out the non-".txt" we can open them and check their respective order. For example, if JAVA was our user input, we would
				// clear the first 2 items in our file and get the FIRST (1) item and so forth.
				//5. When we get each respective number from our .txt file we will put it an array list that will have all the numbers.
				//6. We can then sort this array
				//7. Print out the first item for the MIN score of said subject
				//8. Print out the last item for the MAX score of said subject
			}
		}
		
		
		// We keep asking the user for A VALID INPUT iF THE INPUT is not 1,2,3,4
		
		
	}

}
