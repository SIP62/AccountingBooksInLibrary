package by.bsuir.lab01.dao.file;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.ModificationDao;
import by.bsuir.lab01.entity.Book;

public final class FileModificationDao implements ModificationDao {
	private final static FileModificationDao instance = new FileModificationDao();
	
	private static String fileBooks;//you must read it from property file
	
	private FileModificationDao(){}
	
	public static FileModificationDao getInstance(){
		return instance;
	}
	@Override
	public boolean addNewBook(String titleAndStatus) throws DaoException {
		
		FileInputStream fis = null;
	    Properties property = new Properties();

	    try {
	        fis = new FileInputStream("src/resources/config.properties");
	        property.load(fis);
	        fileBooks = property.getProperty("Book_Library");
	    } catch (IOException e) {
	        System.err.println("ОШИБКА: Файл свойств отсутствует!");
	    }

		BufferedWriter bw = null;
		String ts[] = titleAndStatus.split("#");
		String title = ts[0];
		String nb[] = title.split(";");
		ArrayList<Book> books;
		FileBookDao fbd = new FileBookDao();
		books = fbd.getBooksList();
		Book book = new Book(nb[0], nb[1], Integer.parseInt(nb[2]), nb[3]);
		if(books.contains(book)) return false;
		try {
			FileWriter fw = new FileWriter(fileBooks,true);
			bw = new BufferedWriter(fw);
			bw.write("\n");			
			bw.write(title);
		} catch (IOException ex) {
			throw new DaoException("Dao ExceptionMessage. File of Library is not found.", ex);
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException ex) {
					throw new DaoException("Dao ExceptionMessage. BufferedWriter is not closed.", ex);
				}
			}
		}
		return true;
	}

}
