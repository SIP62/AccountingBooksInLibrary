package by.bsuir.lab01.dao;


public interface FindDao {
	String findBookByAuthor(String author) throws DaoException;
}
