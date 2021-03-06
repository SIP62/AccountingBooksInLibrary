package by.bsuir.lab01.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.bsuir.lab01.controller.command.impl.AddNewBookCommand;
import by.bsuir.lab01.controller.command.impl.FindBookByAuthorCommand;
import by.bsuir.lab01.controller.command.impl.LoginUserCommand;
import by.bsuir.lab01.controller.command.impl.SendMailCommand;

public final class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	
	public CommandHelper(){
		commands.put(CommandName.ADD_NEW_BOOK, new AddNewBookCommand());
		commands.put(CommandName.LOGIN_USER, new LoginUserCommand());
		commands.put(CommandName.FIND_BOOK_BY_AUTHOR, new FindBookByAuthorCommand());
		commands.put(CommandName.SEND_MAIL, new SendMailCommand());
	}	
	
	public Command getCommand(String commandName){
		CommandName command = CommandName.valueOf(commandName);
		return commands.get(command);		
	}
}
