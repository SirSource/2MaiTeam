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
	private static String msgToDecode;
	private static String serverMessage;
	private static int fromRow,fromCol,toRow,toCol;
	public int indexplayer=2 ;
	private static boolean lookingForPlayer = false;
	public Client () throws UnknownHostException, IOException{
		s = new Socket("localhost", 7654);
		try {
			res=new BufferedReader(new InputStreamReader(s.getInputStream()));
		}
		catch (IOException e) {
			System.out.println(e);
			
			s.close();
		}

		try {
			req = new PrintStream(s.getOutputStream());
		}
		catch (IOException e) {
			System.out.println(e);
			s.close();
		}
		
		
	PrintThread thread = 	new PrintThread();
	thread.start();
		
	}
	class PrintThread extends Thread {
		@Override
		public void run() {
			while (true)
				try {
					// aqui es donde llega el mensaje del server

			
					 serverMessage = res.readLine();
		
					 if (lookingForPlayer){
							System.out.println("Server response"+ serverMessage);
							indexplayer = Integer.parseInt(serverMessage);
							setIndex(Integer.parseInt(serverMessage));
					
					 }
					 
					 else {
					 fromRow = Integer.parseInt(serverMessage.substring(0, 1));
					 fromCol = Integer.parseInt(serverMessage.substring(1, 2));
					 toRow = Integer.parseInt(serverMessage.substring(2, 3));
					 toCol = Integer.parseInt(serverMessage.substring(3, 4));
					fetchMove(fromRow, fromCol, toRow, toCol);
					}
					 lookingForPlayer =false;

				}
			
			catch (IOException e) {
					e.printStackTrace();
					
				}
		}
	}
	//
	
	
	public int getIndex( ){
	
		return indexplayer;
		
	}
	
	public void setIndex(int i ){
		indexplayer = i;
	}
	
	public static void sendMove(int a, int b, int c, int d) throws UnknownHostException, IOException{


		msgToDecode = String.valueOf(a) + String.valueOf(b) + String.valueOf(c) + String.valueOf(d);
		req.println(msgToDecode);
		

	}
	
	public static void checkplayer(){
		req.println("indexofplayer");
		lookingForPlayer = true;
		
		
	}
	
	public static void checkClosed(){
		req.println("GotDisconnected");
	}



	public static void fetchMove(int fromRow, int fromCol, int toRow, int toCol ) throws IOException{

			IntroCheckers.checkerboard.moveGUI(fromRow, fromCol, toRow, toCol);
			

	}
	
	
}