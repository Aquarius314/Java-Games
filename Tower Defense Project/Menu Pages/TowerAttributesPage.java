
public class TowerAttributesPage extends Page{

	Button SELL_BUTTON = new Button(110, 250, 80, 40, "SELL");
	Button RETURN_BUTTON = new Button(100, 300, 100, 40, "RETURN");
	TextField TITLE_FIELD = new TextField(6, 20, "TOWER ATTRIBUTES PAGE");
	TextField NAME_FIELD = new TextField(6, 40, "Name: ", "");
	TextField LEVEL_FIELD = new TextField(6, 60, "Level: ", "");
	
	public TowerAttributesPage(Menu menu) {
		super(menu);
		elements.add(SELL_BUTTON);
		elements.add(TITLE_FIELD);
		elements.add(RETURN_BUTTON);
		elements.add(NAME_FIELD);
		elements.add(LEVEL_FIELD);
	}

	@Override
	public void react() {
		if(!UserInterface.isTowerChosen()){
			parentMenu.setCurrentPageType(PageType.MAIN);
		}
		if(RETURN_BUTTON.isClicked()){
			RETURN_BUTTON.finishJob();
			goBack();
		}
		if(SELL_BUTTON.isClicked()){
			SELL_BUTTON.finishJob();
			sellChosenTower();
		}
		if(UserInterface.isTowerChosen())
			super.react();
	}
	private void sellChosenTower(){
		parentMenu.setCurrentPageType(PageType.SELL_CONFIRMATION);
		UserInterface.unchoseTower();
	}
	private void goBack(){
		parentMenu.setCurrentPageType(PageType.MAIN);
		UserInterface.unchoseTower();
	}
	@Override
	public PageType getType() {
		return PageType.TOWER_ATTRIBUTES;
	}
	@Override
	public void refreshValues() {
		Tower t = Map.towers.get(UserInterface.getChosenIndex());
		NAME_FIELD.setValue(t.getName());
		LEVEL_FIELD.setValue(Integer.toString(t.getLevel()));
	}

}
