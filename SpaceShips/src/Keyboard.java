import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	static boolean Wpressed = false, Apressed = false, Spressed = false, Dpressed = false;
	static boolean spacePressed = false, ctrlPressed = false;
	
	@Override
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == 32){ // SPACJA
			for(int i = 0 ; i < Aircraft.player.skills.size(); i++){
				Aircraft.player.skills.get(i).turnOn();
			}
		}
		if(!Win.tankChosen){
			if(e.getKeyCode() == 10){	// ENTER - wybór czo³gu, jeœli jeszcze nie wybrano.
				Aircraft.player = new Player(600, 400, -1);
				Aircraft.player.chooseTank();
				Win.tankChosen = true;
				Win.gameRunning = true;
			}
			if(e.getKeyCode() <= 40 && e.getKeyCode() >= 37){
				Win.selectScreen(e.getKeyCode());
				System.out.println(e.getKeyCode());
			}
		}
		if(e.getKeyCode() == 37)
			Apressed = true;
		if(e.getKeyCode() == 38)
			Wpressed = true;
		if(e.getKeyCode() == 39)
			Dpressed = true;
		if(e.getKeyCode() == 40)
			Spressed = true;
		if(e.getKeyCode() == 17)
			ctrlPressed = true;
	}
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
		case 38 : // W
			Wpressed = false;
			break;
		case 40 : // S
			Spressed = false;
			break;
		case 37 : // A
			Apressed = false;
			break;
		case 39 : // D
			Dpressed = false;
			break;
		case 17 :	// CTRL
			ctrlPressed = false;
			break;
			default :
			break;
		}
	}
	public void keyTyped(KeyEvent e){
		if(!Win.tankChosen){
			if(e.getKeyCode() <= 40 && e.getKeyCode() >= 37){
				Win.selectScreen(e.getKeyCode());
				System.out.println(e.getKeyCode());
			}
			System.out.println("naciœniêto klawisz");
			}
	}
}