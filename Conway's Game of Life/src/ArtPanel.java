import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ArtPanel extends JPanel implements ActionListener{

	public static int R = 30;	// rozmiar wszystkiego ogó³em
	public static int xdim, ydim;	// rozmiary dla planszy
	BufferedImage emptyFile;
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
	static Game game;
	
	public ArtPanel(){
		manualSettings();
	}
	protected void manualSettings(){
		// ustawienie niewidzialnego kursora.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
		// rozmiary planszy
		xdim = 700;
		ydim = 351;
		game = new Game(xdim, ydim);
	}
	@Override
	protected void paintComponent(Graphics g){
		if((System.currentTimeMillis()-time) > 1000/framesPerSec){
			super.paintComponent(g);
			time = System.currentTimeMillis();
			g.setColor(Color.black);
			g.fillRect(0, 0, 2000, 1000);
			game.display(g);
			// nadrzêdne rysowanie
			paintMouse(g);
		}
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