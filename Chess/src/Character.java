import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Character {
	
	Font font = new Font("Times New Roman", Font.BOLD, Win.R+15);

	boolean isAlive = false;
	char player;
	CharType type;
	char alignment;
	int x, y;
	String name = "";
	public Character(char alignment, CharType type, int x, int y){
		this.type = type;
		this.alignment = alignment;
		this.x = x;
		this.y = y;
		Board.fields[x][y].isFree = false;
		Board.fields[x][y].character = this;
		isAlive = true;
		switch(type){
		case KROL :
			name = "Krol";
			break;
		case HETMAN :
			name = "Hetman";
			break;
		case GONIEC :
			name = "Goniec";
			break;
		case SKOCZEK :
			name = "Skoczek";
			break;
		case WIEZA :
			name = "Wieza";
			break;
		case PION :
			name = "Pion";
			break;
			default :
				System.out.println("Jaki� dziki typ pionka!");
				break;
		}
		if(alignment==' ')
			name = "";
	}
	public void paint(Graphics g){
		int rendX = x*Win.R + x*5 + Board.cornerX;
		int rendY = y*Win.R + y*5 + Board.cornerY;
		BufferedImage render = ArtPanel.pionB;
		switch(type){
		case KROL :
			render = (alignment=='A') ? ArtPanel.krolA : ArtPanel.krolB;
			break;
		case HETMAN :
			render = (alignment=='A') ? ArtPanel.hetmanA : ArtPanel.hetmanB;
			break;
		case GONIEC :
			render = (alignment=='A') ? ArtPanel.goniecA : ArtPanel.goniecB;
			break;
		case SKOCZEK :
			render = (alignment=='A') ? ArtPanel.skoczekA : ArtPanel.skoczekB;
			break;
		case WIEZA :
			render = (alignment=='A') ? ArtPanel.wiezaA : ArtPanel.wiezaB;
			break;
		case PION :
			render = (alignment=='A') ? ArtPanel.pionA : ArtPanel.pionB;
			break;
		}
		if(isAlive){
			if(Mouse.isDragging && Board.startC.x==x && Board.startC.y==y){
				g.drawImage(render, Mouse.x-20, Mouse.y-20, Win.artPanel);
			}else{
				g.drawImage(render, rendX+10, rendY+10, Win.artPanel);
			}
		}
	}
	protected void makeFieldPossible(int i, int j){
		if( !(Board.fields[i][j].isFree) ){
			if(Board.fields[i][j].character.alignment != alignment){
				Board.fields[i][j].isPossible = true;
			}
		}else
			Board.fields[i][j].isPossible = true;
	}
	protected void makeFieldImpossible(int i, int j){
		Board.fields[i][j].isPossible = false;
	}
	public void checkForPossibleFields(){
		for(int i = 0 ; i < 8 ; i++){
			for(int j = 0 ; j < 8 ; j++){
				if(type==CharType.KROL){	// tylko s�siaduj�ce pola
					if(Math.abs(i-x)<=1 && Math.abs(j-y)<=1)
						makeFieldPossible(i, j);
					/* algorytm okre�lania mo�liwych p�l (AOMP):
					Pole sprawdzane musi si� znajdowa� w odleg�o�ci nie wi�kszej ni� 1 od kr�la.
					Zatem r�nica ich wsp�rz�dnych <= 1
					 */
				}
				if(type==CharType.HETMAN){	// pionowe/poziome/uko�ne
					if(i==x || j==y || Math.abs(i-x)==Math.abs(j-y))
						makeFieldPossible(i, j);
					/* AOMP:
					Pole sprawdzane musi mie� tak� sam� wsp�rz�dn� x LUB y LUB:
					R�nica pomi�dzy wsp�rz�dnymi hetmana a pola sprawdzanego musi by� taka sama
					zar�wno dla x jak y, dzi�ki czemu wektor przesuni�cia b�dzie mia� k�t 45
					 */
				}
				if(type==CharType.GONIEC){	// uko�ne
					if(Math.abs(i-x)==Math.abs(j-y))
						makeFieldPossible(i, j);
					// AOMP: Patrz powy�ej: HETMAN, z tym �e jedynie dla uko�nych
				}
				if(type==CharType.SKOCZEK){	// wektor (2,1) / (1,2) i ich odbicia dla czterech kierunk�w ( L )
					if( (Math.abs(i-x)==2&&Math.abs(j-y)==1) || (Math.abs(i-x)==1&&Math.abs(j-y)==2) )
						makeFieldPossible(i, j);
					/* AOMP:
					Pole sprawdzane musi by� oddalone od skoczka o wektor (a, b), gdzie:
					|a| = 2, |b| = 1 LUB
					|a| = 1, |b| = 2, co daje nam punkty oddalone o wektory:
					(-1, -2)
					(-1, 2)
					(1, -2)
					(1, 2)
					(-2, -1)
					(-2, 1)
					(2, -1)
					(2, -2)
					Stosuj�c warto�� bezwzgl�dn� wystarczy sprawdzi� tylko dwie kombinacje.
					 */
				}
				if(type==CharType.WIEZA){	// pionowe / poziome
					if(i==x || j==y)
						makeFieldPossible(i, j);
					/* AOMP:
					Pole sprawdzalne musi le�e� na tej samej wsp�rz�dnej x LUB y
					 */
				}
				if(type==CharType.PION){	// w strone przeciwnika o 1 LUB o 2 je�eli jest to pierwszy ruch, bicie na ukos!
					if(alignment=='A'){
						//pierwszy ruch
						if(i==x && y==1 && j==3)
							makeFieldPossible(i, j);
						//kolejne ruchy
						if(i==x && j-1==y)
							makeFieldPossible(i, j);
						// blokowanie przez wroga pola naprzeciw
						for(int k = 0 ; k < 16 ; k++){
							//bicie uko�ne
							if(Math.abs(Board.charsetB[k].x-x)==1 && Board.charsetB[k].y-1==y && Board.charsetB[k].x==i && Board.charsetB[k].y==j)
								makeFieldPossible(i, j);
							//blokowanie czo�owego
							if(Board.charsetB[k].x==x && Board.charsetB[k].y-1==y)
								for(int m = 0 ; m < 8 ; m++)
									for(int n = 0 ; n < 8 ; n++)
									{
										if(Board.fields[m][n].isPossible)
											if( !(Board.fields[m][n].isFree))
												if( Board.fields[m][n].character.alignment != alignment)
													makeFieldImpossible(i, j);
									}
						}
					}
					else{
						//pierwszy ruch
						if(i==x && y==6 && j==4)
							makeFieldPossible(i, j);
						//kolejne ruchy
						if(i==x && j+1==y)
							makeFieldPossible(i, j);
						// blokowanie przez wroga pola naprzeciw
						for(int k = 0 ; k < 16 ; k++){
							//bicie uko�ne
							if(Math.abs(Board.charsetA[k].x-x)==1 && Board.charsetA[k].y+1==y && Board.charsetA[k].x==i && Board.charsetA[k].y==j)
								makeFieldPossible(i, j);
							if(Board.charsetB[k].x==x && Board.charsetB[k].y-1==y)
								for(int m = 0 ; m < 8 ; m++)
									for(int n = 0 ; n < 8 ; n++)
									{
										if(Board.fields[m][n].isPossible)
											if( !(Board.fields[m][n].isFree))
												if( Board.fields[m][n].character.alignment != alignment)
													makeFieldImpossible(i, j);
									}
						}
						
					}
				}
			}
		}
	}
}

