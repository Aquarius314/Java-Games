import java.awt.Color;

public class Mage extends Tower{

	public Mage(int xField, int yField) {
		super(xField, yField);
		color = Color.blue;
		restTime = 1200;
		range = 130;
		missileType = MissileType.MAGIC;
		minDamage = 15;
		maxDamage = 22;
		name = "Mage";
	}

}
