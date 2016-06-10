
public class BlockMover extends Task{

	public BlockMover() {
		step = 300;
	}
	protected void performTask(){
		for(int i = 0 ; i < World.players.size(); i++){
			if(World.players.get(i).isAlive())
				World.players.get(i).getBoard().getBlock().moveDown();
		}
	}

}
