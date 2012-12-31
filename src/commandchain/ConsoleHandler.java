package commandchain;


public class ConsoleHandler extends CommandChain {

	public ConsoleHandler(CommandChain successor, String toHandle,
			Class<?> command) {
		super(successor, toHandle, command);
	}

	

}
