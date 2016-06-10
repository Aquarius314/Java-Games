
public class Player {
	
	private Board board;
	private int nr;
	private static int totalNr = 0;
	private String name;
	private int score = 0;
	private boolean isLiving = true;
	private ArrowCoder codes;
	private Curse[] curses = new Curse[4];
	int[] points = new int[Board.getWidth()];	// do oceniania gdzie wrzucac klocka (automatycznie)

	boolean left = false;
	boolean right = false;
	
	private boolean automatic = false;

	public Player(String name, ArrowCoder settings) {
		setCurses();
		codes = settings;
		this.name = name;
		this.nr = totalNr;
		totalNr++;
		board = new Board(nr, this);
	}
	public void setCurses(){
		for(int i = 0 ; i < curses.length ; i++){
			//curses[i] = new Curse();
		}
	}
	public Board getBoard(){
		if(board == null){
			System.out.println("Gracz nie posiada zadnej planszy do zwrocenia!");
			throw new NullPointerException("");
		}
		return board;
	}
	public void addPoints(int value){
		score += value;
	}
	public int getNr(){
		return nr;
	}
	public void kill(){
		isLiving = false;
	}
	public boolean isAlive(){
		return isLiving;
	}
	public String getName(){
		return name;
	}
	public int getScore(){
		return score;
	}
	public final int getLeftCode(){
		return codes.left;
	}
	public final int getRightCode(){
		return codes.right;
	}
	public final int getRotateCode(){
		return codes.rotate;
	}
	public final int getDropCode(){
		return codes.drop;
	}
	public final int getSlowCode(){
		return codes.slow;
	}
	public void setAutomatic(){
		automatic = true;
	}
	public boolean isAutomatic(){
		return automatic;
	}
	public static void restartTotalNr(){
		totalNr = 0;
	}
	
}