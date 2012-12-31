package command;

import response.*;
import torrent.*;

public abstract class TorrentCommand implements Command {
	
	TorrentManager tc;
	Responder responder;
	
	@Override
	public abstract void execute();

	@Override
	public void initialize(Object... objects) {
		tc=new TorrentClient();
	}
	
	public void setResponder(Responder responder){
		this.responder=responder;
	}
	
}
