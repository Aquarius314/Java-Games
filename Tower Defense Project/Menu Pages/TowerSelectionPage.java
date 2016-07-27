
public class TowerSelectionPage extends Page{

	TextField TITLE_FIELD = new TextField(6, 20, "TOWER SELECTION PAGE");
	
	public TowerSelectionPage(Menu menu) {
		super(menu);
		elements.add(TITLE_FIELD);
	}

	@Override
	public void react() {
		
	}

	@Override
	public PageType getType() {
		return PageType.TOWER_SELECTION;
	}

	@Override
	public void refreshValues() {
		// TODO Auto-generated method stub
		
	}

}
