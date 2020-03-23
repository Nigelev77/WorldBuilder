package gui;


public interface GUI{
	
	public static final float cornerRadius = 20f;
	
	public void setPosition(float x, float y);
	void render(long ccx);
	void setColor(int r, int g, int b, int a);
}
