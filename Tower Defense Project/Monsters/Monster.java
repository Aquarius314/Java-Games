import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Monster {

	private final int LYING_DEAD_TIME = 3000;

	private double xPos, yPos;
	private Point randCoordinate = new Point();
	private double xOldSpeed, yOldSpeed;
	private boolean firstStepMade = false;
	private int reaction;
	private int size;
	private double speed;
	private int milestone = 0;
	private int R, G, B;
	private int maxHp = 30;
	private int hp;
	private boolean alive = true;
	private long deathMoment;
	
	public Monster(int x, int y) {
		hp = maxHp;
		xPos = x;
		yPos = y;
		Random r = new Random();
		randCoordinate.setLocation(r.nextInt(16)-11, r.nextInt(16)-11);
		reaction = r.nextInt(20)+5;
		speed = r.nextDouble()*r.nextInt(3)+1;
		R = r.nextInt(256);
		G = r.nextInt(256);
		B = r.nextInt(256);
		size = r.nextInt(8)+6;
	}
	public void move(){
		double xSpeed = Level.xPath.get(milestone+1) - xPos;
		double ySpeed = Level.yPath.get(milestone+1) - yPos;
		double r = Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2));
		xSpeed /= r;
		ySpeed /= r;
		if(firstStepMade){
			for(int i = 0; i < 4; i++){
				xSpeed = (xSpeed + xOldSpeed)/2;
				ySpeed = (ySpeed + yOldSpeed)/2;
			}
		}
		else{
			firstStepMade = true;
		}
		xOldSpeed = xSpeed;
		yOldSpeed = ySpeed;
		xPos += speed*xSpeed;
		yPos += speed*ySpeed;
		
		// przeskakiwanie na kolejny milestone
		double disToNextMilestone = AL.DISTANCE(Level.xPath.get(milestone+1), Level.yPath.get(milestone+1), xPos, yPos);
		if(disToNextMilestone < reaction)
			milestone++;
		// czy nie wyskoczyl z planszy?
		if(milestone+1 == Level.xPath.size()){
			alive = false;
			deathMoment = System.currentTimeMillis();
		}
	}
	public void tryBury(){
		if(System.currentTimeMillis() - deathMoment >= LYING_DEAD_TIME){
			Map.monsters.remove(this);
		}
	}
	public void getShot(Missile m){
		hp -= m.getDamage();
		if(hp <= 0){
			alive = false;
			deathMoment = System.currentTimeMillis();
		}
	}
	public void getShot(int dmg){
		hp -= dmg;
		if(hp <= 0){
			alive = false;
			deathMoment = System.currentTimeMillis();
		}
	}
	public int getX(){
		return (int)(xPos + randCoordinate.getX());
	}
	public int getY(){
		return (int)(yPos + randCoordinate.getY());
	}
	public int getSize(){
		return size;
	}
	public Color getColor(){
		return new Color(R, G, B);
	}
	public boolean isAlive(){
		return alive;
	}
	public int getMilestone(){
		return milestone;
	}
}