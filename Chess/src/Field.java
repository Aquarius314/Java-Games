
public class Field {
	
	boolean isWhite = false;
	boolean isPossible = false;
	boolean isFree = true;
	Character character;
	String symbol;
	int number;
	String name;

	public Field(boolean color, int sym, int num){
		number = 8-num;
		isWhite = color;
		switch(sym){
		case 0 :	symbol = "A";
			break;
		case 1 :	symbol = "B";
			break;
		case 2 :	symbol = "C";
			break;
		case 3 :	symbol = "D";
			break;
		case 4 :	symbol = "E";
			break;
		case 5 :	symbol = "F";
			break;
		case 6 :	symbol = "G";
			break;
		case 7 :	symbol = "H";
			break;
			default :
				System.out.println("Oj coœ posz³o za du¿o tych pól chyba");
				break;
		}
		name = symbol+Integer.toString(number);
	}
}
