import java.util.Random;

public class MonsterGenerator implements Runnable{

	private Map parentMap;
	private int timePeriod = 200;
	private int xStart, yStart;
	
	public MonsterGenerator(Map parentMap) {
		this.parentMap = parentMap;
		setStart();
	}

	@Override
	public void run() {
		if(parentMap.monstersToGenerate > 0){
			createMonster(xStart, yStart);
		}
	}
	private void createMonster(int x, int y){
		x += new Random().nextInt(9)-4;
		y += new Random().nextInt(9)-4;
		Map.monsters.add(new Monster(x, y));
		parentMap.monstersToGenerate--;
	}
	private void setStart(){
		xStart = parentMap.getLevelStartX();
		yStart = parentMap.getLevelStartY();
	}
	public int getTimePeriod(){
		return timePeriod;
	}
	

}
