import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Introduction {
	
		// t³o
	int backX = 380, backY = 150;
	int backWidth = 600, backHeight = 400;
	Color backColor = new Color(0, 0, 150);
	Color optionsColor = new Color(0, 0, 220);
	Font font = new Font("Times New Roman", Font.BOLD, 40);
		// opcje
	int optionsX = 450, optionsY = backY+50;
	int optionsWidth = 460, optionsHeight = 300;
	String[] options = new String[3];
	
	public Introduction(){
		options[0] = "START";
		options[1] = "KOMBAJN";
		options[2] = "WYJSCIE";
	}
	public void display(Graphics g){
		g.setColor(backColor);
		g.fillRect(backX, backY, backWidth, backHeight);
		g.setColor(optionsColor);
		g.fillRect(optionsX, optionsY, optionsWidth, optionsHeight);
			// bajer
		int d = 1;
		for(int i = 0 ; i < 5 ; i++){
			g.drawRect(backX+d*i, backY+d*i, backWidth-2*d*i, backHeight-2*d*i);
		}
		g.setColor(Color.black);
		for(int i = 0 ; i < 3 ; i++){
			g.drawRect(optionsX, optionsY+i*100, optionsWidth, 100);
		}
		g.setFont(font);
		for(int i = 0 ; i < options.length ; i++){
			g.drawString(options[i], optionsX+(optionsWidth/2)-(options[i].length()*40/2), optionsY+50+100*i);
		}
	}
	public void clickOptions(int y){
	}
}