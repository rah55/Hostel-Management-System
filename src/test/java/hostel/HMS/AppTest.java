package hostel.HMS;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import config.HibernateUtil;
import org.hibernate.Session;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hibernate.Session;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dao.hostelMSDao;
import dao.userDao;
import dao.adminDao;
import daoImplementation.HostelMSDaoImplementation;
import daoImplementation.userDaoImplementation;
import daoImplementation.adminDaoImplementation;
import exception.GlobalException;
import model.room;
import model.user;

public class AppTest {
	// 1st test

	// perform testing on registration
	@Test
	@Disabled // we use this method for disable so that new method can be test

	@DisplayName("Test Registration")
	public void registrationTest() {

		hostelMSDao dao = new HostelMSDaoImplementation();
		user u1 = new user();

		u1.setUserName("puneet");
		u1.setUserPassword("puneet123@");
		u1.setUserPhone("8989898999");
		u1.setUserRole("student");
		u1.setUserAddress("faridabad");

		user u2 = new user();
		u2.setUserName("siksha");
		u2.setUserPassword("siksha123@");
		u2.setUserPhone("8908908990");
		u2.setUserRole("student");
		u2.setUserAddress("delhi");

		assertAll(
				// positive testing for registration
				() -> assertEquals(1, dao.registration(u1)),

				// negative testing for registration
				() -> assertThrows(GlobalException.class, () -> dao.registration(u2)));
	}

	// 2 test
	@Test
	@Disabled // we use this method for disable so that new method can be test
	@DisplayName("This is a test case for change Password")

	void changePasswordTest() throws GlobalException {
		userDao dao = new userDaoImplementation();

		assertAll(

				// Here we check users password change or not
				() -> assertEquals(1, dao.changePassword(5, "sumit123@", "sumit1234@")),

				// Here we check program throws exception or not when user not exist
				() -> assertThrows(Exception.class, () -> dao.changePassword(20, "123kkk", "123hgg")));

	}

	// 3 test
	@Test
	@Disabled // we use this method for disable so that new method can be test
	@DisplayName("this is for creating a room")
	void createRoomTset() throws GlobalException {

		// Here we create object of adminDaoImplementation class
		adminDao dao = new adminDaoImplementation();

		// Here we create one room for add room
		room r1 = new room();
		r1.setRoomId(120);
		r1.setRoomName("ocean");
		r1.setRoomType("non");

		// Here we create one room for check exception is thrown or not
		room r2 = new room();
		r2.setRoomId(121);
		r2.setRoomName("sea");
		r2.setRoomType("AC");
		assertAll(
				// this is for adding room in the room table
				() -> assertEquals(1, dao.createRoom(r1)),

				// Here we check program throws exception or not when room already exist
				() -> assertThrows(GlobalException.class, () -> dao.createRoom(r2)));
	}

	// 4 test
	@Test
	@Disabled // we use this method for disable so that new method can be test
	@DisplayName("This is a test case for delete user")
	void deleteUserTest() {
		// Here we create object of adminDaoImpl class
		adminDao dao = new adminDaoImplementation();

		assertAll(
				// Here we check user is deleted or not
				() -> assertEquals(1, dao.deleteUser(11)),

				// Here we check it return 0 when user is not exist
				() -> assertEquals(0, dao.deleteUser(100)));
	}

	// 5 test
	@Test
	@Disabled // we use this method for disable so that new method can be test
	@DisplayName("This is a test case for add due amount")
	void addUserAmountTest() {
		adminDao dao = new adminDaoImplementation();
		assertAll(
				// Here we add users amount in there account
				() -> assertEquals(1, dao.addDueAmount(5, 1000)),
				// Here we check program throws exception or not when user is not exist
				() -> assertThrows(Exception.class, () -> dao.addDueAmount(15, 500)));
	}

	// 6 test
	@Test
	@DisplayName("This is a test case for paid due amount")
	void paidUserAmountTest() {
		adminDao dao = new adminDaoImplementation();
		assertAll(
				// Here we reduce the amount user paid in there account
				() -> assertEquals(1, dao.paidDueAmount(8, 100)),

				// Here we check program throws exception or not when user is not exist
				() -> assertThrows(Exception.class, () -> dao.paidDueAmount(15, 400)));
	}

}
