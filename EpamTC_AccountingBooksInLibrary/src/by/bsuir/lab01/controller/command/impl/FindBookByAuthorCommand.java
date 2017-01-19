package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.NewBookRequest;
import by.bsuir.lab01.bean.NewBookResponse;
import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.FindBookService;
import by.bsuir.lab01.service.ServiceException;

public class FindBookByAuthorCommand implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		// validation
		if (!validationParameters(request)) {
			throw new CommandException("Validation Exception.");
		}

		// call service
		NewBookRequest findBookRequest = (NewBookRequest) request;
		String result;
		try {
			result = FindBookService
					.findBookByAuthorService(findBookRequest.getTitle());
		} catch (ServiceException ex) {
			throw new CommandException("Command message about exception", ex);
		}

		// create response
		NewBookResponse response = new NewBookResponse();
		if (result != null) {
			response.setResultMessage(result);
		} else {
			response.setErrorMessage("Can't find the book.");
		}
		return response;
	}

	private boolean validationParameters(Request request) {
		NewBookRequest getStatusRequest = (NewBookRequest) request;
		String rq[] = getStatusRequest.getTitle().split("#");
		String status = rq[1];
		if (!status.equals("admin") && !status.equals("user")) return false;
		return true;
	}
}
