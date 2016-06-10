
public class BlockDropper extends Task {

	public BlockDropper() {
		step = 0;
	}
	protected void performTask(){
		for(int i = 0 ; i < World.players.size(); i++){
			if(World.players.get(i).getBoard().getBlock().isFalling && World.players.get(i).isAlive())
				World.players.get(i).getBoard().getBlock().moveDown();
		}
	}

}
