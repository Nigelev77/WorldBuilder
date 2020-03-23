package gui;

import org.lwjgl.nanovg.NVGColor;

public class ColorManager {
	
	public static NVGColor color1 = NVGColor.create();
	public static NVGColor color2 = NVGColor.create();
	
	public static NVGColor assignColors(int r, int g, int b, int a, NVGColor color) {
		color.r(r/255.0f);
		color.g(g/255.0f);
		color.b(b/255.0f);
		color.a(a/255.0f);
		return color;
	}
	
	
}
