package torrent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logger.Logger;

import settings.Settings;

import ca.benow.transmission.TransmissionClient;
import ca.benow.transmission.model.AddedTorrentInfo;
import ca.benow.transmission.model.TorrentStatus;
import ca.benow.transmission.model.TorrentStatus.TorrentField;

public class TorrentClient implements TorrentManager {
	TransmissionClient tc;

	public TorrentClient() {
		try {
			URL url = new URL(Settings.getURL());
			tc = new TransmissionClient(url);
		} catch (MalformedURLException e1) {
			System.out.println(e1.getMessage());
			Logger.log(e1.getMessage());

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Torrent> getAllTorrents() {
		List<Torrent> torrents = null;
		try {
			torrents = convert(tc.getAllTorrents());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			Logger.log(e.getMessage());

		}
		return torrents;
	}

	@Override
	public Torrent addTorrent(String url) {
		AddedTorrentInfo added;
		Torrent ts = null;
		try {
			added = tc.addTorrent(Settings.getDownloadDir(), url, false, 1000,
					TransmissionClient.PRIORITY_HIGH, null, null, null, null,
					null);
			ts = new TorrentInfo(added.obj);
			Logger.log("Added Torrent " + added.getId() + " " + added.getName());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			Logger.log(e.getMessage());

		}
		return ts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TorrentInfo> getAllDownloadingTorrents() {
		List<Torrent> list;
		Iterator<Torrent> torrents;
		List<TorrentInfo> downloading;
		list = getAllTorrents();
		if (list == null)
			return null;
		torrents = list.iterator();
		downloading = new ArrayList<TorrentInfo>();
		while (torrents.hasNext()) {
			TorrentInfo t = (TorrentInfo) torrents.next();
			if ((Integer) t.getField(TorrentField.status) == TorrentStatus.STATUS_DOWNLOADING) {
				TorrentInfo ts = new TorrentInfo(t.getJSONObject());
				downloading.add(ts);
			}
		}
		return downloading;
	}

	protected List<Torrent> convert(List<TorrentStatus> torrents) {
		List<Torrent> info = new ArrayList<Torrent>();
		Iterator<TorrentStatus> i = torrents.iterator();
		while (i.hasNext())
			info.add(new TorrentInfo(i.next().getJSONObject()));
		return info;
	}

}
