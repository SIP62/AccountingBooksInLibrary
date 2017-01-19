package by.bsuir.lab01.dao.file;

import java.util.HashMap;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.LoginCheckDao;
import by.bsuir.lab01.entity.User;

public class FileLoginCheckDao implements LoginCheckDao{
	private final static FileLoginCheckDao instance = new FileLoginCheckDao();
	
	private FileLoginCheckDao(){}

	public static LoginCheckDao getInstance() {
		return instance;
	}

	@Override
	public String loginCheck(String loginAndPass) throws DaoException {
		
		HashMap<Integer, User> users;
		FileUserDao fud = new FileUserDao();
		users = fud.getUsersList();
		

		String lp[] = loginAndPass.split("#");
		User us = new User(0, lp[0], lp[1], null, null);
		if(users.containsValue(us)) return us.getStatus();
		return null;
	}

}
