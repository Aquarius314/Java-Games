import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Board {

	int width, height;	// szerokoœæ i wysokoœæ w polach
	int scoreA, scoreB;	// wynik gracza A i gracza B
	static int cornerX = 200 , cornerY = 40;
	char playerA = 'A', playerB = 'B';
//	static Point oldClicked = new Point(10, 10);
	static Point clickedField = new Point(9, 9);
//	static Point destinationField;
	static Point startC = new Point(10, 10);
	static Point endC = new Point(10, 10);
	static Field[][] fields;
	static Character[] charsetA;
	static Character[] charsetB;
		// stan gry
	boolean victoryA = false, victoryB = false;
	static boolean turnA = true;
	
	public Board(int x, int y){
		width = x;
		height = y;
		charsetA = new Character[16];
		charsetB = new Character[16];
		fields = new Field[x][y];
		boolean whiteColor = true;
		for(int i = 0 ; i < x ; i++){
			for(int j = 0 ; j < y ; j++){
				fields[i][j] = new Field(whiteColor, i, j);
				whiteColor = !whiteColor;
			}
			whiteColor = !whiteColor;
		}
		createArmies();
	}
	public void paint(Graphics g){
		paintBoard(g);
		paintHighlight(g);
		paintSoldiers(g);
		//manualSettings();
	}
	protected void manualSettings(){
		charsetA[12].y=2;
		charsetA[14].y=5;
		charsetB[11].y=3;
		charsetB[12].y=4;
	}
	protected void paintHighlight(Graphics g){
		int rendX = cornerX+(clickedField.x*(Win.R+5));
		int rendY = cornerY+(clickedField.y*(Win.R+5));
		g.setColor(Color.black);
		g.drawOval(rendX, rendY, Win.R, Win.R);
			// stare pole
		//rendX = cornerX+(oldClick.x*(Win.R+5));
		//rendY = cornerY+(oldClick.y*(Win.R+5));
		//g.setColor(Color.red);
		//g.drawOval(rendX, rendY, Win.R, Win.R);
			// mo¿liwe pola
		for(int i = 0 ; i < 8 ; i++)
			for(int j = 0 ; j < 8 ; j++){
				g.setColor(Color.red);
				rendX = cornerX+(i*(Win.R+5));
				rendY = cornerY+(j*(Win.R+5));
				if(fields[i][j].isPossible){
					g.drawRect(rendX+1, rendY+1, Win.R-2, Win.R-2);
					g.drawRect(rendX+2, rendY+2, Win.R-4, Win.R-4);
					g.fillOval(rendX+Win.R/2-5, rendY+Win.R/2-5, 10, 10);
				}
			}
	}
	protected void paintBoard(Graphics g){
		for(int i = 0 ; i < width ; i++){
			for(int j = 0 ; j < height ; j++){
				BufferedImage render = ArtPanel.poleB;
				if(fields[i][j].isWhite)
					render=ArtPanel.poleA;
				int rendX = i*Win.R + i*5 + cornerX;
				int rendY = j*Win.R + j*5 + cornerY;
				g.drawImage(render, rendX, rendY, Win.artPanel);
			}
		}
		g.setColor(Color.red);
		String sym = "";
		for(int i = 0 ; i < width ; i++){
			switch(i){
			case 0 :	sym = "A";
				break;
			case 1 :	sym = "B";
				break;
			case 2 :	sym = "C";
				break;
			case 3 :	sym = "D";
				break;
			case 4 :	sym = "E";
				break;
			case 5 :	sym = "F";
				break;
			case 6 :	sym = "G";
				break;
			case 7 :	sym = "H";
				break;
				default : 
					System.out.println("za duzo");
					break;
			}
			g.drawString(sym, cornerX+i*(Win.R+5)+30, cornerY-20);
			g.drawString(sym, cornerX+i*(Win.R+5)+30, cornerY+(8*Win.R+35)+30);
		}
		for(int i = 0 ; i < height ; i++){
			g.drawString(Integer.toString(8-i), cornerX-30, i*(Win.R+5)+cornerY+35);
			g.drawString(Integer.toString(8-i), cornerX+(8*Win.R+35)+20, i*(Win.R+5)+cornerY+35);
		}
	}
	protected void createArmies(){
			// charsetA;
		char al = playerA;
		charsetA[0] = new Character(al, CharType.WIEZA, 0, 0);
		charsetA[1] = new Character(al, CharType.SKOCZEK, 1, 0);
		charsetA[2] = new Character(al, CharType.GONIEC, 2, 0);
		charsetA[3] = new Character(al, CharType.KROL, 3, 0);
		charsetA[4] = new Character(al, CharType.HETMAN, 4, 0);
		charsetA[5] = new Character(al, CharType.GONIEC, 5, 0);
		charsetA[6] = new Character(al, CharType.SKOCZEK, 6, 0);
		charsetA[7] = new Character(al, CharType.WIEZA, 7, 0);
		for(int i = 8 ; i < 16 ; i++){
			charsetA[i] = new Character(al, CharType.PION, i-8, 1);
		}
			// charsetB
		al = playerB;
		charsetB[0] = new Character(al, CharType.WIEZA, 0, 7);
		charsetB[1] = new Character(al, CharType.SKOCZEK, 1, 7);
		charsetB[2] = new Character(al, CharType.GONIEC, 2, 7);
		charsetB[3] = new Character(al, CharType.KROL, 3, 7);
		charsetB[4] = new Character(al, CharType.HETMAN, 4, 7);
		charsetB[5] = new Character(al, CharType.GONIEC, 5, 7);
		charsetB[6] = new Character(al, CharType.SKOCZEK, 6, 7);
		charsetB[7] = new Character(al, CharType.WIEZA, 7, 7);
		for(int i = 8 ; i < 16 ; i++){
			charsetB[i] = new Character(al, CharType.PION, i-8, 6);
		}
	}
	protected void paintSoldiers(Graphics g){
		for(int i = 0 ; i < 8 ; i++){
			for(int j = 0 ; j < 8 ; j++){
				if(!(fields[i][j].isFree))
					fields[i][j].character.paint(g);
			}
		}
	}
	protected void clearPossibleFields(){
		for(int i = 0 ; i < 8 ; i++){
			for(int j = 0 ; j < 8 ; j++){
				Board.fields[i][j].isPossible = false;
			}
		}
	}
	public void moveSoldiers(){
		clearPossibleFields();
		Character movedChar;
		if(Mouse.isDragging){	// znaczy ¿e przeci¹ga coœ myszka
			if( !(fields[startC.x][startC.y].isFree) ){	// znaczy ¿e stoi tam figura
				movedChar = fields[startC.x][startC.y].character;
				movedChar.checkForPossibleFields();
			}
		}
		if(Mouse.hasMoved){	// znaczy ¿e przeci¹gniêto
			if( !(fields[startC.x][startC.y].isFree) ){	// znaczy ¿e sta³a tam figura
				movedChar = fields[startC.x][startC.y].character;
				if( (turnA&&movedChar.alignment=='A') || (!turnA&&movedChar.alignment=='B') ){	// czy rusza siê figura której tura jest obecnie
					if( !(fields[endC.x][endC.y].isFree) ){	// znaczy ¿e sta³a tam figura wroga, bo by³o mo¿liwe pole
						fields[endC.x][endC.y].character.isAlive = false;
						System.out.println("Zabijam "+fields[endC.x][endC.y].character.name);
					}
					movedChar.checkForPossibleFields();
					if( fields[endC.x][endC.y].isPossible ){	// tylko na mo¿liwe pola
						movedChar.x = endC.x;
						movedChar.y = endC.y;
						fields[endC.x][endC.y].character = movedChar;
						fields[endC.x][endC.y].isFree = false;
						fields[startC.x][startC.y].isFree = true;	// bo stamt¹d posz³a figura
					}
					if( !(fields[endC.x][endC.y].isPossible) ){
						for(int i = 0 ; i < 8 ; i++){
							for(int j = 0 ; j < 8 ; j++){
								if(fields[i][j].isPossible)
									System.out.println("Wolne pole: "+i+" "+j);
							}
						}
					}
					turnA = !turnA;
				}
			}
			Mouse.hasMoved = false;
		}
	}
	public void checkForVictory(){
		for(int i = 0 ; i < 16 ; i++){
			if( charsetA[i].type==CharType.KROL && !(charsetA[i].isAlive) ){
				victoryB = true;
			}
		}
		for(int i = 0 ; i < 16 ; i++){
			if( charsetB[i].type==CharType.KROL && !(charsetB[i].isAlive) ){
				victoryA = true;
			}
		}
	}
}