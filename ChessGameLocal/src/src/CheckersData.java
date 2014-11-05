package src;

public class CheckersData {
	final int ROWS = 8;
	final int COLUMN = 8;
static final int fromR=0, toR=0, fromC=0, toCol=0;
private int soy,middle;
static final int EMPTY=0, RED=1, RED_KING=2, BLACK=3, BLACK_KING=4;
int[][]  board ;

public static int midrow,midcol;

/**
 * Constructor of CheckersData holds all the info of the game.
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
 * @param row
 * @param col
 * @return The element in that position
 */
public int thereIs(int row, int col){
	// (comentario para mi mismo)  este metodo lo vamos voy a usar para cuando el usuario haga el primer click
	//si realmente apreto una de sus fichas  pues que registre la localizacion del click 
	// Y tambien lo voy a usar si en el segundo click esta vacio y si es legal la movida pues que la registre 
	//como segundo click. Y hacer el metodo moveTo 
	
	//Una excepcion es si el usuario hace  un click de una movida que es candidato a jump verificar si se puede comer 
	// Si es cierto, registarlo como segundo click.  Y hacer el metodo moveTo 
	
	// Otra excepcion es si el usuario apreta otra de sus mismas fichas pues registrar ese click como si
	//fuera su primer click 
	//WORKING

	return board[row][col];
	
}


/**
 * Para saber que ficha es la que esta en esa posicion.
 * @param row Array row
 * @param col Array col
 * @return La ficha que esta en la posicion
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
 * @param fromRow
 * @param fromCol
 * @param toRow
 * @param toCol
 * @return
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

public void remove(int midRow, int midCol){
	board[midRow][midCol]= 0;
}

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

}