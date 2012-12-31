package command;

import java.io.IOException;
import java.io.InputStream;

import response.Responder;

public class ConsoleCommand implements Command {
	private String command;
	private Responder responder;

	@Override
	public void execute() {
		Process p;
		int c;
		String error = "", s = "";
		if (command.contains("rm"))
			return;
		try {
			System.out.println("Command : " + command);
			responder.send(command);

			p = Runtime.getRuntime().exec(command.trim() + " &");
			p.waitFor();

			System.out.println(p.exitValue());
			InputStream fis = p.getInputStream();
			while ((c = fis.read()) != -1)
				s += (char) c;
			System.out.println(s);
			InputStream err = p.getErrorStream();
			while ((c = err.read()) != -1)
				error += (char) c;
			System.out.println("Err: " + error);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			// responder.send("Console Command aborted! " + e.getMessage());
		}

	}

	@Override
	public void initialize(Object... objects) {
		this.command = objects[0].toString();
	}

	@Override
	public void setResponder(Responder xmppResponder) {
		this.responder = xmppResponder;
	}

}
