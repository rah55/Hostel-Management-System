package hostel.HMS;

import java.util.Scanner;

import org.apache.log4j.Logger;

import exception.GlobalException;
import service.loginRegister;
import serviceImplementation.loginRegisterImplementation;

public class App 
{
   
    

    	static Logger log=Logger.getLogger(App.class);
    	 public static void main( String[] args )throws GlobalException
    	    {
    	    	Scanner bs=new Scanner(System.in);
    	    	log.info("\t\t\t\t\t---------WELCOME TO HOSTEL MANAGMENT SYSTEM----------");
    	    	loginRegister loginreg = new loginRegisterImplementation();
    	    	log.info("\nPress 1. For Registeration\nPress 2. For Login");
    	    	int op=bs.nextInt();
    	    	
    	    	switch(op) {
    	    	case 1->loginreg.register();
    	    	case 2->loginreg.login();
    	    	}
    	    }
    	
    	
    }

