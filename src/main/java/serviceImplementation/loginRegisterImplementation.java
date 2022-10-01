package serviceImplementation;

import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.*;


import javax.xml.transform.Source;

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
	static Logger log = Logger.getLogger(App.class);
	static Scanner bs = new Scanner(System.in);
	static hostelMSDao dao = new HostelMSDaoImplementation();

	// registration method
	public void register() throws GlobalException {
		log.info("welcome to registeration");
		log.info("Enter Username");
		String uname = bs.next();
		log.info("Create Password");
		String upwd = bs.next();
		log.info("Enter Phone number");
		String uphone = bs.next();
		log.info("Enter Address");
		String uaddress = bs.next();

		user u1 = new user();
		u1.setUserName(uname);
		u1.setUserPassword(upwd);
		u1.setUserPhone(uphone);
		u1.setUserAddress(uaddress);
		u1.setUserRole("student");
		u1.setUserRoom(null);
		u1.setUserFee(0);

		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator valid = vf.getValidator();

		Set<ConstraintViolation<user>> violations = valid.validate (u1);

		if (violations.size() > 0) {
			for (ConstraintViolation<user> violate : violations)
				log.info(violate.getMessage());
		} 
		else {
			int status = dao.registration(u1);

			if (status == 1) {
				log.info("Registration success");
			}
			else {
				throw new GlobalException("Something went wrong");
			}
		}
	}

	public void login() throws GlobalException {
		log.info("welcome to Login");

		log.info("Enter username");
		String username = bs.next();
		log.info("Enter password");
		String password = bs.next();
		// checking login
		user u1 = dao.login(username, password);
		// success message
		log.info("Hello" + u1.getUserName() + "Login Success");
		userDashboard udl = new userDashboardImplementation();
		adminDashboard adl = new adminDashboardImplementation();
		
		// if user role is student user dashboard will open
		// if user role is admin Admin dashboard will open
		
		if (u1.getUserRole().equals("student")) {
			udl.dashboard(u1.getUserId());
		} 
		else if (u1.getUserRole().equals("admin")) {
			adl.dashboard();
		}
	}

}