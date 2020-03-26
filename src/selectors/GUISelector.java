package selectors;

import org.joml.Vector2f;

import gui.GUI;
import gui.GUIMasterRenderer;
import gui.guiSet.Slider;
import io.MouseController;

public class GUISelector {
	
	private static GUI selectedGUI;
	
	public GUISelector() {
		
	}
	
	public static void checkGUI(float x, float y) {
		float radius = GUIMasterRenderer.slider.getknobRadius();
		float knobX = GUIMasterRenderer.slider.getKnobX();
		float knobY = GUIMasterRenderer.slider.getKnobY();
		if(x>=knobX-radius && x<=knobX+radius && y>=knobY-radius && y<=knobY+radius) {
			selectedGUI = GUIMasterRenderer.slider;
		}
	}
	
	public static void releaseGUI() {
		selectedGUI = null;
	}
	
	public static void update() {
		if(selectedGUI!=null) {
			Vector2f mousePosition = MouseController.queryMousePos();
			((Slider) selectedGUI).changeKnobPosition(mousePosition.x);
		}
	}
}
