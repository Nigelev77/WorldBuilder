package engine;

import game.GameEngine;
import glRenderingObjects.Vao;
import loaders.MasterLoader;
import models.StaticModel;
import player.Camera;

public class RenderEngine {
	
	private static MasterRenderer renderer;
	private static MasterLoader loader;

	
	public static void init() {
		renderer = new MasterRenderer();
		loader = new MasterLoader();
		shaders.TypeLoader.init();
	}
	
	public static void render() {
		synchronized(GameEngine.camera) {
			renderer.render(GameEngine.camera);
		}
	}
	
	public static void loadStaticModel(String fileName) {
		StaticModel model = loader.renderStaticModel("res/"+fileName+".obj");
		renderer.loadStaticModel(model);
		System.out.println("Successful");
		
		Vao vao = new Vao();
		float[] vertices = {
				-0.5f, 0.5f, 0f,//v0
				-0.5f, -0.5f, 0f,//v1
				0.5f, -0.5f, 0f,//v2
				0.5f, 0.5f, 0f,//v3
		};
		int[] indices = {
				0,1,3,//top left triangle (v0, v1, v3)
				3,1,2//bottom right triangle (v3, v1, v2)
		};
		int[] dimensions = {3};
		vao.storeData(indices, dimensions, vertices);
		StaticModel rect = new StaticModel(vao);
		renderer.loadStaticModel(rect);
		
	}
	
}
