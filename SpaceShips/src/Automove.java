
public class Automove {

	private int midX, midY, nr;
	int speedX, speedY;
	private boolean justStarted = true;
	public Automove(int nr, int x, int y){
		midX = x;
		midY = y;
		this.nr = nr;
		speedX = 0;
		speedY = 0;
	}
	public void go(int x, int y){
		switch(nr){
		case 1 :
			go1(x, y);
			break;
		case 2 :
			go2(x, y);
			break;
		case 3 :
			go3(x, y);
			break;
		case 4 :
			go4(x, y);
			break;
		case 5 :
			go5(x, y);
			break;
			default :
				System.out.println("Nie ma takiego modu³u automove");
				break;
		}
	}
	private void go1(int x, int y){
		if(justStarted){	// nadanie prêdkoœci pocz¹tkowej
			speedX = 1;
			if(midX + 600 < x)
				justStarted = false;
		}
		else{
			if(midX + 600 < x && speedX == 1){
				speedX = 0;
				speedY = 1;
			}
			if(midY + 200 < y && speedY == 1){
				speedX = -1;
				speedY = 0;
			}
			if(midX + 200 > x && speedX == -1){
				speedX = 0;
				speedY = -1;
			}
			if(midY - 200 > y && speedY == -1){
				speedX = 1;
				speedY = 0;
			}
		}
	}
	private void go2(int x, int y){
		if(justStarted){	// nadanie prêdkoœci pocz¹tkowej
			speedX = 1;
			speedY = 1;
			if(midX + 300 < x)
				justStarted = false;
		}
		else{
			if(y < midY - 50){
				speedY = 1;
			}
			if(y > midY + 50){
				speedY = -1;
			}
			if(x > midX + 600){
				speedX = -1;
			}
			if(x < 100)
				speedX = 1;
		}
	}
	public void go3(int x, int y){
		if(justStarted){	// nadanie prêdkoœci pocz¹tkowej
			speedX = 1;
			speedY = 0;
			if(midX + 100 < x)
				justStarted = false;
		}
		else{
			speedY = (int)(5*(Math.sin((System.currentTimeMillis()+10*midY)/100/Math.PI)));
			speedX = (int)(5*(Math.cos((System.currentTimeMillis()+10*midY)/100/Math.PI)));
			if(x > midX + 500)
				speedX = -speedX;
		}
	}
	public void go4(int x, int y){
		// góra-dó³ z lekkim przesuwaniem w prawo
		if(justStarted){	// nadanie prêdkoœci pocz¹tkowej
			speedX = 1;
			speedY = 0;
			if(midX + 100 < x)
				justStarted = false;
		}
		else{
			speedY = (int)(3*(Math.sin((System.currentTimeMillis()+10*midY)/200/Math.PI)));
			speedX = 1;
		}
	}
	public void go5(int x, int y){
		//góra-dó³, modu³ dla bossów itp
		if(justStarted){
			speedX = 0;
			speedY = 1;
			justStarted = false;
		}
		if(y > 500)
			speedY = -1;
		if(y < 100)
			speedY = 1;
	}
	public int getSpeedX(){
		return speedX;
	}
	public int getSpeedY(){
		return speedY;
	}
}
	/*
	Automove to klasa przechowuj¹ca ró¿ne modu³y automatycznego przemieszczania przeciwników.
	Poszczególne modu³y:
	1. prawo, dó³, lewo, góra ; prostopadle
	2. zygzak: prawo-góra, prawo-dó³, powtarzanie, lewo-góra, lewo-dó³
	3. sinus/cosinus - ruch po okrêgu
	4. góra-dó³ z lekkim przesuwaniem w prawo
	*/
