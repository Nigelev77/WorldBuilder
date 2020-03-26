package gui;


import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NanoVGGL3;

import display.WindowManager;
import gui.guiSet.Circle;
import gui.guiSet.Slider;

public class GUIMasterRenderer {
	
	public static boolean showMenu = false;
	
	private static long NanoVGInstance;
	private static NVGColor color = NVGColor.create();
	private static NVGColor color2 = NVGColor.create();
	private static NVGPaint paint = NVGPaint.create();
	private static Circle circle;
	public static Slider slider;
	
	public static void init() {
		NanoVGInstance = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_DEBUG|NanoVGGL3.NVG_ANTIALIAS|NanoVGGL3.NVG_STENCIL_STROKES);
		//NanoVGGL3.nvgluCreateFramebuffer(NanoVGInstance, WindowManager.WIDTH, WindowManager.HEIGHT, NanoVG.NVG_IMAGE_NEAREST);
		//NanoVGGL3.nvgluBindFramebuffer(NanoVGInstance, NVGLUFramebuffer.create(NanoVGInstance));
		NanoVG.nnvgTextAlign(NanoVGInstance, NanoVG.NVG_ALIGN_LEFT);
		circle = new Circle(700, 800, 50);
		circle.setStartColor(140, 100, 50, 240);
		circle.setEndColor(10, 50, 200, 50);
		slider = new Slider(800, 300, 250, 10);
	}
	
	public static void render() {

		if(showMenu) {
			NanoVG.nvgBeginFrame(NanoVGInstance, 1600, 900, WindowManager.aspectRatio);
			NanoVG.nvgSave(NanoVGInstance);
			drawMenu();
			drawSlider();
			slider.render(NanoVGInstance);
			NanoVG.nvgRestore(NanoVGInstance);
			NanoVG.nvgEndFrame(NanoVGInstance);

		}


	}
	
	private static NVGColor assignColors(int r, int g, int b, int a, NVGColor color) {
		color.r(r/255.0f);
		color.g(g/255.0f);
		color.b(b/255.0f);
		color.a(a/255.0f);
		return color;
	}
	
	private static void drawMenu() {
		NanoVG.nvgBeginPath(NanoVGInstance);
		NanoVG.nvgRoundedRect(NanoVGInstance, 50f, 50f, (float) WindowManager.WIDTH/2, (float) WindowManager.HEIGHT/2, 20f);
		NanoVG.nvgFillColor(NanoVGInstance, assignColors(50, 150, 250, 240, NVGColor.create()));
		NanoVG.nvgFill(NanoVGInstance);
	}
	
	private static void drawCirle() {
		NanoVG.nvgBeginPath(NanoVGInstance);
		NanoVG.nvgArc(NanoVGInstance, 800f, 450f, 200f, (float)Math.toRadians(0f), (float)Math.toRadians(360f), NanoVG.NVG_CCW);
		NanoVG.nvgRadialGradient(NanoVGInstance, 800f, 450f, 10f, 200f, assignColors(150, 50, 25, 230, NVGColor.create()), assignColors(100, 255, 0, 230, NVGColor.create()), paint);
		NanoVG.nvgFillPaint(NanoVGInstance, paint);
		NanoVG.nvgFill(NanoVGInstance);
		NanoVG.nvgClosePath(NanoVGInstance);	
	}
	
	private static void drawSlider() {
		
		NVGPaint knob = NVGPaint.create();
		NVGPaint bg = NVGPaint.create();
		NVGPaint shadow = NVGPaint.create();
		
		
		//draws the slider
		NanoVG.nvgBeginPath(NanoVGInstance);
		NanoVG.nvgBoxGradient(NanoVGInstance, 350, 350, 100, 2, 2, 2, assignColors(0, 0, 0, 32, NVGColor.create()), assignColors(0, 0, 0, 128, NVGColor.create()), bg);
		NanoVG.nvgRoundedRect(NanoVGInstance, 350, 350, 100, 2, 2);
		NanoVG.nvgFillPaint(NanoVGInstance, bg);
		NanoVG.nvgFill(NanoVGInstance);
		
		//draw shadow
		NanoVG.nvgRadialGradient(NanoVGInstance, 350, 351, 12-3, 12+5, assignColors(0, 0, 0, 128, color), assignColors(0,0,0,0,color2), shadow);
		NanoVG.nvgBeginPath(NanoVGInstance);
		NanoVG.nvgRect(NanoVGInstance, 350-12-5, 350-12-5, 12*2+10, 12*2+13);
		NanoVG.nvgCircle(NanoVGInstance, 350, 350, 12);
		NanoVG.nvgPathWinding(NanoVGInstance, NanoVG.NVG_HOLE);
		NanoVG.nvgFillPaint(NanoVGInstance, shadow);
		NanoVG.nvgFill(NanoVGInstance);
		
		
		//draws knob
		NanoVG.nvgLinearGradient(NanoVGInstance, 350, 350-12, 350, 350+12, assignColors(255, 255, 255, 16, color), assignColors(0, 0, 0, 16, color2), knob);
		NanoVG.nvgBeginPath(NanoVGInstance);
		NanoVG.nvgCircle(NanoVGInstance, 350, 350, 12-1);
		NanoVG.nvgFillColor(NanoVGInstance, assignColors(40, 43, 48, 255, NVGColor.create()));
		NanoVG.nvgFill(NanoVGInstance);
		NanoVG.nvgFillPaint(NanoVGInstance, knob);
		NanoVG.nvgFill(NanoVGInstance);
		NanoVG.nvgBeginPath(NanoVGInstance);
		NanoVG.nvgCircle(NanoVGInstance, 350, 350, 11.5f);
		NanoVG.nvgStrokeColor(NanoVGInstance, assignColors(0, 0, 0, 92, NVGColor.create()));
		NanoVG.nvgStroke(NanoVGInstance);
		
	
	}
	
}
