import java.awt.Color;

public abstract class Tower {

	protected int cost = 150;
	protected int sellValue = 100;
	protected int xPos, yPos;
	protected int range;
	protected String name;
	protected MissileType missileType;
	protected int minDamage, maxDamage;
	protected int restTime;	// SPEED
	protected final int MAX_LEVEL = 3;
	protected int level = 1;
	protected long lastShotTime = System.currentTimeMillis();
	public boolean showRange = false;
	Color color;
	
	public Tower(int xField, int yField) {
		xPos = xField*Game.SIZE;
		yPos = yField*Game.SIZE;
	}
	
	public void seekAndDestroy(){
		if(System.currentTimeMillis() - lastShotTime >= restTime){
			seekLoop : for(int i = Level.xPath.size() - 1; i >= 0; i--){ // pêtla leci od najdalej wysuniêtego milestona do zerowego
				double milestoneDist = AL.DISTANCE(Level.xPath.get(i), Level.yPath.get(i), xPos, yPos);
				if(milestoneDist <= range){
					// milestone znajduje siê w zasiêgu wie¿y. Szukam potworów wokó³ danego milestona
					for(Monster m : Map.monsters){
						if(m.getMilestone() == i && m.isAlive())
							if(AL.DISTANCE(m.getX(), m.getY(), xPos+Game.SIZE/2, yPos)+Game.SIZE/2 <= range){
								shoot(m);
								lastShotTime = System.currentTimeMillis();
								break seekLoop;
							}
					}
				}
			}
		}
	}
	protected void shoot(Monster monster){
		Map.missiles.add(new Missile(this, monster));
	}
	public Color getColor(){
		return color;
	}
	public int getX(){
		return xPos;
	}
	public int getY(){
		return yPos;
	}
	public int getRange(){
		return range;
	}
	public int getMaxDmg(){
		return maxDamage;
	}
	public int getMinDmg(){
		return minDamage;
	}
	public MissileType getMissileType(){
		return missileType;
	}
	public char getLetter(){
		return name.charAt(0);
	}
	public boolean showRange(){
		return showRange;
	}
	public int getCost(){
		return cost;
	}
	public int getSellValue(){
		return sellValue;
	}
	public String getName(){
		return name;
	}
	public int getLevel(){
		return level;
	}
}
