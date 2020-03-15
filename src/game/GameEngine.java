package game;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import display.WindowManager;
import engine.RenderEngine;
import glRenderingObjects.ObjectHandler;
import player.Camera;
import selectors.MouseSelector;
import terrain.TerrainRenderer;
import utils.Maths;

public class GameEngine implements Runnable{
	
	//With inspiration from Kai Burjack from repo https://github.com/LWJGL/lwjgl3-demos/blob/master/src/org/lwjgl/demo/opengl/glfw/Multithreaded.java for the synchronized object idea!
	
	private final Thread gameLoopThread;
	
	public static WindowManager windows;
	
	public static boolean isRunning = true;
	
	public static Object glfwLock = new Object();
	
	public static volatile Camera camera;
	
	public static MouseSelector selector = new MouseSelector();
	
	public GameEngine() {
		
		windows = new WindowManager();
		gameLoopThread = new Thread(this, "GAME");
	}
	
	public void start() {
		init();
	}
	
	private void init() {
		windows.createWindow();
		camera = new Camera();
		startGameLoop();
		while(!GLFW.glfwWindowShouldClose(windows.getWindowNum())) {
			synchronized(camera) {
				if(!camera.moved) {
					camera.move();
				}
				selector.update(Maths.setViewMatrix(camera));
			}

			GLFW.glfwWaitEvents();
		}
		synchronized(GameEngine.glfwLock) {
			isRunning = false;
			windows.destroy();
		}
	}
	private void startGameLoop() {
		gameLoopThread.start();
	}

	
	
	@Override
	public void run() {

		gameLoop();
		
	}
	
	private void gameLoop() {
		setup();
		while(GameEngine.isRunning) {
			
			windows.update();
		}
		cleanUp();
	}
	
	private void setup() {
		windows.createContext();
		RenderEngine.init();
		RenderEngine.loadStaticModel("cube");
		RenderEngine.loadStaticModel("person");
		TerrainRenderer.addTerrain(0, 0);
		RenderEngine.addStaticEntity(new Vector3f(100f,0f,-150f), new Vector3f().zero(), 1, "cube");
		RenderEngine.addStaticEntity(new Vector3f(200f, 0f, -250f), new Vector3f().zero(), 2, "cube");
		RenderEngine.addStaticEntity(new Vector3f(300f,0f,-350f), new Vector3f().zero(), 3, "cube");
		RenderEngine.addLight(new Vector3f(250,250,-250));
	}
	
	private void cleanUp() {
		RenderEngine.cleanUp();
	}

}
