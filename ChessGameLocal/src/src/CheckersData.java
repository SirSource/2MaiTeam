package src;

public class CheckersData {
	final int ROWS = 8;
	final int COLUMN = 8;
static final int fromR=0, toR=0, fromC=0, toCol=0;
private int soy,middle,middleCol, middleRow;
static final int EMPTY=0, RED=1, RED_KING=2, BLACK=3, BLACK_KING=4;
int[][]  board ;

public static int midrow,midcol;

/**
 * Constructor of CheckersData matrix 8x8 that holds all the info of the game like where are the pieces.
 */
public  CheckersData(){
	
	
	board = new int[][]   { {1, 0, 1,  0, 0, 0, 3, 0},
			 				{0, 1, 0,  0, 0, 3, 0, 3},
			 				{1, 0, 1,  0, 0, 0, 3, 0},
			 				{0, 1, 0,  0, 0, 3, 0, 3}, 
			 				{1, 0, 1,  0, 0, 0, 3, 0},
			 				{0, 1, 0,  0, 0, 3, 0, 3},
			 				{1, 0, 1,  0, 0, 0, 3, 0},
			 				{0, 1, 0,  0, 0, 3, 0, 3} };
//(cuanto baja, cuanto pa la derechaa)(row,colum)
}


/**
 * To print a representation of the 8x8 Array.
 */

public String toString(){
	
	String r ="";
	for(int i=0; i<ROWS; i++){
		
		for(int j=0;j<COLUMN; j++){
			r = r + board[i][j];
			r = r + "|\n";
		}
		
		
	}
	return r;
	
}
/**
 * To know what is the element in the specified position in the Array.
 * @param Row initial Row position		
 * @param Col initial Column position
 * @return The element in that position in the board
 */
public int thereIs(int Row, int Col){

	return board[Row][Col];
	
}


/**
 * Para saber que ficha es la que esta en esa posicion.
 * @param row Array row
 * @param col Array col
 * @return The element in that array position
 */

/**
 * 
 * @param row
 * @param col
 * @return
 */
public int quefichaes(int row, int col){
	//WORKING!
	// no entiendo por que esta este metodo si existe el metodo de "thereIs" que creo que hace lo mismo -Emanuel 
	 
	if(thereIs(row, col)==1){
		
		soy =1;
	}
	if(thereIs(row, col)==3)
		
		soy=3;
	if(thereIs(row, col)==0)
	{
		
		soy=0;
	}
	return soy;
	
}

/**
 * This checks if a certain move is legal or not.
 * @param fromRow 
 * @param toRow
 * @param fromCol
 * @param toCol
 * @return boolean
 */
public Boolean legalMove(int fromRow, int toRow,int fromCol, int toCol){
	// editado por emanuel   oct18 11:19 
	if (thereIs(fromRow, fromCol)==1 & (fromRow+1 == toRow || fromRow-1==toRow) & fromCol+1==toCol & thereIs(toRow,toCol)==0){   
		return true;
	}
	else if (thereIs(fromRow, fromCol)==3 & (fromRow+1 == toRow || fromRow-1==toRow) & fromCol-1==toCol& thereIs(toRow,toCol)==0){
		return true;
	}
	else {
	return false;	
	}
	
	
	
}


// yo pienso que en fin vamos a tener que borrar lo que escribiste en el metodo y ponerlo en algo sencillo 
// ya que afuera de este metodo me voy a encargar de verificar esto que pusiste para a ver si vale la pena registrar 
// el click y si conseguimos los clicks "legales" hacemos el movimiento
/**
 * This method makes a move and makes the changes in the Array Board associated by it.
 * @param fromRow the initial position in the row axis
 * @param fromCol the initial column position
 * @param toRow the final row position 
 * @param toCol the final column position
 */
public void moveTo(int fromRow, int fromCol,int toRow, int toCol){
	int there = thereIs(toRow, toCol);
	int here = thereIs(fromRow, fromCol);
	
	
	
	if(here==1){
		board[toRow][toCol]= 1;
		board[fromRow][fromCol]=0;
	}
	
	
	
	if(here ==3)
		{
			board[toRow][toCol]= 3;
			board[fromRow][fromCol]=0;
			
		}
	

	}
/**
 * Determines whats inside the on the middle square between movements.
 * @param fromRow initial row position in a move
 * @param fromCol initial column position in a move
 * @param toRow final row position in a move
 * @param toCol final column position in a move
 * @return middle //Which is the exact item or piece in the middle position between moves
 */
public int whatsOnTheMiddle(int fromRow, int fromCol,int toRow, int toCol){
	
	
	
	
	if(quefichaes(fromRow, fromCol)==1)
	{
		if(fromRow<toRow)
		{
			midrow = fromRow + 1;
			midcol = fromCol + 1;
			middle = board[midrow][midcol];
		}
		else
		{
			midrow = fromRow - 1;
			midcol = fromCol + 1;
			middle = board[midrow][midcol];	
		}
	}
	else if(quefichaes(fromRow, fromCol)==3)
	{
		if(fromRow<toRow)
		{
			midrow = fromRow + 1;
			midcol = fromCol - 1;
			middle = board[midrow][midcol];	
		}
		else
		{
			midrow = fromRow - 1;
			midcol = fromCol - 1;
			middle = board[midrow][midcol];		
		}
	}
	
	else
	{
		//nothing 
	}
	return middle;
	
	
}
/**
 * Removes the the piece for 
 * @param midRow middle row position in a move
 * @param midCol middle column position in a move
 */
public void remove(int midRow, int midCol){
	board[midRow][midCol]= 0;
}
/**
 * Method that tells if that jump is a legal move.
 * @param fromRow initial row from where is the piece
 * @param fromCol initial column from where the piece is 
 * @param toRow the row that you want the piece to move to
 * @param toCol the column that you want the piece to move to
 * @return canJump  either True or False
 */

public Boolean canJump(int fromRow, int fromCol,int toRow, int toCol){
	Boolean canJump;
	int here = board[fromRow][fromCol];
	int to = board[toRow][toCol];
	
	
	
	// si te fijas aqui lo hiciste bien, hiciste lo de las dos opciones de ROw y una para column 
	if(here == 1 & (fromRow+2==toRow || fromRow-2==toRow) & fromCol+2==toCol 
			& whatsOnTheMiddle(fromRow, fromCol, toRow, toCol)== 3 & to == 0)
	{
		canJump = true;
	}
	else if (here == 3 & (fromRow-2==toRow || fromRow+2==toRow) & fromCol-2 == toCol
			& whatsOnTheMiddle(fromRow, fromCol, toRow, toCol)== 1 & to == 0) 
	{
		canJump = true;
	}
	
	else
	{
		canJump = false;
	}
	return canJump ;

}
/**
 * To get the location in Array of the MiddleColumn bewtween a move.
 * @param fromRow initial row from where is the piece
 * @param fromCol initial column from where the piece is 
 * @param toRow the row that you want the piece to move to
 * @param toCol the column that you want the piece to move to
 * @return middleCol
 */
public Integer getMiddleCol(int fromRow, int fromCol, int toRow, int toCol){
	if(quefichaes(fromRow, fromCol)==1){
		middleCol= fromCol+1;
	}
	else if(quefichaes(fromRow, fromCol)==3){
		middleCol = fromCol-1;
		
	}
	return middleCol;
}
/**
 * To get the location in the Array Board of the MiddleRow between to moves.
 * @param fromRow initial row from where is the piece
 * @param fromCol initial column from where the piece is 
 * @param toRow the row that you want the piece to move to
 * @param toCol the column that you want the piece to move to
 * @return middleRow location or number format
 */
public Integer getMiddleRow(int fromRow, int fromCol, int toRow, int toCol){
	if(quefichaes(fromRow, fromCol)==1){
		if(fromRow<toRow){
			middleRow = fromRow+1;
		}
		else if(fromRow>toRow){
			middleRow = fromRow - 1;
		}
	}
	
	else if(quefichaes(fromRow, fromCol)==3){
		if(fromRow<toRow){
			middleRow = fromRow + 1;
		}
		else if(fromRow>toRow){
			middleRow = fromRow - 1;
		}
	
	
	}
	return middleRow;
}

}