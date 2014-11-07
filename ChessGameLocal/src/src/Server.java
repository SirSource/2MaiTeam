package src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class Server 
{
	private ServerSocket s;
	private Player p[]=new Player[10];
	private static int num=-1;
	//public static int quePlayer;
	
	//private ArrayData board;
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

	public void serve()
	{
		Socket c;
		while (true)
			try {
				try {
					s.setSoTimeout(100);
					c = s.accept();
					p[++num]=new Player(c);
					//quePlayer = 1 + quePlayer;
					System.out.println("player "+(num+1)+" entereded");
				}
				catch (SocketTimeoutException e)
				{
					for (int i=0; i<=num; i++)
					{
						if (p[i].ready())
						{
					
							String MESSAGE = p[i].request();
							System.out.println(MESSAGE);

						if (MESSAGE.equals("indexofplayer")){
							System.out.println(num+" entereded");
							p[i].getPlayer(num);
							
						}
						
						else 	if (i%2==0) 
							{
							//	System.out.println("we r even");
								//p[i].respond(MESSAGE);
								p[i+1].respond(MESSAGE);


							}

							else
							{
							//	System.out.println("we are odd");
							//	p[i].respond(MESSAGE);
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