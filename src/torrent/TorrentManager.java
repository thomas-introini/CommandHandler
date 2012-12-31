package torrent;

import java.util.List;

public interface TorrentManager {
	public <T> List<T> getAllTorrents();
	public Torrent addTorrent(String url);
	public <T> List<T> getAllDownloadingTorrents();
}
