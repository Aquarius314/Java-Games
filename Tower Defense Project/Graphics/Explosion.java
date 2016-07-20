import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Explosion {

	private int lifetime = 180;
	private long time;
	private boolean frozen = false;
	private int x, y;
	
	public Explosion(int x, int y) {
		time = System.currentTimeMillis();
		this.x = x;
		this.y = y;
	}
	public void explode(Graphics g){
		if(!frozen){
			// tutaj kod eksplozji
			Random r = new Random();
			//g.setColor(new Color(r.nextInt(155)+100, 10, 10));
			//size += 10;
			//g.fillOval(x + Game.xMap - size/2, y + Game.yMap - size/2, size, size);
			Color color = Color.yellow;
			for(int i = 0; i < 20; i++){
				if(r.nextBoolean())
					color = color.brighter();
				else
					color = color.darker();
				g.setColor(color);
				int s = r.nextInt(20)+10;
				g.fillOval(x + r.nextInt(20)-10 + Game.xMap - s/2, y + r.nextInt(20)-10 + Game.yMap - s/2, s, s);
			}
			if(System.currentTimeMillis() - time >= lifetime){
				frozen = true;
			}
		}
	}
	public boolean isFrozen(){
		return frozen;
	}

}
