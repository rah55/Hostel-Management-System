package service;

import exception.GlobalException;

public interface loginRegister {
	
	public void register()throws GlobalException;
	public void login() throws GlobalException;

}
