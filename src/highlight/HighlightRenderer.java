package highlight;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import engine.RenderEngine;
import entities.StaticEntity;
import shaders.StaticModelShader;
import utils.Maths;

public class HighlightRenderer {
	
	private StaticModelShader normalShader;
	private HighlightShader shader;
	
	private Matrix4f projection;
	
	public HighlightRenderer(Matrix4f projection) {
		this.projection = projection;
		shader = new HighlightShader();
		normalShader = new StaticModelShader();
		shader.Start();
		shader.Stop();
		normalShader.Start();
		normalShader.projectionMatrix.loadValue(projection, normalShader);
		normalShader.Stop();
	}
	
	public void render(Matrix4f view, Vector3f lightPos) {
		firstRender(view, lightPos);
		secondRender(view);
	}
	
	public void firstRender(Matrix4f view, Vector3f lightPos) {
		prepareFirst(lightPos);
		for(StaticEntity entity: RenderEngine.getHighlighted()) {
			prepareEntity(entity, view);
			GL30.glDrawElements(GL11.GL_TRIANGLES, entity.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		}
		endFirstRendering();
	}
	
	
	/**
	 * Renders the outside highlighted border
	 * @param view
	 * -Takes in the view matrix in order to draw the highlighted box around the object which was rendered in {@linkplain}firstRender
	 */
	private void secondRender(Matrix4f view) {
		float scale = 1.2f;
		prepareSecond();
		for(StaticEntity entity: RenderEngine.getHighlighted()) {
			prepareHighlight(entity, view, scale);
			GL30.glDrawElements(GL30.GL_TRIANGLES, entity.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		}
		endSecondRendering();
	}
	
	private void endFirstRendering() {
		GL30.glBindVertexArray(0);
		shader.Stop();
	}
	
	private void endSecondRendering() {
		GL30.glBindVertexArray(0);
		GL30.glStencilMask(0xFF);
		GL30.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
		GL30.glEnable(GL11.GL_DEPTH_TEST);
	}
	
	private void prepareFirst(Vector3f lightPos) {
		normalShader.Start();
		GL30.glEnable(GL11.GL_STENCIL_TEST);
		GL30.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
		normalShader.lightPos.loadValue(lightPos, normalShader);
		prepareStencil();
	}
	
	private void prepareSecond() {
		shader.Start();
		GL11.glStencilFunc(GL11.GL_NOTEQUAL, 1, 0xFF);
		GL11.glStencilMask(0x00);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}
	
	private void prepareStencil() {
		GL30.glEnable(GL11.GL_STENCIL_TEST);
		GL30.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
		GL30.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
	}
	
	private void prepareHighlight(StaticEntity entity, Matrix4f view, float scale) {
		GL30.glBindVertexArray(entity.getVaoID());
		Matrix4f translation = Maths.createTransformMatrix(entity.getPosition(), entity.getRotation().x, entity.getRotation().y,
				entity.getRotation().z, entity.getScale()*scale);
		translation.mul(view);
		translation.mul(projection);
		shader.mvp.loadValue(translation, shader);
	}
	
	private void prepareEntity(StaticEntity entity, Matrix4f view) {
		GL30.glBindVertexArray(entity.getVaoID());
		normalShader.viewMatrix.loadValue(view, normalShader);
		normalShader.transform.loadValue(Maths.createTransformMatrix(entity.getPosition(), entity.getRotation().x, entity.getRotation().y,
				entity.getRotation().z, entity.getScale()), normalShader);
		
	}
	
}
