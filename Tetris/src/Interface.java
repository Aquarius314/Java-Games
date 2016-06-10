
public class Interface {

	public static void moveLeft(Player p){
		p.getBoard().getBlock().moveLeft();
	}
	public static void moveRight(Player p){
		p.getBoard().getBlock().moveRight();
	}
	public static void moveDown(Player p){
		p.getBoard().getBlock().moveDown();
	}
	public static void drop(Player p){
		p.getBoard().getBlock().drop();
	}
	public static void rotate(Player p){
		p.getBoard().getBlock().rotate();
	}
	public static void revertGame(){
		World.gameIsOn = !World.gameIsOn;
	}
	public static void restart(){
		Win.world = new World();
	}

}
