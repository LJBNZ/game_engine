package gameobject;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import engine.GameObject;
import engine.Maths;
import engine.Sprite;
import engine.Physics;
import engine.Terrain;
import game.Game;
import input.KeyboardHandler;

public class Player extends GameObject {
	public static final float HEIGHT = 128;
	public static final float WIDTH = 64;
	private float move_speed = 5f;
	private float move_direction = 0;
	private float jump_direction;
	private float max_jump_speed = 20f;
	private float max_fall_speed = 9.8f;
	private boolean airborne = false;
	private float jump_height = 64f;
	private float jump_velocity;
	private float jump_duration = 10f;
	private double jump_started;
	
	public Player (float x, float y, String tex_path) {
		super(x, y, tex_path, WIDTH, HEIGHT);
	}
	
	public void getInput() {
		if (KeyboardHandler.isKeyDown(GLFW_KEY_W) && !airborne) {
			jump();
		}
		if (KeyboardHandler.isKeyDown(GLFW_KEY_A) && !airborne) {
			move(-1);
		}
		if (KeyboardHandler.isKeyDown(GLFW_KEY_D) && !airborne) {
			move(1);
		}
		if (!KeyboardHandler.isKeyDown(GLFW_KEY_D) && !KeyboardHandler.isKeyDown(GLFW_KEY_A)) {
			move_direction = 0;
		}
	}
	
	public void update() {
		if (airborne) {
			double time = glfwGetTime();
			double elapsed = time - jump_started;
			if (elapsed >= 0f && super.getPosY() >= Terrain.getTerrainHeight(super.getPosX())) {
				jump_velocity = (float) Math.max((2 * ((jump_duration / 2) - elapsed * max_jump_speed)), -max_fall_speed);
				super.setPosY(super.getPosY() + jump_velocity);
				if (jump_direction != 0) {
					super.setPosX(super.getPosX() + jump_direction * move_speed);
				}
			} else {
				airborne = false;
				super.setPosY(Terrain.getTerrainHeight(super.getPosX()));
			}
		}
	}

	private void move(float x) {
		move_direction = x;
		float new_x = super.getPosX() + (x * move_speed);
		super.setPosX(new_x);
		super.setPosY(Terrain.getTerrainHeight(new_x) - 6);
	}
	
	private void jump() {
		airborne = true;
		jump_started = glfwGetTime();
		jump_direction = move_direction;
	}
	
	@Override
	public void render() {
		glPushMatrix();
			glTranslatef(super.getPosX() - WIDTH / 2, super.getPosY(), 0);
			sprite.render();
		glPopMatrix();
	}
}
