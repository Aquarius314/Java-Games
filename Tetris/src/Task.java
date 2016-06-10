
public abstract class Task {

	double step = 0;
	long time = System.currentTimeMillis();
	
	public void perform(){
		if(System.currentTimeMillis() - time >= step*(double)(1000.0/TimedEngine.gameSpeed)){
			updateTime();
			performTask();
		}
	}
	protected void performTask(){
		
	}
	private void updateTime(){ 
		time = System.currentTimeMillis();
	}
}
