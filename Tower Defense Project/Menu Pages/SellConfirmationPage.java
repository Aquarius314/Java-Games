
public class SellConfirmationPage extends Page{
	
	TextField TITLE_FIELD = new TextField(6, 20, "SELL CONFIRMATION PAGE");
	TextField QUESTION_FIELD = new TextField(100, 100, "Are you sure?");
	Button YES_BUTTON = new Button(95, 120, 50, 30, "YES");
	Button NO_BUTTON = new Button(155, 120, 50, 30, "NO");
	
	public SellConfirmationPage(Menu menu) {
		super(menu);
		elements.add(TITLE_FIELD);
		elements.add(QUESTION_FIELD);
		elements.add(YES_BUTTON);
		elements.add(NO_BUTTON);
	}

	@Override
	public void react() {
		super.react();
		if(YES_BUTTON.isClicked()){
			YES_BUTTON.finishJob();
			TowerFactory.sellTower(UserInterface.getChosenIndex());
			goBack();
		}
		if(NO_BUTTON.isClicked()){
			NO_BUTTON.finishJob();
			goBack();
		}
	}
	private void goBack(){
		parentMenu.setCurrentPageType(PageType.TOWER_ATTRIBUTES);
	}

	@Override
	public PageType getType() {
		return PageType.SELL_CONFIRMATION;
	}

	@Override
	public void refreshValues() {
		
	}

}
