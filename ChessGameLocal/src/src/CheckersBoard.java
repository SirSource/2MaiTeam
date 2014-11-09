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
import javax.swing.JFrame;
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










public class CheckersBoard extends JPanel implements MouseListener {



	private Icon iconchess = new ImageIcon(getClass().getResource("iconchess.png")); 

	//instance variables 



	private int minutesfirstplayer=10 , secondsfirstplayer=10, minutesecondplayer=10, secondssecondplayer=10; //seconds has to start en 1 segundo 
	private int myindex;
	private int fromCol, toCol, fromRow ,toRow;
	private int scorePlayer1=0, scorePlayer2=0;
	private JLabel labelScorePlayer1 = new JLabel("Score:" + scorePlayer1);
	private JLabel labelScorePlayer2 = new JLabel("Score:" + scorePlayer2);
	private JLabel labeltimerplayer1 = new JLabel("15:0");
	private JLabel labeltimerplayer2 = new JLabel("15:0");
	private JLabel turns = new JLabel("My turn");
	private JLabel labelIam = new JLabel("I am Red");


	private TimerListener timer2 = new TimerListener();   // this is the action listener 

	private Timer time = new Timer(1000,timer2);   // this is the timer //milliseconds (1 second) for the first player
	private Timer time2 = new Timer(1000, timer2);  // thi sis the timer for the second player 

	private TimerIndexListener indexaction = new TimerIndexListener();
	private Timer indextimer = new Timer (1000, indexaction);

	private boolean gameRunning=true, redturn = true, firstclick = true, bothready= false, myturn= false;

	private JLabel[] redpieces = new JLabel[12]; 
	private JLabel[] blackpieces = new JLabel[12]; 


	private JLayeredPane layeredPanecheckerBoard = new JLayeredPane(); // ane that contains the the checkerboard and pieces
	private AudioPlayer music;
	private AudioPlayer moveSoundEffect;

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
	private JButton buttonMusic = new JButton(musicofficon);



	//Connection to server



	/**
	 * Create the board panel. 
	 */

	public CheckersBoard()  {

	music =  new AudioPlayer ("Ocarina song of time.mp3");
	moveSoundEffect = new AudioPlayer("Small item catch.mp3");


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


	panel.add(layeredPanecheckerBoard);
	layeredPanecheckerBoard.setPreferredSize(new Dimension(checkersboardicon.getIconWidth(), checkersboardicon.getIconHeight()));

	JLabel labelcheckerboard = new JLabel(checkersboardicon);
	labelcheckerboard.setBounds(0, 0, checkersboardicon.getIconWidth(), checkersboardicon.getIconHeight());
	layeredPanecheckerBoard.add(labelcheckerboard);
	layeredPanecheckerBoard.addMouseListener(this);

	//  Intialize the black checker pieces. 

	int row=0, col=0;
	for (int j=0; j<12;j++){

	blackpieces[j] = new JLabel();
	blackpieces[j].setIcon(blackpiecesicon);  
	blackpieces[j].setBounds(getColPixels(col), getRowPixels(row), blackpiecesicon.getIconWidth(), blackpiecesicon.getIconHeight());
	layeredPanecheckerBoard.add(blackpieces[j], new Integer(300));

	row=row+2;
	if (row>6 && col==0){
	row=1;
	col=1;
	}
	if (row>7 && col==1){
	row=0;
	col=2;
	}
	}

	//  Intialize the Red checker pieces. 
	row=1;  //si es necesario comenzar en 1 
	col=5;
	for (int j=0; j<12;j++){

	redpieces[j] = new JLabel();
	redpieces[j].setIcon(redpiecesicon);  
	redpieces[j].setBounds(getColPixels(col), getRowPixels(row), redpiecesicon.getIconWidth(), redpiecesicon.getIconHeight());
	layeredPanecheckerBoard.add(redpieces[j], new Integer(300));
	row=row+2;
	if (row>7 && col==5){
	row=0;
	col=6;
	}
	if (row>6 && col==6){
	row=1;
	col=7;
	}
	}

	


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
	turns.setBounds(429, 0, 260, 27);
	layeredPane.add(turns);

	JLabel labelTime = new JLabel("Time Remaining:");
	labelTime.setFont(new Font("Lucida Blackletter", Font.PLAIN, 15));
	labelTime.setForeground(Color.WHITE);
	labelTime.setBounds(23, 81, 115, 27);
	layeredPane.add(labelTime);

	JLabel labelTimeRemaining = new JLabel("Time Remaining: ");
	labelTimeRemaining.setForeground(Color.WHITE);
	labelTimeRemaining.setFont(new Font("Lucida Blackletter", Font.PLAIN, 15));
	labelTimeRemaining.setBounds(944, 87, 131, 27);
	layeredPane.add(labelTimeRemaining);


	buttonMusic.setBounds(50, 600, 58, 29);
	buttonMusic.addActionListener(new ActionListener(){

	public void actionPerformed(ActionEvent e) {
	if (AudioPlayer.clip.isRunning()){
	buttonMusic.setIcon(musiconicon);
	music.stop();
	}
	else {
	buttonMusic.setIcon(musicofficon);
	music.play();
	}
	}
	});
	layeredPane.add(buttonMusic);


	labeltimerplayer2.setForeground(Color.WHITE);
	labeltimerplayer2.setFont(new Font("Lucida Blackletter", Font.PLAIN, 20));
	labeltimerplayer2.setBounds(33, 120, 91, 22);
	layeredPane.add(labeltimerplayer2);

	labeltimerplayer1.setForeground(Color.WHITE);
	labeltimerplayer1.setFont(new Font("Lucida Blackletter", Font.PLAIN, 20));
	labeltimerplayer1.setBounds(983, 115, 92, 27);
	layeredPane.add(labeltimerplayer1);
	labelIam.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	labelIam.setForeground(Color.WHITE);



	labelIam.setBounds(533, 650, 122, 16);
	layeredPane.add(labelIam);

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

	/*
	 * Method for setting the shapes in the game. 
	 */
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


	/*
	 * Method that checks if it's the turn of the player and sets up the game according the index of the player
	 * 
	 */
	public void myturn (){
	IntroCheckers.client.checkplayer();  


	try{
	Thread.sleep(1000);  // thread for the delay 

	}
	catch(InterruptedException ex){
	Thread.currentThread().interrupt();
	}

	myindex= IntroCheckers.client.getIndex();  // the index of the player


	if (myindex%2 == 0){

	bothready= false; 
	myturn = true;
	turns.setText("My Turn");
	labelIam.setText("I am Red");
	indextimer.start();
	time.stop();

	}
	else {
	myturn =  false;
	turns.setText("Other's Player Turn");
	labelIam.setText("I am Black");
	time.start(); // este es el timer del rojo 
	bothready= true; 

	}
	//	}



	}

	/*
	 * Move piece from player's GUI. 
	 * @param fromRow
	 * @param  fromCol
	 * @param to Row
	 * @param to Col 
	 */


	public void moveGUI(int fromRow, int fromCol, int toRow, int toCol ){

	int i=0;
	if (redturn){

	i= getRedPiece(fromCol,fromRow);
	redpieces[i].setBounds(getColPixels(toCol), getRowPixels(toRow), redpiecesicon.getIconWidth(), redpiecesicon.getIconHeight());
	redpieces[i].setIcon(redpiecesicon);
	redturn=false;

	time2.start(); 
	time.stop();

	if(myturn == false && fromCol - 2 == toCol){

	turns.setText("Other's Player Turn");
	scorePlayer1 +=1;
	labelScorePlayer1.setText("Score:" + scorePlayer1);
	//removing the piece from the GUI 
	int j =0;

	j = getBlackPiece(checkerBoard.getMiddleCol(fromRow, fromCol, toRow, toCol),checkerBoard.getMiddleRow(fromRow, fromCol, toRow, toCol));
	blackpieces[j].setBounds(0, 0, 0, 0);
	layeredPanecheckerBoard.remove(blackpieces[j]);
	//removing piece from the array
	checkerBoard.remove(checkerBoard.getMiddleRow(fromRow, fromCol, toRow, toCol), checkerBoard.getMiddleCol(fromRow, fromCol, toRow, toCol));

	}
	}
	else { 
	i=0;
	i= getBlackPiece(fromCol,fromRow);
	blackpieces[i].setIcon(blackpiecesicon);
	blackpieces[i].setBounds( getColPixels(toCol), getRowPixels(toRow), blackpiecesicon.getIconWidth(),blackpiecesicon.getIconHeight());
	redturn=true;
	time2.stop();
	time.start();
	if(myturn == false && fromCol + 2 == toCol){
	turns.setText("My Turn");
	scorePlayer2 +=1;
	labelScorePlayer2.setText("Score:" + scorePlayer2);
	int j =0;
	j = getRedPiece(checkerBoard.getMiddleCol(fromRow, fromCol, toRow, toCol),checkerBoard.getMiddleRow(fromRow, fromCol, toRow, toCol));
	redpieces[j].setBounds(0, 0, 0, 0);
	layeredPanecheckerBoard.remove(redpieces[j]);
	//removing from the array
	checkerBoard.remove(checkerBoard.getMiddleRow(fromRow, fromCol, toRow, toCol), checkerBoard.getMiddleCol(fromRow, fromCol, toRow, toCol));

	}





	}
	checkerBoard.moveTo(fromRow, fromCol, toRow, toCol);

	turns.setText("My Turn");
	myturn = true;


	}

	public int getBlackPiece(int col,int row){

	int x= getColPixels(col), y= getRowPixels(row);
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

	int x= getColPixels(col), y= getRowPixels(row);
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
	 * Gets the square Col location
	 * @param col2 -- The pixels of the Col   
	 * @return col-- The col square position
	 */
	public int getColSquare(int col2){
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
	 * Gets the square Row location.
	 * @param row2 -- The pixels of the Row   
	 * @return row -- The Row square position
	 */
	public  int getRowSquare(int row2){

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
	 * Gets the square Col pixels 
	 * @param squarex --The Col square   (remember is respect to the checkers panel layered panel which is the chekers panel)
	 * @return xpixel -- The pixels of the left most part of the square 
	 */
	public int getColPixels(int squarex){

	int xpixel=3;

	for (int i =0; i<8; i++){


	if (squarex==i){

	return xpixel;
	}

	xpixel+= 74;

	}
	return xpixel;


	}

	/*
	 * Gets the square Row pixels 
	 * @param The Row square   (remember is respect to the checkers panel layered panel which is the chekers panel)
	 * @return ypixel -- The pixels of the top most part of the square 
	 */

	public  int getRowPixels(int squarey){

	int ypixel=3;

	for (int i =0; i<8; i++){


	if (squarey==i){

	return ypixel;
	}

	ypixel+= 75;

	}
	return ypixel;


	}



	public void mousePressed(MouseEvent e) {

	if (gameRunning){
	int col2 = e.getX();
	int row2 = e.getY();
	int squareRowClick= getRowSquare(row2);  /// this will be the variables that will be checked to verify of there is something or if the move is valid
	int squareColClick= getColSquare(col2);    /// this will be the variables that will be checked to verify of there is something or if the move is valid

	if (firstclick && myturn && bothready){

	if (checkerBoard.thereIs(squareRowClick, squareColClick)==1 && redturn== false){
	fromCol= squareColClick;
	fromRow= squareRowClick;


	int i=0;
	i= getBlackPiece(fromCol,fromRow);
	blackpieces[i].setIcon(blackselected);
	firstclick = false;

	}

	else if (checkerBoard.thereIs(squareRowClick, squareColClick)==3 && redturn){
	fromCol= squareColClick;
	fromRow= squareRowClick;

	int i=0;
	i= getRedPiece(fromCol,fromRow);
	redpieces[i].setIcon(redselected);


	firstclick = false;
	}

	}//end of first click

	else if (firstclick == false){   //this is the second click

	if (checkerBoard.legalMove(fromRow, squareRowClick, fromCol, squareColClick))
	{
	toCol= squareColClick;
	toRow = squareRowClick;

	if (checkerBoard.thereIs(fromRow, fromCol)==1){

	moveGUI(fromRow, fromCol, toRow, toCol);
	myturn = false;

	}  

	else if (checkerBoard.thereIs(fromRow, fromCol)==3){

	moveGUI(fromRow, fromCol, toRow, toCol);
	myturn = false;

	}


	try {

	Client.sendMove(fromRow, fromCol, toRow, toCol);


	} catch (UnknownHostException e1) {

	e1.printStackTrace();
	} catch (IOException e1) {

	e1.printStackTrace();
	}	

	firstclick = true;	
	turns.setText("Other's Player Turn");
	}

	//estos dos  if de abajo es por si el jugador se arrepiente y vuelve a dar click en una de sus piezas 
	//pues que esa pieza la trate como i fuera su primer click
	else if (checkerBoard.thereIs(squareRowClick, squareColClick)==1 & redturn==false)
	{
	int i=0;
	i= getBlackPiece(fromCol,fromRow);
	blackpieces[i].setIcon(blackpiecesicon);
	fromCol= squareColClick;
	fromRow= squareRowClick;
	i= getBlackPiece(fromCol,fromRow);
	blackpieces[i].setIcon(blackselected);
	}
	else if (checkerBoard.thereIs(squareRowClick, squareColClick)==3 & redturn)
	{

	int i=0;
	i= getRedPiece(fromCol,fromRow);
	redpieces[i].setIcon(redpiecesicon);
	fromCol= squareColClick;
	fromRow= squareRowClick;
	i= getRedPiece(fromCol,fromRow);
	redpieces[i].setIcon(redselected);
	}


	//for eating 

	else if (checkerBoard.canJump(fromRow, fromCol, squareRowClick, squareColClick)){


	toCol= squareColClick;
	toRow = squareRowClick;

	if (checkerBoard.thereIs(fromRow, fromCol)==1){
	moveGUI(fromRow, fromCol, toRow, toCol);

	try {

	Client.sendMove(fromRow, fromCol, toRow, toCol);


	} catch (UnknownHostException e1) {

	e1.printStackTrace();
	} catch (IOException e1) {

	e1.printStackTrace();
	}

	//removing piece from the GUI 
	int j =0;
	j = getRedPiece(CheckersData.midcol,CheckersData.midrow);
	redpieces[j].setBounds(0, 0, 0, 0);
	layeredPanecheckerBoard.remove(redpieces[j]);
	//removing piece from the array
	checkerBoard.remove(CheckersData.midrow, CheckersData.midcol);

	scorePlayer2++;
	labelScorePlayer2.setText("Score:" + scorePlayer2);
	//	
	}  

	else if (checkerBoard.thereIs(fromRow, fromCol)==3){

	moveGUI(fromRow, fromCol, toRow, toCol);
	moveSoundEffect.play();

	try {

	Client.sendMove(fromRow, fromCol, toRow, toCol);


	} catch (UnknownHostException e1) {

	e1.printStackTrace();
	} catch (IOException e1) {

	e1.printStackTrace();
	}


	//removing piece from the GUI 
	int j =0;
	j = getBlackPiece(CheckersData.midcol,CheckersData.midrow);
	blackpieces[j].setBounds(0, 0, 0, 0);
	layeredPanecheckerBoard.remove(blackpieces[j]);


	//removing piece from the array
	checkerBoard.remove(CheckersData.midrow, CheckersData.midcol);
	scorePlayer1++;
	labelScorePlayer1.setText("Score:" + scorePlayer1);


	}


	firstclick = true;
	myturn = false;
	turns.setText("Other's Player Turn");

	}    //end of jump 

	}  // end of second click 


	}

	}


	public void mouseReleased(MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited(MouseEvent evt) { }

	/*
	 * Class for actionlistener of each timer 
	 */

	public class TimerListener implements ActionListener{


	public void actionPerformed(ActionEvent e) {

	if (gameRunning){

	if (e.getSource().equals(time)){
	secondsfirstplayer--;

	if (secondsfirstplayer==0 && minutesfirstplayer>=0){
	minutesfirstplayer--;
	secondsfirstplayer=59;
	}

	if (minutesfirstplayer<0){

	minutesfirstplayer=0;
	secondsfirstplayer=0;
	
	JFrame frame = new JFrame();
	frame.setSize(1075, 672);
	frame.setResizable(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(new Winner("black"));
	frame.setVisible(true);
	
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

	minutesecondplayer=0;
	secondssecondplayer=0;
	JFrame frame = new JFrame();
	frame.setSize(860, 538);
	frame.setResizable(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(new Winner("red"));
	frame.setVisible(true);
	
	
	
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

	}//end of class TimerListener

	public class TimerIndexListener implements ActionListener{


	public void actionPerformed(ActionEvent e) {
	int currentindex;
	IntroCheckers.client.checkplayer();
	boolean done =false;

	for (int i=0; i<=799999999 ; i++){
	if (i==799999998){
	done = true;
	}
	}
	if (done){
	currentindex= IntroCheckers.client.getIndex();

	if (myindex < currentindex){
	indextimer.stop();	
	bothready = true; 
	time.start();
	}

	}

	}
	}  //end of class TimerIndexListener

}// end of class checkerBoard 