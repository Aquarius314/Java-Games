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
//	public static int xdim, ydim;	// rozmiary dla planszy
	BufferedImage emptyFile;
	File imageFile;
	static BufferedImage enemyShip1, bossShip1;
	static BufferedImage[][] ships = new BufferedImage[4][6];	// 4-poziomy statku, 6-rodzajów statku
	static BufferedImage[] missile = new BufferedImage[3];	// pociski
	// 1-fighter 2-heavy 3-gauss 4-pulsative 5-sniper 6-striker
	Font font = new Font("Times New Roman", Font.BOLD, 10);
	long startTime = System.currentTimeMillis();
		//timer
	long time = System.currentTimeMillis();
	static int framesPerSec = 60;
	Random gen = new Random();
		//colors
	Color chosenColor = new Color(1, 100, 25);
	Color oldColor = Color.red;
		//booleans:
	static boolean isLPressed = false;
	static boolean isEPressed = false;
		//objects
	Aircraft aircraft = new Aircraft();
	
	public ArtPanel(){
		manualSettings();
		loadImages();
	}
	protected void loadImages(){
		String[] folderName = {
				"Fighter/",
				"Gauss/",
				"Heavy/",
				"Pulsative/",
				"Sniper/",
				"Striker/"
		};
		for(int i = 0 ; i < folderName.length ; i++){
			for(int j = 0 ; j < 4 ; j++){
				String pathName = "Images/Ships/"+folderName[i]+Integer.toString(j+1)+".png";
				System.out.println("Wczytuje: "+pathName);
				imageFile = new File(pathName);
				try { 
					ships[j][i] = ImageIO.read(imageFile);
				}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
					}
			}
		}
		imageFile = new File("Images/Weapons/1.png");
		try { 
			missile[0] = ImageIO.read(imageFile);
		}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
			}
		imageFile = new File("Images/Weapons/2.png");
		try { 
			missile[1] = ImageIO.read(imageFile);
		}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
			}
		imageFile = new File("Images/Weapons/3.png");
		try { 
			missile[2] = ImageIO.read(imageFile);
		}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
			}
		imageFile = new File("Images/Enemies/Boss/1.png");
		try { 
			bossShip1 = ImageIO.read(imageFile);
		}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
			}
		
		
		
		imageFile = new File("Images/Enemies/Light/1.png");
		try { 
			enemyShip1 = ImageIO.read(imageFile);
	}	catch (IOException e) {	System.out.println("Brak jakiegos pliku do wczytania.");
		}
		
		
	}
	protected void manualSettings(){
		// ustawienie niewidzialnego kursora.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
		// rozmiary planszy
//		xdim = 170;
//		ydim = 85;
	}
	@Override
	protected void paintComponent(Graphics g){
		if((System.currentTimeMillis()-time) > 1000/framesPerSec){
			super.paintComponent(g);
			time = System.currentTimeMillis();
			g.setColor(Color.black);
			g.fillRect(0, 0, 2000, 1000);
			// g³ówne rysowanie
			if(!Win.tankChosen)
				aircraft.initialView(g);
			else{
				aircraft.background(g);
				aircraft.mainView(g);
			}
			// nadrzêdne rysowanie
			paintMouse(g);
			if(!Win.gameRunning && Win.tankChosen){
				displayGameOver(g);
			}
		}
	}
	protected void displayGameOver(Graphics g){
		g.setColor(Color.black);
		g.fillRect(500, 465, 325, 136);
		g.setFont(new Font("Times New Roman", Font.BOLD, 50));
		g.setColor(Color.white);
		g.drawString("KONIEC GRY", 500, 500);
		g.drawString("WYNIK: "+Integer.toString(Aircraft.score), 500, 600);
	}
	protected void paintMouse(Graphics g){
		int rendX = Win.mouseX - 4;
		int rendY = Win.mouseY - 4;
		g.setColor(Color.white);
		g.fillOval(rendX, rendY, 8, 8);
		g.setColor(Color.black);
		g.fillOval(rendX+2, rendY+2, 4, 4);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}