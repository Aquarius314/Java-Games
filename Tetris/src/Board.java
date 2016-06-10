import java.util.ArrayList;
import java.util.Random;

public class Board {

	private Player motherPlayer;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 20;
	private Field[][] fields;
	private Block fallingBlock;
	private int nr;
	Random rnd = new Random();
	private int nextType;
	
	public Board(int nr, Player mother) {
		motherPlayer = mother;
		this.nr = nr;
		fields = createFieldsArray(WIDTH, HEIGHT);
		fallingBlock = new Block(rnd.nextInt(7)+1, this);
		nextType = new Random().nextInt(7)+1;
	}
	public void nextBlock(){
		fallingBlock = new Block(nextType, this);
		nextType = rnd.nextInt(7)+1;
		//nextType = 1;
	}
	public int getNextBlockType(){
		return nextType;
	}
	public Block getBlock(){
		if(fallingBlock == null){
			System.out.println("Plansza nie posiada zadnego aktywnego klocka!");
			throw new NullPointerException("");
		}
		else
			return fallingBlock;
	}
	public Field getField(int i, int j){
		if(i < 0 || j < 0 || i >= fields.length || j >= fields[0].length){
			System.out.println("Zle podane indeksy dla pola! (i="+i+", fields.length="+fields.length+", j="+j+", fields[0].length="+fields[0].length);
			throw new IllegalArgumentException("");
		}
		return fields[i][j];
	}
	public void checkRows(){	// tutaj usuwane sa pelne rzedy
		ArrayList <Integer> indexesToClear = new ArrayList <Integer> ();
		for(int j = 0 ; j < getHeight(); j++){
			int fullness = 0;
			for(int k = 0 ; k < Board.getWidth(); k++){
				if(getField(k, j).getContent() == 2)
					fullness++;
			}
			if(fullness == getWidth())
				indexesToClear.add(j);
		}
		
		World.players.get(this.nr).addPoints((int)(Math.pow(indexesToClear.size(), 2)));
		
		for(int i = 0 ; i < indexesToClear.size(); i++)
			clearRow(indexesToClear.get(i));
		
	}
	public void checkColumns(){	// tutaj sprawdzany jest koniec gry
		for(int i = 0 ; i < getWidth() ; i++)
			if(fields[i][0].getContent() == 2){
				System.out.println("Koniec gry dla gracza "+motherPlayer.getNr());
				motherPlayer.kill();
			}
	}
	public void clearRow(int nr){
		for(int i = 0 ; i < getWidth() ; i++){
			for(int j = nr ; j > 0 ; j--){
				fields[i][j].setContent( fields[i][j-1].getContent() );
				if(fields[i][j].getContent() == 2)
					fields[i][j].setColor( fields[i][j-1].getColor() );
			}
		}
	}
	private Field[][] createFieldsArray(int w, int h){
		Field[][] f = new Field[w][h];
		for(int i = 0 ; i < w ; i++){
			for(int j = 0 ; j < h ; j++){
				f[i][j] = new Field();
			}
		}
		return f;
	}
	public static int getWidth(){
		return WIDTH;
	}
	public static int getHeight(){
		return HEIGHT;
	}
	public int getNr(){
		return nr;
	}
	public Player getMother(){
		return motherPlayer;
	}
}
