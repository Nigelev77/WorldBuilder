package gui;


public interface GUIItem{
	
	public static final float cornerRadius = 20f;
	
	public void setPosition(float x, float y);
	void render(long ccx);
}
