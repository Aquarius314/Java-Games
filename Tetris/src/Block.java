import java.awt.Color;
import java.util.Random;

public class Block {

	 private int[] X = new int[4];
	 private int[] Y = new int[4];
	 private Board motherBoard;
	 private Color color;
	 private int type;
	 boolean isFalling = false;
	
	public Block(int type, Board mother){
		chooseType(type);
		this.type = type;
		motherBoard = mother;
		moveToMiddle();
	}
	public Block(int type){
		chooseType(type);
		this.type = type;
	}
	private void moveToMiddle(){
		int value = new Random().nextInt(3)-1;
		for(int i = 0 ; i < 4 ; i++){
			X[i] += Board.getWidth()/2 - 1 + value;
		}
	}
	public void chooseType(int t){
		switch(t){
		case 1 :	// DLUGI
			X = new int[]{0,0,0,0};
			Y = new int[]{0,1,2,3};
			color = Color.RED;
			break;
		case 2 :	// KWADRATOWY
			X = new int[]{0,0,1,1};
			Y = new int[]{0,1,0,1};
			color = Color.CYAN;
			break;
		case 3 :	// T
			X = new int[]{0,0,0,1};
			Y = new int[]{0,1,2,1};
			color = Color.ORANGE.darker().darker();
			break;
		case 4 :	// L
			X = new int[]{0,0,0,1};
			Y = new int[]{0,1,2,2};
			color = Color.GREEN.darker();
			break;
		case 5 :	// ODWROCONE L
			X = new int[]{0,1,1,1};
			Y = new int[]{2,0,1,2};
			color = Color.BLUE.darker();
			break;
		case 6 :	// B£YSKAWICA
			X = new int[]{0,0,1,1};
			Y = new int[]{1,2,0,1};
			color = Color.YELLOW;
			break;
		case 7 :	// ODWROCONA BLYSKAWICA
			X = new int[]{0,0,1,1};
			Y = new int[]{0,1,1,2};
			color = Color.MAGENTA;
			break;
			default :
				System.out.println("Zle podano typ klocka!");
				break;
		}
	}
	private boolean isFreeUnder(){
		for(int i = 0 ; i < 4 ; i++){
			if(Y[i]+1 >= Board.getHeight())	// ¿eby nie wysz³o poza tablicê
				return false;
			else if(motherBoard.getField(X[i], Y[i]+1).getContent() == 2)
				return false;
		}
		
		return true;
	}
	private boolean isFreeLeft(){
		for(int i = 0 ; i < 4 ; i++){
			if(X[i]-1 < 0)
				return false;
			if(motherBoard.getField(X[i]-1, Y[i]).getContent() == 2)
				return false;
		}
		
		return true;
	}
	private boolean isFreeRight(){
		for(int i = 0 ; i < 4 ; i++){
			if(X[i]+1 >= Board.getWidth())
				return false;
			if(motherBoard.getField(X[i]+1, Y[i]).getContent() == 2)
				return false;
		}
		
		return true;
		
	}
	private void convertToStatic(){
		for(int i = 0 ; i < 4 ; i++){
			motherBoard.getField(X[i], Y[i]).setStaticContent(color);
		}
		motherBoard.checkRows();
		motherBoard.checkColumns();
	}
	public void moveDown(){
		if(isFreeUnder()){
			for(int i = 0 ; i < 4 ; i++)
				Y[i]++;
		}else{
			convertToStatic();
			motherBoard.nextBlock();
		}
	}
	public void moveLeft(){
		if(isFreeLeft()){
			for(int i = 0 ; i < 4 ; i++)
				X[i]--;
		}
	}
	public void moveRight(){
		if(isFreeRight()){
			for(int i = 0 ; i < 4 ; i++)
				X[i]++;
		}
	}
	public void drop(){
		isFalling = true;
	}
	public Color getColor(){
		return color;
	}
	public int getX(int i){
		if(i < 0 || i >= 4){
			System.out.println("Zle podane i!");
			throw new IllegalArgumentException("");
		}
		else
			return X[i];
	}
	public int getY(int i){
		if(i < 0 || i >= 4){
			System.out.println("Zle podane i!");
			throw new IllegalArgumentException("");
		}
		else
			return Y[i];
	}
	public void rotate(){
		// calosc zamykam w petli zeby moc z niej w odpowiednim momencie wyskoczyc
		int oneLoop = 0;
		motherLoop: while(oneLoop == 0){
			oneLoop = 1;
		// tworze i zeruje macierz 4x4
		int d = type == 1 ? 4 : 3;	// dla pod³u¿nego bêdzie klatka 4x4, dla reszty: 3x3
		if(type != 2 ){	// a dla kwadratowego nic nie trzeba obracac
			// tworze macierz i przerzucam do niej klocka:
			// tworze i zeruje macierz dxd
			int[][] matrix = new int[d][d];
			for(int i = 0 ; i < d ; i++)
				for(int j = 0 ; j < d ; j++)
					matrix[i][j] = 0;
			// wyznaczam najmniejsze X i Y z tablic X[] i Y[]
			int minX = X[0], minY = Y[0];
			for(int i = 1 ; i < d ; i++){
				if(X[i] < minX)	minX = X[i];
				if(Y[i] < minY)	minY = Y[i];
			}
			// przepisuje do czystej macierzy klocka, odejmujac najmniejsze X i Y
			// dzieki temu przystaje on do lewej i gornej krawedzi macierzy.
			for(int i = 0 ; i < 4 ; i++)
				matrix[Y[i]-minY][X[i]-minX] = 1;
			
			// dla przypadkow przy scianie przerywam calosc:
			if(minX + d > Board.getWidth()){
				System.out.println("Protestuje, bo: minX+d=" +(minX+d)+ ", a Board.getWidth()=" + Board.getWidth());
				break motherLoop;
			}
			
			if(d==4){	// dla dlugiego przycinam jeszcze raz
				if(matrix[3][0] == 1){	// jest pionowo
					for(int i = 0 ; i < 4 ; i++){
						matrix[i][0] = 0;
						matrix[i][1] = 1;
					}
				}else{	// jest poziomo
					for(int i = 0 ; i < 4 ; i++){
						matrix[0][i] = 0;
						matrix[1][i] = 1;
					}
				}
			}
			// nowa macierz to macierz z klockiem obrocona o 90 stopni
			int[][] newMatrix = new int[d][d];
			for(int i = 0 ; i < d ; i++){
				for(int j = 0 ; j < d ; j++){
					newMatrix[i][j] = matrix[j][d-i-1];
				}
			}
			boolean isFree = true;	// sprawdzam czy nowy klocek nie naruszy statycznych klockow
			int index = 0;
			for(int i = 0 ; i < d ; i++){
				for(int j = 0 ; j < d ; j++){
					if(i+minY < Board.getHeight() && j+minX < Board.getWidth()){
						if(motherBoard.getField(j+minX, i+minY).getContent() == 2){
							isFree = false;
						}
					}
				}
			}
			if(isFree){
				index = 0;
				for(int i = 0 ; i < d ; i++){
					for(int j = 0 ; j < d ; j++){
						if( minY >= 0 && j+minX < Board.getWidth() && i+minY < Board.getHeight()){	// wymiary sie beda zgadzac z plansza.
							if(newMatrix[i][j] == 1){
								X[index] = j+minX;
								Y[index] = i+minY;
								index++;
							}
						}
					}
				}
			}
		}
	}
	}
	public int[] getTabX(){
		return X;
	}
	public int[] getTabY(){
		return Y;
	}
	public int getType(){
		return type;
	}
}