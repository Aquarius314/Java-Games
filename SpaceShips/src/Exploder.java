import java.awt.Graphics;

public class Exploder extends Skill {

	int x, y;
	int speed = 30;
	int size = 20;
	int explodeDuration = 1000;
	boolean exploded = false;
	public Exploder(Tank tank) {
		super(tank);
		duration = 1100;
		reload = 2150;
	}
	public void perform(){
		super.perform();
		if(isOn){
			if(!exploded){
				x -= speed;
				if(System.currentTimeMillis() - time > explodeDuration ){
					exploded = true;
					for(int i = 0 ; i <= 50 ; i++){
						int dx = x+rnd.nextInt(10000)-5000;
						int dy = y+rnd.nextInt(10000)-5000;
						owner.missiles.add(new Missile(x+dx/100, y+dy/100, dx, dy, tankType.GAUSS, owner.id ));
						owner.missiles.get(owner.missiles.size()-1).bulletSpeed = 30;
					}
				}
			}
		}
	}
	public void turnOn(){
		if(!isOn){
			exploded = false;
			x = owner.x;
			y = owner.y;
		}
		super.turnOn();
	}
	public void display(Graphics g){
		Displayer.displayExploder(g, this);
	}

}
