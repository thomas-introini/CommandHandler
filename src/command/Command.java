package command;

import response.Responder;

public interface Command {
	public void execute();
	public void initialize(Object... objects);
	public void setResponder(Responder xmppResponder);
}
