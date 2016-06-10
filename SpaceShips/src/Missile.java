import java.awt.Graphics;

public class Missile {

	double realX, realY;
	double xSpeed, ySpeed;
	int bulletSpeed;
	tankType type;
	double destX, destY;		
	double rotateInRadians;
	int dmg;
	int size;
	int shooterID;
	public Missile(int X, int Y, int DESTX, int DESTY, tankType type, int ID){
		this.type = type;
		realX = X;
		realY = Y;
		destX = DESTX;
		destY = DESTY;
		setSpeeds();
		selectWeapon(type);
		rotateInRadians = setRotate();
		shooterID = ID;
	}
	protected double setRotate(){
		double rotate = 0;
		rotate = Math.atan((realY-destY)/(realX-destX));
		return rotate;
	}
	protected void setSpeeds(){
		double x = destX-realX;
		double y = destY-realY;
		double r = Math.sqrt( x*x + y*y );
		xSpeed = x/r;
		ySpeed = y/r;
	}
	public void move(){
		realX += xSpeed*bulletSpeed;
		realY += ySpeed*bulletSpeed;
	}
	public void smartRemove(){	// usuwa pociski znajduj¹ce siê daleko za ekranem, czym przyspiesza grê
		if(realX>3000 || realY > 2000 || realX < -1000 || realY < -1000)
			Aircraft.enemiesMissiles.remove(this);
	}
	public void display(Graphics g){
		Displayer.displayMissiles(g, this);
	}
	public void checkForHit(){
		if(shooterID != -1){	// wersja dla przeciwników
			double distX = realX - Aircraft.player.getX();
			double distY = realY - Aircraft.player.getY();
			double dist = Math.sqrt(distX*distX + distY*distY);
			if(dist <= bulletSpeed/2+50){
				Aircraft.player.getShot(dmg);
				Aircraft.enemiesMissiles.remove(this);
			}
		}
		else{	// wersja dla czo³gu g³ównego
			for(int i = 0 ; i < Aircraft.enemies.size(); i++){
				double distX = realX - Aircraft.enemies.get(i).getX();
				double distY = realY - Aircraft.enemies.get(i).getY();
				double dist = Math.sqrt(distX*distX + distY*distY);
				if(dist <= bulletSpeed/2+50){
					Aircraft.enemies.get(i).getShot(dmg+Aircraft.player.dmg);
					Aircraft.player.removeMissile(this);
				}
			}
		}
	}
	public void selectWeapon(tankType weptype){
		switch(weptype){
		case FIGHTER :
			bulletSpeed = 40;
			size = 10;
			dmg = 10;
			break;
		case GAUSS :
			bulletSpeed = 100;
			size = 2;
			dmg = 15;
			break;
		case HEAVY :
			bulletSpeed = 30;
			size = 26;
			dmg = 80;
			break;
		case PULSATIVE :
			bulletSpeed = 30;
			size = 10;
			dmg = 10;
			break;
		case SNIPER :
			bulletSpeed = 65;
			size = 4;
			dmg = 20;
			break;
		case STRIKER :
			bulletSpeed = 60;
			size = 15;
			dmg = 20;
			break;
			default :
				break;
		}
		if(shooterID == -1)
			dmg = dmg + (Aircraft.player.improvement.damage + Aircraft.player.dmg);
	}
	public void convert(){
		xSpeed = - xSpeed;
		ySpeed = - ySpeed;
		shooterID = -1;
		Aircraft.player.missiles.add(this);
		Aircraft.enemiesMissiles.remove(this);
	}
}
