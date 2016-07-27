import java.awt.Color;

public class UserInterface {

	public static Menu menu = new Menu();
	private static int chosenTowerIndex;
	private static boolean towerIsChosen = false;
	
	private UserInterface() {
	}

	public static void serve(){
		if(Mouse.isLeftClicked() || Mouse.isRightClicked()){
			if(Mouse.getX() >= Game.xMap && Mouse.getY() >= Game.yMap){
				if(Mouse.getX() < Game.xMap+Game.SIZE*Map.WIDTH && Mouse.getY() < Game.yMap+Game.SIZE*Map.HEIGHT){
					clickOnMap(Mouse.getX() - Game.xMap, Mouse.getY() - Game.yMap);
				}
			}
		}
		menu.perform();
	}
	private static void clickOnMap(int x, int y){	// x,y klikniecia wzgledem mapy (czyli Game.xMap i Game.yMap)
		int xField = x/Game.SIZE;
		int yField = y/Game.SIZE;
		chosenTowerIndex = searchForTowerIndexByField(xField, yField);
	}
	public static void autoChoose(){
		unchoseTower();
		towerIsChosen = true;
		chosenTowerIndex = Map.towers.size()-1;
		Map.towers.get(chosenTowerIndex).showRange = true;
	}
	private static int searchForTowerIndexByField(int x, int y){
		int index = -1;
		
		towerIsChosen = false;
		for(Tower t : Map.towers)
			t.showRange = false;
		for(int i = 0; i < Map.towers.size(); i++){
			if(Map.towers.get(i).getX() == x*Game.SIZE && Map.towers.get(i).getY() == y*Game.SIZE){
				index = i;
				towerIsChosen = true;
				Map.towers.get(index).showRange = true;
				break;
			}
		}
		
		return index;
	}
	public static void unchoseTower(){
		if(towerIsChosen)
			Map.towers.get(chosenTowerIndex).showRange = false;
		towerIsChosen = false;
	}
	public static boolean isTowerChosen(){
		return towerIsChosen;
	}
	public static int getChosenIndex(){
		return chosenTowerIndex;
	}
}
