
public class TimedEvent {

	int waitForStart = 0;
	long time1 = System.currentTimeMillis() + waitForStart;
	long time2 = System.currentTimeMillis() + waitForStart;
	long time3 = System.currentTimeMillis() + waitForStart;
	long time4 = System.currentTimeMillis() + waitForStart;
	long time5 = System.currentTimeMillis() + waitForStart;
	int lapse4 = 2000;
	int lapse5 = 20000;
	public void run(){
		if( (System.currentTimeMillis()-time1) > 30 ){	// ok. 30/sekunde - akcje podstawowe
			time1 = System.currentTimeMillis();
			Aircraft.actions();
		}
		if( (System.currentTimeMillis()-time2) > 100 ){	// interwa³ dla strzelania gracza (maks 10/sekunde)
			time2 = System.currentTimeMillis();
			if(Keyboard.ctrlPressed)
				Aircraft.player.shoot(Aircraft.player.getX(), Aircraft.player.getY());
		}
		if( (System.currentTimeMillis()-time3) > 20 ){	// tworzenie gwiazd 10/sekunde
			time3 = System.currentTimeMillis();
			Aircraft.stars.add(new Star());
			Aircraft.stars.add(new Star());
			}
		if( (System.currentTimeMillis()-time4) > lapse4 ){	// tworzenie przeciwników	0.5/sekunde
			time4 = System.currentTimeMillis();
			Aircraft.generateEnemy();
			Aircraft.player.heal();
		}
		if( (System.currentTimeMillis()-time5) > lapse5 ){	// tworzenie bossów 	1 na 20sekund
			time5 = System.currentTimeMillis();
			time4 += 10000;
			Aircraft.generateBoss();
			Aircraft.player.heal();
		}
	}
}
