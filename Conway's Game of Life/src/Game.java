import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Game {

	Random gen = new Random();
	static int XDIM, YDIM;
	static int[][] cells;
	static int wallsValue = 0;
	public Game(int xDIM, int yDIM){
		XDIM = xDIM;
		YDIM = yDIM;
		cells = new int[XDIM][YDIM];
		for(int i = 0 ; i < XDIM ; i ++){
			for(int j = 0 ; j < YDIM ; j++){
				cells[i][j] = 0;
			}
		}
		//cells = setStructure();
		createLines();
		//squares();
		//addSomeSettings();
	}
	protected void squares(){
		for(int i = XDIM/4 ; i < XDIM-10 ; i++){
			for(int j = YDIM/4 ; j < YDIM ; j++){
				if(i%3!=0 && j%3!=0)
					cells[i][j] = 1;
			}
		}
	}
	public void createPlane(int x, int y, int dir){	// 1 - prawo g�ra 2 - prawo d� 3 - lewo d� 4 - lewo g�ra
		int[][] szybowiec = new int[][]{
			{0,0,1},
			{1,0,1},
			{0,1,1}
		};
		for(int a = 0 ; a < szybowiec.length ; a++){
			for(int b = 0 ; b < szybowiec[0].length ; b++){
				if(dir == 1)
					cells[b+x][a+y] = szybowiec[a][b];
				if(dir == 2)
					cells[b+x][a+y] = szybowiec[b][a];
				if(dir == 3)
					cells[b+x][a+y] = szybowiec[szybowiec.length-a-1][szybowiec[0].length-b-1];
				if(dir == 4)
					cells[b+x][a+y] = szybowiec[szybowiec[0].length-b-1][szybowiec.length-a-1];
			}
		}
	}
	public void createShip(int x, int y, int dir){	// 1 - prawo 2 - d� 3 - lewo 4 - g�ra (zgodnie ze wsk.zeg)
		// statek
		int[][] statek = new int[][]{
			{1,0,0,1,0},
			{0,0,0,0,1},
			{1,0,0,0,1},
			{0,1,1,1,1},
			{0,0,0,0,0}
		};
		for(int a = 0 ; a < statek.length ; a++){
			for(int b = 0 ; b < statek[0].length ; b++){
				if(dir == 1)
					cells[b+x][a+y] = statek[a][b];
				if(dir == 2)
					cells[b+x][a+y] = statek[b][a];
				if(dir == 3)
					cells[b+x][a+y] = statek[statek.length-a-1][statek[0].length-b-1];
				if(dir == 4)
					cells[b+x][a+y] = statek[statek[0].length-b-1][statek.length-a-1];
			}
		}
	}
	protected void createLines(){
		for(int i = 0 ; i < XDIM ; i++){
			for(int j = 0 ; j < YDIM ; j++){
				if(j%2==0){
					cells[i][j] = 1;
				}else{
					cells[i][j] = 0;
				}
			}
		}
		// zak��cenie:
		cells[XDIM/2][YDIM/2] = 0;
		cells[XDIM/2][YDIM/2+1] = 0;
		cells[XDIM/2][YDIM/2+2] = 0;
	}
	protected void addSomeSettings(){
	}
	protected int[][] setStructure(){
		int[][] newBoard = new int[XDIM][YDIM];
		int[][] gliderGun = new int[][]{
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,1,1,0,0},
			{0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,1,1,0,0},
			{0,0,1,1,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0},
			{0,0,1,1,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		};
		int[][] board = gliderGun;
		for(int i = 0 ; i < board[0].length ; i++){
			for(int j = 0 ; j < board.length ; j++){
				newBoard[i][j] = board[j][i];
			}
		}
		// zr�b tu zabezpieczenie przed r�nic� rozmiar�w
		return newBoard;
	}
	public void display(Graphics g){
		//paintBoard(g);
		paintCells(g);
	}
	protected void paintBoard(Graphics g){
		g.setColor(Color.red);
		for(int i = 0 ; i <= XDIM ; i++){	// linie pionowe
			g.drawLine(i*Win.R, 0, i*Win.R, Win.R*YDIM);
		}
		for(int i = 0 ; i <= YDIM ; i++){	// linie poziome
			g.drawLine(0, i*Win.R, Win.R*XDIM, i*Win.R);
		}
	}
	protected void paintCells(Graphics g){
		g.setColor(Color.blue);
		for(int i = 0 ; i < XDIM ; i++){
			for(int j = 0 ; j < YDIM ; j++){
				if(cells[i][j] == 1){
					//g.setColor(Color.blue);
					g.fillRect(i*Win.R, j*Win.R, Win.R, Win.R);
				}
			}
		}
	}
	static void evolve(){
		int[][] newBoard = new int[XDIM][YDIM];
		for(int i = 0 ; i < XDIM ; i++){
			for(int j = 0 ; j < YDIM ; j++){
				// rodzenie si�
				int w = wallsValue;	// wartosc domyslna na scianach
				int UL=w, UM=w, UR=w, ML=w, M=w, MR=w, DL=w, DM=w, DR=w;
				if(i-1 >= 0 && j-1 >= 0){
					UL = cells[i-1][j-1];	// g�ra lewo
				}
				if( j-1 >= 0){
					UM = cells[i][j-1];		// g�ra �rodek
				}
				if(i+1 < XDIM && j-1 >= 0){
					UR = cells[i+1][j-1];	// g�ra prawo
				}
				if(i-1 >= 0 ){
					ML = cells[i-1][j];		// �rodek lewo
				}
				if(i >= 0 && j >= 0){
					M = cells[i][j];		// �RODEK
				}
				if(i+1 < XDIM ){
					MR = cells[i+1][j];		// �rodek prawo
				}
				if(i-1 >= 0 && j+1 < YDIM){
					DL = cells[i-1][j+1];	// d� lewo
				}
				if( j+1 < YDIM){
					DM = cells[i][j+1];		// d� �rodek
				}
				if(i+1 < XDIM && j+1 < YDIM){
					DR = cells[i+1][j+1];	// d� prawo
				}
				// przepisa�em kom�rki s�siaduj�ce. teraz por�wnuj�.
				if(M==0){	// dla martwych kom�rek: 3 �ywe w s�siedztwie o�ywiaj� je
					int life = UL + UM + UR + ML + MR + DL + DM + DR;
					if(life == 3)
						newBoard[i][j] = 1;	// o�ywienie kom�rki
				}else{
					int life = UL + UM + UR + ML + MR + DL + DM + DR;
					if(life == 3 || life == 2){
						newBoard[i][j] = 1;	// pozostaje �ywa
					}
					else
						newBoard[i][j] = 0;	// kom�rka umiera.
				}
			}
		}
		cells = newBoard;
	}
}
