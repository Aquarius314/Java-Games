import java.util.ArrayList;
import java.util.Arrays;

public class Level {

	//TODO develop settings for each level. Not only number of monsters
	//TODO Design many different waves of monsters, including time between waves
	private int monstersNumber;
	private int xStart, yStart;
	static ArrayList <Integer> xPath = new ArrayList <Integer> ();
	static ArrayList <Integer> yPath = new ArrayList <Integer> ();
	private int[][] board;
	
	public Level(int numberOfMonsters, int[][] b) {
		board = b;
		monstersNumber = numberOfMonsters;
		boolean isValid = checkForValidLevel();
		if(isValid){
			findStart();
			setPath();
		}
		else{
			System.out.println("Niepoprawnie ustawiony level! (Brak startu lub wyjscia)");
			System.exit(-1);
		}
	}
	private boolean checkForValidLevel(){
		boolean startFound = false;
		boolean exitFound = false;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] == 2)
					startFound = true;
				if(board[i][j] == -1)
					exitFound = true;
				if(startFound && exitFound)
					return true;
			}
		}
		return startFound && exitFound;
	}
	private void setPath(){
		xPath.add(xStart + Game.SIZE/2);
		yPath.add(yStart + Game.SIZE/2);
		int[][] sketchBoard = new int[board.length][board[0].length];
		for(int i = 0; i < board.length; i++){
			sketchBoard[i] = Arrays.copyOf(board[i], board[i].length);
		}
		boolean exitFound = false;
		for(int i = 2; !exitFound; i++){
			for(int x = 0; x < getWidth(); x++){
				for(int y = 0; y < getHeight(); y++){
					// znalezienie pola z szukanym numerem
					if(sketchBoard[x][y] == i){
						for(int a = -1; a < 2; a++){
							bLoop : for(int b = -1; b < 2; b++){
								// warunek pomijania naroznikow i srodka
								if(a*b != 0 || (a==0&&b==0)) continue bLoop;
								// warunek na omijanie elementow wystajacych poza tablice
								if(x+a < 0 || x+a >= getWidth() || y+b < 0 || y+b >= getHeight()) continue bLoop;
								// numerowanie pól s¹siaduj¹cych z numerowanymi poprzednio
								if(sketchBoard[x+a][y+b] == 1){
									sketchBoard[x+a][y+b] = i+1;
									yPath.add((x+a)*Game.SIZE + Game.SIZE/2);
									xPath.add((y+b)*Game.SIZE + Game.SIZE/2);
								}
								// warunek na dotarcie do mety
								if(sketchBoard[x+a][y+b] == -1){
									exitFound = true;
									sketchBoard[x+a][y+b] = i+1;
									yPath.add((x+a)*Game.SIZE + Game.SIZE/2);
									xPath.add((y+b)*Game.SIZE + Game.SIZE/2);
								}
							}
						}
					}
				}
			}
		}
		// rysowanie algorytmu
//		System.out.println();
//		for(int i = 0; i < sketchBoard.length; i++){
//			for(int j = 0; j < sketchBoard[0].length; j++){
//				System.out.print(sketchBoard[i][j] + " ");
//				if(sketchBoard[i][j] < 10)
//					System.out.print(" ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println(xPath);
//		System.out.println(yPath);
	}
	private void findStart(){
		for(int x = 0; x < getWidth(); x++){
			for(int y = 0; y < getHeight(); y++){
				if(board[y][x] == 2){
					this.xStart = x*Game.SIZE;
					this.yStart = y*Game.SIZE;
				}
			}
		}
	}
	public void setField(int x, int y, int content){
		board[x][y] = content;
	}
	public int getStartX(){
		return xStart + Game.SIZE/2;
	}
	public int getStartY(){
		return yStart + Game.SIZE/2;
	}
	public int getHeight(){
		return board[0].length;
	}
	public int getWidth(){
		return board.length;
	}
	public int getFieldContent(int x, int y){
		if(x < 0 || y < 0 || x >= getWidth() || y >= getHeight()){
			return 0;
		}
		return board[y][x];
	}
	public int getMonstersNumber(){
		return monstersNumber;
	}

}
