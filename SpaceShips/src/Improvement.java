
public class Improvement {

	public int health;
	public int damage;
	public int resistance;
	public int speed;
	public int reload;
	public Improvement(){
		health = 0;
		damage = 0;
		resistance = 0;
		speed = 0;
		reload = 0;
	}
	public void levelUp(tankType type){
		switch(type){
		case FIGHTER :
			health += 30;
			damage += 5;
			resistance += 5;
			break;
		case GAUSS :
			health += 15;
			damage += 2;
			resistance += 10;
			break;
		case HEAVY :
			health += 50;
			damage += 15;
			resistance += 15;
			break;
		case PULSATIVE :
			health += 30;
			damage += 10;
			resistance += 5;
		case SNIPER :
			health += 20;
			damage += 10;
			resistance += 10;
			break;
		case STRIKER :
			health += 20;
			damage += 12;
			resistance += 3;
			break;
			default :
				System.out.println("Problemy w Improvement.levelUp");
				break;
		}
	}
}
