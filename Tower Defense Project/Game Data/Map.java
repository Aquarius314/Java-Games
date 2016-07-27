import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Map {

	static int WIDTH;
	static int HEIGHT;
	int monstersToGenerate = 0;
	private Level currentLevel;
	//TODO design for many different levels

	MonsterGenerator monsterGenerator;
	static ArrayList <Monster> monsters = new ArrayList <Monster> ();
	static ArrayList <Missile> missiles = new ArrayList <Missile> ();
	static ArrayList <Tower> towers = new ArrayList <Tower> ();
	
	public Map() {
		loadLevel(Game.level1);
		monsterGenerator = new MonsterGenerator(this);
		manualSettings();
	}
	private void manualSettings(){
		//towerFactory.buildTower(TowerType.MAGE, 2, 3);
	}
	
	public void loadLevel(Level level){
		currentLevel = level;
		HEIGHT = level.getHeight();
		WIDTH = level.getWidth();
		monstersToGenerate = level.getMonstersNumber();
	}
	public void calculate(){
		moveMonsters();
		reactTowers();
		moveMissiles();
		removeDeadMonsters();
	}
	private void reactTowers(){
		for(Tower t : towers)
			t.seekAndDestroy();
	}
	private void moveMonsters(){
		for(Monster m : monsters)
			if(m.isAlive())
				m.move();
	}
	private void moveMissiles(){
		try{
			ArrayList <Missile> copyOfMissiles = new ArrayList <Missile> ();
			copyOfMissiles.addAll(missiles);
			
			for(Missile m : copyOfMissiles)
				m.move();
		}
		catch(ConcurrentModificationException e){
			System.out.println("Concurrent from moveMissiles!");
		}
	}
	public void paint(Graphics g){
		Displayer.displayMap(g, this);
		paintMonsters(g);
		paintTowers(g);
		paintAnimations(g);
		paintMissiles(g);
	}
	private void paintAnimations(Graphics g){
		Displayer.displayExplosions(g);
	}
	private void paintTowers(Graphics g){
		for(Tower t : towers)
			Displayer.displayTower(g, t);
	}
	private void paintMonsters(Graphics g){
		try{
			ArrayList <Monster> copyOfMonsters = new ArrayList <Monster> ();
			copyOfMonsters.addAll(monsters);
			
			for(Monster m : copyOfMonsters)
				Displayer.displayMonster(g, m);
		}
		catch(ConcurrentModificationException e){
			System.out.println("Concurrent from paintMonsters!");
		}
	}
	private void removeDeadMonsters(){
		try{
			ArrayList <Monster> deadMonsters = new ArrayList <Monster> ();
			deadMonsters.addAll(monsters);
			
			for(Monster m : deadMonsters){
				if(!m.isAlive()){
					m.tryBury();
				}
			}
		}catch(ConcurrentModificationException e){
			System.out.println("Concurrent from removeDeadMonsters!");
		}
	}
	private void paintMissiles(Graphics g){
		try{
			ArrayList <Missile> copyOfMissiles = new ArrayList <Missile> ();
			copyOfMissiles.addAll(missiles);
			
			for(Missile m : copyOfMissiles)
				Displayer.displayMissile(g, m);
		}
		catch(ConcurrentModificationException e){
			System.out.println("Concurrent from paintMissiles!");
		}
	}
	public void markFieldAsOccupied(int x, int y){
		if(currentLevel.getFieldContent(x, y) == 0)
			currentLevel.setField(y, x, 4);
	}
	public Tower getTower(int index){
		return towers.get(index);
	}
	public Level getLevel(){
		return currentLevel;
	}
	public int getLevelStartX(){
		return currentLevel.getStartX();
	}
	public int getLevelStartY(){
		return currentLevel.getStartY();
	}
	
}
