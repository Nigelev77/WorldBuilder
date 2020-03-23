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
	
	private static final float scale = 1.1f;
	
	private Matrix4f projection;
	
	public HighlightRenderer(Matrix4f projection) {
		this.projection = projection;
		shader = new HighlightShader();
		normalShader = new StaticModelShader();
		shader.Start();
		shader.Stop();
		normalShader.Start();

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
		prepareSecond();
		for(StaticEntity entity: RenderEngine.getHighlighted()) {
			prepareHighlight(entity, view, scale);
			GL30.glDrawElements(GL30.GL_TRIANGLES, entity.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		}
		endSecondRendering();
	}
	
	private void endFirstRendering() {
		GL30.glBindVertexArray(0);
		normalShader.Stop();
	}
	
	private void endSecondRendering() {
		GL30.glBindVertexArray(0);
		GL30.glStencilMask(0xFF);
		GL30.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
		GL30.glEnable(GL11.GL_DEPTH_TEST);
		shader.Stop();
	}
	
	private void prepareFirst(Vector3f lightPos) {
		normalShader.Start();
		GL30.glEnable(GL11.GL_DEPTH_TEST);
		normalShader.projectionMatrix.loadValue(projection, normalShader);
		normalShader.lightPos.loadValue(lightPos, normalShader);
		prepareStencil();
	}
	
	private void prepareSecond() {
		shader.Start();
		GL11.glStencilFunc(GL11.GL_NOTEQUAL, 1, 0xFF);
		GL11.glStencilMask(0x00);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	private void prepareStencil() {
		GL30.glEnable(GL11.GL_STENCIL_TEST);
		GL30.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
		GL30.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
	}
	
	private void prepareHighlight(StaticEntity entity, Matrix4f view, float scale) {
		GL30.glBindVertexArray(entity.getVaoID());
//		Matrix4f translation = Maths.createTransformMatrix(entity.getPosition(), entity.getRotation().x, entity.getRotation().y,
//				entity.getRotation().z, entity.getScale()*scale);
//		translation.mul(view);
//		translation.mul(projection);
//		
		Matrix4f newMvp = new Matrix4f().identity();
		newMvp.mul(projection);
		newMvp.mul(view);
		newMvp.mul(Maths.createTransformMatrix(entity.getPosition(), entity.getRotation().x, entity.getRotation().y,
				entity.getRotation().z, entity.getScale()*scale));
		shader.mvp.loadValue(newMvp, shader);
	}
	
	private void prepareEntity(StaticEntity entity, Matrix4f view) {
		GL30.glBindVertexArray(entity.getVaoID());
		normalShader.transform.loadValue(entity.getTransformMatrix(), normalShader);
		normalShader.viewMatrix.loadValue(view, normalShader);
		
	}
	
}
