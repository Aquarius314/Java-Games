import java.util.ArrayList;

public class World {

	long time;
	public static boolean gameIsOn;
	
	static ArrayList <Player> players;
	TimedEngine engine; 

	ArrowCoder settings1;
	ArrowCoder settings2;
	
	public World(){
		init();
	}
	public void init(){
		Player.restartTotalNr();
		players  = new ArrayList <Player> ();
		engine = new TimedEngine();
		settings1 = new ArrowCoder(65, 68, 87, 83, 69);
		settings2 = new ArrowCoder(37, 39, 38, 40, 97);
		players.add(new Player("skrypt SI", settings1));
		players.add(new Player("Gracz2", settings2));
		players.get(0).setAutomatic();
		gameIsOn = true;
		time = System.currentTimeMillis();
	}
	public void calculate(){
		if(gameIsOn)
			engine.calculate();
	}
	public static Player getPlayer(int index){
		return players.get(index);
	}
}