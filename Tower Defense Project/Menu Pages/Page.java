import java.awt.Graphics;
import java.util.ArrayList;

/*
 * Klasa dla poszczegolnych stron w menu
 */
public abstract class Page {
	
	private long time = System.currentTimeMillis();	// to optimize refreshing values frequency.
	private int refreshingTimeLapse = 100;
	public ArrayList <PageElement> elements = new ArrayList <PageElement> ();
	protected Menu parentMenu;
	static public final int WIDTH = 300;
	
	public Page(Menu menu){
		parentMenu = menu;
	}
	public void display(Graphics g){
		Displayer.displayPage(g, this);
	}
	protected abstract void refreshValues();
	public void react(){
		// valueFields refreshing
		if(System.currentTimeMillis() - time >= refreshingTimeLapse){
			time = System.currentTimeMillis();
			refreshValues();
		}
		// reacting for clicking buttons
		for(PageElement b : elements)
			if(b.getClass() == Button.class){
				b.react();
			}
	}
	public abstract PageType getType();

}
