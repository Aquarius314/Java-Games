import java.util.Random;

public class AutomaticPlayer extends Task {
	
	int index = 0;
	
	public AutomaticPlayer() {
		step = 250;
	}
	protected void performTask(){
		for(int i = 0 ; i < World.players.size(); i++){
			if(World.players.get(i).isAutomatic() && World.players.get(i).isAlive()){
				index = i;
				takeControll();
			}
		}
	}
	private void takeControll(){
				/*
				 * skrypt AI dla Tetrisa
				 * zaczêto pisaæ 24.05.2016
				 */
		if(!World.players.get(index).getBoard().getBlock().isFalling){
			World.players.get(index).points = new int[Board.getWidth()];
			//analyze2();
			//if(World.players.get(index).getBoard().getBlock().getType() == 1){
				analyze1();
				makeDecision();
			//}
		}
	}
	private void makeDecision(){
		
		int moves = 0;	// wartosci ujemne to ilosc ruchow w lewo, dodatnie - w prawo
		int rotates = 0;	// ile razy obrocic w lewo ( rotate() )
		int indexMax = 0;
		
		for(int i = 1 ; i < World.players.get(index).points.length ; i++){
			if(World.players.get(index).points[i] > World.players.get(index).points[indexMax]){
				indexMax = i;
			}
		}
		
		int currentX = World.players.get(index).getBoard().getBlock().getX(0);
		moves = currentX - indexMax;
		/*
		switch(World.players.get(index).getBoard().getBlock().getType()){
		case 4 :
			rotates = 2;
			moves++;
			break;
		case 5 :
			rotates = 2;
			break;
		case 7 :
			moves++;
			break;
			default :
				break;
		}
		*/
		for(int i = 0 ; i < rotates ; i++)
			World.players.get(index).getBoard().getBlock().rotate();
		if(moves >= 0){
			for(int i = 0 ; i < moves ; i++)
				World.players.get(index).getBoard().getBlock().moveLeft();
		}
		else{
			for(int i = 0 ; i < Math.abs(moves); i++){
				World.players.get(index).getBoard().getBlock().moveRight();
			}
		}
		
		World.players.get(index).getBoard().getBlock().drop();
	}
	private void randomMoves(int moves){	// losowe ruchy
		for(int i = 0 ; i < moves ; i++){
			switch(new Random().nextInt(4)+1){
			case 1 :
				World.players.get(index).getBoard().getBlock().moveLeft();
				break;
			case 2 :
				World.players.get(index).getBoard().getBlock().moveRight();
				break;
			case 3 :
				World.players.get(index).getBoard().getBlock().rotate();
				break;
			case 4 :
				World.players.get(index).getBoard().getBlock().drop();
				break;
			default :
				System.out.println("Cos pojebales synu.");
				break;
			}
		}
	}
	private int[] measureHeights(Board b){
		int[] heights = new int[Board.getWidth()];
		
		for(int iWidth = 0 ; iWidth < Board.getWidth(); iWidth++){
			for(int iHeight = 0 ; iHeight < Board.getHeight() ; iHeight++){
				if(b.getField(iWidth, iHeight).getContent() == 2){	// znaczy ze mamy wierzcholek!
					heights[iWidth] = Board.getHeight() - iHeight;
					iHeight = Board.getHeight(); // ¿eby skoñczyæ wewnêtrzn¹ pêtlê.
				}
			}						
		}
		
		return heights;
	}
	private void analyze1(){	// Analiza wysokoœci
		/* 
		 * pierwszy typ analizy. Mierzy wysokoœci na poszczególnych szerokoœciach i najmocniej wycenia najni¿sze
		 */
		int[] heights = measureHeights(World.players.get(index).getBoard());

		for(int i = 0 ; i < heights.length ; i++){
			World.players.get(index).points[i] -= heights[i];
		}
		
	}
	private void analyze2(){

		Block block = World.players.get(index).getBoard().getBlock();	// spisuje klocka
		// klocek podluzny tej analizie nie podlega.
		if(block.getType() == 1){
			// nic nie rób.
		}
		else{	// zatem bierzemy pod uwage wszystkie klocki oprocz podluznego.
				// mozemy wiec zamykac je w macierzy 3x3, co ulatwi obliczenia.
			// tworze macierz 3x3 i przerzucam do niej klocka:
			int[][] blockMatrix = saveBlockInMatrix(block);		// tworze i zeruje macierz 4x4		
			
			// mam utworzona macierz przechowujaca klocka. Teraz porownuje j¹ z najwyzej wysunietymi elementami na dole.
			// jaka jest suma ró¿nic pomiêdzy wysokoœciami?
			int[] heights = measureHeights(World.players.get(index).getBoard());
			int currentDifferencesSum = measureHeightsDifferences(heights);
			
			Board newBoard = new Board(0, null);
			int minimumWidth = 0;
			int minimumRotates = 0;
			int minimumSum = 1000;
			for(int iWidth = 0 ; iWidth < Board.getWidth(); iWidth++){
				for(int iRotates = 0 ; iRotates < 4 ; iRotates++){
					newBoard = World.players.get(index).getBoard();
					// przesuwamy klocka do lewej œciany, o ile to mo¿liwe (po to jest iControl, dla bezpieczenstwa)
					
					for(int iControl = 0 ; newBoard.getBlock().getX(0) > 0 && iControl < 10 ; iControl++){
						newBoard.getBlock().moveLeft();
					}
					// teraz przesuwamy go w prawo o tyle ile wymaga iWidth
					for(int i = 0 ; i < iWidth ; i++){
						newBoard.getBlock().moveRight();
					}
					// nastêpnie obracamy go tyle razy ile wymaga iRotates (o ile siê da, tym siê martwi¹ inne funkcje)
					for(int i = 0 ; i < iRotates ; i++){
						newBoard.getBlock().rotate();
					}
					// spuszczamy
					newBoard.getBlock().drop();
					// i mierzymy jaki jest efekt w postaci roznicy wysokosci
					int[] newHeights = measureHeights(newBoard);
					int newDifferencesSum = measureHeightsDifferences(newHeights);
					if(newDifferencesSum < minimumSum){
						minimumSum = newDifferencesSum;
						minimumWidth = iWidth;
						minimumRotates = iRotates;
					}
					//System.out.println("Roznica: " + (currentDifferencesSum - newDifferencesSum));
					World.players.get(index).points[iWidth] += (currentDifferencesSum - newDifferencesSum);
					
				}
			}
			// przesuwamy klocka do lewej œciany, o ile to mo¿liwe (po to jest iControl, dla bezpieczenstwa)
			for(int iControl = 0 ; World.players.get(index).getBoard().getBlock().getX(0) > 0 && iControl < 10 ; iControl++){
				World.players.get(index).getBoard().getBlock().moveLeft();
			}
			// teraz przesuwamy go w prawo o tyle ile wymaga iWidth
			for(int i = 0 ; i < minimumWidth ; i++){
				World.players.get(index).getBoard().getBlock().moveRight();
			}
			// nastêpnie obracamy go tyle razy ile wymaga iRotates (o ile siê da, tym siê martwi¹ inne funkcje)
			for(int i = 0 ; i < minimumRotates ; i++){
				System.out.println("Obracam!");
				World.players.get(index).getBoard().getBlock().rotate();
			}
			World.players.get(index).getBoard().getBlock().drop();
			
		}
	}
	private int measureHeightsDifferences(int[] heights){
		int sum = 0;
		for(int i = 1 ; i < heights.length ; i++){
			int difference = Math.abs( heights[i] - heights[i-1] );
			sum += difference;
		}
		return sum;
	}
	private int[][] saveBlockInMatrix(Block block){
		int[][] matrix = createEmptyMatrix(3, 3);
		int minX = getMin(block.getTabX()), minY = getMin(block.getTabY());		// wyznaczam najmniejsze X i Y z tablic X[] i Y[]
		for(int i = 0 ; i < 4 ; i++)	// przepisuje do czystej macierzy klocka, odejmujac najmniejsze X i Y
			matrix[block.getY(i)-minY][block.getX(i)-minX] = 1;	// oczywiscie 1 oznacza segment, 0 to puste pole
		return matrix;
	}
	private void printMatrix(int[][] matrix){
		System.out.println("Macierz sumowana: ");
		for(int i = 0 ; i < matrix.length ; i++){
			for(int j = 0 ; j < matrix[0].length ; j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	private int[][] createEmptyMatrix(int xDimension, int yDimension){
		int[][] matrix = new int[xDimension][yDimension];
		for(int i = 0 ; i < xDimension ; i++){
			for(int j = 0 ; j < yDimension ; j++){
				matrix[i][j] = 0;
			}
		}
		return matrix;
	}
	private int getMin(int[] matrix){
		int min = matrix[0];
		for(int i = 1 ; i < matrix.length ; i++){
			if(matrix[i] < min){
				min = matrix[i];
			}
		}
		return min;
	}
}




