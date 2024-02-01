package com.quizapp.velocity.exam;

public class DeveloperView {
   AppBlueprintImpl abi=new AppBlueprintImpl();
	public void process(int choice) {
		switch(choice) {
		case 1:
			abi.studentRegistration();
			break;
		case 2:
			abi.studentLogin();
			break;
		case 3:
			System.out.println("case 3 executig");
			break;
		case 4:
			System.out.println("case 4 executig");
			break;
		case 5:
			System.out.println("case 5 executig");
			break;
		case 6:
			System.out.println("case 6 executig");
			break;
		case 7:
			System.out.println("case 7 executig");
			break;
		case 8:
			abi.sure();
			abi.addQuestionInDatabase();
		    abi.removequestion();
			break;
		default :
			System.out.println("Invalid");
		}
	}
}
