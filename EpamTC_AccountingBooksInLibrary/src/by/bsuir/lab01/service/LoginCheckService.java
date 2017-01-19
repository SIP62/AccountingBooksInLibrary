package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.LoginCheckDao;

public final class LoginCheckService {
	private LoginCheckService(){}

	public static String loginCheckService(String loginAndPass) throws ServiceException{
			DaoFactory daoFactory = DaoFactory.getDaoFactory();
			LoginCheckDao loginCheckDao = daoFactory.getLoginCheckDao();
			
			String result;
			try {
				result =loginCheckDao.loginCheck(loginAndPass);
			} catch (DaoException ex) {
				throw new ServiceException("Service Exception MEssage", ex);
			}
			return result;
	}

}
