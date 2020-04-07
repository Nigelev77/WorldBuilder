package gui.guiSet;

import org.joml.Vector2f;
import org.lwjgl.nanovg.NanoVG;

import gui.ColorManager;
import gui.GUI;
import gui.GUIItem;
import gui.LinearGradient;
import io.MouseController;

public class Menu extends GUI implements GUIItem{
	
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
		NanoVG.nvgClosePath(ccx);
	}

	@Override
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}


	@Override
	public void interact() {
		Vector2f mousePos = MouseController.queryMousePos();
		this.x = mousePos.x;
		this.y = mousePos.y;
		
	}


	@Override
	public boolean checkBoundaries(float x, float y) {
		if(x>=this.x && x<=this.x+w && y>=this.y && y<=this.y+h) {
			return true;
		}
		return false;
	}



	
}
