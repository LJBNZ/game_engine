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

		player = new Player(window_width / 2 - (Player.SIZE / 2), 200, RES_PATH + "player.png");
		
		game_objects.add(player);

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
		
		Terrain.render((int)player.getPosX(), 1000, 100);
		
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
		glOrtho(0 + player.getPosX(), window_width + player.getPosX(), 0, window_height, -1, 1);
		glTranslatef(window_width / 2 - Player.SIZE / 2, 0, 0);
		glMatrixMode(GL_MODELVIEW);
	}
}
