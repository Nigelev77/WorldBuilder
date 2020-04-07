package gui;

public abstract class GUI {
	
	protected boolean inFocus = false;
	protected boolean mouseOver = false;
	
	
	public abstract void interact();
	public abstract boolean checkBoundaries(float x, float y);
}
