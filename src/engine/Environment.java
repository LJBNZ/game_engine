package engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import environmentobject.Bush;
import environmentobject.Grass;
import environmentobject.Tree;

public class Environment {

	private static final int MIN_INTERVAL = 20;
	private static final int RENDER_X_BUFFER = 128;
	private static final int CHUNK_SIZE = 2000;
	private static ArrayList<Tree> trees;
	private static ArrayList<Grass> grasses;
	private static ArrayList<Bush> bushes;
	private static ArrayList<Integer> tree_positions;
	private static int chunk_left_bound;
	private static int chunk_right_bound;
	
	public static void init() {
		trees = new ArrayList<Tree>();
		trees.add(new Tree("Spruce", game.Game.RES_PATH + "tree.png", 128, 256));
		
		grasses = new ArrayList<Grass>();
		grasses.add(new Grass("gr1", game.Game.RES_PATH + "sml_grass1.png", 8, 16));
		grasses.add(new Grass("gr2", game.Game.RES_PATH + "sml_grass2.png", 8, 16));
		grasses.add(new Grass("gr3", game.Game.RES_PATH + "sml_grass3.png", 8, 16));
		grasses.add(new Grass("gr4", game.Game.RES_PATH + "sml_grass4.png", 8, 16));
		
		bushes = new ArrayList<Bush>();
		bushes.add(new Bush("Berry Bush", game.Game.RES_PATH + "bush1.png", 64, 32));
		bushes.add(new Bush("Long grass", game.Game.RES_PATH + "bush2.png", 64, 32));
		
		tree_positions = getTreePositions(-1000, CHUNK_SIZE);
		chunk_left_bound = -1000;
		chunk_right_bound = chunk_left_bound + CHUNK_SIZE;
	}
	
	public static void update(float player_x) {
		//System.out.println(player_x);
		//generate to the left
		if (player_x < chunk_left_bound + 400) {
			tree_positions = getTreePositions(chunk_left_bound - CHUNK_SIZE / 2, CHUNK_SIZE);
			chunk_right_bound -= CHUNK_SIZE / 2;
			chunk_left_bound -= CHUNK_SIZE / 2;
			
		//generate to the right
		} else if (player_x > chunk_right_bound - 400) {
			tree_positions = getTreePositions(chunk_right_bound - CHUNK_SIZE / 2, CHUNK_SIZE);
			chunk_right_bound += CHUNK_SIZE /  2;
			chunk_left_bound += CHUNK_SIZE / 2;
		}
	}

	public static void render(float player_x) {
		
		float half_win_width = Main.WINDOW_WIDTH / 2;
		float left_bound = player_x - (half_win_width + RENDER_X_BUFFER);
		float right_bound = player_x + (half_win_width + RENDER_X_BUFFER);
				
		Iterator<Integer> itr = tree_positions.iterator();
		
		while (itr.hasNext()) {
			int tree_x = itr.next();
			if (tree_x > left_bound && tree_x <= right_bound) {
				trees.get(0).render(tree_x, Terrain.getTerrainHeight(tree_x));
			}
		}
		
		for (int x = (int) ((int) left_bound - (left_bound % 200)); x < right_bound; x += 200) {
			int random = (int) (Math.abs(Maths.noise(x)) * 10 % bushes.size());
			Bush b = bushes.get(random);
			b.render(x, Terrain.getTerrainHeight(x) + 8);
		}
		
		Terrain.render(player_x);
		
		for (int x = (int) ((int) left_bound - (left_bound % 8)); x < right_bound; x += 8) {
			int random = (int) (Math.abs(Maths.noise(x)) * 10 % grasses.size());
			Grass g = grasses.get(random);
			g.render(x, Terrain.getTerrainHeight(x) + 8);
		}
		
	}
	
	public static ArrayList<Integer> getTreePositions(int start_x, int chunk_size) {
		
		ArrayList<Integer> positions = new ArrayList<Integer>();
		
		for (int x = start_x; x <= start_x + chunk_size; x += MIN_INTERVAL) {
			if (treeTest(x)) {
				x += 100;
				positions.add(x);
			}
		}
		
		return positions;
	}
	
	private static boolean treeTest(int x) {
		System.out.println(Maths.noise(x));

		if (Maths.noise(x) > 0.8f) {
			return true;
		}
		return false;
	}
	
}
