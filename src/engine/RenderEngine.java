package engine;

import loaders.MasterLoader;
import models.StaticModel;

public class RenderEngine {
	
	private static MasterRenderer renderer;
	private static MasterLoader loader;
	
	public static void init() {
		renderer = new MasterRenderer();
	}
	
	public static void render() {
		
		renderer.render();
	}
	
	public static void loadStaticModel(String fileName) {
		StaticModel model = loader.renderStaticModel("res/"+fileName+".obj");
	}
	
}
