import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{

	int x, y;
	int mistakenMouseX = 8;
	int mistakenMouseY = 30;

	public void mouseClicked(MouseEvent arg0){
	}
	public void mouseEntered(MouseEvent arg0){
	}
	public void mouseExited(MouseEvent arg0){
	}
	public void mousePressed(MouseEvent arg0){
	}
	public void mouseReleased(MouseEvent arg0){
		x = arg0.getX() - mistakenMouseX;
		y = arg0.getY() - mistakenMouseY;
	}
	public void mouseMoved(MouseEvent arg0){
		x = arg0.getX() - mistakenMouseX;
		y = arg0.getY() - mistakenMouseY;
	}
	public void mouseDragged(MouseEvent arg0) {
		x = arg0.getX() - mistakenMouseX;
		y = arg0.getY() - mistakenMouseY;
	}
}