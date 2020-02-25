package engine;

public class RenderEngine {
	
	private static MasterRenderer renderer;
	private static MasterLoader loader;
	
	public static void init() {
		renderer = new MasterRenderer();
	}
	
	public static void render() {
		renderer.render();
	}
	
}
