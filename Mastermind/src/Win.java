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
	public static World world = new World();	// sugerowane miejsce akcji gry
	
	public static Win window;
	
	public Win(){
		jframe = new JFrame("MASTERMIND");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(535, 660);
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
				System.out.println("Start");
			}
		});
	}
}