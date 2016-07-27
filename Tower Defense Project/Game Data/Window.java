import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Window implements ActionListener {

	public JFrame jframe;
	public static JPanel artPanel = new ArtPanel();
	public Timer timer = new Timer(20, this);
	public static Keyboard keyboard = new Keyboard();
	public static Mouse mouse = new Mouse();
	public static Game game = new Game();	// sugerowane miejsce akcji gry
	
	public static Window window;
	
	public Window(){
		jframe = new JFrame("Ancient TD");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		game.gameLoop();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				window = new Window();
				System.out.println("Start");
			}
		});
	}
}