package engine;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {
	public float height;
	public float width;
	public Texture tex;
	
	public Sprite(String tex_path, float height, float width) {
		this.tex = Texture.loadTexture(tex_path);
		this.height = height;
		this.width = width;
	}

	public void render() {
		if (this.tex == null) {
			glBegin(GL_QUADS);
				glVertex2f(0, 0);
				glVertex2f(0, height);
				glVertex2f(width, height);
				glVertex2f(width, 0);
			glEnd();
		} else {
			glColor3f(1f, 1f, 1f);
			glEnable(GL_TEXTURE_2D);
			glEnable(GL_ALPHA_TEST);
			glAlphaFunc(GL_GREATER,0);
			tex.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0,0); glVertex2f(0, 0);
				glTexCoord2f(0,1); glVertex2f(0, height);
				glTexCoord2f(1,1); glVertex2f(width, height);
				glTexCoord2f(1,0); glVertex2f(width, 0);
			glEnd();
			glDisable(GL_TEXTURE_2D);
			glDisable(GL_ALPHA_TEST); 
		}
		
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getWidth() {
		return width;
	}
}
