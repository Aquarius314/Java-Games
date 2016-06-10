import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{

	static int x, y;
	int mistakenMouseX = 8;
	int mistakenMouseY = 30;
	static boolean isDragging = false;
	static boolean hasMoved = false;
	static boolean charIsSelected = false;

	public void mouseClicked(MouseEvent arg0){
		x = arg0.getX() - mistakenMouseX;
		y = arg0.getY() - mistakenMouseY;
	}
	public void mouseEntered(MouseEvent arg0){
	}
	public void mouseExited(MouseEvent arg0){
	}
	public void mousePressed(MouseEvent arg0){
		x = arg0.getX() - mistakenMouseX;
		y = arg0.getY() - mistakenMouseY;
		if(Win.gameIsRunning){	// jeœli gra ju¿ siê rozpoczê³a
			if(x >= Board.cornerX && x <= Board.cornerX + (8*Win.R+35) ){
				if(y>=Board.cornerY && y<=Board.cornerY+(8*Win.R+35)){
					int rendX = (x-Board.cornerX)/(Win.R+5);
					int rendY = (y-Board.cornerY)/(Win.R+5);
					Board.startC = new Point(rendX, rendY);
					isDragging = true;
				}
			}
		}else{
			Introduction intro = ArtPanel.introduction;
			if(x >= intro.optionsX && x <= intro.optionsX+intro.optionsWidth){
				if(y >= intro.optionsY && y <= intro.optionsY+intro.optionsHeight){
					ArtPanel.introduction.clickOptions(y);
				}
			}
		}
	}
	public void mouseReleased(MouseEvent arg0){
		x = arg0.getX() - mistakenMouseX;
		y = arg0.getY() - mistakenMouseY;
		if(x>=Board.cornerX && x<=Board.cornerX+(8*Win.R+35)){
			if(y>=Board.cornerY && y<=Board.cornerY+(8*Win.R+35)){
				int rendX = (x-Board.cornerX)/(Win.R+5);
				int rendY = (y-Board.cornerY)/(Win.R+5);
				Board.endC = new Point(rendX, rendY);
				isDragging = false;
				if(Board.startC.x == Board.endC.x && Board.startC.y == Board.endC.y){
					hasMoved = false;
					Board.clickedField = new Point(rendX, rendY);
				}else{
					hasMoved = true;
				}
			}
		}
	}
	public void mouseMoved(MouseEvent arg0){
		x = arg0.getX() - mistakenMouseX;
		y = arg0.getY() - mistakenMouseY;
	}
	public void mouseDragged(MouseEvent arg0) {
		x = arg0.getX() - mistakenMouseX;
		y = arg0.getY() - mistakenMouseY;
		
		if(!isDragging)
			isDragging = true;
	}
}