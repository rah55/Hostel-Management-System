package service;

import exception.GlobalException;

public interface adminDashboard {

	public void dashboard() throws GlobalException;

	public void viewRooms();

	public void viewUsers();

	public void createRoom() throws GlobalException;

	public void allotRoom() throws GlobalException;

	public void deleteUser() throws GlobalException;

	public void userInARoom();

	public void addDueAmount() throws GlobalException;

	public void paidDueAmount() throws GlobalException;

	public void viewuserprofile() throws GlobalException;
}
