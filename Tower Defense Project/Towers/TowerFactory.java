
public class TowerFactory {

	Map parentMap;
	
	public TowerFactory(Map parent) {
		parentMap = parent;
	}
	public void buildTower(TowerType type, int x, int y){
		// check if x,y are valid
		boolean clickInsideMap = false;
		if(x >= 0 && y >= 0 && x < Map.WIDTH && y < Map.HEIGHT)
			clickInsideMap = true;
		if(parentMap.getLevel().getFieldContent(x, y) == 0 && clickInsideMap){
			switch(type){
			case ARTILLERY:
				parentMap.towers.add( new Artillery(x, y) );
				break;
			case MAGE:
				parentMap.towers.add( new Mage(x, y) );
				break;
			case ARCHER:
				parentMap.towers.add( new Archer(x, y) );
				break;
			default:
				System.out.println("Nieznany rodzaj wiezy!");
				break;
			}
			parentMap.markFieldAsOccupied(x, y);
		}
		else if(clickInsideMap){
			System.out.println("Pole jest juz zajete!");
		}
		else{
			
		}
	}
	public void destroyTower(int x, int y){
		boolean destroyed = false;
		for(Tower t : parentMap.towers){
			if(t.getX() == x*Game.SIZE && t.getY() == y*Game.SIZE){
				parentMap.towers.remove(t);
				parentMap.getLevel().setField(y, x, 0);
				destroyed = true;
				break;
			}
		}
		if(!destroyed)
			System.out.println("Nie udalo sie zniszczyc wiezy! (Byc moze nie znaleziono tam zadnej)");
	}
}