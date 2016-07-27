import java.awt.Font;
import java.awt.Graphics;

public class TextField implements PageElement{

	private String text;
	private int xPos, yPos;
	private String value;
	private boolean hasValue = false;
	
	public TextField(int x, int y, String textContent) {
		text = textContent;
		xPos = x + Menu.xCorner;
		yPos = y + Menu.yCorner;
	}
	public TextField(int x, int y, String textContent, String valueContent){
		text = textContent;
		xPos = x + Menu.xCorner;
		yPos = y + Menu.yCorner;
		value = valueContent;
		hasValue = true;
	}
	public TextField(){
		
	}

	@Override
	public void display(Graphics g) {
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		String totalText = text;
		if(hasValue)
			totalText += value;
		g.drawString(totalText, xPos, yPos);
	}

	public boolean hasValue(){
		return hasValue;
	}
	@Override
	public void setValue(String v){
		value = v;
	}
	@Override
	public void react(){
		// nothing to do here.
	}
}
