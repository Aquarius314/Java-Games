import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
			default :
				System.out.println(e.getKeyCode());
				break;
		}
	}
	public void keyReleased(KeyEvent e){
	}
	public void keyTyped(KeyEvent arg0){
	}
}