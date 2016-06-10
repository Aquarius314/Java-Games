import java.awt.Graphics;
import java.util.Random;

public class Star {
	
	Random gen = new Random();
	int x, y;
	private int s;	// size / speed, dlatego tylko "s"
	public Star(){
		x = -5;
		y = gen.nextInt(1000);
		int rand;
		rand = gen.nextInt(10);
		// zak³ócanie równego rozk³adu prawdopodobieñstwa dla s:
		if(rand < 5)
			s = 1;
		if(rand >= 5 && rand < 8)
			s = 2;
		if(rand >= 8)
			s = 3;
	}
	public void moveAndDie(){
		x += s;
		if(x > 1500){
			Aircraft.stars.remove(this);
		}
	}
	public void display(Graphics g){
		Displayer.displayStar(g, this);
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getS(){
		return s;
	}
}
