import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	public static final long LEFT_ARROW_DEADLINE = 100;
	public static final long RIGHT_ARROW_DEADLINE = 100;
	public static long timeL = 0;
	public static long timeR = 0;
	public static long time1 = 0;
	public static long time2 = 0;
	
	private void checkKeysForPlayers(Player p, int key){
		if(key == p.getLeftCode())
			Interface.moveLeft(p);
		else if(key == p.getRightCode())
			Interface.moveRight(p);
		else if(key == p.getRotateCode())
			Interface.rotate(p);
		else if(key == p.getDropCode())
			Interface.drop(p);
		else if(key == p.getSlowCode())
			Interface.moveDown(p);
	}
	@Override
	public void keyPressed(KeyEvent e){
		for(int i = 0 ; i < World.players.size(); i++)
			checkKeysForPlayers(World.players.get(i), e.getKeyCode());
		if(e.getKeyCode() == 32)
			Interface.revertGame();
		if(e.getKeyCode() == 115)	// F4
			Interface.restart();
		System.out.println(e.getKeyCode());
	}
	public void keyReleased(KeyEvent e){
	}
	public void keyTyped(KeyEvent arg0){
	}
}