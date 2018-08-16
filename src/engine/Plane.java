package engine;

import static org.lwjgl.opengl.GL11.*;

public class Plane {
	public float height;
	public float width;
	public Texture tex;
	public float scroll_speed_dividend;
	public float y_dividend;
	
	public Plane(String tex_path, float width, float height, float scroll_speed_dividend, float y_dividend) {
		this.tex = Texture.loadTexture(tex_path);
		this.height = height;
		this.width = width;
		this.scroll_speed_dividend = scroll_speed_dividend;
		this.y_dividend = y_dividend;
	}
	
	public void update() {
		
	}
	
	public void render(float player_x, float player_y) {

			int start = (int) ((int) (player_x - width * 2) - ((player_x - width * 2) % width));
			
			for (int x = start; x <= start + width * 3; x += width) {
				glPushMatrix();
				glTranslatef(player_x / scroll_speed_dividend, 200 + player_y / y_dividend, 0f);
					glPushMatrix();
						glColor3f(1f, 1f, 1f);
						glEnable(GL_TEXTURE_2D);
						glEnable(GL_ALPHA_TEST);
						glAlphaFunc(GL_GREATER,0);
						tex.bind();
						
						glBegin(GL_QUADS); 
							glTexCoord2f(0,0); glVertex2f(x, 0);
							glTexCoord2f(0,1); glVertex2f(x, height);
							glTexCoord2f(1,1); glVertex2f(x + width, height);
							glTexCoord2f(1,0); glVertex2f(x + width, 0);
						glEnd();
						
						glDisable(GL_TEXTURE_2D);
						glDisable(GL_ALPHA_TEST); 
					glPopMatrix();
				glPopMatrix();
			}

	}
}


