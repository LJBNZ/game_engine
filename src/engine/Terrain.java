package engine;

import static org.lwjgl.opengl.GL11.*;

public class Terrain {
	private static final int X_PERIOD_DIVIDEND = 2000;
	public static final int Y_AMPLITUDE = 3000;
	private static final int GROUND_HEIGHT = 200;
	private static final int TILE_SIZE = 50;

	public static void render(float center) {
		float half_win_width = Main.WINDOW_WIDTH / 2;
		float left_sample = Math.floorDiv((int) (center - half_win_width), TILE_SIZE) * TILE_SIZE;
		float right_sample = (float) (Math.ceil((center + half_win_width) / TILE_SIZE)) * TILE_SIZE;
		
		for (float terrain_sample = left_sample; terrain_sample <= right_sample; terrain_sample += TILE_SIZE) {
			//draw the terrain
			float height = getTerrainHeight(terrain_sample);
			float next_height = getTerrainHeight(terrain_sample + TILE_SIZE);
			
			glBegin(GL_QUADS);
				glColor3f(0.18f, 0.463f, 0.094f);
				glVertex3f(terrain_sample, -1000, 0);
				glVertex3f(terrain_sample, height, 0);
				glVertex3f(terrain_sample + TILE_SIZE, next_height, 0);
				glVertex3f(terrain_sample + TILE_SIZE, -1000, 0);

			glEnd();

		}

	}
	
	public static int getTerrainHeight(float x) {
		return (int) (GROUND_HEIGHT + Maths.perlinNoise(Math.abs(x)/X_PERIOD_DIVIDEND) * Y_AMPLITUDE);
	}
}
