package terrain;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL40;

import engine.RenderEngine;
import terrainManager.TerrainManager;

public class TerrainRenderer {
	
	private static List<Terrain> terrains = new ArrayList<Terrain>();
	
	private Matrix4f projectionMatrix;
	private TerrainShader shader;
	private static TerrainManager manager;
	
	
	public TerrainRenderer(Matrix4f projection) {
		this.projectionMatrix = projection;
		this.shader = new TerrainShader();
		shader.Start();
		shader.Stop();
		
	}
	
	public static void loadTerrainManager(TerrainManager terrain) {
		manager = terrain;
	}
	
	public void render(Matrix4f viewMatrix) {
		GL30.glPolygonMode(GL30.GL_FRONT_AND_BACK, GL30.GL_LINE);
		prepare(viewMatrix);
		
		/*
		for(Terrain terrain:terrains) {
			prepareTerrain(terrain);
			GL30.glDrawElements(GL11.GL_TRIANGLES, terrain.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		}
		*/
		manager.changeChunkHeight();
		manager.updateMesh();
		GL30.glBindVertexArray(manager.getVaoID());
		shader.transform.loadValue(manager.getTransform(), shader);
		shader.lightPos.loadValue(RenderEngine.getLight().getPosition(), shader);
		GL30.glDrawElements(GL11.GL_TRIANGLES, manager.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		endRendering();
		GL30.glPolygonMode(GL30.GL_FRONT_AND_BACK, GL30.GL_FILL);
	}
	
	private void endRendering() {
		GL30.glBindVertexArray(0);
		shader.Stop();
	}
	
	public static void addTerrain(int x, int z) {
		terrains.add(new Terrain(x, z));
	}
	
	private void prepare(Matrix4f viewMatrix) {
		shader.Start();
		shader.projection.loadValue(projectionMatrix, shader);
		shader.view.loadValue(viewMatrix, shader);
	}
	
	private void prepareTerrain(Terrain terrain) {
		GL30.glBindVertexArray(terrain.getVao());
		shader.transform.loadValue(terrain.getTransform(), shader);
		shader.lightPos.loadValue(RenderEngine.getLight().getPosition(), shader);
	}
}
