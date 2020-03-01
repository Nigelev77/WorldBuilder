package engine;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import models.StaticModel;
import player.Camera;
import shaders.StaticModelShader;
import utils.Maths;

public class MasterRenderer {
	
	private List<StaticModel> statics = new ArrayList<StaticModel>();
	private StaticModelShader shader;
	private Matrix4f projectionMatrix = new Matrix4f();
	private Matrix4f viewMatrix = new Matrix4f();
	
	
	public MasterRenderer() {
		shader = new StaticModelShader();
		shader.Start();
		shader.Stop();
	}
	
	public void render(Camera camera) {
		shader.Start();
		prepare(camera);
		for(StaticModel model:statics) {
			prepareModel(model);
			GL15.glDrawElements(GL15.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
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
	
	private void prepare(Camera camera) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1);
		Maths.setProjectionAndView(viewMatrix, projectionMatrix, camera);
		shader.projectionMatrix.loadValue(projectionMatrix, shader);
		shader.viewMatrix.loadValue(viewMatrix, shader);
		
	}
	
	private void prepareModel(StaticModel model) {
		GL30.glBindVertexArray(model.getVaoId());
	}
	
	public void loadStaticModel(StaticModel staticModel) {
		statics.add(staticModel);
	}
}
