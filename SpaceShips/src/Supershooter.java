
public class Supershooter extends Skill {

	public Supershooter(Tank tank) {
		super(tank);
		duration = 1000;
		reload = 4000;
	}
	public void perform(){
		super.perform();
		for(int i = -1 ; i < 2 ; i++){
			owner.missiles.add(new Missile(owner.x+i*25, owner.y+i*20, owner.x - 100, owner.y+i*20, owner.type, owner.id));
		}
	}

}
