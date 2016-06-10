import java.awt.Color;
import java.awt.Graphics;

public class Player extends Tank {
	
	Improvement improvement;
	int level = 1;
	int experience = 0;
	int expneeded = level*level;
	boolean AUTO_ATTACK = false;
	boolean IMMORTAL_MODE = false;
	
	public Player(int X, int Y, int ID){
		super(X, Y);
		id = -1;
		size = 30;
		improvement = new Improvement();
		maxhealth = 100;
		hp = maxhealth;
	}
	public void chooseTank(){	// pierwsze wybranie czo³gu
		for(int i = 0 ; i < 2 ; i++){
			for(int j = 0 ; j < 3 ; j++){
				if(Displayer.chosenScreen[i][j] == 1){
					switch(i*3+j){
					case 0 :
						type = tankType.FIGHTER;
						break;
					case 1 :
						type = tankType.GAUSS;
						break;
					case 2 :
						type = tankType.HEAVY;
						break;
					case 3 :
						type = tankType.PULSATIVE;
						break;
					case 4 :
						type = tankType.SNIPER;
						break;
					case 5 :
						type = tankType.STRIKER;
						break;
					}
				}
			}
		}
		selectTankType();
	}
	@Override
	public void display(Graphics g){
		for(int i = 0 ; i < missiles.size(); i++){
			missiles.get(i).display(g);
		}
		Displayer.displayPlayer(g, this);
		Displayer.displayHealth(g, this);
		Displayer.displayExperience(g, this);
		Displayer.displayInfo(g, this);
		Displayer.displayRest(g, this);
		for(int i = 0 ; i < skills.size(); i++){
			if(skills.get(i).isOn)
				skills.get(i).display(g);
		}
	}
	protected void displayHealth(Graphics g){
		g.setColor(Color.green);
		g.fillRect(x-(maxhealth+improvement.health)/2, y-size-10, hp, 5);
		g.setColor(Color.red);
		g.fillRect(x-(maxhealth+improvement.health)/2+hp, y-size-10, maxhealth+improvement.health-hp, 5);
	}
	public void improve(){
		if(experience >= expneeded){
			level++;
			experience = 0;
			improvement.levelUp(type);
			expneeded = level*level;
			maxhealth += improvement.health;
			hp = maxhealth ;
			dmg += improvement.damage;
		}
	}
	public void shoot(int shootX, int shootY){
		super.shoot(shootX, shootY);
		if(shootReady){
			if(type == tankType.GAUSS){	// seria drobnych pocisków
				for(int i = 0 ; i < 2*level+3 ; i++){
					missiles.add( new Missile(shootX, shootY, shootX-200, shootY+(i-(2*level+4)/2)*3, type, id) );
				}
			}
			if(type == tankType.PULSATIVE){ // fala pocisków
				for(int i = 0 ; i < level+3 ; i++){
					missiles.add( new Missile(shootX, shootY, shootX-200, shootY+(i-(level+3)/2)*25, type, id) );
				}
			}
			if(type == tankType.SNIPER){
				missiles.add( new Missile(shootX, shootY-20, shootX-200, shootY-20, type, id));
				missiles.add( new Missile(shootX, shootY+20, shootX-200, shootY+20, type, id));
			}
			if(type==tankType.STRIKER || type==tankType.HEAVY || type==tankType.FIGHTER){	// wspólne, zwyk³y.
				missiles.add( new Missile(shootX, shootY, shootX-200, shootY, type, id));
			}
			
			power = 0;
			shootReady = false;
		}
		//Audio.playShot();
	}
	public void getShot(int damage){
		hp -= (damage*(100.0-improvement.resistance)/100.0);
//		System.out.println("cios: "+damage);
//		System.out.println("res: "+improvement.resistance);
//		System.out.println("odpornosc: "+(100.0-improvement.resistance)/100.0);
//		System.out.println("Zabiera: "+(damage*(100.0-improvement.resistance)/100.0));
		if(hp <= 0){
			Aircraft.gameOver();
		}
	}
	public void heal(){
		if(hp < maxhealth)
			hp++;
	}
	public void moveMissiles(){
		for(int i = 0 ; i < missiles.size(); i++){
			// znikanie starych pocisków, przestrzelonych na du¿y minus
			if(missiles.get(i).realX < -1000){
				missiles.remove(i);
				i--;
				continue;
			}
			missiles.get(i).move();
			missiles.get(i).smartRemove();
		}
		// limity pocisków
		if(missiles.size() >= MAXBULLETS )
			missiles.remove(0);
	}
	public void checkForHits(){
		for(int i = 0 ; i < missiles.size(); i++){
			missiles.get(i).checkForHit();
		}
	}
	public void removeMissile(Missile mis){
		missiles.remove(mis);
	}
	public void specialModes(){
		if(AUTO_ATTACK){
			Keyboard.ctrlPressed = true;
			Aircraft.player.skills.get(0).turnOn();
		}
		if(IMMORTAL_MODE){
			hp = maxhealth;
		}
	}
}
