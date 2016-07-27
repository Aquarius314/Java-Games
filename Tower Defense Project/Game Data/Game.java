
import java.awt.Graphics;

public class Game {
	
	static int[][] board1 = {
			{1, 1, 1, 1, 1, 2, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 0, 1, -1},
			{1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
			{1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	};
	static int[][] board2 = {
			{-1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
			{0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
			{0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
			{0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
			{0, 1, 0, 1, 0, 1, 0, 2, 0, 1, 0, 1, 0, 1},
			{0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
			{0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1},
			{0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
			{0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};

	public static final int SIZE = 40;
	public static Map currentMap;
	public static final int xMap = 200, yMap = 50;
	static Level level1 = new Level(5000, board2);
	
	public Game(){
		currentMap = new Map();
	}
	public void gameLoop(){
		currentMap.monsterGenerator.generate();
		currentMap.calculate();
		UserInterface.serve();
	}
	public void paint(Graphics g){
		currentMap.paint(g);
		UserInterface.menu.display(g);
	}
}