
public class Button {

	String name;
	int x, y, width, height;
	int nameX, nameY;
	boolean raised = true;
	
	public Button(int X, int Y, int W, int H, String N, int nX, int nY) {
		x = X;
		y = Y;
		width = W;
		height = H;
		name = N;
		nameX = nX;
		nameY = nY;
	}
	public int getX(){	return x;	}
	public int getY(){	return y;	}
	public int getWidth(){	return width;	}
	public int getHeight(){	return height;	}
}
