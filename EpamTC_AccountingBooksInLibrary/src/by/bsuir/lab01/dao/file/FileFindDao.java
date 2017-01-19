package by.bsuir.lab01.dao.file;

import java.util.ArrayList;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.FindDao;
import by.bsuir.lab01.entity.Book;

public final class FileFindDao implements FindDao {
	private final static FileFindDao instance = new FileFindDao();
	
	private FileFindDao(){}
	
	public static FileFindDao getInstance(){
		return instance;
	}
	
	public String findBookByAuthor(String authorAndStatus) throws DaoException{
		
		ArrayList<Book> books;
		FileBookDao fbd = new FileBookDao();
		books = fbd.getBooksList();
		String table = "";
		String as[] = authorAndStatus.split("#");
		String author = as[0];
		
		for(Book bi: books) {
			if(bi.getAuthor().equals(author) || author.equals("allAuthors"))
				table = table + bi.getAuthor() + ";" + bi.getTitle() + ";" + bi.getYear() + ";" + bi.getIssuetype() + ";";
		}
		return table;
	}
}
