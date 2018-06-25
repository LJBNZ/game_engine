package engine;

import java.awt.Rectangle;

public class Physics {
	
	public static boolean checkCollision(GameObject obj1, GameObject obj2) {
		Rectangle r1 = new Rectangle((int)obj1.getPosX(), (int)obj1.getWidth(), (int)obj1.getPosY(), (int)obj1.getHeight());
		Rectangle r2 = new Rectangle((int)obj2.getPosX(), (int)obj2.getWidth(), (int)obj2.getPosY(), (int)obj2.getHeight());

		return r1.intersects(r2);
	}

}
