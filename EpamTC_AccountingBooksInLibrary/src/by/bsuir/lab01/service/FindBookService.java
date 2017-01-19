package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.FindDao;

public final class FindBookService {
	private FindBookService(){}

	public static String findBookByAuthorService(String author)  throws ServiceException{
			DaoFactory daoFactory = DaoFactory.getDaoFactory();
			FindDao findDao = daoFactory.getFindDao();
			String result;
			try {
				result = findDao.findBookByAuthor(author);
			} catch (DaoException ex) {
				throw new ServiceException("Service Exception MEssage", ex);
			}
			return result;
		}
	
}
