package by.bsuir.lab01.dao.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.entity.User;

class FileUserDao {
	
	private static String fileUsers;//you must read it from property file
	
	HashMap<Integer, User> getUsersList() throws DaoException {
		
		FileInputStream fis = null;
	    Properties property = new Properties();

	    try {
	        fis = new FileInputStream("src/resources/config.properties");
	        property.load(fis);

	        fileUsers = property.getProperty("User_List");
	        
	    } catch (IOException e) {
	        System.err.println("ОШИБКА: Файл свойств отсутствует!");
	    }
		
		
		HashMap<Integer, User> users = new HashMap<Integer, User>();
		Scanner scan = null;
		BufferedReader brLogin = null;
		try {
			FileReader lf = new FileReader(fileUsers);
			brLogin = new BufferedReader(lf);
		} catch (FileNotFoundException ex) {
			throw new DaoException("Dao ExceptionMessage. File with logins is not found.", ex);
		}
		String lo;
		String[] login  = new String[5];
		int loginId = 0;
		try {
			int j = 0;
			while ((lo = brLogin.readLine()) != null) {
				int i = 0;
				
				scan = new Scanner(lo);
				scan.useDelimiter(";");
				while(scan.hasNext()){
					if (scan.hasNextInt()) {
						loginId = scan.nextInt();
					} else {
						login[i] = scan.next();
					}
					i++;
				}
				j++;
				users.put(j, new User(loginId, login[1], login[2], login[3], login[4]));
			}

		} catch (IOException ex) {
			throw new DaoException("Dao ExceptionMessage. Error of readLine from CSV file.", ex);
		}finally {
			if (scan != null) {
				scan.close();
			}
			if (brLogin != null) {
				try {
					brLogin.close();
				} catch (IOException ex) {
					throw new DaoException("Dao ExceptionMessage. Error of BufferedReader closing.", ex);
				}
			}
		}
		
		return users;
		
		
		
		
	}
	
	
	
	
	
	
}
