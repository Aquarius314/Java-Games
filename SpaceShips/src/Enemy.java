import java.awt.Color;
import java.util.Random;

public class Enemy extends Tank {
	Random gen = new Random();

	int dirX = 0, dirY = 0;
	int range = 1200;
	Automove moveType;
	public Enemy(int X, int Y, int ID) {
		super(X, Y);
		id = ID;
		size = 40;
		reload = 21-Aircraft.player.level;
		color = new Color(255, 0, 0);
		maxhealth = 300;
		hp = maxhealth;
		//moveType = new Automove(gen.nextInt(3)+1, x, y);	// wyró¿niamy 4 rodzaje autoruchów, od 1 do 4
		moveType = new Automove(gen.nextInt(4)+1, x, y);
		type = tankType.FIGHTER;
		//selectTankType();
	}
	public void autofire(){
		double destX = Aircraft.player.getX();
		double destY = Aircraft.player.getY();
		double distX = destX-x;
		double distY = destY-y;
		double distance = Math.sqrt(distX*distX + distY*distY);
		if(distance <= range)
			shoot(this.x, this.y, (int)destX, (int)destY);
	}
	public void shoot(int shootX, int shootY, int destX, int destY){
		if(shootReady){
			Aircraft.enemiesMissiles.add( new Missile(shootX, shootY, destX, destY, type, id) );
			power = 0;
			shootReady = false;
		}
	}
	public void getShot(int damage){
		hp -= damage;
		if(hp <= 0){
			Aircraft.enemies.remove(this);
			Aircraft.player.experience++;
			Aircraft.score++;
		}
	}
	public void automove(){
//		if(x == 400)
//			dirX = -10;
//		if(x == 100)
//			dirX = 10;
//		x += dirX;
		moveType.go(x, y);
		x += 5*moveType.getSpeedX();
		y += 5*moveType.getSpeedY();
	}
}
