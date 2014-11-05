package src;
import java.io.BufferedReader;
//import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class Player {
	private BufferedReader req;
	private PrintStream res;
	
	
	public Player(Socket c) throws IOException {
		req= new BufferedReader(new InputStreamReader(c.getInputStream()));
        res = new PrintStream(c.getOutputStream());
	}
	
	public boolean ready() throws IOException{
		return req.ready();
	}
	
	public String request() throws IOException
	{
		return req.readLine();
	}
	
	public void respond(String r) throws IOException
	{
		res.println("Received" + r);
	}
}