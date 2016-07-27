import java.awt.Graphics;
import java.util.ArrayList;

public class Menu {

	private ArrayList <Button> buttons = new ArrayList <Button> ();
	private ArrayList <Page> pages = new ArrayList <Page> ();
	private PageType currentPage;
	public static int xCorner = Game.xMap + Map.WIDTH*Game.SIZE;
	public static int yCorner = Game.yMap;
	
	public Menu() {
		currentPage = PageType.MAIN;
		pages.add(new MenuPage(this));
		pages.add(new SellConfirmationPage(this));
		pages.add(new TowerAttributesPage(this));
		pages.add(new TowerSelectionPage(this));
	}
	
	public void perform(){
		for(int i = 0 ; i < buttons.size(); i++){
			if(buttons.get(i).isClicked())
				buttons.get(i).react();
		}
		for(Page p : pages)
			if(p.getType() == currentPage)
				p.react();
		
		//UITester();
		
	}
	private void UITester(){
		//TODO delete this function when it's job is done
		System.out.println("Pamietaj, ze dziala UITester w klasie Menu!");
		if(Mouse.isLeftClicked())
			currentPage = PageType.SELL_CONFIRMATION;
		else
			currentPage = PageType.MAIN;
	}
	public void display(Graphics g){
		for(Page p : pages){
			if(currentPage == p.getType())
				p.display(g);
		}
	}
	
	public Button getButton(int index){
		return buttons.get(index);
	}
	
	public PageType getCurrentPageType(){
		return currentPage;
	}
	public void setCurrentPageType(PageType type){
		currentPage = type;
	}

}
