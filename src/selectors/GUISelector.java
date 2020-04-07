package selectors;

import gui.GUI;
import gui.GUIMasterRenderer;

public class GUISelector {
	
	private static GUI selectedGUI;
	
	public GUISelector() {
		
	}
	
	public static void checkGUI(float x, float y) {
		for(GUI gui:GUIMasterRenderer.guis) {
			if(gui.checkBoundaries(x, y)) {
				selectedGUI=gui;
			}
		}
	}
	
	public static void releaseGUI() {
		selectedGUI = null;
	}
	
	public static void update() {
		if(selectedGUI!=null) {
			selectedGUI.interact();
		}
	}
}
