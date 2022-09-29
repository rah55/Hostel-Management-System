package serviceImplementation;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import dao.adminDao;
import daoImplementation.adminDaoImplementation;
import exception.GlobalException;
import model.room;
import model.user;
import service.adminDashboard;

public class adminDashboardImplementation implements adminDashboard {
	
	
	static Logger log=Logger.getLogger(adminDashboardImplementation.class);
	static Scanner bs=new Scanner(System.in);
	static adminDashboard adl=new adminDashboardImplementation();
	static adminDao dao=new adminDaoImplementation();
	@Override
	public void dashboard() throws GlobalException {
		log.info("\t\t\t-------------------welcome to Admin dashboard----------------");
		
		int op=0;
		while(op<10)
		{
			//these are the key for performing actions
		log.info("\n\tPress 1 for View Rooms\t\t\t"
				+ "Press 2 for View Users\n\t"
				+ "Press 3 for Create Rooms\t\t"
				+ "Press 4 for Allot room to user\n"
				+ "\tPress 5 for view user in a room\t\t"
				+ "Press 6 for view user profile\n\t"
				+ "Press 7 for Add Due Amount\t\t"
				+ "Press 8 for Pay Due Amount\n\t"
				+ "Press 9 for delete user");
		
		op=bs.nextInt();
		switch(op) {
		
		case 1->adl.viewRooms();   		//method for viewRooms
		case 2->adl.viewUsers();		//method for viewUsers
		case 3->adl.createRoom();		//method for createRooms
		case 4->adl.allotRoom();		//method for allot the Rooms
		case 5->adl.userInARoom();		//method for knowing the user in a particular room
		case 6->adl.viewuserprofile();	//method for view user profile
		case 7->adl.addDueAmount();		//method for adding the due amount
		case 8->adl.paidDueAmount();	//method for paid amount
		case 9->adl.deleteUser();		//method for delete the user
		default->System.exit(0);	
		}
	}
	}

	@Override
	//this is view rooms method
	public void viewRooms() {
		List<room> roomList=dao.viewRooms();
		log.info("\nroom num\t\t roomName\t\t roomType");
		for(room r:roomList)
			log.info(" \t "+r.getRoomId()+" \t\t "+r.getRoomName()+" \t\t "+r.getRoomType());
	}

	@Override
	// this is view user method
	public void viewUsers() {
		List<user> userList=dao.viewUsers();
		log.info("\nUser Id\t\tUserName\t\tuser Phone\t\tuserRoom");
		for(user u1:userList)
			log.info("\t"+u1.getUserId()+"\t\t"+u1.getUserName()+"\t\t"+u1.getUserPhone()+"\t\t"+u1.getUserRoom().getRoomId());
		
	}

	@Override
	//this is create room method
	public void createRoom()  {
		log.info("Enter Room Id");
		int id=bs.nextInt();
		log.info("Enter Room Name");
		String rname=bs.next();
		log.info("Enter Room Type");
		String rtype=bs.next();
		room r1=new room();
		r1.setRoomId(id);
		r1.setRoomName(rname);
		r1.setRoomType(rtype);
		try { // if room is not created before so we use try block
			int st=dao.createRoom(r1);
			if(st==1) {
				log.info("room added successfully");
			}
		}
		catch(Exception e) {
			log.info(e.getMessage());
		}
		
		
	}

	@Override
	//this is the method to allot the room 
	public void allotRoom() throws GlobalException {
		
		log.info("Enter user Id");
		int uid=bs.nextInt();
		log.info("Enter room Id");
		int rId=bs.nextInt();
		int st=dao.allotRoom(uid, rId);
		if(st==1) { 
			log.info("Room alloted successfully");
		}
		else {
			throw new GlobalException("Something went wrong");
		}
		
	}

	@Override
	//this is the method to delete the user 
	public void deleteUser() throws GlobalException {
		log.info("Enter user Id to delete");
		int uid=bs.nextInt();
		int st=dao.deleteUser(uid);
		if(st==1) {
			log.info("deleted!...");
		}
		else {
			throw new GlobalException("Something went wrong");
		}
	}

	@Override
	//method to show the user in a particular room
	public void userInARoom() {
		log.info("Enter Room Id");
		int rid=bs.nextInt();
		List<user> userList = dao.userInARoom(rid);
	log.info("\nUser Id \t\t UserName \t\t user Phone");
	for(user u1:userList)
		log.info("\t"+u1.getUserId()+"\t\t"+u1.getUserName()+"\t\t"+u1.getUserPhone());
	}

	@Override
	// this is the method for add due amount of the fees
	public void addDueAmount() throws GlobalException {
		log.info("Enter Amount to add");
		int amount=bs.nextInt();
		log.info("Enter user Id");
		int uid=bs.nextInt();
		int st=dao.addDueAmount(uid, amount);
		if(st==1) {
			log.info("amount added");
		}
		else {
			throw new GlobalException("Something went wrong");
		}
	}

	@Override
	//this is the method for paid due amount of the fees 
	public void paidDueAmount() throws GlobalException {
		log.info("Enter Amount to pay");
		int amount=bs.nextInt();
		log.info("Enter user Id");
		int uid=bs.nextInt();
		int st=dao.paidDueAmount(uid, amount);
		if(st==1) {
			log.info("amount added");
		}
		else {
			throw new GlobalException("Something went wrong");
		}	
	}

	@Override
	//this method show the profile of the user
	public void viewuserprofile() throws GlobalException {
		log.info("Enter user id");
		int uid=bs.nextInt();
		user u1=dao.viewuserprofile(uid);
		log.info(u1);
	}

}
