import java.awt.Graphics;

public class Wave extends Skill {

	int r;
	public Wave(Tank tank) {
		super(tank);
		r = 50;
		reload = 3000;
		duration = 1000;
	}
	public void perform(){
		super.perform();
		r += 30;
		if(isOn)
			for(int i = 0 ; i < Aircraft.enemiesMissiles.size(); i++){
				Missile mis = Aircraft.enemiesMissiles.get(i);
				double dist = Math.sqrt(Math.pow(mis.realX-owner.x, 2) + Math.pow(mis.realY-owner.y, 2));
				if(dist <= r){
					Aircraft.enemiesMissiles.get(i).convert();
				}
			}
	}
	public void turnOn(){
		if(!isOn)
			r = 50;
		super.turnOn();
	}
	public void turnOff(){
		super.turnOff();
	}
	public void display(Graphics g){
		Displayer.displayWave(g, this);
	}

}
