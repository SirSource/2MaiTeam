package src;



import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JTextField;


public class CheckersBoard extends JPanel implements MouseListener {

	
	
	private Icon iconchess = new ImageIcon(getClass().getResource("iconchess.png")); 

	//instance variables 
    private boolean myturn= false;
    private boolean bothready= false;
	
	private int minutesfirstplayer= 15, secondsfirstplayer=1, minutesecondplayer=15, secondssecondplayer=1; //seconds has to start en 1 segundo 
	private int myindex=0; 
	
	private int squarex1, squarex2, squarey1,squarey2;
	private int scorePlayer1=0, scorePlayer2=0;
	private JLabel labelScorePlayer1 = new JLabel("Score:" + scorePlayer1);
	private JLabel labelScorePlayer2 = new JLabel("Score:" + scorePlayer2);
	private JLabel labeltimerplayer1 = new JLabel("15:0");
	private JLabel labeltimerplayer2 = new JLabel("15:0");
	private JLabel turns = new JLabel("Reds Turn");
	private Timer2 timer2 = new Timer2();   // this is the action listener 
	
	private Timer time = new Timer(1000,timer2);   // this is the timer //milliseconds (1 second) for the first player
	private Timer time2 = new Timer(1000, timer2);  // thi sis the timer for the second player 
	
	private Timerindexchecker indexaction = new Timerindexchecker();
	private Timer indextimer = new Timer (1000, indexaction);
	
	private boolean gameRunning=true, redturn = true, firstclick = true;

	private JLabel[] redpieces = new JLabel[12]; 
	private JLabel[] blackpieces = new JLabel[12]; 

	
	private JLayeredPane layeredPanecheckerBoard = new JLayeredPane(); // [ane that contains the the checkerboard and pieces
	private AudioPlayer bjMusic;
	
	//Icons 
	
// button for the music button
	private Icon musiconicon = new ImageIcon(getClass().getResource("sound.png"));
	private Icon musicofficon = new ImageIcon(getClass().getResource("speaker.png"));
	

	// the icons used for the labels
	private ImageIcon redpiecesicon = new ImageIcon(getClass().getResource("redpiece3.png"));
	private ImageIcon blackpiecesicon = new ImageIcon(getClass().getResource("blackpieces2.png"));
	private ImageIcon blackselected = new ImageIcon(getClass().getResource("blackselected.png"));
	private ImageIcon redselected = new ImageIcon(getClass().getResource("redselected.png"));
	
	//icon to store the images 
	private ImageIcon redpiecescircle = new ImageIcon(getClass().getResource("redpiece3.png"));
	private ImageIcon blackpiecescircle = new ImageIcon(getClass().getResource("blackpieces2.png"));
	private ImageIcon blackcircleselected = new ImageIcon(getClass().getResource("blackselected.png"));
	private ImageIcon redcircleselected = new ImageIcon(getClass().getResource("redselected.png"));
	
	private ImageIcon redpiecesstars = new ImageIcon(getClass().getResource("star_red_piece.png"));
	private ImageIcon blackpiecesstars = new ImageIcon(getClass().getResource("star_black_piece.png"));
	private ImageIcon redstarselect = new ImageIcon(getClass().getResource("star_red_selected.png"));
	private ImageIcon blackstarselect = new ImageIcon(getClass().getResource("star_black_selected.png"));
	
	private ImageIcon redpiecesmachinery = new ImageIcon(getClass().getResource("red_machinery_piece.png"));
	private ImageIcon blackpiecesmachinery = new ImageIcon(getClass().getResource("black_machinery_piece.png"));
	private ImageIcon redmachineryselect = new ImageIcon(getClass().getResource("red_machinery_select.png"));
	private ImageIcon blackmachineryselect = new ImageIcon(getClass().getResource("black_machinery_select.png"));

	
	//Instance variable for the chekers board
	private CheckersData checkerBoard = new CheckersData();
	private JButton btnMusic = new JButton(musicofficon);
	
	
	
	//Connection to server
	  
	

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
// 	 @param number of the player 
	public CheckersBoard()  {
	
		bjMusic =  new AudioPlayer ("Ocarina song of time.mp3");
// if player is an even # then myturn variable is true other wise is false
	
		
	
      
		setBackground(new Color(160, 82, 45));
		Icon backgroundicon = new ImageIcon(getClass().getResource("background4.png"));
		setPreferredSize(new Dimension(backgroundicon.getIconWidth(), backgroundicon.getIconHeight()));

		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setPreferredSize(new Dimension(backgroundicon.getIconWidth(), backgroundicon.getIconHeight()));



		Icon checkersboardicon = new ImageIcon(getClass().getResource("checkersboard3.png"));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(139, 69, 19));


		panel.setBounds(195, 39,720, 608);
		layeredPane.add(panel, new Integer(300));

		//checkers board layered panel

		
		panel.add(layeredPanecheckerBoard);
		layeredPanecheckerBoard.setPreferredSize(new Dimension(checkersboardicon.getIconWidth(), checkersboardicon.getIconHeight()));

		JLabel labelcheckerboard = new JLabel(checkersboardicon);
		labelcheckerboard.setBounds(0, 0, checkersboardicon.getIconWidth(), checkersboardicon.getIconHeight());
		layeredPanecheckerBoard.add(labelcheckerboard);
		layeredPanecheckerBoard.addMouseListener(this);
		
	//esto es para inicializar los pieces. 
		
		int a=0, b=0;
		for (int j=0; j<12;j++){

			blackpieces[j] = new JLabel();
			blackpieces[j].setIcon(blackpiecesicon);  
			blackpieces[j].setBounds(getxPixels(b), getyPixels(a), blackpiecesicon.getIconWidth(), blackpiecesicon.getIconHeight());

			layeredPanecheckerBoard.add(blackpieces[j], new Integer(300));
			
			a=a+2;
			if (a>6 && b==0){
				a=1;
				b=1;
			}
			if (a>7 && b==1){
				a=0;
				b=2;
			}
		}

		a=1;  //si es necesario comenzar en 1 o
		b=5;
		for (int j=0; j<12;j++){

			redpieces[j] = new JLabel();
			redpieces[j].setIcon(redpiecesicon);  
			redpieces[j].setBounds(getxPixels(b), getyPixels(a), redpiecesicon.getIconWidth(), redpiecesicon.getIconHeight());
			layeredPanecheckerBoard.add(redpieces[j], new Integer(300));
			a=a+2;
			if (a>7 && b==5){
				a=0;
				b=6;
			}
			if (a>6 && b==6){
				a=1;
				b=7;
			}
		}

		// end of panel of checkers board layered panel 
		//System.out.println(blackpieces[0].getBounds().x);
		
		
		JLabel labelPlayer1 = new JLabel("Player1");
		labelPlayer1.setFont(new Font("Lucida Bright", Font.PLAIN, 22));
		labelPlayer1.setForeground(Color.WHITE);
		labelPlayer1.setBounds(966, 15, 109, 35);
		layeredPane.add(labelPlayer1, new Integer(300));
	


		JLabel labelPlayer2 = new JLabel("Player2");
		labelPlayer2.setForeground(Color.WHITE);
		labelPlayer2.setFont(new Font("Lucida Bright", Font.PLAIN, 22));
		labelPlayer2.setBounds(23, 19, 115, 27);
		layeredPane.add(labelPlayer2, new Integer(300));
		
		
		turns.setForeground(Color.WHITE);
		turns.setFont(new Font("Lucida Bright", Font.PLAIN, 22));
		turns.setBounds(456, 0, 139, 27);
		layeredPane.add(turns);

		JLabel labelTime = new JLabel("Time Remaining:");
		labelTime.setFont(new Font("Lucida Blackletter", Font.PLAIN, 15));
		labelTime.setForeground(Color.WHITE);
		labelTime.setBounds(23, 81, 115, 27);
		layeredPane.add(labelTime);

		JLabel labelTimeRemainingn = new JLabel("Time Remaining: ");
		labelTimeRemainingn.setForeground(Color.WHITE);
		labelTimeRemainingn.setFont(new Font("Lucida Blackletter", Font.PLAIN, 15));
		labelTimeRemainingn.setBounds(944, 87, 131, 27);
		layeredPane.add(labelTimeRemainingn);
		
		
		btnMusic.setBounds(50, 600, 58, 29);


		btnMusic.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if (AudioPlayer.clip.isRunning()){
					btnMusic.setIcon(musiconicon);
					bjMusic.stop();
				}
				else {
					btnMusic.setIcon(musicofficon);
					bjMusic.play();
				}
			}
		});

		layeredPane.add(btnMusic);


		labeltimerplayer2.setForeground(Color.WHITE);
		labeltimerplayer2.setFont(new Font("Lucida Blackletter", Font.PLAIN, 20));
		labeltimerplayer2.setBounds(33, 120, 91, 22);
		layeredPane.add(labeltimerplayer2);

		labeltimerplayer1.setForeground(Color.WHITE);
		labeltimerplayer1.setFont(new Font("Lucida Blackletter", Font.PLAIN, 20));
		labeltimerplayer1.setBounds(983, 115, 92, 27);
		layeredPane.add(labeltimerplayer1);


		//time.start();  // acivate every time is the first players turn
		

	
		labelScorePlayer2.setForeground(Color.WHITE);
		labelScorePlayer2.setFont(new Font("Lucida Blackletter", Font.PLAIN, 18));
		labelScorePlayer2.setBounds(31, 172, 115, 27);
		layeredPane.add(labelScorePlayer2);

	
		labelScorePlayer1.setForeground(Color.WHITE);
		labelScorePlayer1.setFont(new Font("Lucida Blackletter", Font.PLAIN, 18));
		labelScorePlayer1.setBounds(944, 172, 115, 27);
		layeredPane.add(labelScorePlayer1);

		JLabel labelbackground = new JLabel(backgroundicon);
		labelbackground.setBounds(0, 0, backgroundicon.getIconWidth(), backgroundicon.getIconHeight());
		layeredPane.add(labelbackground);




	}
	
	
	
	public void setShape(String shape){
		
	
		
		if (shape == "Stars"){
		

				blackpiecesicon.setImage(blackpiecesstars.getImage());
				redpiecesicon.setImage(redpiecesstars.getImage());
				blackselected.setImage(blackstarselect.getImage());
               redselected.setImage(redstarselect.getImage());

		}
		else if (shape == "Circles"){
			
			redpiecesicon.setImage(redpiecescircle.getImage());
			blackpiecesicon.setImage(blackpiecescircle.getImage());
			blackselected.setImage(blackcircleselected.getImage());
           redselected.setImage(redcircleselected.getImage());

		}
		else if (shape == "Machinery Pieces"){
			
			redpiecesicon.setImage(redpiecesmachinery.getImage());
			blackpiecesicon.setImage(blackpiecesmachinery.getImage());
			blackselected.setImage(blackmachineryselect.getImage());
           redselected.setImage(redmachineryselect.getImage());
		}
		
	}
	
	public void myturn (){
		Client.checkplayer();
		//myindex = Client.index1;
		//System.out.println("fudge!!!"+ Client.getfudge());
		System.out.println("checkerboard index "+ Client.getIndex());
		if (myindex %2 ==0){
			 // este es el timer de verificar si hay dos players 
			bothready= false; 
			myturn = true;
			indextimer.start();
			
		}
		else {
			myturn =  false;
			time.start(); // este es el timer del rojo 
			bothready= true; 
		}
		
		
	}
	
	
	public void moveGUI(int squarey1, int squarex1, int squarey2, int squarex2 ){

		int i=0;
		if (redturn){
			
			i= getRedPiece(squarex1,squarey1);
	        redpieces[i].setBounds(getxPixels(squarex2), getyPixels(squarey2), redpiecesicon.getIconWidth(), redpiecesicon.getIconHeight());
	        redpieces[i].setIcon(redpiecesicon);
	    	redturn=false;
	    	turns.setText("Blacks Turn");
	    	time2.start(); 
			time.stop();
			
			if(myturn == false && squarex1 - 2 == squarex2){
				scorePlayer1 +=1;
				labelScorePlayer1.setText("Score:" + scorePlayer1);
				//removing from the GUI 
				int j =0;
	
				 j = getBlackPiece(checkerBoard.getMiddleCol(squarey1, squarex1, squarey2, squarex2),checkerBoard.getMiddleRow(squarey1, squarex1, squarey2, squarex2));
				 blackpieces[j].setBounds(0, 0, 0, 0);
				 layeredPanecheckerBoard.remove(blackpieces[j]);
				  //removing from the array
				 checkerBoard.remove(checkerBoard.getMiddleRow(squarey1, squarex1, squarey2, squarex2), checkerBoard.getMiddleCol(squarey1, squarex1, squarey2, squarex2));
				
			}
		}
		else { 
	    i=0;
		i= getBlackPiece(squarex1,squarey1);
		blackpieces[i].setIcon(blackpiecesicon);
		blackpieces[i].setBounds( getxPixels(squarex2), getyPixels(squarey2), blackpiecesicon.getIconWidth(),blackpiecesicon.getIconHeight());
		redturn=true;
		turns.setText("Reds Turn");
		time2.stop();
		time.start();
		if(myturn == false && squarex1 + 2 == squarex2){
			scorePlayer2 +=1;
			labelScorePlayer2.setText("Score:" + scorePlayer2);
			int j =0;
		    j = getRedPiece(checkerBoard.getMiddleCol(squarey1, squarex1, squarey2, squarex2),checkerBoard.getMiddleRow(squarey1, squarex1, squarey2, squarex2));
		    redpieces[j].setBounds(0, 0, 0, 0);
		    layeredPanecheckerBoard.remove(redpieces[j]);
		    //removing from the array
		 checkerBoard.remove(checkerBoard.getMiddleRow(squarey1, squarex1, squarey2, squarex2), checkerBoard.getMiddleCol(squarey1, squarex1, squarey2, squarex2));
			
		}
		}
		checkerBoard.moveTo(squarey1, squarex1, squarey2, squarex2);
        myturn = true;
        
        
	}
	
	public int getBlackPiece(int col,int row){
		 
		int x= getxPixels(col), y= getyPixels(row);
		//Rectangle verify = new Rectangle (3,3,65,65);
		for (int j=0; j<12;j++){

            	if (blackpieces[j].getBounds().x ==x && blackpieces[j].getBounds().y==y)
            	{
            		return j;
            	}
            }
			
            
        	return 0;
		}
		
	public int getRedPiece(int col,int row){
		 
		int x= getxPixels(col), y= getyPixels(row);
		//Rectangle verify = new Rectangle (3,3,65,65);
		for (int j=0; j<12;j++){

            	if (redpieces[j].getBounds().x ==x && redpieces[j].getBounds().y==y)
            	{
            		return j;
            	}
            }
			
            
        	return 0;
		}
		
	
	
	
	/*
	 * gets the square x (col)
	 * @param the pixels of x   (remember is respect to the checkers panel layered panel which is the chekers panel)
	 * 	  @return x square position
	 */
	public int getxSquare(int col2){
		int col=0;
		int a=0, b=75;

		for (int i =0; i<8; i++){


			if (col2 >= a && col2<= b){

				col = i ;
			}
			a=b;
			b+= 74;

		}
		return col;

	}
	/*
	 * gets the square y   (row)
	 * @param the pixels  of y (remember is respect to the checkers panel layered panel which is the checkers panel)\
	 * @return y square position 
	 */
	public  int getySquare(int row2){

		int  row=0;
		int a=0;
		int  b=75;
		for (int i =0; i<8; i++){


			if (row2 >= a && row2<= b){

				row = i ;
			}
			a=b;
			b+= 75;

		}

		return row;

	}
	/*
	 * this class if convienient for the movement and the redrawings
	 * gets the square x pixels (col)  t
	 * @param the square of x   (remember is respect to the checkers panel layered panel which is the chekers panel)
		 * @return the pixels of the left most part of the square  x position 
	 */
	public int getxPixels(int squarex){

		int a=3;

		for (int i =0; i<8; i++){


			if (squarex==i){

				return a;
			}

			a+= 74;

		}
		return a;


	}
	
	/*
	 * this class if convienient for the movement and the redrawings
	 * gets the square y pixels (row)  
	 * @param the square of y   (remember is respect to the checkers panel layered panel which is the chekers panel)
	 * @return the pixels of the left most part of the square y position 
	 */

	public  int getyPixels(int squarey){

		int a=3;

		for (int i =0; i<8; i++){


			if (squarey==i){

				return a;
			}

			a+= 75;

		}
		return a;


	}



	public void mousePressed(MouseEvent e) {
	
		if (gameRunning){
			int col2 = e.getX();
			int row2 = e.getY();
			int squarey= getySquare(row2);  /// this will be the variables that will be checked to verify of there is something or if the move is valid
			int squarex= getxSquare(col2);    /// this will be the variables that will be checked to verify of there is something or if the move is valid
//			System.out.println(squarex+"-"+ squarey);
			// x = colums y= rows
			if (firstclick && myturn){

			if (checkerBoard.thereIs(squarey, squarex)==1 && redturn== false){
				squarex1= squarex;
				squarey1= squarey;
				
				
					int i=0;
					i= getBlackPiece(squarex1,squarey1);
					blackpieces[i].setIcon(blackselected);
				    firstclick = false;
				
			}
			
			else if (checkerBoard.thereIs(squarey, squarex)==3 && redturn){
				squarex1= squarex;
				squarey1= squarey;
				
				int i=0;
				i= getRedPiece(squarex1,squarey1);
		        redpieces[i].setIcon(redselected);
		
	
				firstclick = false;
			}
//
			}//end of first click
			
			else if (firstclick == false){   //this is the second click
			
				if (checkerBoard.legalMove(squarey1, squarey, squarex1, squarex))
				{
					squarex2= squarex;
					squarey2 = squarey;
				
					if (checkerBoard.thereIs(squarey1, squarex1)==1){

				moveGUI(squarey1, squarex1, squarey2, squarex2);
				myturn = false;
				//redturn=true;
					}  
					
					else if (checkerBoard.thereIs(squarey1, squarex1)==3){

				moveGUI(squarey1, squarex1, squarey2, squarex2);
				myturn = false;
				
					}
					
					//checkerBoard.moveTo(squarey1, squarex1, squarey2, squarex2);
					
			
					try {

						Client.sendMove(squarey1, squarex1, squarey2, squarex2);
					
						
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					
					firstclick = true;	
				}
				
				//estos dos  if de abajo es por si el jugador se arrepiente y vuelve a dar click en una de sus piezas 
				//pues que esa pieza la trate como i fuera su primer click
				else if (checkerBoard.thereIs(squarey, squarex)==1 & redturn==false)
				{
					int i=0;
					i= getBlackPiece(squarex1,squarey1);
					blackpieces[i].setIcon(blackpiecesicon);
					squarex1= squarex;
					squarey1= squarey;
					i= getBlackPiece(squarex1,squarey1);
					blackpieces[i].setIcon(blackselected);
				}
				else if (checkerBoard.thereIs(squarey, squarex)==3 & redturn)
				{
					
					int i=0;
					i= getRedPiece(squarex1,squarey1);
					redpieces[i].setIcon(redpiecesicon);
					squarex1= squarex;
					squarey1= squarey;
					i= getRedPiece(squarex1,squarey1);
					redpieces[i].setIcon(redselected);
				}
				
				
				//este es el if que vamos a usar para comer
				
				else if (checkerBoard.canJump(squarey1, squarex1, squarey, squarex)){
					
					
				squarex2= squarex;
				squarey2 = squarey;

				if (checkerBoard.thereIs(squarey1, squarex1)==1){
				moveGUI(squarey1, squarex1, squarey2, squarex2);
				
				try {

					Client.sendMove(squarey1, squarex1, squarey2, squarex2);
				
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
				//removing from the GUI 
			     int j =0;
			    j = getRedPiece(CheckersData.midcol,CheckersData.midrow);
			    redpieces[j].setBounds(0, 0, 0, 0);
			    layeredPanecheckerBoard.remove(redpieces[j]);
			    //removing from the array
			 checkerBoard.remove(CheckersData.midrow, CheckersData.midcol);
	
			 scorePlayer2++;
			 labelScorePlayer2.setText("Score:" + scorePlayer2);
//			
				}  
				
				else if (checkerBoard.thereIs(squarey1, squarex1)==3){

			moveGUI(squarey1, squarex1, squarey2, squarex2);
			
			try {

				Client.sendMove(squarey1, squarex1, squarey2, squarex2);
			
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
			//removing from the GUI 
			int j =0;
			 j = getBlackPiece(CheckersData.midcol,CheckersData.midrow);
			 blackpieces[j].setBounds(0, 0, 0, 0);
			 layeredPanecheckerBoard.remove(blackpieces[j]);
			 
			 
			  //removing from the array
			 checkerBoard.remove(CheckersData.midrow, CheckersData.midcol);
			 scorePlayer1++;
			 labelScorePlayer1.setText("Score:" + scorePlayer1);
				
		
				}
				
				//checkerBoard.moveTo(squarey1, squarex1, squarey2, squarex2);
				firstclick = true;
				myturn = false;
				
				}    //end of jump 
			
			}  // end of second click 
		
	  
		}
		else {
             // Do not do anything because game is not running or is not your turn
		}
	}


	public void mouseReleased(MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited(MouseEvent evt) { }

/*
 * class for timer of each timer 
 */

	public class Timer2 implements ActionListener{


		public void actionPerformed(ActionEvent e) {
			
			if (gameRunning){
	
			if (e.getSource().equals(time)){
				secondsfirstplayer--;
			
				if (secondsfirstplayer==0 && minutesfirstplayer>=0){
					minutesfirstplayer--;
					secondsfirstplayer=59;
				}
				
				 if (minutesfirstplayer<0){
					 
					//aqui podemos poner que cree un Frame que diga que el otro jugador gan�
					minutesfirstplayer=0;
					secondsfirstplayer=0;
					gameRunning=false;
				}
				
				String s4 = String.valueOf(minutesfirstplayer);
				String s3 = String.valueOf(secondsfirstplayer); 

				labeltimerplayer1.setText(s4+":"+s3);
			}

			if (e.getSource().equals(time2)){
				secondssecondplayer--;
				if (secondssecondplayer==0 && minutesecondplayer>=0){
					minutesecondplayer--;
					secondssecondplayer=59;
				}
				 if (minutesecondplayer<0) {
					 
					 //aqui podemos poner que cree un Frame que diga que el otro jugador gan�
					minutesecondplayer=0;
					secondssecondplayer=0;
					gameRunning=false;
				}
				String s4 = String.valueOf(minutesecondplayer);
				String s3 = String.valueOf(secondssecondplayer); 

				labeltimerplayer2.setText(s4+":"+s3);
			}

		}  // end game is running 
			
			else {
				// don't count anymore 	`
				time2.stop();
				time.stop();
			}
		}   

	}//end of class timer
	
	public class Timerindexchecker implements ActionListener{


		public void actionPerformed(ActionEvent e) {
			Client.checkplayer();
			//int currentindex= Client.index1;
			
			if (myindex < Client.getIndex()){
				indextimer.stop();	
				bothready = true; 
				time.start();
			}
			
			
		
		}
		
		
	
	}
	
		
	
	
}
