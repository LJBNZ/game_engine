package engine;

import static org.lwjgl.opengl.GL11.*;

public class GameObject {
	private float posx;
	private float posy;
	private Sprite sprite;
	
	public GameObject(float x, float y) {
		this.posx = x;
		this.posy = y;
	}
	
	public GameObject(float x, float y, float r, float g, float b, float height, float width) {
		this.posx = x;
		this.posy = y;
		sprite = new Sprite(r, g, b, height, width);
	}
	
	public float getPosX() {
		return posx;
	}

	public float getPosY() {
		return posy;
	}
	
	public void setPosX(float x) {
		this.posx = x;
	}

	public void setPosY(float y) {
		this.posy = y;
	}
	
	public float getWidth() {
		return sprite.getWidth();
	}

	public float getHeight() {
		return sprite.getHeight();
	}
	
	public void update() {
		
	}
	
	public void render() {
		glPushMatrix();
			glTranslatef(posx, posy, 0);
			sprite.render();
		glPopMatrix();
	}
}
