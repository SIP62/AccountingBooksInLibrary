package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.NewBookRequest;
import by.bsuir.lab01.bean.NewBookResponse;
import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.LoginCheckService;
import by.bsuir.lab01.service.ServiceException;

public class LoginUserCommand implements Command{

	@Override
	public Response execute(Request request) throws CommandException{
		// call service
				NewBookRequest loginCheckRequest = (NewBookRequest) request;
				String result;
				try {
					result = LoginCheckService
							.loginCheckService(loginCheckRequest.getTitle());
				} catch (ServiceException ex) {
					throw new CommandException("Command message about exception", ex);
				}

				// create response
				NewBookResponse response = new NewBookResponse();
				if (result != null) {
					response.setResultMessage(result);
				} else {
					response.setErrorMessage("Wrong login or password!");
				}
				return response;
	}

}
