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
	public static int quePlayer;
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
					quePlayer = 1 + quePlayer;
					System.out.println("player "+(num+1)+" entereded");
				}
				catch (SocketTimeoutException e)
				{
					for (int i=0; i<=num; i++)
					{
						if (p[i].ready())
						{
							//String r=p[i].request();
							//int j=1;
							//int m,n;
							//board = new ArrayData();
							String MESSAGE = p[i].request();
							System.out.println(MESSAGE);
//							int fromRow = Integer.parseInt(MESSAGE.substring(0, 1));
//							int fromCol = Integer.parseInt(MESSAGE.substring(1, 2));
//							int toRow = Integer.parseInt(MESSAGE.substring(2, 3));
//							int toCol = Integer.parseInt(MESSAGE.substring(3, 4));
							//ArrayData.moveTo(fromRow, fromCol, toRow, toCol);
						
						
							if (i%2==0) 
							{
								System.out.println("we r even");
								p[i].respond(MESSAGE);
								p[i+1].respond(MESSAGE);


							}

							else
							{
								System.out.println("we are odd");
								p[i].respond(MESSAGE);
								p[i-1].respond(MESSAGE);	
							}
							/*int j=1;
							if (i%2==0) j=0;
							for( ; j<=num; j+=2) p[j].respond(MESSAGE);*/
//							for(m=0; m<8; m++)
//							{
//								for(n=0;n<8;n++)
//								{
//									//int sa= board.board[m][n];
//									//System.out.print("  "+sa);
//								}
//								//System.out.println();
//
//							}
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