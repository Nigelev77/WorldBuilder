package gui.guiSet;

import org.lwjgl.nanovg.NanoVG;

import gui.ColorManager;
import gui.GUIItem;
import gui.LinearGradient;

public class Rect implements GUIItem {

	private float x, y, w, h;
	
	public Rect(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	

	@Override
	public void render(long ccx) {
		NanoVG.nvgRoundedRect(ccx, x, y, w, h, cornerRadius);
	}

	@Override
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getWidth() {
		return w;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getHeight() {
		return h;
	}
	
}
