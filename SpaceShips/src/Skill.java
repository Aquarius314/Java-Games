import java.awt.Graphics;
import java.util.Random;

public class Skill {

	Random rnd = new Random();
	// poni¿sze zmienne wyra¿one s¹ w milisekundach!
	long time;
	int reload;
	int duration;
	
	Tank owner;
	boolean isOn = false;
	public Skill(Tank tank){
		owner = tank;
		time = System.currentTimeMillis() - 10000;
	}
	public void perform(){
		if(isOn){	// wygasza po up³ywie czasu "duration"
			if(System.currentTimeMillis() - time >= duration)
				turnOff();
		}
	}
	public void turnOff(){
		isOn = false;
	}
	public void turnOn(){
		if(!isOn){
			if(System.currentTimeMillis() - time >= reload){
				isOn = true;
				time = System.currentTimeMillis();
			}
		}
	}
	public void display(Graphics g){
	}
}
