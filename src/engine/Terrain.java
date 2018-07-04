package engine;

import static org.lwjgl.opengl.GL11.*;

public class Terrain {

	public static void render(float center, int range, int tile_size) {
		for (float x = center - range / 2; x <  center + range / 2; x += tile_size) {
			float height = Maths.perlinNoise(x) * 100;
			float next_height = Maths.perlinNoise(x + tile_size) * 100;
			glBegin(GL_QUADS);
				glColor3f(0.3f, 0.9f, 0f);
				glVertex2f(x, 0);
				glVertex2f(x, 200 + height);
				glVertex2f(x + tile_size, 200 + next_height);
				glVertex2f(x + tile_size, 0);
			glEnd();
		}
		
	}

}
