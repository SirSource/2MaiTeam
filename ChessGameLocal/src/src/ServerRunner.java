package src;

public class ServerRunner {
	/**
	 * Main method it runs the server.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("server is running");
		Server s = new Server();
		s.serve();
		System.out.println("server is NOT");

	}

}