package shaders;

public class StaticModelShader extends Shader{
	
	private static final String VERTEX_FILE = "shaders/staticVertex";
	private static final String FRAGMENT_FILE = "shaders/staticFragment";
	
	
	
	public StaticModelShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}



	@Override
	protected void getAllUniformLocations() {
		
		
	}



	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "vertex_position");
		
		
	}




}
