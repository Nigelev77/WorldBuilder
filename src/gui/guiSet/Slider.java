package gui.guiSet;

import org.joml.Vector2f;
import org.lwjgl.nanovg.NanoVG;

import gui.ColorManager;
import gui.GUI;
import gui.GUIItem;
import gui.LinearGradient;
import gui.RadialGradient;
import io.MouseController;

public class Slider extends GUI implements GUIItem{
	
	private Circle knob;
	private Rect slider;
	
	private float value;
	
	private LinearGradient gradient;
	private RadialGradient shadow;
	
	public Slider(float x, float y, float w, float h) {
		slider = new Rect(x, y, w, h);
		knob = new Circle(x, y+5, 10);
	}
	
	public void changeValue(float x) {
		value = (value+x) % 100;
		knob.setCenter(slider.getX()+slider.getWidth()*(value/100f), knob.getY());
	}

	public void changeKnobPosition(float x) {
		float startbounds = slider.getX();
		float endbounds = slider.getWidth()+slider.getX();
		if(x>=startbounds && x<=endbounds) {
			knob.setCenter(x, knob.getY());
			value = (knob.getX()-slider.getX())/slider.getWidth();
		}

	}
	
	@Override
	public void setPosition(float x, float y) {
		slider.setPosition(x, y);
		knob.changePos(x+knob.getX(), y);
	}

	@Override
	public void render(long ccx) {
		NanoVG.nvgBeginPath(ccx);
		slider.render(ccx);
		NanoVG.nvgFillColor(ccx, ColorManager.assignColors(100, 100, 100, 150, ColorManager.color1));
		NanoVG.nvgFill(ccx);
		NanoVG.nvgClosePath(ccx);
		NanoVG.nvgBeginPath(ccx);
		knob.render(ccx);
		NanoVG.nvgFillColor(ccx, ColorManager.assignColors(70, 10, 200, 255, ColorManager.color1));
		NanoVG.nvgFill(ccx);
		NanoVG.nvgClosePath(ccx);
		NanoVG.nvgBeginPath(ccx);
		NanoVG.nvgTextBox(ccx, slider.getX()+slider.getHeight()+20f, slider.getY(), 100, Float.toString(value));
		NanoVG.nvgClosePath(ccx);
	}
	
	public float getKnobX() {
		return knob.getX();
	}
	
	public float getKnobY() {
		return knob.getY();
	}
	
	public float getknobRadius() {
		return 10f;
	}
	public GUIItem getKnob() {
		return (GUIItem) knob;
	}

	@Override
	public void interact() {
		Vector2f mousePosition = MouseController.queryMousePos();
		changeKnobPosition(mousePosition.x);
		
	}



	@Override
	public boolean checkBoundaries(float x, float y) {
		float knobX = knob.getX();
		float knobY = knob.getY();
		float r = getknobRadius();
		if(x>=knobX-r && x<=knobX+r && y>=knobY- r && y<=knobY+r) {
			return true;
		}
		return false;
	}
}
