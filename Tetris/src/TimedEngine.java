import java.util.ArrayList;

public class TimedEngine {

	public static int gameSpeed;
	
	ArrayList <Task> tasks = new ArrayList <Task> ();
	public TimedEngine() {
		gameSpeed = 200;
		tasks.add(new BlockMover());
		tasks.add(new BlockDropper());
		tasks.add(new GameSpeeder());
		tasks.add(new AutomaticPlayer());
	}
	public void calculate(){
		for(int i = 0 ; i < tasks.size(); i++){
			tasks.get(i).perform();
		}
	}

}
