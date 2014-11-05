package src;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client {
	public static Socket s = null ;
	
	static int i;
	static BufferedReader res=null;
	static PrintStream req=null;
	//static String msgToRecieve = res.readLine();
	
	private static String msgToDecode;
public Client () throws UnknownHostException, IOException{
	s = new Socket("localhost", 7654);

}
	static class PrintThread extends Thread {
		@Override
		public void run() {
			while (true)
				try {
					// aqui es donde llega el mensaje del server
					
					if (res.ready()) System.out.println("Server's respond: " + res.readLine());
					
					//aqui va el metodo de mover el GUI
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
//
	public static void sendMove(int a, int b, int c, int d) throws UnknownHostException, IOException{
		
		try {
			res=new BufferedReader(new InputStreamReader(s.getInputStream()));
		}
		catch (IOException e) {
			System.out.println(e);
		}

		try {
			req = new PrintStream(s.getOutputStream());
		}
		catch (IOException e) {
			System.out.println(e);
		}

		new PrintThread().start();
		//while (true) {
	
			msgToDecode = String.valueOf(a) + String.valueOf(b) + String.valueOf(c) + String.valueOf(d);
			req.println(msgToDecode);
	//}	
			
	}
	
	public static void fetchMove() throws IOException{
		String youSaid = res.readLine();
		System.out.println(youSaid);
		int y1 = Integer.parseInt(youSaid.substring(0, 1));
		int x1 = Integer.parseInt(youSaid.substring(1, 2));
		int y2 = Integer.parseInt(youSaid.substring(2, 3));
		int x2 = Integer.parseInt(youSaid.substring(3, 4));
		//Llamar metodo MOVEGUI
		
		
		if(msgToDecode.equals(youSaid)==false){
		//hacer el movimiento en el Array
			IntroCheckers.checkerboard.moveGUI(y1, x1, y2, x2);
//			
		}
		else {
			//nada pasa por que eres tu mismo. 
		}
	}
}