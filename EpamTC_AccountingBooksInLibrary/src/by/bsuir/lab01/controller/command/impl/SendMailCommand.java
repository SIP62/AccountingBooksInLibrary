package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.NewBookRequest;
import by.bsuir.lab01.bean.NewBookResponse;
import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.SendMailService;
import by.bsuir.lab01.service.ServiceException;

public class SendMailCommand implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		// validation
		if (!validationParameters(request)) {
			throw new CommandException("Validation Exception.");
		}

		// call service
		NewBookRequest sendMailRequest = (NewBookRequest) request;
		boolean result = false;
		try {
			result = SendMailService
					.sendMailService(sendMailRequest.getTitle());
		} catch (ServiceException ex) {
			throw new CommandException("Command message about exception", ex);
		}

		// create response
		NewBookResponse response = new NewBookResponse();
		if (result) {
			response.setResultMessage("eMail is sent.");
		} else {
			response.setErrorMessage("Can't send eMail.");
		}
		return response;
	}

	private boolean validationParameters(Request request) {
		NewBookRequest getStatusRequest = (NewBookRequest) request;
		String rq[] = getStatusRequest.getTitle().split("#");
		String status = rq[2];
		if (!status.equals("admin") && !status.equals("user")) return false;
		return true;
	}
}
