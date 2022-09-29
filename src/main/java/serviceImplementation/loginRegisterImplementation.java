package serviceImplementation;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import dao.hostelMSDao;
import daoImplementation.HostelMSDaoImplementation;
import exception.GlobalException;
import hostel.HMS.App;
import model.user;
import service.adminDashboard;
import service.loginRegister;
import service.userDashboard;

public class loginRegisterImplementation implements loginRegister {
	 static Logger log=Logger.getLogger(App.class);          //this is logger to avoid println
	    static Scanner bs=new Scanner(System.in);  			//this is scanner class
	    static hostelMSDao dao=new HostelMSDaoImplementation(); //here we create dao object of hostelmsdao class
	    
	    public void register() throws GlobalException{
			log.info("welcome to registeration");
			log.info("Enter Username");
			String uname=bs.next();
			log.info("Create Password");
			String upwd=bs.next();
			log.info("Enter Phone number");
			String uphone=bs.next();
			log.info("Enter Address");
			String uaddress=bs.next();
			
			user u1=new user();
			u1.setUserName(uname);
			u1.setUserPassword(upwd);
			u1.setUserPhone(uphone);
			u1.setUserAddress(uaddress);
			u1.setUserRole("student");
			u1.setUserRoom(null);
			u1.setUserFee(0);
			
			if(Pattern.matches("[a-zA-Z]{4,}", uname) && Pattern.matches("[a-zA-Z0-9@#]{6,}",upwd)&& Pattern.matches("[0-9]{10}", uphone))
			{
				
				int status=dao.registration(u1);
				if(status==1) {
					log.info("Registration success");
				}
				else {
					throw new GlobalException("Something went wrong...!!");
				}
			    }
			    else {
				    throw new GlobalException("Invalid data Please Enter a valid data ");
			    }

	    }
	    
	    public void login()throws GlobalException {
			
			log.info("welcome to Login");
			log.info("Please Enter username : ");
			String username=bs.next();
			log.info("Please Enter password : ");
			String password=bs.next();
			//check login
			user u1=dao.login(username, password);
			//success message
			log.info("Hello "+u1.getUserName()+" Login Successfull Welcome to Hostel ");
			
			//creating object for admindashboard and userdashboard
			userDashboard udl =new userDashboardImplementation();
			adminDashboard adl = new adminDashboardImplementation();
			//if userrole is student userdashboard will open
			//if userrole is admin admindashboard will open
			
			if(u1.getUserRole().equals("student")) {
				udl.dashboard(u1.getUserId());
			}
			else if(u1.getUserRole().equals("admin")) {
				adl.dashboard();
			}
			
		
		}
}
