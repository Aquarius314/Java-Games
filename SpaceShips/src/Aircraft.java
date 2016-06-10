import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Aircraft {
	
	static Random gen = new Random();
	static int score = 0;
	static Player player;	// -1 to wartoœæ zarezerwowana dla g³ównego czo³gu
	static ArrayList <Enemy> enemies = new ArrayList <Enemy> ();
	static ArrayList <Missile> enemiesMissiles = new ArrayList <Missile> ();
	static ArrayList <Star> stars = new ArrayList <Star> ();
	static Boss boss;
	//static Audio audio = new Audio();
	public Aircraft(){
		manualSettings();
	}
	public void initialView(Graphics g){
		Displayer.displayInit(g);
	}
	public void mainView(Graphics g){
		for(int i = 0 ; i < enemies.size(); i++){
			enemies.get(i).display(g);
		}
		for(int i = 0 ; i < enemiesMissiles.size(); i++){
			enemiesMissiles.get(i).display(g);
		}
		player.display(g);
	}
	public void background(Graphics g){
		for(int i = 0 ; i < stars.size(); i++){
			stars.get(i).display(g);
		}
	}
	protected void manualSettings(){
		// pocz¹tkowe ustawienie gwiazd
		for(int i = 0 ; i < 800 ; i++){
			Aircraft.stars.add(new Star());
			Aircraft.stars.get(i).x = gen.nextInt(1400);
		}
	}
	public static void generateEnemy(){
		enemies.add(new Enemy(-50, gen.nextInt(300)+200, Aircraft.enemies.size()));
	}
	public static void generateBoss(){
		enemies.add(new Boss(150, gen.nextInt(300)+200, Aircraft.enemies.size()));
	}
	public static void actions(){
			// akcje zewnêtrzne, t³o itp:
		for(int i = 0 ; i < stars.size(); i++)
			stars.get(i).moveAndDie();
			// akcje dla g³ównego czo³gu:
		player.move();	// poruszanie
		player.rest();	// regeneracja dzia³a
		player.moveMissiles();	// przesuwanie pocisków
		player.checkForHits();	// sprawdzenie trafenia
		player.improve();			// sprawdzenie poziomu itp
		player.specialModes();		// ustawienie niestandardowe, kody, testery etc
		for(int i = 0 ; i < player.skills.size(); i++){
			if(player.skills.get(i).isOn)
				player.skills.get(i).perform();
		}
			// teraz wrogowie	
		for(int i = 0 ; i < enemies.size(); i++){
			Enemy en = enemies.get(i);	// wybieram kolejnego przeciwnika do obliczeñ
			en.automove();	// poruszanie automatyczne
			en.rest();		// prze³adowywanie dzia³a
			en.autofire();	// strzelanie automatyczne
		}
		for(int i = 0 ; i < enemiesMissiles.size(); i++){
			enemiesMissiles.get(i).move();
			enemiesMissiles.get(i).checkForHit();
		}
		for(int i = 0 ; i < enemiesMissiles.size(); i++){
			enemiesMissiles.get(i).smartRemove();
		}
	}
	public static void gameOver(){
		Win.gameRunning = false;
	}
}
