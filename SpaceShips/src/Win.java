import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Win implements ActionListener{

	public JFrame jframe;
	public static JPanel artPanel;
	public static Audio audio = new Audio();
	public Timer timer = new Timer(20, this);
	public Random gen = new Random();
	public static int mouseX, mouseY;
	int mistakenMouseX = 8;
	int mistakenMouseY = 30;
	long startTime = System.currentTimeMillis();
	TimedEvent calculations = new TimedEvent();
	
	// Klawiaturka
	Keyboard keyboard = new Keyboard();
	
	static boolean gameRunning = false;
	static boolean tankChosen = false;
	
	public static Win window;
	
	public Win(){
		jframe = new JFrame("Grafika");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jframe.setVisible(true);
		jframe.addKeyListener(keyboard);
		
		jframe.add(artPanel = new ArtPanel());
		timer.start();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		artPanel.repaint();
		if(gameRunning){
			calculations.run();
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
	public static void selectScreen(int arrow){
		for(int i = 0 ; i < 2 ; i++){	// i rzêdów
			for(int j = 0 ; j < 3 ; j++){	// j kolumn
				if(Displayer.chosenScreen[i][j] == 1){
					switch(arrow){
					case 37 :	// lewo
						if(j>0){
							Displayer.chosenScreen[i][j-1]=1;
							Displayer.chosenScreen[i][j] = 0;
							i = 5; j = 5;	// koñczê obie pêtle
						}
						break;
					case 38 :	// góra
						if(i==1){
							Displayer.chosenScreen[i-1][j]=1;
							Displayer.chosenScreen[i][j] = 0;
							i = 5; j = 5;	// koñczê obie pêtle
						}
						break;
					case 39 :	// prawo
						if(j<2){
							Displayer.chosenScreen[i][j+1]=1;
							Displayer.chosenScreen[i][j] = 0;
							i = 5; j = 5;	// koñczê obie pêtle
						}
						break;
					case 40 :	// dó³
						if(i==0){
							Displayer.chosenScreen[i+1][j]=1;
							Displayer.chosenScreen[i][j] = 0;
							i = 5; j = 5;	// koñczê obie pêtle
						}
						break;
					}
				}
			}
		}
	}
}