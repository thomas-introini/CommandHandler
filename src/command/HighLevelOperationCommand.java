package command;


import response.Responder;
import settings.HighLevelOperationSettings;

public class HighLevelOperationCommand implements Command {
	private Command consoleCommand;
	
	@Override
	public void execute() {
		consoleCommand.execute();
	}

	@Override
	public void initialize(Object... objects) {
		if(objects.length<2) throw new IllegalArgumentException("Too Few Parameters!");
		System.out.println(objects[1]);
		String[] param=new String[]{HighLevelOperationSettings.m.getProperty(objects[0].toString(), "")+" "+HighLevelOperationSettings.t.getProperty(objects[1].toString(), "")};
		consoleCommand=new ConsoleCommand();
		consoleCommand.initialize((Object[])param);

	}

	@Override
	public void setResponder(Responder xmppResponder) {
		consoleCommand.setResponder(xmppResponder);
	}

}
