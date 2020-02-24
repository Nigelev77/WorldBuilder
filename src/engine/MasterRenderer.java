package engine;

import org.lwjgl.opengl.GL11;

public class MasterRenderer {
	
	public void render() {
		prepare();
	}
	
	private void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(1, 1, 0, 1);
	}
}
