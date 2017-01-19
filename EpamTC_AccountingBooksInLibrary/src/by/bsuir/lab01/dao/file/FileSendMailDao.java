package by.bsuir.lab01.dao.file;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.SendMailDao;
import by.bsuir.lab01.entity.User;

public class FileSendMailDao implements SendMailDao{
	private final static FileSendMailDao instance = new FileSendMailDao();
	
	
	private FileSendMailDao(){}

	public static SendMailDao getInstance() {
		return instance;
	}

	@Override
	public boolean sendMail(String newBook_Login_Status_eMail_eMailPassword) throws DaoException {
		
		HashMap<Integer, User> users;
		FileUserDao fud = new FileUserDao();
		users = fud.getUsersList();
		
		String ns[] = newBook_Login_Status_eMail_eMailPassword.split("#");
		String newBook = ns[0];
		String login = ns[1];
		String status = ns[2];
		String sender = ns[3];
		String pass = ns[4];
		String[] recievers = new String[users.size()];
		
		
		Set<Map.Entry<Integer, User>> setusers = users.entrySet();
		Iterator<Map.Entry<Integer, User>> it = setusers.iterator();
		int i = 0;
		while (it.hasNext()) {
			Map.Entry<Integer, User> me = it.next();
			
			String lo = me.getValue().getLogin();
			if(status.equals("admin") && !lo.equals(login)) {
				recievers[i] = me.getValue().geteMail();
				i = i + 1;
			}
			if(status.equals("user") && me.getValue().getStatus().equals("admin")) {
				recievers[i] = me.getValue().geteMail();
				i = i + 1;
			}
		}
		if(i == 0){
			throw new DaoException("Recievers are absent!");
			
		}
		 
		final by.bsuir.lab01.dao.file.ssl.Sender sslSender = new by.bsuir.lab01.dao.file.ssl.Sender(sender, pass);
		for (int j = 0; j<i; j++) {
			sslSender.send("Add new book", newBook, sender, recievers[j]);
		}	

		
		return true;
	}

}
