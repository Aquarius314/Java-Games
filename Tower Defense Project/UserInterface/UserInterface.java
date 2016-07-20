
public class UserInterface {

	public UserInterface() {
		// TODO Auto-generated constructor stub
	}
	public void serve(){
		if(Mouse.isLeftClicked()){
			leftClick();
		}
	}
	private void leftClick(){
		// okreslam obszar klikniecia:
		// MAPA
		if(Mouse.getX() >= Game.xMap && Mouse.getY() >= Game.yMap){
			if(Mouse.getX() < Game.xMap+Game.SIZE*Map.WIDTH && Mouse.getY() < Game.yMap+Game.SIZE*Map.HEIGHT){
				clickOnMap(Mouse.getX() - Game.xMap, Mouse.getY() - Game.yMap);
			}
		}
	}
	private void clickOnMap(int x, int y){	// x,y klikniecia wzgledem mapy (czyli - Game.xMap i Game.yMap)
		
	}
}
