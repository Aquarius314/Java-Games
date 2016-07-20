import static java.util.concurrent.TimeUnit.*;

import java.awt.Graphics;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Game {
	
	static int[][] board1 = {
			{0, 2, 0, 0, 0, 0, 0, 0},
			{0, 1, 0, 1, 1, 1, 0, 0},
			{0, 1, 1, 1, 0, 1, 0, 0},
			{0, 0, 0, 0, 1, 1, 0, 0},
			{0, 0, 1, 1, 1, 0, 0, 0},
			{0, 0, 1, 0, 0, 0, 0, 0},
			{0, 0, 1, 1, 1, 1, 1, -1},
			{0, 0, 0, 0, 0, 0, 0, 0}
	};

	public static final int SIZE = 40;
	Map currentMap;
	static UserInterface UI;
	public static final int xMap = 200, yMap = 50;
	static Level level1 = new Level(5000, board1);
	ScheduledThreadPoolExecutor eventPool = new ScheduledThreadPoolExecutor(5);
	
	public Game(){
		currentMap = new Map();
		eventPool.scheduleAtFixedRate(currentMap.monsterGenerator, 1, currentMap.monsterGenerator.getTimePeriod(), MILLISECONDS);
		UI = new UserInterface();
	}
	public void gameLoop(){
		currentMap.calculate();
		UI.serve();
	}
	public void paint(Graphics g){
		currentMap.paint(g);
	}
}