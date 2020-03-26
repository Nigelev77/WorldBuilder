package gui.guiSet;

import org.lwjgl.nanovg.NanoVG;

import gui.ColorManager;
import gui.GUI;
import gui.LinearGradient;
import gui.RadialGradient;

public class Slider implements GUI{
	
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
		if(x>startbounds && x<endbounds) {
			knob.setCenter(x, knob.getY());
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
	public GUI getKnob() {
		return (GUI) knob;
	}
}
