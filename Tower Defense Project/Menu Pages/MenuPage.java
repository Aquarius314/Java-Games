import java.awt.Graphics;

public class MenuPage extends Page{

	TextField TITLE_FIELD = new TextField(6, 20, "MAIN MENU PAGE");
	TextField LIFES_FIELD = new TextField(6, 40, "Lifes left: ", Integer.toString(Player.getLifes()));
	TextField MOUSE_COORDINATES = new TextField(6, 60, "X: ", Integer.toString(Mouse.getX()-Menu.xCorner));
	TextField MONEY_FIELD = new TextField(6, 80, "Money: ", "");
	
	public MenuPage(Menu menu) {
		super(menu);
		elements.add(TITLE_FIELD);
		elements.add(LIFES_FIELD);
		elements.add(MOUSE_COORDINATES);
		elements.add(MONEY_FIELD);
	}

	@Override
	public void react() {
		super.react();
		if(UserInterface.isTowerChosen()){
			// go to TowerAttributes page
			parentMenu.setCurrentPageType(PageType.TOWER_ATTRIBUTES);
		}
	}
	protected void refreshValues(){
		LIFES_FIELD.setValue(Integer.toString(Player.getLifes()));
		MOUSE_COORDINATES.setValue(Integer.toString(Mouse.getX()-Menu.xCorner));
		MONEY_FIELD.setValue(Integer.toString(Player.getMoney()));
	}
	
	@Override
	public PageType getType(){
		return PageType.MAIN;
	}

}
