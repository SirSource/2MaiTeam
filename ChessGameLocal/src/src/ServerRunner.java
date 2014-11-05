package src;
public class ServerRunner {
	
	public static void main(String[] args) {
		System.out.println("server is running");
		Server s=new Server();
		s.serve();
		System.out.println("server is NOT");

	}

}