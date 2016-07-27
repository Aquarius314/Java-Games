import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Button implements PageElement{

	private boolean clicked = false;
	private boolean mouseAbove = false;
	private String textField = "";
	private int xPos, yPos;
	private int width, height;
	
	public Button(int x, int y, int w, int h, String text) {
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		textField = text;
	}

	@Override
	public void display(Graphics g) {
		int rendX = xPos + Menu.xCorner;
		int rendY = yPos + Menu.yCorner;
		int rendHeight = height/2 + 8;
		int rendWidth = width/2 - textField.length()/2*12;
		g.setColor(Color.red);
		for(int i = 0; i < 2; i++)
			g.drawRect(rendX-i, rendY-i, width+2*i, height+2*i);
		if(mouseAbove){
			g.drawRect(rendX+1, rendY+1, width-2, height-2);
			g.setColor(Color.red.darker().darker().darker().darker());
			g.fillRect(rendX+2, rendY+2, width-4, height-4);
			g.setColor(Color.red);
		}
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.drawString(textField, rendX+rendWidth, rendY+rendHeight);
	}
	
	@Override
	public void setValue(String v){
		System.out.println("Nie mozna ustalic wartosci dla przycisku!");
	}
	
	@Override
	public void react(){
		boolean xAbove = false, yAbove = false;
		if(Mouse.getX() >= xPos + Menu.xCorner && Mouse.getX() <= xPos + Menu.xCorner + width){
			xAbove = true;
		}

		if(Mouse.getY() >= yPos + Menu.yCorner && Mouse.getY() <= yPos + Menu.yCorner + height){
			yAbove = true;
		}
		if(xAbove && yAbove){	// mouse is above this button
			mouseAbove = true;
		}
		else{
			mouseAbove = false;
		}
		if(mouseAbove && Mouse.isLeftClicked()){
			clicked = true;
		}
	}
	public void finishJob(){
		clicked = false;
	}
	public boolean isClicked(){
		return clicked;
	}
	public int getX(){
		return xPos;
	}
	public int getY(){
		return yPos;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public boolean isMouseAbove(){
		return mouseAbove;
	}
	public String getText(){
		return textField;
	}
}
