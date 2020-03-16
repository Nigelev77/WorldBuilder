package highlight;

import org.joml.Matrix4f;

import shaders.Shader;
import shaders.Uniform;

public class HighlightShader extends Shader{
	
	private static final String vFile = "shaders/HighlightV";
	private static final String fFile = "shaders/HighlightF";
	
	protected Uniform<Matrix4f> mvp;
	
	
	public HighlightShader() {
		super(vFile, fFile);

	}
	@Override
	protected void getAllUniformLocations() {
		mvp = new Uniform<Matrix4f>("mvp", this);
		
	}
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "vertex_position");
		super.bindAttribute(2, "vertex_normal");
	}
	
}
