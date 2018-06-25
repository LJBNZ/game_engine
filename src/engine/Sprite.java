package engine;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {
	public float r;
	public float g;
	public float b;
	
	public float height;
	public float width;
	
	public Sprite(float r, float g, float b, float height, float width) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.height = height;
		this.width = width;
	}

	public void render() {
		glColor3f(r, g, b);
		glBegin(GL_QUADS);
			glVertex2f(0, 0);
			glVertex2f(0, height);
			glVertex2f(width, height);
			glVertex2f(width, 0);
		glEnd();
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getWidth() {
		return width;
	}
}
