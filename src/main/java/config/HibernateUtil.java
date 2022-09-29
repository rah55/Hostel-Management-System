package config;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import model.room;
import model.user;

public class HibernateUtil {
	private static SessionFactory sesFactory;       //this is session factory for connection
	
	public static SessionFactory getSessionFactory() {
		
		if(sesFactory==null) {
			
			try {
				
				Configuration config=new Configuration(); //creating configuration for configuration
				Properties pro=new Properties();     //this is for setting all the properties in it
				
				
				//here we set all the properties which we required
				pro.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");	
				pro.put(Environment.URL,"jdbc:mysql://localhost:3306/hostelms");	
				pro.put(Environment.USER, "root");
				pro.put(Environment.PASS,"Rahul123@");
				pro.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				pro.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				pro.put(Environment.SHOW_SQL, "false");
				pro.put(Environment.HBM2DDL_AUTO, "update");
				
				//here we set all the configuration
				config.setProperties(pro);
				config.addAnnotatedClass(user.class);
				config.addAnnotatedClass(room.class);
				
				//here we build our session
				sesFactory=config.buildSessionFactory();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return null;
				}
			}
			return sesFactory;
				
				
			}
				
	public static Session getSession() {
		return getSessionFactory().openSession();
	}
			
			
}


