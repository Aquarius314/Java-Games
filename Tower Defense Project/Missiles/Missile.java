import java.util.Random;

public class Missile {

	protected MissileType type;
	protected double speed = 8;
	protected int xPos, yPos;
	protected int damage;
	Monster purpose;
	
	public Missile(Tower t, Monster m) {
		type = t.getMissileType();
		purpose = m;
		damage = new Random().nextInt(t.getMaxDmg()-t.getMinDmg()+1)+t.getMinDmg();
		xPos = t.getX()+Game.SIZE/2;
		yPos = t.getY()+Game.SIZE/2;
	}
	public void move(){
		double x = purpose.getX() - xPos;
		double y = purpose.getY() - yPos;
		double r = Math.sqrt( Math.pow(x, 2) + Math.pow(y, 2) );
		double xSpeed = x/r;
		double ySpeed = y/r;
		xPos += xSpeed*speed;
		yPos += ySpeed*speed;
		// trafienia:
		if(AL.DISTANCE(xPos, yPos, purpose.getX(), purpose.getY()) <= speed+1){
			hit();
		}
	}
	protected void hit(){
		purpose.getShot(this);
		Map.missiles.remove(this);
	}
	public int getDamage(){
		return damage;
	}
	public int getX(){
		return xPos;
	}
	public int getY(){
		return yPos;
	}
	public MissileType getType(){
		return type;
	}

}
