import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Displayer {

	public static void displayBackground(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, 2000, 1000);
	}
	private static Color chooseColor(int col){
		Color kol = Color.black;
		switch(col){
		case 0 :
			kol = (Color.black);
			break;
		case 1 :
			kol = (Color.red);
			break;
		case 2 :
			kol = (Color.orange).darker().darker();
			break;
		case 3 :
			kol = (Color.yellow);
			break;
		case 4 :
			kol = (Color.green);
			break;
		case 5 :
			kol = (Color.blue);
			break;
		case 6 :
			kol = (Color.white);
			break;
		case 7:
			kol = (Color.magenta);
			break;
		case 8 :
			kol = (Color.cyan);
			break;
			default :
				System.out.println("Zjebales kolory, synu");
				break;
		}
		return kol;
	}
	public static void displayBoard(Graphics g, Board b){
		if(b.gameIsOn){
			g.setColor(Color.DARK_GRAY);
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			// podzia³ka na pionki i podpowiedzi
			for(int i = 2 ; i <= b.ROWS+1 ; i++){
				// paski poziome
				g.setColor(Color.black);
				g.drawLine(b.getX(), b.getY()+i*50, b.getX()+b.getWidth()-100, b.getY()+i*50);
				// pola
				for(int j = 0 ; j < b.PAWNS ; j++){
					g.setColor(chooseColor(b.getBoard()[i-2][j]));
					g.fillOval(b.getX()+j*50+5, b.getY()+(i-1)*50+5, 40, 40);
				}
			}
			// wskazane pole
			if(b.fieldIsClicked){
				g.setColor(Color.cyan);
				g.drawRect(b.getX()+(b.clickedField-1)*50 + 1, b.getY()+(b.turn)*50 + 1, 48, 48);
				g.drawRect(b.getX()+(b.clickedField-1)*50 + 2, b.getY()+(b.turn)*50 + 2, 46, 46);
			}
			// wskazany rz¹d
			g.setColor(Color.cyan);
			g.drawRect(b.getX() + 1, b.getY()+(b.turn)*50 + 1, 248, 48);
			
			g.setColor(Color.black);
			// górna kreska
			g.drawLine(b.getX(), b.getY()+50, b.getX()+b.getWidth()-100, b.getY()+50);
			
			for(int i = 1 ; i <= b.PAWNS ; i++){
				// paski pionowe
				g.drawLine(b.getX()+i*50, b.getY(), b.getX()+i*50, b.getY()+(b.ROWS+1)*50);
			}
			// ostatni pasek pionowy
			g.drawLine(b.getX()+b.getWidth()-100, b.getY(), b.getX()+b.getWidth()-100, b.getY()+(b.ROWS+1)*50);
			
			// podpowiedzi
			for(int i = 0 ; i < b.ROWS ; i++){
				for(int j = 0 ; j < 2 ; j++){
					g.setColor(Color.black);
					for(int k = 0 ; k < 5 ; k++){
						g.drawOval(b.getX()+b.PAWNS*50+k*25+5, b.getY()+(i+1)*50+j*25+5, 15, 15);
					}
					for(int k = 0 ; k < b.getClues()[i][j] ; k++){
						if(j == 0)
							g.setColor(Color.black);
						if(j == 1)
							g.setColor(Color.white);
						g.fillOval(b.getX()+b.PAWNS*50+k*25+5, b.getY()+(i+1)*50+j*25+5, 15, 15);
					}
				}
			}
			// kolory do wyboru
			for(int i = 1 ; i <= 8 ; i++){
				g.setColor(chooseColor(i));
				g.fillOval(b.getX()+b.PAWNS*75+30, b.getY()+i*50+50, 50, 50);
			}
			g.setColor(Color.black);
			g.drawOval(b.getX()+b.PAWNS*75+30, b.getY()+9*50+50, 50, 50);
		}
		// pola zakryte (lub odkryte)
		if(b.showButton.raised){
			for(int j = 0 ; j < b.PAWNS ; j++){
				g.setColor(Color.gray);
				g.fillOval(b.getX()+j*50+5, b.getY()+5, 40, 40);
			}
		}
		if(!b.showButton.raised || !b.gameIsOn){
			for(int j = 0 ; j < b.PAWNS ; j++){
				g.setColor(chooseColor(b.getSet()[j]));
				g.fillOval(b.getX()+j*50+5, b.getY()+5, 40, 40);
			}
		}
	}
	public static void displayButton(Graphics g, Button b){
		g.setColor(Color.gray);
		g.fill3DRect(b.x, b.y, b.width, b.height, b.raised);
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.setColor(Color.black);
		g.drawString(b.name, b.x+b.nameX, b.y+b.nameY);
	}
	public static void displayResult(Graphics g, Board b){
		g.setFont(new Font("Times New Roman", Font.BOLD, 40));
		if(b.victory){
			g.setColor(Color.green);
			g.drawString("Gratulacje! Wygrywasz!", b.getX()+50, b.getY()+b.getHeight()-100);
		}else{
			g.setColor(Color.red);
			g.drawString("Koniec tur! Przegrywasz!", b.getX()+50, b.getY()+b.getHeight()-100);
		}
	}
}