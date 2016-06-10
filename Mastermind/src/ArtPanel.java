import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ArtPanel extends JPanel{

	BufferedImage emptyFile;
	File imageFile;
	Font font = new Font("Times New Roman", Font.BOLD, 10);
	//timer
	long time = System.currentTimeMillis();
	static int framesPerSec = 60;
	public ArtPanel(){
		manualSettings();
	}
	protected void manualSettings(){
		// ustawienie niewidzialnego kursora.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
	}
	@Override
	protected void paintComponent(Graphics g){
		if((System.currentTimeMillis()-time) > 1000/framesPerSec){
			super.paintComponent(g);
			time = System.currentTimeMillis();
			// t�o
			Displayer.displayBackground(g);
			// g��wne rysowanie
			Win.world.display(g);
			// nadrz�dne rysowanie
			paintMouse(g);
		}
	}
	protected void paintMouse(Graphics g){
		int rendX = Win.mouse.x - 4;
		int rendY = Win.mouse.y - 4;
		g.setColor(Color.white);
		g.fillOval(rendX, rendY, 8, 8);
		g.setColor(Color.black);
		g.fillOval(rendX+2, rendY+2, 4, 4);
	}
}