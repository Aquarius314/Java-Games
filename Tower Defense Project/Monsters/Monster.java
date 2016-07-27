import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Monster {

	private final int LYING_DEAD_TIME = 3000;
	private final int DIRECTION_ESTIMATIONS_NUMBER = 3;	// how many times monsters should 'estimate' their direction

	private double xPos, yPos;
	private Point randCoordinate;
	private double xOldSpeed, yOldSpeed;
	private boolean firstStepMade = false;
	// default stats, for non-randomized monsters
	private int reaction = 15;
	private int size = 10;
	private double speed = 2.5;
	private int milestone = 0;
	private int R = 100, G = 100, B = 200;
	private int maxHp = 30;
	private int hp;
	private boolean alive = true;
	private long deathMoment;
	private long bornMoment;
	private double rotation = 0;
	private double oldRotation;
	
	public Monster(int x, int y) {
		bornMoment = System.currentTimeMillis();
		hp = maxHp;
		xPos = x;
		yPos = y;
		randCoordinate = new Point(0, 0);
		randomize();
	}
	private void randomize(){
		Random r = new Random();
		randCoordinate.setLocation(r.nextInt(16)-11, r.nextInt(16)-11);
		reaction = r.nextInt(20)+5;
		speed = r.nextDouble()*r.nextInt(3)+1;
		R = r.nextInt(256);
		G = r.nextInt(256);
		B = r.nextInt(256);
		size = r.nextInt(8)+6;
		rotation = r.nextInt(360);
	}
	public void move(){
		double xSpeed = Level.xPath.get(milestone+1) - xPos;
		double ySpeed = Level.yPath.get(milestone+1) - yPos;
		double r = Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2));
		xSpeed /= r;
		ySpeed /= r;
		if(firstStepMade){
			for(int i = 0; i < DIRECTION_ESTIMATIONS_NUMBER; i++){
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
			die();
		}
		// obracanie w strone nastepnego milestona
		if(isAlive())
			rotate();
	}
	private void die(){
		alive = false;
		deathMoment = System.currentTimeMillis();
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
	private void rotate(){
		// rozwazam cztery przypadki (4 cwiartki ukladu wsp)
		int part = 0;
		double cx = Level.xPath.get(milestone+1) - getX();
		double cy = Level.yPath.get(milestone+1) - getY();
		if(cx >= 0 && cy >= 0)
			part = 1;
		if(cx < 0 && cy >= 0)
			part = 2;
		if(cx < 0 && cy < 0)
			part = 3;
		if(cx >= 0 && cy < 0)
			part = 4;
		double rotate = oldRotation;
		try{
			rotate = 180/Math.PI*Math.atan((-cy) / (-cx));
		}
		catch(ArithmeticException e){
			//ignore
		}
		
		if(part == 2 || part == 3)
			rotate += 180;
		
		int rot = (int)speed*3;
		if(Math.abs(rotation-rotate) >= rot+1){
			if(rotation < rotate)
				rotation += rot;
			if(rotation > rotate)
				rotation -= rot;
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
	public int getTotalLifeTime(){
		return (int)(deathMoment - bornMoment);
	}
	public double getRotationInRadians(){
		return rotation/180*Math.PI;
	}
}