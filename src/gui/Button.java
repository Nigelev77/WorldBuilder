package gui;

import org.lwjgl.nanovg.NanoVG;

import gui.guiSet.Rect;

public class Button extends GUI implements GUIComponent{
	
	private Rect box;
	private String text;
	private boolean pressed = false;
	
	
	public Button(float x, float y, float w, float h, String text) {
		box = new Rect(x, y, w, h);
		this.text = text;
	}
	

	@Override
	public void render(long ctx) {
		if(!pressed) {
			NanoVG.nvgBeginPath(ctx);
			box.render(ctx);
			NanoVG.nvgFillColor(ctx, ColorManager.assignColors(0, 0, 0, 255, ColorManager.color1));
			NanoVG.nvgFill(ctx);
			NanoVG.nvgClosePath(ctx);
			NanoVG.nvgBeginPath(ctx);
			NanoVG.nvgFillColor(ctx, ColorManager.assignColors(255, 255, 255, 255, ColorManager.color1));
			NanoVG.nvgTextBox(ctx, box.getX()+box.getWidth()/10f, box.getY()+box.getHeight()/10f, box.getWidth()*0.8f, text);
			NanoVG.nvgFill(ctx);
			NanoVG.nvgClosePath(ctx);
		}else {
			NanoVG.nvgBeginPath(ctx);
			box.render(ctx);
			NanoVG.nvgFillColor(ctx, ColorManager.assignColors(255, 255, 255, 255, ColorManager.color1));
			NanoVG.nvgFill(ctx);
			NanoVG.nvgClosePath(ctx);
			NanoVG.nvgBeginPath(ctx);
			
			NanoVG.nvgFillColor(ctx, ColorManager.assignColors(0, 0, 0, 255, ColorManager.color1));
			NanoVG.nvgTextBox(ctx, box.getX()+box.getWidth()/10f, box.getY()+box.getHeight()/10f, box.getWidth()*0.8f, text);
			NanoVG.nvgFill(ctx);
			NanoVG.nvgClosePath(ctx);
		}
		
	}

	@Override
	public void interact() {
		pressed=!pressed;
		
	}

	@Override
	public boolean checkBoundaries(float x, float y) {
		float bx = box.getX();
		float by = box.getY();
		float w = box.getWidth();
		float h = box.getHeight();
		if(x>=bx && x<=bx+w && y>=by && y<by+h) {
			return true;
		}
		return false;
	}
	

}
