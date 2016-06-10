import java.awt.Graphics;

public class Boss extends Enemy{

	public Boss(int X, int Y, int ID) {
		super(X, Y, ID);
		maxhealth = 10000;
		hp = maxhealth;
		moveType = new Automove(5, x, y);
	}
	public void display(Graphics g){
		Displayer.displayTank(g, this);
	}
	
}
