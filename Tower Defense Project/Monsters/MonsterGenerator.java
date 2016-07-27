import java.util.Random;

public class MonsterGenerator{

	private Map parentMap;
	private int generationPeriod = 300;
	private int xStart, yStart;
	private long lastCreationTime;
	private boolean doubleCreation = false;
	
	public MonsterGenerator(Map parentMap) {
		lastCreationTime = System.currentTimeMillis();
		this.parentMap = parentMap;
		setStart();
	}

	public void generate() {
		if(parentMap.monstersToGenerate > 0){
			if(System.currentTimeMillis() - lastCreationTime >= generationPeriod){
				lastCreationTime = System.currentTimeMillis();
				createMonster(xStart, yStart);
				if(doubleCreation)
					createMonster(xStart, yStart);
			}
		}
	}
	private void createMonster(int x, int y){
		Map.monsters.add(new Monster(x, y));
		parentMap.monstersToGenerate--;
	}
	private void setStart(){
		xStart = parentMap.getLevelStartX();
		yStart = parentMap.getLevelStartY();
	}
	public int getTimePeriod(){
		return generationPeriod;
	}
	

}
