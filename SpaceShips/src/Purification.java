import java.awt.Graphics;

public class Purification extends Shield{

	public Purification(Tank tank) {
		super(tank);
		radius = 10000;
		duration = 150;
		reload = 10000;
	}
	public void perform(){
		super.perform();
		Aircraft.enemies.clear();
	}
	public void display(Graphics g){
		Displayer.displayPurification(g, this);
	}

}
