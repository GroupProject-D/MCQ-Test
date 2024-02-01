package com.quizapp.velocity.exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AppBlueprintImpl implements ApplicationBlueprint {
	Scanner sc = new Scanner(System.in);
	UserView op1 = new UserView();

	public Connection connectDatabase() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaclass", "root", "root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	@Override
	public void studentRegistration() {
		System.out.println("In registration");
		Connection con = connectDatabase();
		try {
			PreparedStatement ps = con.prepareStatement(
					"insert into student(firstname,lastname,username,password,city,mail_id,phone_number)values(?,?,?,?,?,?,?)");
			PreparedStatement ps2 = con.prepareStatement("insert into admin(username,password)values(?,?)");
			System.out.println("Enter FirstName");
			String fname = sc.next();
			ps.setString(1, fname);
			System.out.println("Enter LastName");
			String lname = sc.next();
			ps.setString(2, lname);
			System.out.println("Enter Username");
			String uname = sc.next();
			ps.setString(3, uname);
			ps2.setString(1, uname);
			System.out.println("Enter password");
			String pass = sc.next();
			ps.setString(4, pass);
			ps2.setString(2, pass);
			System.out.println("Enter City");
			String city = sc.next();
			ps.setString(5, city);
			System.out.println("Enter Mail_ID");
			String email = sc.next();
			ps.setString(6, email);
			System.out.println("Enter Mobile_number");
			String mob_no = sc.next();
			ps.setString(7, mob_no);
			int a2 = ps2.executeUpdate();
			int a = ps.executeUpdate();
			System.out.println("SIGN UP COMPLETE Procced for login" + a + ">>" + a2);
			System.out.println();
			System.out.println();

			getdetails(uname);

			System.out.println();
			System.out.println("Press 1 To get on main Window");
			int choice = sc.nextInt();
			if (choice == 1) {
				op1.showDetailsToUser();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getdetails(String uname) {
		Connection con = connectDatabase();
		try {
			PreparedStatement ps = con
					.prepareStatement("select student_id,username,password from student where username=?");
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Your Student Id is >>");
				System.out.println(rs.getInt("student_id"));
				System.out.println("Your Username is >>");
				System.out.println(rs.getString("username"));
				System.out.println("Your password is >>");
				System.out.println(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void studentLogin() {
		System.out.println("Login Page");
		System.out.println("Enter Username");
		String useruname = sc.next();
		System.out.println("Enter Password");
		String userpass = sc.next();
		String sysuname = null;
		String syspass = null;
		int a=0;
		Connection con = connectDatabase();
		try {
			PreparedStatement ps = con.prepareStatement("select username,password from student where username=?");
			ps.setString(1, useruname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sysuname = rs.getString("username");
				syspass = rs.getString("password");
			}
			if (useruname.equals(sysuname) && userpass.equals(syspass)) {
				System.out.println("Enter 1 For Test !");
				System.out.println("Enter anynumber to go back Home Page");
				 a = sc.nextInt();
				if (a == 1) {
					questionList();
				} else {
					op1.showDetailsToUser();
				}
			}
			else {
				System.out.println("No user found");
				System.out.println("Enter 1 For Sign UP");
				System.out.println("Enter Any Number For Home page");
				a=sc.nextInt();
				if(a==1) {
					studentRegistration();
				}
				else {
					op1.showDetailsToUser();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void questionList() {

	}

	@Override
	public void showSingleResultByUsername() {

	}

	@Override
	public void showAllStudentResult() {

	}

	@Override
	public void showSingleResultById() {

	}

	@Override
	public void addQuestionInDatabase() {
		Connection con = connectDatabase();
		try {
			PreparedStatement ps = con.prepareStatement(
					"insert into quiz(Question,OptionA,OptionB,OptionC,OptionD,answer)values(?,?,?,?,?,?)");
			System.out.println("Question query done");
			for (int i = 1; i <= 10; i++) {
				System.out.println("Type your Question");
				String question = sc.nextLine();
				ps.setString(1, question);
				System.out.println("Enter option 1");
				String option_A = sc.nextLine();
				ps.setString(2, option_A);
				System.out.println("Enter option 2");
				String option_B = sc.nextLine();
				ps.setString(3, option_B);
				System.out.println("Enter option 3");
				String option_C = sc.nextLine();
				ps.setString(4, option_C);
				System.out.println("Enter option 4");
				String option_D = sc.nextLine();
				ps.setString(5, option_D);
				System.out.println("Enter Correct Answer only 1 2 3 4 Allowed !!! ");
				int ans = sc.nextInt();
				ps.setInt(6, ans);
				int a = ps.executeUpdate();
				System.out.println("querry run success" + a);
				sc.nextLine();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removequestion() {
		Connection con = connectDatabase();
		try {
			PreparedStatement ps1 = con.prepareStatement("delete from quiz where Q_no>10");
			int a = ps1.executeUpdate();
			System.out.println("QUESTION DELETED SUCCESSFULLY !!" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sure() {
		System.out.println("You sure You want to delete all questions ???");
		System.out.println("Press 1 for enter 10 Question");
		System.out.println("press any number go back to Main Slide");
		int num = sc.nextInt();
		if (num == 1) {
			Connection con = connectDatabase();
			try {
				PreparedStatement ps1 = con.prepareStatement("truncate table quiz");
				int a = ps1.executeUpdate();
				System.out.println("All questions deleted Successfully" + a);
				sc.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			op1.showDetailsToUser();
		}
	}

}
