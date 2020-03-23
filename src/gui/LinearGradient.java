package gui;

import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;

public class LinearGradient {
	
	private int sr = 0;
	private int sg = 0;
	private int sb = 0;
	private int sa = 0;
	private int er = 0;
	private int eg = 0;
	private int eb = 0;
	private int ea = 0;
	private NVGColor startColor;
	private NVGColor endColor;
	private NVGPaint paint;
	
	public LinearGradient() {
		startColor = NVGColor.create();
		endColor = NVGColor.create();
		paint = NVGPaint.create();
	}
	
	
	public void changeStartColor(int r, int g, int b, int a) {
		sr = r;
		sg = g;
		sb = b;
		sa = a;
		ColorManager.assignColors(sr, sg, sb, sa, startColor);
	}
	
	public void changeEndColor(int r, int g, int b, int a) {
		er = r;
		eg = g;
		eb = b;
		ea = a;
		ColorManager.assignColors(er, eg, eb, ea, endColor);
	}
	
	public void setGradient(long ctx, float sx, float sy, float ex, float ey) {
		NanoVG.nvgLinearGradient(ctx, sx, sy, ex, ey, startColor, endColor, paint);
		NanoVG.nvgFillPaint(ctx, paint);
		NanoVG.nvgFill(ctx);
	}
	
}
