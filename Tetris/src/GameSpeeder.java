
public class GameSpeeder extends Task {

	public GameSpeeder() {
		step = 200;
	}
	protected void performTask(){
		TimedEngine.gameSpeed++;
	}

}
