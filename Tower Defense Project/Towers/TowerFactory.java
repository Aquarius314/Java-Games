
public class TowerFactory {
	
	private TowerFactory() {
		
	}
	public static void buildTower(TowerType type, int x, int y){
		// check if x,y are valid
		boolean clickInsideMap = false;
		if(x >= 0 && y >= 0 && x < Map.WIDTH && y < Map.HEIGHT)
			clickInsideMap = true;
		if(Game.currentMap.getLevel().getFieldContent(x, y) == 0 && clickInsideMap){
			switch(type){
			case ARTILLERY:
				Map.towers.add( new Artillery(x, y) );
				break;
			case MAGE:
				Map.towers.add( new Mage(x, y) );
				break;
			case ARCHER:
				Map.towers.add( new Archer(x, y) );
				break;
			default:
				System.out.println("Nieznany rodzaj wiezy!");
				break;
			}
			Game.currentMap.markFieldAsOccupied(x, y);
			UserInterface.autoChoose();
		}
		else if(clickInsideMap){
			System.out.println("Pole jest juz zajete!");
		}
		else{
			
		}
	}
	public static void destroyTower(int x, int y){
		boolean destroyed = false;
		System.out.println(x + " " + y);
		for(Tower t : Map.towers){
			if(t.getX() == x*Game.SIZE && t.getY() == y*Game.SIZE){
				Map.towers.remove(t);
				Game.currentMap.getLevel().setField(y, x, 0);
				destroyed = true;
				break;
			}
		}
		if(!destroyed)
			System.out.println("Nie udalo sie zniszczyc wiezy! (Byc moze nie znaleziono tam zadnej)");
	}
	private static void destroyTowerByIndex(int index){
		int x = Map.towers.get(index).getX()/Game.SIZE;
		int y = Map.towers.get(index).getY()/Game.SIZE;
		destroyTower(x, y);
	}
	public static void sellTower(int index){
		boolean sold = false;
		if(index >= Map.towers.size()){
			System.out.println("No such tower! (index: "+index+", size: "+Map.towers.size());
		}
		else{
			Player.giveMoney(Map.towers.get(index).getSellValue());
			destroyTowerByIndex(index);
			sold = true;
		}
		if(!sold)
			System.out.println("Couldn't sell tower!");
	}
}