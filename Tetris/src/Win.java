import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Win implements ActionListener {

	public JFrame jframe;
	public JPanel artPanel = new ArtPanel();
	public Timer timer = new Timer(20, this);
	public static Keyboard keyboard = new Keyboard();
	public static Mouse mouse = new Mouse();
	public static World world;	// sugerowane miejsce akcji gry
	
	public static int playersNumber;
	
	public static Win window;
	
	public Win(){
		jframe = new JFrame("KubTris");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		playersNumber = 2;
		jframe.setSize(220*playersNumber, 660);
		world = new World();
		jframe.setVisible(true);
		jframe.addKeyListener(keyboard);
		jframe.addMouseListener(mouse);
		jframe.addMouseMotionListener(mouse);
		
		jframe.add(artPanel);
		timer.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		artPanel.repaint();
		world.calculate();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				window = new Win();
			}
		});
	}
}