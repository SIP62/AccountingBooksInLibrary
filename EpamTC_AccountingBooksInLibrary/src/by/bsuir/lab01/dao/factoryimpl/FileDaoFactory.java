package by.bsuir.lab01.dao.factoryimpl;

import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.FindDao;
import by.bsuir.lab01.dao.LoginCheckDao;
import by.bsuir.lab01.dao.ModificationDao;
import by.bsuir.lab01.dao.SendMailDao;
import by.bsuir.lab01.dao.file.FileFindDao;
import by.bsuir.lab01.dao.file.FileLoginCheckDao;
import by.bsuir.lab01.dao.file.FileModificationDao;
import by.bsuir.lab01.dao.file.FileSendMailDao;

public final class FileDaoFactory extends DaoFactory{
	private final static FileDaoFactory instance = new FileDaoFactory();
	
	private FileDaoFactory(){}
	
	public final static FileDaoFactory getInstance(){
		return instance;
	}
	
	@Override
	public LoginCheckDao getLoginCheckDao() {
		return FileLoginCheckDao.getInstance();
	}
	
	@Override
	public FindDao getFindDao() {
		return FileFindDao.getInstance();
	}

	@Override
	public ModificationDao getModificationDao() {
		return FileModificationDao.getInstance();
	}

	@Override
	public SendMailDao getSendMailDao() {
		return FileSendMailDao.getInstance();
	}

}
