import java.awt.Graphics;

public class Beam extends Skill {

	int dmg;
	public Beam(Tank tank) {
		super(tank);
		reload = 2000;
		duration = 1000;
		dmg = 40;
	}
	public void display(Graphics g){
		Displayer.displayBeam(g, this);
	}
	public void perform(){
		super.perform();
		for(int i = 0 ; i < Aircraft.enemies.size(); i++){
			Enemy en = Aircraft.enemies.get(i);
			if(en.y + 60 >= owner.y - 20 && en.y - 60 <= owner.y + 20)
				Aircraft.enemies.get(i).getShot(dmg);
		}
		for(int i = 0 ; i < Aircraft.enemiesMissiles.size(); i++){
			Missile mis = Aircraft.enemiesMissiles.get(i);
			if(mis.realY + 10 >= owner.y - 20 && mis.realY - 10 <= owner.y + 20){
				Aircraft.enemiesMissiles.remove(mis);
				i--;
			}
		}
	}
}
