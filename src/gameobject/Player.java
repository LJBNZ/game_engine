package gameobject;

import static org.lwjgl.glfw.GLFW.*;
import engine.GameObject;
import engine.Sprite;
import game.Game;
import input.KeyboardHandler;

public class Player extends GameObject {
	public static final float SIZE = 32;
	private float speed = 5f;
	
	public Player (float x, float y) {
		super(x, y, 0.8f, 0.1f, 0.5f, SIZE, SIZE);
	}
	
	public void getInput() {
		if (KeyboardHandler.isKeyDown(GLFW_KEY_W)) {
			move(0, 1);
		}
		if (KeyboardHandler.isKeyDown(GLFW_KEY_A)) {
			move(-1, 0);
		}
		if (KeyboardHandler.isKeyDown(GLFW_KEY_S)) {
			if ((super.getPosY() - 1 * speed) > Game.GROUND_LEVEL) {
				move(0, -1);
			}
		}
		if (KeyboardHandler.isKeyDown(GLFW_KEY_D)) {
			move(1, 0);
		}
	}

	private void move(float x, float y) {
		super.setPosX(super.getPosX() + (x * speed));
		super.setPosY(super.getPosY() + (y * speed));
	}
}
