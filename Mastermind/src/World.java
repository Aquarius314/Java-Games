import java.awt.Graphics;

public class World {

	static Board board;
	public World(){
		board = new Board(10, 10, 500, 600);
	}
	public void calculate(){
		board.actions();
		if(board.turn == 0){
			board.gameIsOn = false;
		}
	}
	public static void reInitialize(){
		// inicjacja klasy Board
		board = new Board(10, 10, 500, 600);
	}
	public void display(Graphics g){
		Displayer.displayBoard(g, board);
		if(board.gameIsOn){
			Displayer.displayButton(g, board.showButton);
			if( !board.checkForEmptiness(board.turn-1) )
				Displayer.displayButton(g, board.checkButton);
		}
		if(!board.gameIsOn){
			Displayer.displayResult(g, board);
			Displayer.displayButton(g, board.reinButton);
		}
	}
}