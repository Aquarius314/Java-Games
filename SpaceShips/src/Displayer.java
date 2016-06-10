import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Displayer {

	static int chosenScreen[][] = new int[][]{
			{1, 0, 0},
			{0, 0, 0}
	};
	static Random rnd = new Random();
	public static void displayTank(Graphics g, Tank tank){
		int rendX = tank.x - 50;
		int rendY = tank.y - 50;
		g.drawImage(ArtPanel.enemyShip1, rendX, rendY, Win.artPanel);
	}
	public static void displayTank(Graphics g, Boss tank){
		int rendX = tank.x - 50;
		int rendY = tank.y - 50;
		g.drawImage(ArtPanel.bossShip1, rendX, rendY, Win.artPanel);
	}
	public static void displayPlayer(Graphics g, Player tank){
		int rendX = tank.x - 50;
		int rendY = tank.y - 70;
		BufferedImage rend;
		int type = 0;
		switch(tank.type){
		case FIGHTER :
			type = 0;
			break;
		case GAUSS :
			type = 1;
			break;
		case HEAVY :
			type = 2;
			break;
		case PULSATIVE :
			type = 3;
			break;
		case SNIPER :
			type = 4;
			break;
		case STRIKER :
			type = 5;
			break;
			default :
				break;
		}
		int levelRend = tank.level-1;
		if(levelRend > 3)
			levelRend = 3;
		rend = ArtPanel.ships[levelRend][type];
		g.drawImage(rend, rendX, rendY, Win.artPanel);
	}
	public static void displayInit(Graphics g){
		int xdim = 1350;
		//int ydim = 700;
		//tu mo¿na wstawiæ wyœwietlanie jakiegoœ t³a albo coœ
		//napis wyboru statku
		g.setColor(Color.white);
		g.setFont(new Font("Times New Roman", Font.BOLD, 40));
		g.drawString("W Y B I E R Z   S T A T E K", 420, 40);
		
			//ekran wyboru
		// obramówka
		g.drawRect(80, 50, xdim-160, 620);
		
		// wspó³rzêdne poszczególnych ekranów
		int tanksX[] = new int[]{	100, 400, 700, 100, 400, 700	};
		int tanksY[] = new int[]{	100, 100, 100, 400, 400, 400	};
		int tanksColor[] = new int[]{	50, 50, 50, 50, 50, 50	};
		String tanksNameEN[] = new String[]{ "FIGHTER", "GAUSS", "HEAVY", "PULSATIVE", "SNIPER", "STRIKER"	};
		for(int i = 0 ; i < 6 ; i++){
			g.drawImage(ArtPanel.ships[0][i], tanksX[i]+50, tanksY[i]+50, Win.artPanel);
		}
		for(int i = 0 ; i < 2 ; i++){
			for(int j = 0 ; j < 3 ; j++){
				if(chosenScreen[i][j]==1)
					tanksColor[3*i+j]=0;
			}
		}
		for(int i = 0 ; i < 6 ; i++){
			g.setColor(new Color(255,255,255,tanksColor[i]));
			g.fillRect(tanksX[i], tanksY[i], 250, 250);
			g.setColor(Color.white);
			g.drawRect(tanksX[i], tanksY[i], 250, 250);
			g.drawString(tanksNameEN[i], tanksX[i]+4, tanksY[i]-5);
			g.drawRect(tanksX[i]-1, tanksY[i]-41, 252, 292);
		}
	}
	public static void displayRest(Graphics g, Player tank){
			//	pasek reload dla strzelania
		int x = 700;
		int y = 2;
		for(int i = 0 ; i < tank.power ; i++){
			g.setColor(new Color(0,255,0,100+i*3));
			g.fillRect(x+i*(100/tank.reload), y, 100/tank.reload-1, 9);
			if(i == tank.reload-1)
				break;
		}
			// pasek reload dla skilli
		for(int k = 0 ; k < tank.skills.size(); k++){
			for(int i = 0 ; i*200 < System.currentTimeMillis()-tank.skills.get(k).time; i++){
				g.setColor(new Color(0,150+i*2,0,150));
				g.fillRect(x+i*(20000/tank.skills.get(k).reload), y+10, 20000/tank.skills.get(k).reload-1, 9);
				if((i+1)*200 >= tank.skills.get(k).reload)
						break;
			}
		}
		
	}
	public static void displayHealth(Graphics g, Tank tank){
		g.setColor(new Color(250,0,0,150));
		g.fillRect(tank.x-(100)/2, tank.y-tank.size-10, 100, 5);
		g.setColor(new Color(0,250,0,150));
		g.fillRect(tank.x-(100)/2, tank.y-tank.size-10, 100*tank.hp/tank.maxhealth, 5);
	}
	public static void displayHealth(Graphics g, Player tank){
		int totalHealth = tank.maxhealth;
		g.setColor(new Color(250,0,0,100));
		g.fillRect(tank.x-(100)/2, tank.y-tank.size-10, 100, 5);
		g.setColor(new Color(0,250,0,100));
		g.fillRect(tank.x-(100)/2, tank.y-tank.size-10, 100*tank.hp/(totalHealth), 5);
	}
	public static void displayExperience(Graphics g, Player tank){
		g.setColor(Color.blue);
		int displaylength = 20;
		int explength = displaylength*tank.experience/tank.expneeded;
		int rest = 20*(tank.expneeded-tank.experience)/tank.expneeded;
		g.fillRect(tank.x-displaylength/2, tank.y-tank.size-4, explength, 3);
		g.setColor(Color.white);
		g.fillRect(tank.x-displaylength/2+explength, tank.y-tank.size-4, rest, 3);
	}
	public static void displayInfo(Graphics g, Player tank){
		g.setFont(new Font("Times New Roman", Font.BOLD, 12));
		g.setColor(Color.white);
		g.drawString("TYP CZOLGU: "+tank.type, 0, 15);
		g.drawString("|POZIOM: "+tank.level, 150, 15);
		int realDmg = tank.dmg;
		g.drawString("|DMG: "+realDmg, 250, 15);
		g.drawString("|ODPORNOSC: "+tank.improvement.resistance, 350, 15);
		g.drawString("|EXP: "+tank.experience+"/"+tank.expneeded, 450, 15);
		g.drawString("|HP: "+tank.hp+"/"+(tank.maxhealth), 550, 15);
	}
	public static void displayMissiles(Graphics g, Missile mis){
		// transformacje
		int rendSize = 12;
		int rendX = (int)mis.realX-rendSize/2;
		int rendY = (int)mis.realY-rendSize/2;
		AffineTransform AT = AffineTransform.getTranslateInstance(rendX, rendY);
		//AT.rotate(Math.toRadians(-mis.rotateInRadians), 50, 15);
		AT.rotate(mis.rotateInRadians);
		BufferedImage rend;
		if(mis.shooterID == -1)
			rend = ArtPanel.missile[0];
		else
			rend = ArtPanel.missile[1];
		//rend = ArtPanel.missile[2];/////////////////////////////////////
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(rend, AT, null);
	}
	public static void displayStar(Graphics g, Star star){
		g.setColor(Color.white);
		int rendX = star.getX();
		int rendY = star.getY();
		int rendSize = star.getS();
		g.fillRect(rendX, rendY, rendSize, rendSize);
	}
	
	/////////// SKILLE
	public static void displayShield(Graphics g, Shield shield){
		int oX = shield.owner.x, oY = shield.owner.y;
		g.setColor(Color.white);
		g.drawOval(oX-shield.radius, oY-shield.radius, shield.radius*2, shield.radius*2);
		g.setColor( new Color(255, 255, 255, shield.intense) );
		g.fillOval(oX-shield.radius, oY-shield.radius, shield.radius*2, shield.radius*2);
	}
	public static void displayPurification(Graphics g, Purification shield){
		int oX = shield.owner.x, oY = shield.owner.y;
		g.setColor(Color.red);
		g.drawOval(oX-shield.radius, oY-shield.radius, shield.radius*2, shield.radius*2);
		g.setColor( new Color(255, 0, 0, 140) );
		g.fillOval(oX-shield.radius, oY-shield.radius, shield.radius*2, shield.radius*2);
	}
	public static void displayBeam(Graphics g, Beam beam){
		int x = beam.owner.x;
		int y = beam.owner.y;
		g.setColor(new Color(255,0,80,70));
		g.fillOval(x-20, y-20, 40, 40);
		g.fillRect(-10, y-20, x+10, 40);
	}
	public static void displayWave(Graphics g, Wave wave){
		int x = wave.owner.x;
		int y = wave.owner.y;
		g.setColor(Color.green);
		g.drawOval(x-wave.r, y-wave.r, 2*wave.r, 2*wave.r);
	}
	public static void displayExploder(Graphics g, Exploder exp){
		g.setColor(Color.red);
		int size = rnd.nextInt(10)+30;
		g.fillOval(exp.x-size, exp.y-size, 2*size, 2*size);
	}
//	public static void displayBomb(Graphics g, Bomb bomb){
//		g.setColor(new Color(200, 200, 0));
//		for(int i = 0 ; i < bomb.missiles.size(); i++){
//			g.fillOval((int)bomb.missiles.get(i).realX-bomb.r, (int)bomb.missiles.get(i).realY-bomb.r, bomb.r*2, bomb.r*2);
//		}
//	}
}
