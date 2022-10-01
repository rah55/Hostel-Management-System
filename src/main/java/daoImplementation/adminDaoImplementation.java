package daoImplementation;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import config.HibernateUtil;
import dao.adminDao;
import exception.GlobalException;
import model.room;
import model.user;

public class adminDaoImplementation implements adminDao {

	@Override
	public List<room> viewRooms() {
		// auto closable session object
		try (Session ses = HibernateUtil.getSession()) {
			// getting rows of a room table
			Query qu = ses.createQuery("from room");
			List<room> roomList = qu.getResultList();             // this will show the room list
			return roomList;
		}
	}

	@Override
	public List<user> viewUsers() {

		try (Session ses = HibernateUtil.getSession()) {
			// getting rows of a room table
			String student = "student";
			Query qu = ses.createQuery("from user where userRole=:student").setParameter("student", student);
			List<user> userList = qu.getResultList();
			return userList;
		}

	}

	@Override
	public int createRoom(room r1) throws GlobalException {

		try (Session ses = HibernateUtil.getSession()) {

			ses.beginTransaction();
			String roomName = r1.getRoomName();
			room r2 = null;
			// checking for existing room name
			r2 = (room) ses.createQuery("from room where roomName=:roomName").setParameter("roomName", roomName)
					.uniqueResult();
			// if room name is unique then we can save the room
			if (r2 == null) {
				ses.save(r1);
				// commit
				ses.getTransaction().commit();
				return 1;
			} else {
				throw new GlobalException("room name is already existed");
			}
		}
	}

	@Override
	public int allotRoom(int uId, int rId) {
		try (Session ses = HibernateUtil.getSession()) {
			ses.beginTransaction();
			int status = ses.createQuery("update user set userRoom_roomId=:rId where userId=:uId")
					.setParameter("rId", rId).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;

		}
	}

	@Override
	public int deleteUser(int uId) {
		try (Session ses = HibernateUtil.getSession()) {
			ses.beginTransaction();
			int status = ses.createQuery("delete from user where userId=:uId").setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		}

	}

	@Override
	public List<user> userInARoom(int rId) {

		try (Session ses = HibernateUtil.getSession()) {

			Query qu = ses.createQuery("from user where userRoom_roomId=:rId").setParameter("rId", rId);
			List<user> userList = qu.getResultList();
			return userList;
		}
	}

	@Override
	public int addDueAmount(int uId, int amount) {
		try (Session ses = HibernateUtil.getSession()) {
			ses.beginTransaction();
			int dueamount = (int) ses.createQuery("select userFee from user where userId=:uId").setParameter("uId", uId)
					.uniqueResult();

			dueamount += amount;
			int status = ses.createQuery("update user set userFee=:dueamount where userId=:uId")
					.setParameter("dueamount", dueamount).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		}
	}

	@Override
	public int paidDueAmount(int uId, int amount) {
		try (Session ses = HibernateUtil.getSession()) {
			ses.beginTransaction();
			int dueamount = (int) ses.createQuery("select userFee from user where userId=:uId").setParameter("uId", uId)
					.uniqueResult();

			dueamount -= amount;
			int status = ses.createQuery("update user set userFee=:dueamount where userId=:uId")
					.setParameter("dueamount", dueamount).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		}
	}

	@Override
	public user viewuserprofile(int uId) throws GlobalException {

		try (Session ses = HibernateUtil.getSession()) {

			user u1 = ses.get(user.class, uId);
			return u1;
		}

	}

}
