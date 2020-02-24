package engine;

public class RenderEngine {
	
	private static MasterRenderer renderer;
	
	public static void init() {
		renderer = new MasterRenderer();
	}
	
	public static void render() {
		renderer.render();
	}
	
}
