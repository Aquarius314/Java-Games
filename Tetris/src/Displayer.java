import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Displayer {

	public static final int R = 20;
	public static final int CORNER_X = 0;
	public static final int CORNER_Y = 30;
	public static final int H = Board.getHeight();
	public static final int W = Board.getWidth();
	
	
	public static void displayBackground(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, 2000, 1000);
	}
	public static void displayWorld(Graphics g){
		for(int i = 0 ; i < World.players.size(); i++){
			displayBoard(World.players.get(i).getBoard(), g);
			displayPlayer(World.players.get(i), g);
		}
	}
	private static void displayBoard(Board b, Graphics g){
		g.setColor(Color.gray.brighter());
		// wyswietlanie planszy
		g.drawRect(CORNER_X + (b.getNr()*(W*R+10)), CORNER_Y-R, W*R, H*R);
		// wyswietlanie zawartosci statycznej
		for(int i = 0 ; i < W; i++){
			for(int j = 0 ; j < H; j++){
				if(b.getField(i, j).getContent()==2){
					g.setColor(b.getField(i, j).getColor());
					g.fill3DRect(CORNER_X + (b.getNr()*(W*R+10)) + i*R, CORNER_Y - H + j*R, R, R, true);
				}
			}
		}
		// wyswietlanie klocka spadajacego
		displayBlock(b.getBlock(), CORNER_X + (b.getNr()*(W*R+10)), CORNER_Y, g);
		// jesli jest martwy, zaslaniam plansze
		if(!(b.getMother().isAlive())){
			g.setColor(new Color(0, 0, 0, 180));
			g.fillRect(CORNER_X + (b.getNr()*(W*R+10)), CORNER_Y-R, W*R+1, H*R+1);
		}
	}
	private static void displayBlock(Block b, int x, int y, Graphics g){
		g.setColor(b.getColor());
		for(int i = 0 ; i < 4 ; i++){
			g.fill3DRect(x + b.getX(i)*R, y + (b.getY(i)-1)*R, R, R, true);
		}
	}
	private static void displayName(String n, int x, int y, Graphics g){
		g.setColor(Color.black);
		g.setFont(new Font("Times New Roman", Font.BOLD, 24));
		g.drawString(n, x, y);
	}
	private static void displayScore(int score, int x, int y, Graphics g){
		g.setColor(Color.black);
		g.drawString("Wynik: "+Integer.toString(score), x, y);
	}
	private static void displayNextBlock(Board b, int x, int y, Graphics g){
		displayName("Nastepny: ", x, y, g);
		g.setColor(Color.BLACK);
		g.fillRect(x, y+2, 6*R, 6*R);
		displayBlock(new Block(b.getNextBlockType()), x+2*R, y+20+2*R, g);
	}
	private static void displayPlayer(Player p, Graphics g){
		int corner_x = CORNER_X + (p.getBoard().getNr()*(W*R+10));
		int corner_y = CORNER_Y + H*R - R;
		int width = W*R;
		int height = 210;
		// wyswietl pole dla informacji o graczu
		g.setColor(Color.blue);
		g.fillRect(corner_x, corner_y, width, height);
		displayName(p.getName(), corner_x+20, corner_y+20, g);
		displayScore(p.getScore(), corner_x+20, corner_y+40, g);
		displayName("Szybkosc: "+Integer.toString(TimedEngine.gameSpeed), corner_x+20, corner_y+60, g);
		displayNextBlock(p.getBoard(), corner_x+20, corner_y+80, g);
		if(p.isAutomatic())
			displayAutomaticPlayer(corner_x, corner_y, p, g);
	}
	private static void displayAutomaticPlayer(int x, int y, Player p, Graphics g){
		g.setColor(Color.white);
		for(int i = 0 ; i < p.points.length; i++){
			g.drawString(Integer.toString(p.points[i]), x+i*R, y+5);
		}
	}
}