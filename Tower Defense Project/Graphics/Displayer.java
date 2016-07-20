import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Displayer {
	
	public static ArrayList <Explosion> explosions = new ArrayList <Explosion> ();

	private Displayer(){};
	
	public static void displayBackground(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, 2000, 1000);
	}
	public static void displayMonster(Graphics g, Monster m){
		if(m.isAlive())
			g.setColor(m.getColor());
		else
			g.setColor(m.getColor().darker().darker().darker().darker().darker());
		g.fillOval(m.getX()-m.getSize()/2 + Game.xMap, m.getY()-m.getSize()/2 + Game.yMap, m.getSize(), m.getSize());
		g.setColor(Color.black);
		g.drawOval(m.getX()-m.getSize()/2 + Game.xMap, m.getY()-m.getSize()/2 + Game.yMap, m.getSize(), m.getSize());
	}
	public static void displayMap(Graphics g, Map m){
		g.setColor(Color.red);
		for(int i = 1 ; i <= 5; i++){
			g.drawRect(Game.xMap-i, Game.yMap-i, Map.WIDTH*Game.SIZE+2*i-2, Map.HEIGHT*Game.SIZE+2*i-2);
		}
		for(int i = 0; i < Map.HEIGHT; i++){
			for(int j = 0; j < Map.WIDTH; j++){
				if(m.getLevel().getFieldContent(i, j) == 0){
					g.setColor(Color.gray);
				}
				else{
					g.setColor(Color.black);
				}
				g.fillRect(i*Game.SIZE + Game.xMap, j*Game.SIZE + Game.yMap, Game.SIZE-1, Game.SIZE-1);
			}
		}
		displayPath(g, m);
	}
	public static void displayTower(Graphics g, Tower t){
		g.setFont(new Font("Times New Roman", Font.BOLD, 24));
		g.setColor(t.getColor());
		g.fillRect(t.getX() + Game.xMap, t.getY() + Game.yMap, Game.SIZE-1, Game.SIZE-1);
		// zasieg
		if(t.showRange()){
			g.setColor(Color.green);
			g.drawOval(t.getX()+Game.xMap-t.getRange()+20+Game.SIZE/2, t.getY()+Game.yMap-t.getRange()+20+Game.SIZE/2, t.getRange()*2-40, t.getRange()*2-40);
		}
		int[] xPoints = {
				t.getX() + Game.SIZE/2 + Game.xMap,
				t.getX() + Game.SIZE + Game.xMap-1,
				t.getX() + Game.SIZE/2 + Game.xMap,
				t.getX() + Game.xMap
		};
		int[] yPoints = {
				t.getY() + Game.yMap,
				t.getY() + Game.SIZE/2 + Game.yMap,
				t.getY() + Game.SIZE + Game.yMap-1,
				t.getY() + Game.SIZE/2 + Game.yMap
		};
		g.setColor(t.getColor().darker());
		g.fillPolygon(xPoints, yPoints, 4);
		g.setColor(Color.black);
		g.drawString(Character.toString(t.getLetter()), t.getX() + Game.xMap + 10, t.getY() + Game.yMap + 25);
	}
	public static void displayMissile(Graphics g, Missile m){
		String missileLetter = "";
		if(m.getType() == MissileType.ARROW)
			missileLetter = "A";
		if(m.getType() == MissileType.MAGIC)
			missileLetter = "M";
		if(m.getType() == MissileType.BOMB)
			missileLetter = "B";
		g.setFont(new Font("Times New Roman", Font.BOLD, 24));
		g.setColor(Color.red);
		g.drawString(missileLetter, m.getX()+Game.xMap, m.getY()+Game.yMap);
	}
	public static void displayExplosions(Graphics g){
		for(Explosion e : explosions){
			e.explode(g);
		}
	}
	private static void displayPath(Graphics g, Map m){
		g.setColor(Color.white);
		for(int i = 1; i < Level.xPath.size(); i++){
			int xOld = Level.xPath.get(i-1) + Game.xMap;
			int yOld = Level.yPath.get(i-1) + Game.yMap;
			int xNew = Level.xPath.get(i) + Game.xMap;
			int yNew = Level.yPath.get(i) + Game.yMap;
			g.drawLine(xOld, yOld, xNew, yNew);
		}
	}
}