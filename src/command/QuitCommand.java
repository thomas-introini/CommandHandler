package command;

import main.CommandHandler;
import response.Responder;

public class QuitCommand implements Command {
	private Responder responder;
	@Override
	public void execute() {
		responder.send("Shutting down...");
		CommandHandler.shutdown();
	}

	@Override
	public void initialize(Object... objects) {

	}

	@Override
	public void setResponder(Responder xmppResponder) {
		this.responder=xmppResponder;
	}

}
