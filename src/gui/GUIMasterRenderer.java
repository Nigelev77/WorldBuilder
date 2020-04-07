package gui;


import java.util.ArrayList;
import java.util.List;

import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NanoVGGL3;
import org.lwjgl.opengl.GL11;

import display.WindowManager;
import gui.guiSet.Circle;
import gui.guiSet.Menu;
import gui.guiSet.Slider;
import selectors.GUISelector;

public class GUIMasterRenderer {
	
	public static boolean showMenu = false;
	
	private static long NanoVGInstance;
	private static NVGColor color = NVGColor.create();
	private static NVGColor color2 = NVGColor.create();
	private static NVGPaint paint = NVGPaint.create();
	private static Circle circle;
	public static Slider slider;
	private static Menu menu;
	public static List<GUI> guis = new ArrayList<GUI>();
	public static int font;
	public static Button button;
	
	public static void init() {
		NanoVGInstance = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_DEBUG|NanoVGGL3.NVG_ANTIALIAS|NanoVGGL3.NVG_STENCIL_STROKES);
		NanoVG.nnvgTextAlign(NanoVGInstance, NanoVG.NVG_ALIGN_LEFT);
		circle = new Circle(700, 800, 50);
		circle.setStartColor(140, 100, 50, 240);
		circle.setEndColor(10, 50, 200, 50);
		slider = new Slider(800, 300, 250, 10);
		menu = new Menu(800, 250, 250, 250, 40, 69, 180, 190);
		guis.add(menu);
		guis.add(slider);
		font = NanoVG.nvgCreateFont(NanoVGInstance, "OpenSans", "res/OpenSans-Bold.ttf");
		button = new Button(700, 800, 50, 50, "Test button");
		guis.add(button);
	}
	
	public static void render() {

		if(showMenu) {
			GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
			NanoVG.nvgBeginFrame(NanoVGInstance, 1600, 900, WindowManager.aspectRatio);
			NanoVG.nvgSave(NanoVGInstance);
			menu.render(NanoVGInstance);
			slider.render(NanoVGInstance);
			button.render(NanoVGInstance);
			NanoVG.nvgBeginPath(NanoVGInstance);
			NanoVG.nvgFillColor(NanoVGInstance, ColorManager.assignColors(250, 90, 80, 250, color));
			NanoVG.nvgTextBox(NanoVGInstance, 100, 250, 100, "Hello, this is trying to create a text box and we'll see how well it'll work!");
			
			NanoVG.nvgFill(NanoVGInstance);
			NanoVG.nvgClosePath(NanoVGInstance);
			
			NanoVG.nvgRestore(NanoVGInstance);
			NanoVG.nvgEndFrame(NanoVGInstance);
			GUISelector.update();
		}


	}
	
	
}
