package commandchain;

import command.HighLevelOperationCommand;

public class HighLevelOpHandler extends CommandChain {

	public HighLevelOpHandler(CommandChain successor, String toHandle) {
		super(successor, toHandle, HighLevelOperationCommand.class);
	}
	

}
