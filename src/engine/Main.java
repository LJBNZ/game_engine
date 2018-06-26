package engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;

import game.Game;
import input.KeyboardHandler;

public class Main {
	
	static long window;
	static int window_width = 800;
	static int window_height = 600;
	static Game game;
	private static GLFWKeyCallback keyCallback;
	private final static int FPS_CAP = 60;
	private static double frame_delay;
	private static double next_frame = 0;
		
	public static void main(String args[]) {
		//Initialise the game
		initDisplay();
		initInput();
		initGL();
		initGame();
		
		//enter the gameloop
		gameLoop();
		
		//cleanup on exit
		cleanUp();
	}
	
	private static void initInput() {
		glfwSetKeyCallback(window, keyCallback = new KeyboardHandler());		
		
	}

	private static void cleanUp() {
		glfwTerminate();
	}

	private static void gameLoop() {
		while (!glfwWindowShouldClose(window)) {
			if (glfwGetTime() >= next_frame) {
				getInput();
				update();
				render();
				next_frame = glfwGetTime() + frame_delay / 2;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		cleanUp();
	}

	public static void initDisplay() {
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		window = glfwCreateWindow(window_width, window_height, "game", 0, 0);
		
		glfwDefaultWindowHints(); 
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); 
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); 
		glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glfwSwapInterval(1);

		glfwShowWindow(window);
	}

	public static void initGL() {		
		GLUtil.setupDebugMessageCallback();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, window_width, 0, window_height, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		
		glDisable(GL_DEPTH_TEST);
		
		glClearColor(0f, 0f, 1f, 0);
	}
	
	public static void initGame() {
		game = new Game(window, window_width, window_height);
		frame_delay = 1f / FPS_CAP;		
	}
	
	private static void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		game.render();
		glfwSwapBuffers(window);
	}
	
	private static void update() {
		glfwPollEvents();
		game.update();
		
	}
	
	private static void getInput() {
		game.getInput();
	}
}
