package com.quizapp.velocity.exam;

import java.util.Scanner;

public class UserView {
	private Scanner scanner;
	int getuserinput() {
		System.out.println("Enter Option to procced");
		scanner = new Scanner(System.in);
		int unum=scanner.nextInt();
		return unum;
	}
 public void showDetailsToUser() {
	 System.out.println("Welcome to Quiz based application");
	 System.out.println("1. Student Registration");
	 System.out.println("2. Student Login");
	 System.out.println("3. Display the list of questions");
	 System.out.println("4. Store Quiz result into database");
	 System.out.println("5. Display Quiz result");
	 System.out.println("6. Display all students score as per ascending order");
	 System.out.println("7. Fetch student score by using id");
	 System.out.println("8. Add question with 4 options into database");
	 int userinput=getuserinput();
	 if(userinput>0 && userinput<9) {
		 DeveloperView cqd=new DeveloperView();
		 cqd.process(userinput);
	 }
	 else {
		 System.out.println("Wrong input try again");
		 System.out.println();
		 System.out.println();
		 showDetailsToUser();
	 }

 }
}
