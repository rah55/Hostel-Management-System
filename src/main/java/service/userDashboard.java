package service;

import exception.GlobalException;

public interface userDashboard {

	public void dashboard(int uId) throws GlobalException;
	public void viewRoom();
	public void viewDueAmount();
	public void viewProfile();
	public void changePhonenumber();
	public void changePassword() throws GlobalException;

}
