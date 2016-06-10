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
		int leftX, rightX, upY, downY;
		// sprawdzanie dla przycisku sprawdzaj¹cego
		Button but;
		but = World.board.showButton;
		leftX = but.getX();
		rightX = leftX + but.getWidth();
		upY = but.getY();
		downY = upY + but.getHeight();
		if(x >= leftX && x <= rightX && y >= upY && y <= downY){	// czy trafia we w³aœciwy obszar
			World.board.showButton.raised = !World.board.showButton.raised;
		}
		but = World.board.checkButton;
		leftX = but.getX();
		rightX = leftX + but.getWidth();
		upY = but.getY();
		downY = upY + but.getHeight();
		if(x >= leftX && x <= rightX && y >= upY && y <= downY){	// czy trafia we w³aœciwy obszar
			if(World.board.gameIsOn && !World.board.checkForEmptiness(World.board.turn-1)){	// czy gra trwa oraz czy rz¹d nie jest pusty
				World.board.checkButton.raised = false;
			}
		}
		// sprawdzanie dla wyboru pola
		int w;
		if(true){	// pozosta³oœæ po starym ifie. Nieusuniête z powodu lenistwa.
			leftX = World.board.getX();
			upY -= (World.board.ROWS - World.board.turn+1)*50;
			downY -= (World.board.ROWS - World.board.turn+1)*50;
			w = x - leftX;	// w-wideness: jak daleko od lewego boku jest klikniêcie
			int cF = 0;	// clickedField
			if(y >= upY && y <= downY && w >= 0 && w < 250) {
				for(int a = 0 ; a < 5 ; a++){	// sprytne sprawdzanie na którym polu naciœniêto
					if(w >= a*50 && w < (a+1)*50)
						cF = a+1;
				}
				// klikniêto któreœ pole (pole cF licz¹c od 1 do 5 od lewej do prawej)
				if(!World.board.fieldIsClicked || World.board.clickedField != cF){
					World.board.clickedField = cF;
					World.board.fieldIsClicked = true;
				}
			}
		}
		// sprawdzanie dla wyboru koloru
		if(World.board.fieldIsClicked){
			leftX = World.board.getX()+World.board.PAWNS*75+30;
			rightX = leftX + 50;
			upY = World.board.getY() + 2*50;
			w = y - upY;
			if(x >= leftX && x <= rightX){
				for(int a = 0 ; a <= 8 ; a++){
					if(w >= a*50 && w < (a+1)*50){
						int choice = a+1;
						if(a+1 == 9)
							choice = 0;
						World.board.setValue(World.board.turn-1, World.board.clickedField-1, choice);
						World.board.fieldIsClicked = false;
					}
				}
			}
		}
		// sprawdzanie dla ponownej gry
		if(!World.board.gameIsOn){
			leftX = World.board.reinButton.x;
			rightX = leftX + World.board.reinButton.width;
			upY = World.board.reinButton.y;
			downY = upY + World.board.reinButton.height;
			if(x >= leftX && x <= rightX && y >= upY && y <= downY){
				World.reInitialize();
			}
		}
	}
	public void mouseReleased(MouseEvent arg0){
		x = arg0.getX() - mistakenMouseX;
		y = arg0.getY() - mistakenMouseY;
		if(!World.board.checkButton.raised){
			World.board.checkButton.raised = true;
			World.board.turn--;
		}
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