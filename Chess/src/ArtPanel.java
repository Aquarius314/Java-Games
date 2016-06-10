import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ArtPanel extends JPanel implements ActionListener{

	public static int R = 30;	// rozmiar wszystkiego ogó³em
		// objects
	static Board board;
	static Introduction introduction;
	BufferedImage emptyFile;
	static BufferedImage krolA, hetmanA, goniecA, skoczekA, wiezaA, pionA;
	static BufferedImage krolB, hetmanB, goniecB, skoczekB, wiezaB, pionB;
	static BufferedImage poleA, poleB;
	File imageFile;
	Font font = new Font("Times New Roman", Font.BOLD, 10);
	long startTime = System.currentTimeMillis();
		//timer
	long time = System.currentTimeMillis();
	static int framesPerSec = 60;
	Random gen = new Random();
		//colors
	Color chosenColor = new Color(1, 100, 25);
	Color oldColor = Color.red;
	String imageFolder = "Images1";
		//booleans:
	public ArtPanel(){
		loadFiles();
		board = new Board(Win.standardFields, Win.standardFields);
		introduction = new Introduction();
		manualSettings();
	}
	public void loadFiles(){
		imageFile = new File(imageFolder+"/poleA.png");
		try { 
			poleA = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/poleB.png");
		try { 
			poleB = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/krolA.png");
		try { 
			krolA = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/krolB.png");
		try { 
			krolB = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/hetmanA.png");
		try { 
			hetmanA = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/hetmanB.png");
		try { 
			hetmanB = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/goniecA.png");
		try { 
			goniecA = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/goniecB.png");
		try { 
			goniecB = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/skoczekA.png");
		try { 
			skoczekA = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/skoczekB.png");
		try { 
			skoczekB = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/wiezaA.png");
		try { 
			wiezaA = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/wiezaB.png");
		try { 
			wiezaB = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/pionA.png");
		try { 
			pionA = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		imageFile = new File(imageFolder+"/pionB.png");
		try { 
			pionB = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
	}
	protected void manualSettings(){
		// ustawienie niewidzialnego kursora.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
		// tworzenie kilku postaci
	}
	@Override
	protected void paintComponent(Graphics g){
		if((System.currentTimeMillis()-time) > 1000/framesPerSec){
			super.paintComponent(g);
			time = System.currentTimeMillis();
			g.setColor(Color.black);
			g.fillRect(0, 0, 2000, 1000);
			if(Win.gameIsRunning){
				// g³ówne rysowanie
				highlightField(g);	// podœwietlenie wskazywanego pola oraz informacje o polu
				board.paint(g);
				// nadrzêdne rysowanie
				displayGameInfo(g);
			}
			else
				introduction.display(g);
			paintMouse(g);
		}
	}
	protected void displayGameInfo(Graphics g){
		String info = "";
		int rendX = Board.cornerX + 8*(Win.R)+35 + 40;
		int rendY = Board.cornerY + 20;
		g.drawString("Armia A:", rendX, rendY);
		for(int i = 0 ; i < 16 ; i++){
			if(Board.charsetA[i].isAlive){
				info = Board.charsetA[i].name;
				rendY += 20;
			}
			g.drawString(info, rendX, rendY);
		}
		rendX += 100;
		rendY = Board.cornerY + 20;
		g.drawString("Armia B:", rendX, rendY);
		for(int i = 0 ; i < 16 ; i++){
			if(Board.charsetB[i].isAlive){
				info = Board.charsetB[i].name;
				rendY += 20;
			}
			g.drawString(info, rendX, rendY);
		}
			// zwyciestwo
		if( board.victoryA || board.victoryB ){	// zwyciestwo TYLKO A
			if(board.victoryA && !(board.victoryB) )
				info = "WYGRYWA GRACZ A!";
			if(board.victoryB && !(board.victoryA) )
				info = "WYGRYWA GRACZ B!";
			if( board.victoryB && board.victoryA )
				info = "REMIS!";
			g.setFont(new Font("Times New Roman", Font.BOLD, 50));
			g.setColor(Color.black);	// t³o dla tekstu zwyciestwa
			g.fillRect(150, 300, 900, 180);
			g.setColor(Color.red);
			g.drawString("KONIEC GRY! "+info, 200, 400);
			
			g.setFont(font);
		}
	}
	protected void highlightField(Graphics g){
		int cursorX = Mouse.x;
		int cursorY = Mouse.y;
		String info = "";
		if(cursorX>=Board.cornerX && cursorX<=8*Win.R+35+Board.cornerX
			&& cursorY>=Board.cornerY && cursorY<=8*Win.R+35+Board.cornerY){
			cursorX -= Board.cornerX;
			cursorY -= Board.cornerY;
			cursorX /= (Win.R+5);
			cursorY /= (Win.R+5);
				//info
			g.setColor(Color.white);
			g.drawString("X: "+Integer.toString(cursorX+1), 5, 40);
			g.drawString("Y: "+Integer.toString(cursorY+1), 5, 80);
			
			info = (Board.fields[cursorX][cursorY].isWhite) ? "BIALE":"CZARNE";
			g.drawString("Pole: "+Board.fields[cursorX][cursorY].name+" "+info, 5, 120);
			info = "";
			for(int i = 0 ; i < 16 ; i++){
				if(Board.charsetA[i].x==cursorX && Board.charsetA[i].y==cursorY && Board.charsetA[i].isAlive)
					info = "bialy " + Board.charsetA[i].name;
				if(Board.charsetB[i].x==cursorX && Board.charsetB[i].y==cursorY && Board.charsetB[i].isAlive)
					info = "czarny " + Board.charsetB[i].name;
			}
			g.drawString("Tu stoi " + info.toLowerCase(), 5, 160);
			if(Mouse.charIsSelected){
				info = Board.fields[Board.clickedField.x][Board.clickedField.y].character.name + " " + Board.fields[Board.clickedField.x][Board.clickedField.y].character.alignment;
				g.drawString("Wybrano: " + info, 5, 240);
			}
			
				//konkretne pole
			int rendX = cursorX*(Win.R+5)+Board.cornerX;
			int rendY = cursorY*(Win.R+5)+Board.cornerY;
			g.setColor(Color.black);
			g.drawRect(rendX-6, rendY-6, Win.R+11, Win.R+11);	// zewnetrzny
			g.drawRect(rendX, rendY, Win.R-1, Win.R-1);	// wiekszy wewnetrzny
			g.drawRect(rendX+1, rendY+1, Win.R-3, Win.R-3);	// mniejszy wewnetrzny
		}
		info = (Board.turnA) ? "gracz A" : "gracz B";
		g.setColor(Color.white);
		g.drawString("Obecna tura: "+info, 5, 200);
	}
	protected void paintMouse(Graphics g){
		int rendX = Mouse.x - 4;
		int rendY = Mouse.y - 4;
		g.setColor(Color.white);
		g.fillOval(rendX, rendY, 8, 8);
		g.setColor(Color.black);
		g.fillOval(rendX+2, rendY+2, 4, 4);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}
