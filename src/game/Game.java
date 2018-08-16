package game;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import engine.Environment;
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
	public Time game_time;
	
	
	public Game(long window, float window_width, float window_height) {
		game_objects = new ArrayList<GameObject>();
		environment_objects = new ArrayList<GameObject>();
		Environment.init();

		player = new Player((window_width / 2 - (Player.WIDTH / 2)), 200, RES_PATH + "player.png");		
		game_objects.add(player);
		
		game_time = new Time(glfwGetTime());
	}
	
	public void getInput() {
		player.getInput();
	}
	
	public void update() {
		game_time.update();
		
		Environment.update(player.getPosX());
		
		for (GameObject obj : game_objects) {
			obj.update();
		}
	}
	
	public void render() {
		move_camera(player, window_width, window_height);
				
		Environment.render(player.getPosX(), player.getPosY());
		
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
			glTranslatef(window_width / 2 - Player.WIDTH / 2, window_height / 2 - Player.WIDTH / 2 - 100, 0);
		glMatrixMode(GL_MODELVIEW);
	}
}
