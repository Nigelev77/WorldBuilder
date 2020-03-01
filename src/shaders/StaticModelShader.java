package shaders;

import org.joml.Matrix4f;

public class StaticModelShader extends Shader{
	
	private static final String VERTEX_FILE = "shaders/staticVertex";
	private static final String FRAGMENT_FILE = "shaders/staticFragment";
	
	public Uniform<Matrix4f> projectionMatrix;
	public Uniform<Matrix4f> viewMatrix;
	
	
	
	public StaticModelShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}



	@Override
	protected void getAllUniformLocations() {
		projectionMatrix = new Uniform<Matrix4f>("projectMatrix", this);
		viewMatrix = new Uniform<Matrix4f>("viewMatrix", this);
	}



	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "vertex_position");
		
		
	}




}
