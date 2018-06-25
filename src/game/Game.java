package game;

import java.util.ArrayList;

import engine.GameObject;
import gameobject.Player;

public class Game {
	
	private ArrayList<GameObject> game_objects;
	private Player player;
	public static final float GROUND_LEVEL = 0;
	
	public Game(long window, float window_width, float window_height) {
		game_objects = new ArrayList<GameObject>();
		player = new Player(window_width / 2 - (Player.SIZE / 2), window_height / 2 - (Player.SIZE / 2));
		GameObject floor = new GameObject(-100, 0, 0f, 1f, 0f, 200, 1000);
		
		game_objects.add(floor);
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
		for (GameObject obj : game_objects) {
			obj.render();
		}
		
	}
}
