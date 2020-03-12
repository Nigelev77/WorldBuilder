package engine;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

import entities.Light;
import entities.StaticEntity;
import game.GameEngine;
import glRenderingObjects.Vao;
import loaders.MasterLoader;
import models.StaticModel;

public class RenderEngine {
	
	private static MasterRenderer renderer;
	private static MasterLoader loader;
	
	private static List<StaticEntity> staticEntities = new ArrayList<StaticEntity>();
	
	private static Light light = new Light(new Vector3f().zero(), new Vector3f().zero(), 1, null);
	
	public static void init() {
		renderer = new MasterRenderer();
		loader = new MasterLoader();
		shaders.TypeLoader.init();
	}
	
	public static void render() {
		synchronized(GameEngine.camera) {
			
			renderer.render(GameEngine.camera);
			GameEngine.camera.moved=false;
		}
	}
	
	public static void loadStaticModel(String fileName) {
		StaticModel model = loader.renderStaticModel(fileName);
		renderer.loadStaticModel(model);
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
		StaticModel rect = new StaticModel(vao, "rect");
		renderer.loadStaticModel(rect);
		
		
	}
	
	public static void cleanUp() {
		renderer.cleanUp();
	}

	public static void addStaticEntity(Vector3f position, Vector3f rotation, float scale, String model) {
		StaticModel staticModel = renderer.getStaticModel(model);
		
		staticEntities.add(new StaticEntity(position, rotation, scale, staticModel));
	}
	
	public static List<StaticEntity> getEntities(){
		return staticEntities;
	}
	
	public static void addLight(Vector3f position) {
		light.setPosition(position);
	}
	
	public static Light getLight() {
		return RenderEngine.light;
	}
	
}
