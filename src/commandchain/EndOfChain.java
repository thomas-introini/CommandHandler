package commandchain;

import java.io.File;

import command.Command;

import logger.Logger;

public class EndOfChain extends CommandChain {
	public EndOfChain() {
		super(null,"",null);
	}

	@Override
	public Command handle(File request) {
		Logger.log("End Of Chain reached for request: " + request.getName());
		return null;
	}

}
