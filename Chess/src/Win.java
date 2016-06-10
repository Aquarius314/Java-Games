import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Win implements ActionListener{

	public JFrame jframe;
	static public JPanel artPanel;
	public Timer timer = new Timer(20, this);
	public Random gen = new Random();
	public static int standardFields = 8;
	public static int R = 70;	// rozmiar ogolny, gra przewidziana na 70
//	public static int mouseX, mouseY;
	public static int XDIM = standardFields*R, YDIM = standardFields*R;
	static boolean gameIsRunning = true;
		//myszka
	public Mouse mouse = new Mouse();
	int mistakenMouseX = 8;
	int mistakenMouseY = 30;
	
	public static Win window;
	
	public Win(){
		jframe = new JFrame("Grafika");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jframe.setVisible(true);
		jframe.addMouseListener(mouse);
		jframe.addMouseMotionListener(mouse);
		jframe.add(artPanel = new ArtPanel());
		timer.start();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		artPanel.repaint();
		if(gameIsRunning)
			calculate();
	}
	public void calculate(){
		ArtPanel.board.moveSoldiers();
		ArtPanel.board.checkForVictory();
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
}