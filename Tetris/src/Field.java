import java.awt.Color;

public class Field {

	private int content;	// 0 - pusty, 1 - aktualny klocek, 2 - stare klocki
	private Color color = Color.GRAY;
	public Field() {
		content = 0;
	}
	public void setContent(int con){
		content = con;
	}
	public int getContent(){
		return content;
	}
	public Color getColor(){
		return color;
	}
	public void setColor(Color col){
		color = col;
	}
	public void setStaticContent(Color col){
		content = 2;
		color = col;
	}

}
