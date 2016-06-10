import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Tank {

	int id;
	int maxspeed = 30;
	static final int MAXBULLETS = 1000;
	int maxhealth;
	protected int x, y;
		// zmienne grywalne
	double xSpeed, ySpeed;
	int size;
	int speed;
	int hp;
	int dmg;
	int power = 0;
	int reload = 10;	// minimum 1
	tankType type;
	ArrayList <Skill> skills = new ArrayList <Skill> ();
	ArrayList <Missile> missiles = new ArrayList <Missile> ();
		// zmienne pomocnicze
	boolean shootReady = true;
	Color color;
	
	public Tank(int X, int Y){
		x = X;
		y = Y;
		xSpeed = 0;
		ySpeed = 0;
		speed = 3;
		dmg = 5;
		color = new Color(255, 255, 0);
	}
	protected void selectTankType(){
		switch(type){
		case FIGHTER :
			reload = 1;
			dmg = 20;
			maxspeed = 30;
			skills.add(new Shield(this));
			break;
		case GAUSS :
			reload = 6;
			dmg = 8;
			maxspeed = 25;
			skills.add(new Purification(this));
			break;
		case HEAVY :
			reload = 10;
			dmg = 50;
			maxspeed = 20;
			skills.add(new Exploder(this));
			break;
		case PULSATIVE :
			reload = 12;
			dmg = 15;
			maxspeed = 30;
			skills.add(new Wave(this));
			break;
		case STRIKER :
			reload = 12;
			dmg = 35;
			maxspeed = 35;
			skills.add(new Beam(this));
			break;
		case SNIPER :
			reload = 8;
			dmg = 40;
			maxspeed = 30;
			skills.add(new Supershooter(this));
			break;
			default :
				System.out.println("Z³y rodzaj statku");
				break;
		}
	}
	public void move(){
		// ograniczenie prêdkoœci!
		if(Math.abs(xSpeed) <= maxspeed){
			if(Keyboard.Apressed)
				xSpeed -= speed;
			if(Keyboard.Dpressed)
				xSpeed += speed;
		}
		if(Math.abs(ySpeed) <= maxspeed){
			if(Keyboard.Wpressed)
				ySpeed -= speed;
			if(Keyboard.Spressed)
				ySpeed += speed;
		}
			
		// teraz wykonywanie tych ruchów.
		x += xSpeed;
		y += ySpeed;
		// oraz wytracanie prêdkoœci
		if(xSpeed != 0)
			xSpeed -= (Math.abs(xSpeed)/xSpeed);
		if(ySpeed != 0)
			ySpeed -= (Math.abs(ySpeed)/ySpeed);
		//reflect();
	}
	public void rest(){			// regeneracja strzelania i umiejêtnoœci specjalnych
		power++;
		if(power == reload)
			shootReady = true;
	}
	// tylko w celu dziedziczenia
	public void shoot(int x, int y){
		// nie usuwaæ
	}
	public void display(Graphics g){
		Displayer.displayTank(g, this);
		Displayer.displayHealth(g, this);
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
