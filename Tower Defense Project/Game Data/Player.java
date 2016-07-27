
public class Player {

	private static int lifes = 20;
	private static int money = 400;
	
	private Player() {
		// TODO Auto-generated constructor stub
	}
	public static int getLifes(){
		return lifes;
	}
	public static void giveMoney(int value){
		money += value;
	}
	public static void takeMoney(int value){
		if(value > money){
			System.out.println("Cannot take more money than player currently has!");
		}
		else{
			money -= value;
		}
	}
	public static int getMoney(){
		return money;
	}

}
