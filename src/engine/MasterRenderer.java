package engine;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import models.StaticModel;

public class MasterRenderer {
	
	private List<StaticModel> statics = new ArrayList<StaticModel>();
	
	public void render() {
		prepare();
	}
	
	private void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(1, 1, 0, 1);
	}
	
	public void loadStaticModel(StaticModel staticModel) {
		statics.add(staticModel);
	}
}
