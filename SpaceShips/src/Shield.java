import java.awt.Graphics;

public class Shield extends Skill{

	int radius;
	int intense;
	public Shield(Tank tank){
		super(tank);
		radius = 150;
		duration = 3000;
		reload = 4000;
		intense = 80;
	}
	public void perform(){
		super.perform();
		if(isOn)
			protectFromMissiles();
	}
	public void turnOff(){
		super.turnOff();
		intense = 80;
	}
	public void protectFromMissiles(){
		intense--;
		for(int i = 0 ; i < Aircraft.enemiesMissiles.size(); i++){
			double distance;
			Missile mis = Aircraft.enemiesMissiles.get(i);
			distance = Math.pow(mis.realX - owner.x, 2) + Math.pow(mis.realY - owner.y, 2);
			distance = Math.sqrt(distance);
			if(distance <= radius){	// znaczy ¿e zasz³o trafienie
				Aircraft.enemiesMissiles.remove(mis);
				i--;
				//intense += 20;
			}
		}
		if(intense < 1)
			intense = 1;
		if(intense > 250)
			intense = 250;
	}
	public void display(Graphics g){
		Displayer.displayShield(g, this);
	}
}
