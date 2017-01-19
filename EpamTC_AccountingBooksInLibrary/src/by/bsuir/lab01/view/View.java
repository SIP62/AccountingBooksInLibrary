package by.bsuir.lab01.view;

import java.util.Scanner;
import java.util.regex.Pattern;

import by.bsuir.lab01.bean.NewBookRequest;
import by.bsuir.lab01.bean.NewBookResponse;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.controller.BookController;

public class View {
	private BookController controller = new BookController();
	private static String status = "";
	private static String login;
	
	public void menu(){
		
		String author;
		String title;
		String year;
		String issue;
		String regex = "[\\w[à-ÿÀ-ß]]*";

		Scanner con = new Scanner(System.in);

		for(int i = 0; i < 5; i++) {
			System.out.println("Enter login:");
			String logintest = con.nextLine();
			if (!Pattern.matches(regex, logintest)) {
				if(i < 4){
					System.out.println("Please use alfabet and numbers! Try once more! You have " + (4-i) + " attempts");
					continue;
				}
			}
			System.out.println("Enter password:");
			String passwordtest = con.nextLine();
			if (!Pattern.matches(regex, passwordtest)) {
				if(i < 4){
					System.out.println("Please use alfabet and numbers! Try once more! You have " + (4-i) + " attempts");
					continue;
				}
			}

			NewBookRequest loginCheckRequest = new NewBookRequest();
			loginCheckRequest.setCommandName("LOGIN_USER");
			PassScrambler ps = new PassScrambler();
			String scrambledPass = ps.scramble(passwordtest);
			loginCheckRequest.setTitle(logintest + "#" + scrambledPass);
			Response loginCheckResponse = controller.executeRequest(loginCheckRequest);
			if(loginCheckResponse.getErrorMessage() != null){
				System.out.println(loginCheckResponse.getErrorMessage());
				if(i < 4)System.out.println("Try once more! You have " + (4-i) + " attempts");
			}else{
				NewBookResponse getLoginCheckResponse = (NewBookResponse)loginCheckResponse;
				status = getLoginCheckResponse.getResultMessage();
				login = logintest;
				System.out.println("Welcome " + login + " to the Book Library");
				System.out.println("Your status is " + status);
				break;
			}
		}
		if (!status.equals("admin") && !status.equals("user")) {
			System.out.println("You are is alien! Work is over!");
			if (con != null) {
				con.close();
				}
			return;
		}

		while(true) {
			System.out.println("Enter the number of the task:");
			System.out.println("1 - Book list of the library");
			System.out.println("2 - Find books by author");
			if(status.equals("admin")) System.out.println("3 - Add new book");
			if(status.equals("user")) System.out.println("4 - Offer to include a new book by e-Mail");
			System.out.println("0 - Finish the work");
			String mode = con.nextLine();
			switch(mode){
				case "1":
					System.out.println("mode 1 - Book list of the library");
					NewBookRequest booksListRequest = new NewBookRequest();
					booksListRequest.setCommandName("FIND_BOOK_BY_AUTHOR");
					booksListRequest.setTitle("allAuthors" + "#" + status);
					Response booksListResponse = controller.executeRequest(booksListRequest);
					if(booksListResponse.getErrorMessage() != null){
						
						System.out.println(booksListResponse.getErrorMessage());
					}else{
						NewBookResponse getBooksListResponse = (NewBookResponse)booksListResponse;
						String booksList = getBooksListResponse.getResultMessage();
						BookListView bookListView = new BookListView(booksList, con);
						bookListView.getPagesOfBookList();

					}
					break;
				case "2":
					System.out.println("mode 2 - Find books by author");
					NewBookRequest findBookRequest = new NewBookRequest();
					findBookRequest.setCommandName("FIND_BOOK_BY_AUTHOR");
					System.out.println("Enter the Author of the book:");
					author = con.nextLine();
					findBookRequest.setTitle(author + "#" + status);
					Response findBookResponse = controller.executeRequest(findBookRequest);
					if(findBookResponse.getErrorMessage() != null){
						System.out.println(findBookResponse.getErrorMessage());
					}else{
						NewBookResponse findBookByAuthorResponse = (NewBookResponse)findBookResponse;

						String findBooksList = findBookByAuthorResponse.getResultMessage();
						BookListView findbookListView = new BookListView(findBooksList, con);
						findbookListView.getPagesOfBookList();
						
						
					}
					break;	
				case "3":
					System.out.println("mode 3 - Add new book");
					NewBookRequest request = new NewBookRequest();
					request.setCommandName("ADD_NEW_BOOK");
					System.out.println("Enter the Author of the new book:");
					author = con.nextLine();
					System.out.println("Enter the Title of the new book:");
					title = con.nextLine();
					System.out.println("Enter the edition year of the new book:");
					System.out.println("Use 4 number format YYYY");
					year = con.nextLine();
					System.out.println("Choose edition of the new book:");
					System.out.println("Enter the number 1 for printed book");
					System.out.println("Enter the number 2 for book in file");
					String n = con.nextLine();
					issue = "";
					if(n.equals("1")) issue = "printed";
					if(n.equals("2")) issue = "file";
					String newBookString = author + ";" + title + ";" + year + ";" + issue;
					request.setTitle(newBookString + "#" + status);
					Response response = controller.executeRequest(request);
					if(response.getErrorMessage() != null){
						System.out.println(response.getErrorMessage());
					}else{
						NewBookResponse newBookResponse = (NewBookResponse)response;
						System.out.println(newBookResponse.getResultMessage());
						
						System.out.println("Send eMail with the new book to users?");
						System.out.println("Yes (enter 'y')   Now (enter 'n')");
						String eMail = con.nextLine();
						if(eMail.equals("y")) {
							System.out.println("Enter your e-Mail");
							String email = con.nextLine();
							System.out.println("Enter your e-Mail password");
							String emailPassword = con.nextLine();
							NewBookRequest sendMailRequest = new NewBookRequest();
							sendMailRequest.setCommandName("SEND_MAIL");
							sendMailRequest.setTitle(newBookString + "#" + login + "#" + status + "#" + email + "#" + emailPassword);
							Response sendMailResponse = controller.executeRequest(sendMailRequest);
							if(sendMailResponse.getErrorMessage() != null){
								System.out.println(sendMailResponse.getErrorMessage());
							}else{
								NewBookResponse sendMailToUsersResponse = (NewBookResponse)sendMailResponse;
								System.out.println(sendMailToUsersResponse.getResultMessage());
							}	
						}
						
					}
					break;
				case "4":
					System.out.println("mode 4 - Offer to include a new book by e-Mail");
					System.out.println("Enter the Author of the new book:");
					author = con.nextLine();
					System.out.println("Enter the Title of the new book:");
					title = con.nextLine();
					System.out.println("Enter the edition year of the new book:");
					System.out.println("Use 4 number format YYYY");
					year = con.nextLine();
					System.out.println("Choose edition of the new book:");
					System.out.println("Enter the number 1 for printed book");
					System.out.println("Enter the number 2 for book in file");
					String nu = con.nextLine();
					issue = "";
					if(nu.equals("1")) issue = "printed";
					if(nu.equals("2")) issue = "file";
					String offeredBookString = author + ";" + title + ";" + year + ";" + issue;
					System.out.println("Enter your e-Mail");
					String email = con.nextLine();
					System.out.println("Enter your e-Mail password");
					String emailPassword = con.nextLine();
					NewBookRequest sendMailRequest = new NewBookRequest();
					sendMailRequest.setCommandName("SEND_MAIL");
					sendMailRequest.setTitle(offeredBookString + "#" + login + "#" + status + "#" + email + "#" + emailPassword);
					Response sendMailResponse = controller.executeRequest(sendMailRequest);
					if(sendMailResponse.getErrorMessage() != null){
						System.out.println(sendMailResponse.getErrorMessage());
					}else{
						NewBookResponse sendMailToUsersResponse = (NewBookResponse)sendMailResponse;
						System.out.println(sendMailToUsersResponse.getResultMessage());
					}	
					break;
				case "0":
					System.out.println("mode 0 - Work is over");
					if (con != null) {
						con.close();
						}
					return;
				default: System.out.println("Read conditions attentively and enter true number!");	
			}
		}
	}
}
