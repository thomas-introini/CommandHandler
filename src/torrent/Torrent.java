package torrent;

public interface Torrent {
	public int getId();
	public double getPercentDone();
	public String getStatus();
	public String getName();
	public String getDownloadRate();
}
