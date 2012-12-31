package torrent;

import org.json.JSONObject;

import ca.benow.transmission.model.TorrentStatus;


public class TorrentInfo extends TorrentStatus implements Torrent{

	public static TorrentField[] defaultFields = new TorrentField[] { TorrentField.id, TorrentField.name, TorrentField.status,
	      TorrentField.percentDone, TorrentField.eta,TorrentField.rateDownload };
	
	public TorrentInfo(JSONObject jsonObject) {
		super(jsonObject);
	}

	public int getId() {
		return (Integer) getField(TorrentField.id);
	}

	public double getPercentDone() {
		return (double)getField(TorrentField.percentDone);
	}

	public String getStatus() {
		return String.valueOf(getField(TorrentField.status));
	}

	public String getName() {
		return (String) getField(TorrentField.name);
	}

	@Override
	public String getDownloadRate() {
		return (String) getField(TorrentField.rateDownload);
	}

}
