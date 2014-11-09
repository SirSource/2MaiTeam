package src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
/**
 * This 
 */

public class Server 
{
	private ServerSocket s;
	private  Player p[]=new Player[10];
	private static int num=-1;
	//public static int quePlayer;
	
	//private ArrayData board;
	/**
	 * Creates a new ServerSocket and handles Exceptions.
	 */
	public Server()
	{
		try 
		{

			s=new ServerSocket(7654);

		}
		catch (IOException e) 
		{
			System.out.println(e);
		}

	}
/**
 * Method that tells the server what to do, like accepting client connections
 */
	public void serve()
	{
		Socket c;
		while (true)
			try {
				try {
					s.setSoTimeout(100);
					c = s.accept(); //Accepts the connection
					
					p[++num]= new Player(c); //Creates a new player
					//quePlayer = 1 + quePlayer;
					System.out.println("player "+(num+1)+" entereded");
				}
				catch (SocketTimeoutException e)
				{
					for (int i=0; i<=num; i++)
					{
						if (p[i].ready() )
						{
					// Receives the message from client
							String MESSAGE = p[i].request();
							System.out.println(MESSAGE);

						if (MESSAGE.equals("indexofplayer")){
							System.out.println(num+" entereded");
							p[i].getPlayer(num);
							
						}
						
						else 	if (i%2==0) 
							{
							// Server responds to other players
								p[i+1].respond(MESSAGE);


							}

							else
							{
						//Respond to other player
								p[i-1].respond(MESSAGE);	
							}

						}
					}
				}

			}	

		catch (IOException e) 
		{

			System.out.println("here "+e);
		}





	}
}