package by.bsuir.lab01.dao.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.entity.Book;

public class FileBookDao {

	private static String fileBooks;//you must read it from property file
	
	ArrayList<Book> getBooksList() throws DaoException {
		
		FileInputStream fis = null;
	    Properties property = new Properties();

	    try {
	        fis = new FileInputStream("src/resources/config.properties");
	        property.load(fis);
	        fileBooks = property.getProperty("Book_Library");
	    } catch (IOException e) {
	        System.err.println("ОШИБКА: Файл свойств отсутствует!");
	    }

		ArrayList<Book> books = new ArrayList<Book>();
		Scanner scan = null;
		BufferedReader brBooks = null;
		
		try {
			FileReader fr = new FileReader(fileBooks);
			brBooks = new BufferedReader(fr);
			
		} catch (FileNotFoundException ex) {
			throw new DaoException("Dao ExceptionMessage. File of Library is not found.", ex);
		}
		String bs;
		String[] book = new String[4];

		try {
			while ((bs = brBooks.readLine()) != null) {
				int i = 0;
				scan = new Scanner(bs);
				scan.useDelimiter(";");
				while (scan.hasNext()){
					book[i] = scan.next();
					i++;
				}
				books.add(new Book(book[0], book[1], Integer.parseInt(book[2]), book[3]));
			}
		} catch (IOException ex) {
			throw new DaoException("Dao ExceptionMessage. Error of string enter from CSV file.", ex);
			
		}finally {
			if (scan != null) {
				scan.close();
			}
			if (brBooks != null) {
				try {
					brBooks.close();
				} catch (IOException ex) {
					throw new DaoException("Dao ExceptionMessage. Error of CSV file closing.", ex);
				}
			}
		}
			
		return books;
		
	}

}
