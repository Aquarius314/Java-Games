import java.awt.Color;

public class Artillery extends Tower{
	
	private int explosionRange;

	public Artillery(int xField, int yField) {
		super(xField, yField);
		color = Color.red;
		restTime = 2500;
		range = 120;
		missileType = MissileType.BOMB;
		minDamage = 10;
		maxDamage = 20;
		explosionRange = 30;
		name = "Bomber";
	}
	public int getExplosionRange(){
		return explosionRange;
	}
	
	@Override
	protected void shoot(Monster monster){
		Map.missiles.add(new Bomb(this, monster));
	}

}
