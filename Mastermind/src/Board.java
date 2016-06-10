import java.util.ArrayList;
import java.util.Random;

public class Board {

	Random rnd = new Random();
	private int x, y;
	private int width, height;
	final int ROWS = 10;
	final int PAWNS = 5;
	int turn = ROWS;
	boolean showMeCorrect = true;
	boolean gameIsOn = true;
	boolean fieldIsClicked = false;
	boolean victory = false;
	Button checkButton = new Button(getX()+PAWNS*50+10, getY()+(ROWS+1)*50+10+3, 150, 45, "SPRAWDZAJ", 15, 30);
	Button showButton = new Button(getX()+PAWNS*50+11, getY()+10, 150, 50, "ODKRYJ", 35, 30);
	Button reinButton = new Button(getX()+151, getY()+530, 250, 50, "ZAGRAJ PONOWNIE", 10, 30);
	int clickedField = 0;
	private int[][] colors;	// 0 - puste, 1,2,3,4,5,6,7,8 - czerwony, pomarañczowy, ¿ó³ty, zielony, niebieski, bia³y, magenta, cyan
	private int[] correctSet;
	private int[][] clues;	// wskazówki co do poprawnoœci u³o¿enia: [nrWiersza][rodzaj: 0-czarne, 1-bia³e]
	public Board(int X, int Y, int W, int H) {
		setBoard(X, Y, W, H);
		colors = clearBoard(ROWS, PAWNS);
		clues = clearBoard(ROWS, 2);
		correctSet = chooseCombination(PAWNS);
		manuals();
	}
	public void actions(){
		if(!checkButton.raised){
			checkCompatibility();
			fieldIsClicked = false;
		}
	}
	private void manuals(){
		
	}
	public boolean checkForEmptiness(int row){
		for(int i = 0 ; i < PAWNS ; i++){
			if(colors[row][i] != 0 )
				return false;
		}
		return true;
	}
	private void checkCompatibility(){
		int[] correctColors = new int[]{	0,0,0,0,0,0,0,0,0	};
		clues = clearBoard(ROWS, 2);
		for(int i = 0 ; i < PAWNS ; i++){
			correctColors[correctSet[i]] = 1;
		}
		
		for(int i = 0 ; i < ROWS ; i++){
			for(int j = 0 ; j < PAWNS ; j++){
				for(int k = 0 ; k < 8 ; k++){	// 8 - dla ka¿dego koloru.
					if( correctColors[colors[i][j]] != 0 ){
						clues[i][1]++;
						k = 8;
					}
				}
				if( colors[i][j] == correctSet[j]){
					clues[i][0]++;
					clues[i][1]--;
				}
			}
		}
		// sprawdzanie ewentualnego zwyciestwa:
		if(clues[turn-1][0] == PAWNS){
			gameIsOn = false;
			victory = true;
		}
	}

	private int[] randomRowWithoutRepeats(){
		int[] newRow = new int[PAWNS];
		ArrayList <Integer> colorsToChoose = new ArrayList <Integer> (8);
		for(int i = 0 ; i < 8 ; i++){
			colorsToChoose.add(i);	// wype³niam listê kolejnymi wartoœciami
		}
		for(int i = 0 ; i < PAWNS ; i++){
			newRow[i] = colorsToChoose.get(rnd.nextInt(colorsToChoose.size()))+1;
			for(int j = 0 ; j < colorsToChoose.size(); j++){
				if(colorsToChoose.get(j) == newRow[i]-1){
					colorsToChoose.remove(j);
				}
			}
		}
		return newRow;
	}
	private int[] chooseCombination(int pawns){
		int[] newSet = new int[pawns];
		newSet = randomRowWithoutRepeats();
		return newSet;
	}
	private int[][] clearBoard(int rows, int pawns){
		int[][] newBoard = new int[rows][pawns];
		
		for(int i = 0 ; i < rows ; i++){
			for(int j = 0 ; j < pawns; j++){
				newBoard[i][j] = 0;
			}
		}
		
		return newBoard;
	}
	public void setBoard(int X, int Y, int W, int H){
		x = X;
		y = Y;
		width = W;
		height = H;
	}
	public void setValue(int x, int y, int val){
		if(x < colors.length && y < colors[0].length){
			System.out.println(x + " vs " + colors.length);
			System.out.println(y + " vs " + colors[0].length);
			colors[x][y] = val;
		}else{
			System.out.println("Nie mogê wpisaæ wartoœci w nieistniej¹cy indeks tablicy 'colors' !");
		}
	}
	public int[][] getClues(){	return clues;	}
	public int[][] getBoard(){	return colors;	}
	public int[] getSet(){	return correctSet;	}
	public int getX(){	return x;	}
	public int getY(){	return y;	}
	public int getWidth(){	return width;	}
	public int getHeight(){	return height;	}
}
