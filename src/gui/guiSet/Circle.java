package gui.guiSet;

import org.lwjgl.nanovg.NanoVG;

import gui.CircularGUI;
import gui.ColorManager;
import gui.PaintManager;
import gui.RadialGradient;

public class Circle implements CircularGUI{
	
	
	private float cx;
	private float cy;
	private float radius;
	private RadialGradient gradient;
	
	public Circle(float cx, float cy, float radius) {
		this.cx = cx;
		this.cy = cy;
		this.radius = radius;
		gradient = new RadialGradient();
	}
	
	@Override
	public void setCenter(float x, float y) {
		cx = x;
		cy = y;
		
	}

	@Override
	public void render(long ccx) {
		NanoVG.nvgBeginPath(ccx);
		NanoVG.nvgArc(ccx, cx, cy, radius, (float)Math.toRadians(0f), (float)Math.toRadians(360f), NanoVG.NVG_CCW);
		gradient.setGradient(ccx, cx, cy, 30, radius);
		
		NanoVG.nvgClosePath(ccx);	
		
	}

	@Override
	public void setColor(int r, int g, int b, int a) {
		gradient.changeStartColor(r, g, b, a);
		gradient.changeEndColor(r, g, b, a);
		
	}
	
	public void setStartColor(int r, int g, int b, int a) {
		gradient.changeStartColor(r, g, b, a);
	}
	
	public void setEndColor(int r, int g, int b, int a) {
		gradient.changeEndColor(r, r, b, a);
	}
}
