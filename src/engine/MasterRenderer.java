package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import entities.StaticEntity;
import game.GameEngine;
import glRenderingObjects.ObjectHandler;
import highlight.HighlightRenderer;
import models.StaticModel;
import player.Camera;
import shaders.StaticModelShader;
import terrain.TerrainRenderer;
import utils.Maths;

public class MasterRenderer {
	
	private List<StaticModel> statics = new ArrayList<StaticModel>();
	private Map<String, StaticModel> modelNames = new HashMap<String, StaticModel>();
	private StaticModelShader shader;
	private Matrix4f projectionMatrix = new Matrix4f();

	
	private TerrainRenderer terrainRenderer;
	private HighlightRenderer highlightRenderer;
	
	public MasterRenderer() {
		Maths.setProjectionMatrix(projectionMatrix);
		GameEngine.selector.setProjection(projectionMatrix);
		shader = new StaticModelShader();
		shader.Start();
		shader.Stop();
		terrainRenderer = new TerrainRenderer(projectionMatrix);
		highlightRenderer = new HighlightRenderer(projectionMatrix);
	}
	
	public void render(Camera camera) {
		Matrix4f viewMatrix = Maths.setViewMatrix(camera);
		renderStatics(viewMatrix);
		terrainRenderer.render(viewMatrix);
		highlightRenderer.render(viewMatrix, RenderEngine.getLight().getPosition());
	}
	
	
	
	public void renderStatics(Matrix4f viewMatrix) {
		shader.Start();
		prepare(viewMatrix);
		for(StaticEntity statics: RenderEngine.getEntities()) {
			prepareStatic(statics);
			GL15.glDrawElements(GL15.GL_TRIANGLES, statics.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			finish();
		}
		endRendering();
	}
	
	private void endRendering() {
		shader.Stop();
	}
	
	private void finish() {
		GL30.glBindVertexArray(0);
	}
	
	private void prepare(Matrix4f viewMatrix) {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT|GL11.GL_STENCIL_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1);
		shader.projectionMatrix.loadValue(projectionMatrix, shader);
		shader.viewMatrix.loadValue(viewMatrix, shader);
		shader.lightPos.loadValue(RenderEngine.getLight().getPosition(), shader);
		enableCulling();
	}
	
	private void enableCulling() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	
	private void prepareStatic(StaticEntity entity) {
		GL30.glBindVertexArray(entity.getVaoID());
		shader.transform.loadValue(entity.getTransformMatrix(), shader);
	}
	
	
	public void loadStaticModel(StaticModel staticModel) {
		statics.add(staticModel);
		modelNames.put(staticModel.getName(), staticModel);
	}
	
	public void cleanUp() {
		shader.cleanUp();
		ObjectHandler.cleanUp();
	}
	
	public StaticModel getStaticModel(String name) {
		return modelNames.get(name);
	}
	
}
