package response;

public interface Responder {
	public void initialize(Object...objects);
	public void initialize();
	public void send(String message);
}
