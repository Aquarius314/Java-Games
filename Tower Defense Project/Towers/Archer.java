import java.awt.Color;

public class Archer extends Tower{

	public Archer(int xField, int yField) {
		super(xField, yField);
		color = Color.green;
		restTime = 400;
		range = 110;
		missileType = MissileType.ARROW;
		minDamage = 7;
		maxDamage = 12;
		name = "Archer";
	}

	

}
