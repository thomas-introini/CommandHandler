package command;

import java.util.Iterator;
import java.util.List;

import torrent.Torrent;



public class StatusTorrentCommand extends TorrentCommand implements Command {

	@Override
	public void execute() {
		List<Torrent> lt=tc.getAllDownloadingTorrents();
		if(lt.size()==0){
			responder.send("No downloading torrent or no service avaiable");
			return;
		}
		Iterator<Torrent> i = lt.iterator();
		while (i.hasNext()){
			Torrent t=(Torrent)i.next();
			System.out.println(t);
			responder.send(t.getName()+" "+t.getPercentDone()*100+"%");
		}
		
	}

}
