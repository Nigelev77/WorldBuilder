package engine;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import models.StaticModel;
import shaders.StaticModelShader;

public class MasterRenderer {
	
	private List<StaticModel> statics = new ArrayList<StaticModel>();
	private StaticModelShader shader;
	
	public MasterRenderer() {
		shader = new StaticModelShader();
	}
	
	public void render() {
		prepare();
		for(StaticModel model:statics) {
			prepareModel(model);
			GL15.glDrawElements(GL15.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			finish();
		}
		endRendering();
	}
	
	private void endRendering() {
		shader.stop();
	}
	
	private void finish() {
		GL30.glBindVertexArray(0);
		
	}
	
	private void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(1, 1, 0, 1);
	}
	
	private void prepareModel(StaticModel model) {
		shader.start();
		GL30.glBindVertexArray(model.getVaoId());
	}
	
	public void loadStaticModel(StaticModel staticModel) {
		statics.add(staticModel);
	}
}
