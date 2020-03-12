package terrain;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import shaders.Shader;
import shaders.Uniform;

public class TerrainShader extends Shader{
	
	private static final String vertex = "shaders/TerrainV";
	private static final String fragment = "shaders/TerrainF";
	
	protected Uniform<Matrix4f> projection;
	protected Uniform<Matrix4f> view;
	protected Uniform<Matrix4f> transform;
	protected Uniform<Vector3f> lightPos;
	
	
	public TerrainShader() {
		super(vertex, fragment);
	}


	@Override
	protected void getAllUniformLocations() {
		projection = new Uniform<Matrix4f>("projection", this);
		view = new Uniform<Matrix4f>("view", this);
		transform = new Uniform<Matrix4f>("transform", this);
		lightPos = new Uniform<Vector3f>("lightPos", this);
	}


	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		
	}

}
