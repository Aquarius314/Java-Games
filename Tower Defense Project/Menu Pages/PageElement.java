import java.awt.Graphics;

public interface PageElement {
	
	public void display(Graphics g);
	public void setValue(String v);
	public void react();
}
