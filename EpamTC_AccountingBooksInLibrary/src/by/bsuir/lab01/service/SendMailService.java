package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.SendMailDao;

public final class SendMailService {
	private SendMailService(){}

	public static boolean sendMailService(String title) throws ServiceException{
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		SendMailDao sendMailDao = daoFactory.getSendMailDao();
		
		try {
			sendMailDao.sendMail(title);
		} catch (DaoException ex) {
			throw new ServiceException("Service Exception Message", ex);
		}
		return true;

	}

}
