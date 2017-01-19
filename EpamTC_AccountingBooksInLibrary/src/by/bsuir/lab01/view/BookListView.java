package by.bsuir.lab01.view;

import java.util.Formatter;
import java.util.Scanner;

public class BookListView {
	String booksList;
	Scanner con;
	int numberBooksPerPage = 3;
	public BookListView(String booksList, Scanner con) {
		super();
		this.booksList = booksList;
		this.con = con;
	}
	public void getPagesOfBookList() {
		String table[] = booksList.split(";");
		
		System.out.println("table.length = " + table.length);
		
		int pagesNumber = table.length/(4*numberBooksPerPage);
		if(table.length % (4*numberBooksPerPage) != 0) pagesNumber += 1;
		
		int j = 1;
		
		while(true) {
			Formatter f = new Formatter();
			int limit = numberBooksPerPage;
			if(j == pagesNumber) limit = table.length/4 - (limit * (j - 1));
			int k = (j-1)*4*numberBooksPerPage;
			for (int i = 0; i < limit; i++) {
				f.format("%-20s %-40s %-5s %-8s\n", table[k], table[k+1], table[k+2], table[k+3]);
				k = k + 4;
				
			}
			System.out.println(f);
			System.out.println("Next Page (enter 'n')   Previous Page (enter 'p')   Exit (enter 'e')");
			String pageMode = con.nextLine();
			if(pageMode.equals("n")) {
				if(j < pagesNumber) j = j + 1;
				else {
					System.out.println("The last Page");
					
				}
			}
			else if(pageMode.equals("p")) {
				if(j > 1) j = j - 1;
				else {
					System.out.println("The first Page");
					
				}
			}
			else if(pageMode.equals("e")) break;
			else System.out.println("Enter the true symbol!");
		}

		
	}
	
	


}
