import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{

	private static int x, y;
	int mistakenMouseX = 8;
	int mistakenMouseY = 30;
	static boolean leftClick = false;
	static boolean middleClick = false;
	static boolean rightClick = false;

	public void mouseClicked(MouseEvent arg0){
		int xClick = -1, yClick = -1;
		if(x > Game.xMap && y > Game.yMap){
			xClick = (x-Game.xMap)/Game.SIZE;
			yClick = (y-Game.yMap)/Game.SIZE;
		}
		//TODO remove below
		if(arg0.getButton() == MouseEvent.BUTTON1)
			Window.game.currentMap.towerFactory.buildTower(TowerType.ARCHER, xClick, yClick);
		else if(arg0.getButton() == MouseEvent.BUTTON3)
			Window.game.currentMap.towerFactory.buildTower(TowerType.ARTILLERY, xClick, yClick);
		else
			Window.game.currentMap.towerFactory.destroyTower(xClick, yClick);
	}
	public void mouseEntered(MouseEvent arg0){
	}
	public void mouseExited(MouseEvent arg0){
	}
	public void mousePressed(MouseEvent arg0){
		if(arg0.getButton() == MouseEvent.BUTTON1)
			leftClick = true;
		if(arg0.getButton() == MouseEvent.BUTTON2)
			middleClick = true;
		if(arg0.getButton() == MouseEvent.BUTTON3)
			rightClick = true;
	}
	public void mouseReleased(MouseEvent arg0){
		if(arg0.getButton() == MouseEvent.BUTTON1)
			leftClick = false;
		if(arg0.getButton() == MouseEvent.BUTTON2)
			middleClick = false;
		if(arg0.getButton() == MouseEvent.BUTTON3)
			rightClick = false;
	}
	public void mouseMoved(MouseEvent arg0){
		x = arg0.getX() - mistakenMouseX;
		y = arg0.getY() - mistakenMouseY;
	}
	public void mouseDragged(MouseEvent arg0) {
		x = arg0.getX() - mistakenMouseX;
		y = arg0.getY() - mistakenMouseY;
	}
	public static int getX(){
		return x;
	}
	public static int getY(){
		return y;
	}
	public static boolean isLeftClicked(){
		return leftClick;
	}
	public static boolean isMiddleClicked(){
		return middleClick;
	}
	public static boolean isRightClicked(){
		return rightClick;
	}
}