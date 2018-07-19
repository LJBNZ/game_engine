package game;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import engine.GameObject;
import engine.Terrain;
import gameobject.Player;

public class Game {
	
	private ArrayList<GameObject> game_objects;
	private ArrayList<GameObject> environment_objects;
	private Player player;
	private int window_width = 800;
	private int window_height = 600;
	public static final float GROUND_LEVEL = 200;
	public static final String RES_PATH = "./src/resources/";
	
	public Game(long window, float window_width, float window_height) {
		game_objects = new ArrayList<GameObject>();
		environment_objects = new ArrayList<GameObject>();

		player = new Player((window_width / 2 - (Player.SIZE / 2)), 200, RES_PATH + "player.png");
		GameObject test_tree = new GameObject(600 - 64, Terrain.getTerrainHeight(600), RES_PATH + "tree.png", 128, 256);
		GameObject test_tree1 = new GameObject(300 - 64, Terrain.getTerrainHeight(300), RES_PATH + "tree.png", 128, 256);
		GameObject test_tree2 = new GameObject(400 - 64, Terrain.getTerrainHeight(400), RES_PATH + "tree.png", 128, 256);
		GameObject test_tree3 = new GameObject(100 - 64, Terrain.getTerrainHeight(100), RES_PATH + "tree.png", 128, 256);
		
		game_objects.add(player);
		game_objects.add(test_tree);
		game_objects.add(test_tree1);
		game_objects.add(test_tree2);
		game_objects.add(test_tree3);



	}
	
	public void getInput() {
		player.getInput();
	}
	
	public void update() {
		for (GameObject obj : game_objects) {
			obj.update();
		}
	}
	
	public void render() {
		move_camera(player, window_width, window_height);
		
		Terrain.render((int)player.getPosX());
		
		for (GameObject obj : environment_objects) {
			obj.render();
		}
		
		for (GameObject obj : game_objects) {
			obj.render();
		}
		
		
	}
	
	private void move_camera(Player player, int window_width, int window_height) {
		glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(player.getPosX(), window_width + player.getPosX(), player.getPosY(), player.getPosY() + window_height, -1, 1);
			glTranslatef(window_width / 2 - Player.SIZE / 2, window_height / 2 - Player.SIZE / 2, 0);
		glMatrixMode(GL_MODELVIEW);
	}
}
