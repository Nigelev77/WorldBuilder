package shaders;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class StaticModelShader extends Shader{
	
	private static final String VERTEX_FILE = "shaders/staticVertex";
	private static final String FRAGMENT_FILE = "shaders/staticFragment";
	
	public Uniform<Matrix4f> projectionMatrix;
	public Uniform<Matrix4f> viewMatrix;
	public Uniform<Vector3f> lightPos;
	public Uniform<Matrix4f> transform;
	
	
	public StaticModelShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}



	@Override
	protected void getAllUniformLocations() {
		projectionMatrix = new Uniform<Matrix4f>("projectMatrix", this);
		viewMatrix = new Uniform<Matrix4f>("viewMatrix", this);
		lightPos = new Uniform<Vector3f>("lightPos", this);
		transform = new Uniform<Matrix4f>("transform", this);
	}



	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "vertex_position");
		super.bindAttribute(2, "vertex_normal");
		
	}




}
