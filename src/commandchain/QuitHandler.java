package commandchain;

import logger.Logger;
import command.Command;

public class QuitHandler extends CommandChain {

	public QuitHandler(CommandChain successor, String toHandle,Class<?> command) {
		super(successor, toHandle, command);
	}
	public Command handle(String...param){
		Command ret=null;
		if(param[0].equalsIgnoreCase(toHandle))
			try {
				ret=(Command) command.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				Logger.log(e.getMessage());
			}
		else
			ret=successor.handle(param);
		return ret;
	}
}
