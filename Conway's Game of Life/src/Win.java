import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Win implements ActionListener, KeyListener, MouseListener, MouseMotionListener{

	public JFrame jframe;
	public JPanel artPanel;
	public Timer timer = new Timer(20, this);
	public Random gen = new Random();
	public static int R = 2;
	public static int mouseX;
	public static int mouseY;
	int mistakenMouseX = 8;
	int mistakenMouseY = 30;
	long waitForStart = 0;	// wyra¿one w milisekundach
	long time = System.currentTimeMillis() + waitForStart;
	long time2 = System.currentTimeMillis() + waitForStart;
	
	public static Win window;
	
	public Win(){
		jframe = new JFrame("Grafika");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jframe.setVisible(true);
		jframe.addKeyListener(this);
		jframe.addMouseListener(this);
		jframe.addMouseMotionListener(this);
		
		jframe.add(artPanel = new ArtPanel());
		timer.start();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		artPanel.repaint();
		calculate();
		slowCalculate();
	}
	public void calculate(){
	}
	public void slowCalculate(){
		if( (System.currentTimeMillis()-time) > 20 ){
			time = System.currentTimeMillis();
			Game.evolve();
		}
		if( (System.currentTimeMillis()-time2) > 3000 ){
			time2 = System.currentTimeMillis();
			//ArtPanel.game.createShip(15, 50, 1);	// pierwszy poziomy
			//ArtPanel.game.createShip(100, 0, 2);	// pierwszy pionowy	// chyba dla 250x150
			//ArtPanel.game.createShip(15, 97, 1);	// t³umi¹cy poziomy
			//ArtPanel.game.createShip(245, 60, 3);	// przeciwny
			//ArtPanel.game.createShip(75, 145, 4);	// do góry
			//ArtPanel.game.createPlane(33, 0, 2);
			//totalChaos();
			ArtPanel.game.createShip(5, 225, 1);
		}
	}
	protected void totalChaos(){
		for(int i = 0 ; i < 4 ; i++){
			ArtPanel.game.createShip(1, i*20+1, 1);
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				window = new Win();
				System.out.println("Start");
			}
		});
	}
	@Override
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case 87 : // W
			break;
		case 69 : // E
			break;
		case 65 : // A
			break;
		case 68 : // D
			break;
		case 90 : // Z
			break;
		case 88 : // X
			break;
			default :
				System.out.println(e.getKeyCode());
				break;
		}
	}
	public void keyReleased(KeyEvent e){
	}
	public void keyTyped(KeyEvent arg0){
	}
	public void mouseClicked(MouseEvent arg0){
		mouseX = arg0.getX() - mistakenMouseX;
		mouseY = arg0.getY() - mistakenMouseY;
	}
	public void mouseEntered(MouseEvent arg0){
	}
	public void mouseExited(MouseEvent arg0){
	}
	public void mousePressed(MouseEvent arg0){
		mouseX = arg0.getX() - mistakenMouseX;
		mouseY = arg0.getY() - mistakenMouseY;
	}
	public void mouseReleased(MouseEvent arg0){
		mouseX = arg0.getX() - mistakenMouseX;
		mouseY = arg0.getY() - mistakenMouseY;
	}
	public void mouseMoved(MouseEvent arg0){
		mouseX = arg0.getX() - mistakenMouseX;
		mouseY = arg0.getY() - mistakenMouseY;
	}
	public void mouseDragged(MouseEvent arg0) {
		mouseX = arg0.getX() - mistakenMouseX;
		mouseY = arg0.getY() - mistakenMouseY;
	}
}