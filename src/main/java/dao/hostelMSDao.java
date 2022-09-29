package dao;

import exception.GlobalException;
import model.user;

public interface hostelMSDao {
	public int registration(user u1) throws GlobalException;
	public user login(String username,String password) throws GlobalException;
}
