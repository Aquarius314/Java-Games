import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Bomb extends Missile{

	private double explosionRange;
	
	public Bomb(Artillery t, Monster m) {
		super(t, m);
		explosionRange = t.getExplosionRange();
	}
	@Override
	protected void hit(){
		explode();
		super.hit();
	}
	private void explode(){
		try{
			Displayer.explosions.add(new Explosion(xPos, yPos));
			
			ArrayList <Monster> copyOfMonsters = new ArrayList <Monster> ();
			copyOfMonsters.addAll(Map.monsters);
			
			System.out.println("Explode!");
			for(Monster m : copyOfMonsters){
				double dist = AL.DISTANCE(m.getX(), m.getY(), xPos, yPos);
				if( dist <= explosionRange && m.isAlive() ){
					if(dist > 3)
						m.getShot((int)(damage*((explosionRange-dist)/explosionRange)));
					else
						m.getShot((int)(damage));
					System.out.println(damage*((explosionRange-dist)/explosionRange));
				}
			}
		}
		catch(ConcurrentModificationException e){
			System.out.println("Concurrent from explode!");
		}
	}
}
