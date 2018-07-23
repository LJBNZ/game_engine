package engine;

import static org.lwjgl.opengl.GL11.*;

public class EnvironmentObject {
	public String name;
	public Sprite sprite;
	
	public EnvironmentObject(String name, String tex_path, float width, float height) {
		this.name = name;
		sprite = new Sprite(tex_path, height, width);
	}
	
	public float getWidth() {
		return sprite.getWidth();
	}

	public float getHeight() {
		return sprite.getHeight();
	}
	
	public void update() {
		
	}
	
	public void render(float posx, float posy) {
		glPushMatrix();
			glTranslatef(posx - sprite.width / 2, posy - 10, 0);
			sprite.render();	
		glPopMatrix();
	}
}
