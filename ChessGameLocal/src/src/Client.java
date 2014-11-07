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
	private static String m;
	private static int y1,x1,y2,x2;
	public static int index1 =0;
	private static boolean index = false;
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

			
					 m = res.readLine();
					//Aqui descomponen m en 4 numeros tambien pueden enviar m
					//al metodo y que sea el metodo el que lo descomponga. Como
					//ustedes quieran.
				//	System.out.println(m);
					//try {
					 
					 
					 if (index){
						 index1 = Integer.parseInt(m);
					 }
					 else {
					 y1 = Integer.parseInt(m.substring(0, 1));
					 x1 = Integer.parseInt(m.substring(1, 2));
					 y2 = Integer.parseInt(m.substring(2, 3));
					 x2 = Integer.parseInt(m.substring(3, 4));
					fetchMove(y1, x1, y2, x2);
					}
					 index =false;
					//}
					
//					catch (Run e) {
//						
//					}
					
					//aqui va el metodo de mover el GUI
				}
			
			catch (IOException e) {
					e.printStackTrace();
					
				}
		}
	}
	//
	public static void sendMove(int a, int b, int c, int d) throws UnknownHostException, IOException{


		msgToDecode = String.valueOf(a) + String.valueOf(b) + String.valueOf(c) + String.valueOf(d);
		req.println(msgToDecode);
		

	}
	
	public static void checkplayer(){
		req.println("indexofplayer");
		index = true;
		
	}


	public static void fetchMove(int y1, int x1, int y2, int x2 ) throws IOException{

			IntroCheckers.checkerboard.moveGUI(y1, x1, y2, x2);
			

	}
	
}