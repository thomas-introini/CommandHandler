package command;

import torrent.Torrent;

public class AddTorrentCommand extends TorrentCommand implements Command {

	String urlToAdd;

	public AddTorrentCommand() {
		super();
	}

	@Override
	public void initialize(Object... objects) {
		super.initialize(objects);
		if (objects.length > 0)
			urlToAdd = (String) objects[0];
	}

	@Override
	public void execute() {
		Torrent add = tc.addTorrent(urlToAdd);
		if (add != null)
			responder.send(add.getName() + " added!");
		else
			responder.send("No torrent added!");
	}

}
