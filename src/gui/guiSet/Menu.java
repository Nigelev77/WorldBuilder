package gui.guiSet;

import org.lwjgl.nanovg.NanoVG;

import gui.ColorManager;
import gui.GUI;
import gui.LinearGradient;

public class Menu implements GUI{
	
	private float x, y, w, h;
	private int r,g,b,a;
	private LinearGradient gradient;
	
	public Menu(float x, float y, float w, float h, int r, int g, int b, int a) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	

	@Override
	public void render(long ccx) {
		NanoVG.nvgBeginPath(ccx);
		NanoVG.nvgRoundedRect(ccx, x, y, w, h, cornerRadius);
		NanoVG.nvgFillColor(ccx, ColorManager.assignColors(r, g, b, a, ColorManager.color1));
		NanoVG.nvgFill(ccx);
	}

	@Override
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void setColor(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}


	
}
