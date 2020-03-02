package engine;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import display.WindowManager;
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
		createProjectionMatrix();
		shader.projectionMatrix.loadValue(projectionMatrix, shader);
		shader.viewMatrix.loadValue(viewMatrix, shader);
		//System.out.println(projectionMatrix);
	}
	
	private void prepareModel(StaticModel model) {
		GL30.glBindVertexArray(model.getVaoId());
	}
	
	public void loadStaticModel(StaticModel staticModel) {
		statics.add(staticModel);
	}
	
	private void createProjectionMatrix() {
		float y_scale = (float) ((1f/Math.tan(Math.toRadians(Maths.FOV/2f)))*WindowManager.aspectRatio);
		float x_scale = y_scale/WindowManager.aspectRatio;
		float frustum_length = Maths.FAR_PLANE - Maths.NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix._m00(x_scale);
		projectionMatrix._m11(y_scale);
		projectionMatrix._m22(-((Maths.FAR_PLANE+Maths.NEAR_PLANE)/frustum_length));
		projectionMatrix._m23(-1f);
		projectionMatrix._m32(-((2*Maths.NEAR_PLANE*Maths.FAR_PLANE)/frustum_length));
		projectionMatrix._m33(0f);
	}
}
